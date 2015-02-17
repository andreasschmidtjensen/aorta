/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.language.model.Metamodel;
import aorta.kr.util.FormulaQualifier;
import aorta.tracer.StateListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import aorta.logging.Logger;
import gov.nasa.jpf.annotation.FilterField;

/**
 *
 * @author Andreas
 */
public abstract class State {
		
	private static final Logger logger = Logger.getLogger(State.class.getName());
	
	@FilterField
	private MentalState mentalState;	
	
	@FilterField
	private List<Var> bindings;
	
	@FilterField
	private Metamodel metamodel;
	
	@FilterField
	private final List<StateListener> stateListeners = new ArrayList<>();
	
	private boolean changed;

	public State(MentalState mentalState, Metamodel metamodel) {
		this.mentalState = mentalState;
		this.metamodel = metamodel;
		
		bindings = new ArrayList<>();
	}
	
	public abstract String getIdentifier();
	public abstract String getDescription();
	
	public void newCycle() {
	}

	public synchronized void addStateListener(StateListener sl) {
		stateListeners.add(sl);
	}
	public synchronized void removeStateListener(StateListener sl) {
		stateListeners.remove(sl);
	}
	
	public synchronized void notifyNewState() {
		for (StateListener sl : stateListeners) {
			sl.newState(this);
		}
	}
	
	public synchronized void notifyTermAdded(String ruleName, Term term) {
		if (!StateListener.IgnoredEvents.isIgnored(ruleName)) {
			for (StateListener sl : stateListeners) {
				sl.termAdded(ruleName, term);
			}
		}
	}
	
	public synchronized void notifyTermRemoved(String ruleName, Term term) {
		if (!StateListener.IgnoredEvents.isIgnored(ruleName)) {
			for (StateListener sl : stateListeners) {
				sl.termRemoved(ruleName, term);
			}
		}
	}
	
	public void prepareForTransition() {
		changed = false;
		bindings.clear();
	}
	
	public boolean hasChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	
	public void setMentalState(MentalState mentalState) {
		this.mentalState = mentalState;
	}

	public void insertTerm(Struct term, KBType type) {
		final Struct qualified = FormulaQualifier.qualifyStruct(term, type);
		insertInMentalState(qualified);

		logger.log(Level.FINEST, "Updating mental state; inserting " + qualified);
	}

	public void insertTerm(Struct qualifiedTerm) {
		try {
			KBType type = FormulaQualifier.getQualifier(qualifiedTerm);
			Struct term = (Struct) FormulaQualifier.getQualified(qualifiedTerm);

			insertTerm(term, type);
		} catch (NullPointerException ex) {
			logger.log(Level.SEVERE, qualifiedTerm + " threw NPE for insertTerm(" + qualifiedTerm + ")", ex);
		}
	}

	public void removeTerm(Struct term, KBType type) {
		final Struct qualified = FormulaQualifier.qualifyStruct(term, type);
		removeFromMentalState(qualified);

		logger.log(Level.FINEST, "Updating mental state; removing " +qualified);
	}

	public void removeTerm(Struct qualifiedTerm) {
		KBType type = FormulaQualifier.getQualifier(qualifiedTerm);
		Struct term = (Struct) FormulaQualifier.getQualified(qualifiedTerm);

		removeTerm(term, type);
	}

	public void insertInMentalState(Struct struct) {
		mentalState.insert(struct);			
		logger.fine("insertIntoMentalState(" + struct + ")");
		setChanged(true);
	}

	public void removeFromMentalState(final Struct struct) {
		mentalState.remove(struct);			
		logger.fine("removeFromMentalState(" + struct + ")");
		setChanged(true);
	}
	
	public List<Var> getBindings() {
		return bindings;
	}

	public void setBindings(List<Var> bindings) {
		this.bindings = bindings;
	}

	public void clearBindings() {
		bindings.clear();
	}
	
	public void addBindings(List<Var> bindings) {
		this.bindings = mergeBindings(this.bindings, bindings);
	}
	
	public void addBindings(SolveInfo info) {
		if (info.isSuccess()) {
			try {
				bindings = mergeBindings(bindings, info.getBindingVars());
			} catch (NoSolutionException ex) {
				// ignore because of isSuccess
			}
		}
	}

	public Metamodel getMetamodel() {
		return metamodel;
	}
	
	public MentalState getMentalState() {
		return mentalState;
	}

	public static List<Var> mergeBindings(List<Var> currentBindings, List<Var> newBindings) {
		List<Var> result = new ArrayList<>(currentBindings);
		for (Var var : newBindings) {
			boolean exists = false;
			for (Var currVar : result) {
				if (var.getOriginalName().equals(currVar.getOriginalName())) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				result.add(var);
			}
		}
		return result;
	}

	public static List<Var> mergeBindings(SolveInfo si1, SolveInfo si2) {
		if (si1.isSuccess() && si2.isSuccess()) {
			try {
				List<Var> result = new ArrayList<>();
				result.addAll(si1.getBindingVars());
				for (Var var : si2.getBindingVars()) {
					boolean exists = false;
					for (Var currVar : result) {
						if (var.getOriginalName().equals(currVar.getOriginalName())) {
							exists = true;
							break;
						}
					}
					if (!exists) {
						result.add(var);
					}
				}
				return result;
			} catch (NoSolutionException ex) {
				// ignore
			}
		}
		return null;
	}

}
