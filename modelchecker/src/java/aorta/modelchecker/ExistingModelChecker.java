/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.modelchecker;

import ajpf.product.MCAPLmodel;
import ajpf.product.Product;
import ajpf.product.Product.ProductState;
import ajpf.psl.MCAPLProperty;
import ajpf.psl.Proposition;
import ajpf.psl.ast.Property_AST;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Andreas
 */
public class ExistingModelChecker {

	private Product product;
	private boolean initialized;

	private MCAPLmodel model;
	private String property;

	public ExistingModelChecker(MCAPLmodel model, String property) {
		this.model = model;
		this.property = property;
	}
	
	public void start() {
		init();
		handleState(0);
		product.done(0);
		try {
			check(-1);
		} catch (BacktrackException ex) {
		}
	}
	
	public List<ProductState> getAcceptingPath() {
		return product.getAcceptingPath();
	}
	// TODO: make iterative
	private void handleState(int state) {
		try {
			if (check(state)) {
				Set<Integer> neighbors = model.getNeighbors(state);
				if (neighbors != null) {
					for (int neighbor : neighbors) {
						handleState(neighbor);
					}
				}
				backtrack(state);
			}
		} catch (BacktrackException ex) {
			backtrack(state);
		}
	}
	
	public void init() {
		Property_AST prop_ast = new Property_AST();
		prop_ast.parse(property);
		MCAPLProperty prop = prop_ast.toMCAPLNative();
		Set<Proposition> props = prop.getProps();
		MCAPLProperty negprop = prop.negate();
		
		product = new Product(negprop, props, Product.AutomataType.DEFAULT_AUTOMATA, false);
		product.setModel(model);
		initialized = true;
	}

	public boolean check(int stateId) throws BacktrackException {

		if (stateId == -1) {
			// If we've backtracked right past the beginning and, no, I'm not really clear what's going on
			// internally in AJPF here.  Anyway we're done and we need to fully run the DFS and check for accepting paths.
			product.done(0);
			return product.DFS().isEmpty();
		} else {
			if (stateId > 0) {
				// This node in the model is fully explored, note as such in the program model
				product.done(stateId);
			}

			// Check state returns true if an acccepting path hasn't been found in the product
			return checkstate(stateId);
		}

	}

	public boolean checkstate(int stateId) throws BacktrackException {
		// Add this new state to the program model inside the product automaton.
		if (initialized) {
			// If this is an end state in the program model check for accepting paths.  
			// Don't generate a new product state because of trivial truths but do add the state to the program model
			// so pruning will work properly when backtracking occurs.
			if (model.getNeighbors(stateId) == null) {
				// Adding new state for pruning
				boolean returnvalue = product.currentPathEnded();
				product.justAddModelState(stateId);
				return (!returnvalue);
			}

			// Otherwise we add a new model state to the product and check if we're in a state
			// were there is state in the property that can be paired with this state in the model
			// so all paths from this model node are trivially true.
			if (!product.addModelState(stateId)) {
				throw new BacktrackException();
			}

			// If we've looped it wasn't an end state but we want to look for accepting runs anyway.
			// If a loop was detected when we added the new state then these will have been generated.
			return (!product.hasAcceptingPath());
		}
		return true;
	}

	public void backtrack(int stateId) {
		product.done(stateId);

		// Since we're maintaining the "current path" in the model and the product automata this need to be pruned accordingly.
		if (product != null) {
			product.pruneCurrentPath(stateId);
		}

		// Check in case we've pruned back beyond the creation of the automata
		if (stateId < 0) {
			initialized = false;
		}
	}

	private static class BacktrackException extends Exception {

		public BacktrackException() {
		}
	}
}
