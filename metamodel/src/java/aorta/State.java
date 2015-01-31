/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Var;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.model.Metamodel;
import aorta.kr.util.FormulaQualifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreas
 */
public abstract class State {
	
	private static final Logger logger = Logger.getLogger(State.class.getName());
	
	private MentalState mentalState;	
	private List<Var> bindings;
	private Metamodel metamodel;
	
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

	public void insertTerm(QueryEngine engine, Struct term, KBType type) {
		final Struct qualified = FormulaQualifier.qualifyStruct(term, type);
		insertInMentalState(engine, qualified);

		logger.log(Level.FINEST, "Updating mental state; inserting " + qualified);
	}

	public void insertTerm(QueryEngine engine, Struct qualifiedTerm) {
		try {
			KBType type = FormulaQualifier.getQualifier(qualifiedTerm);
			Struct term = (Struct) FormulaQualifier.getQualified(qualifiedTerm);

			insertTerm(engine, term, type);
		} catch (NullPointerException ex) {
			logger.log(Level.SEVERE, qualifiedTerm + " threw NPE for insertTerm(" + qualifiedTerm + ")", ex);
		}
	}

	public void removeTerm(QueryEngine engine, Struct term, KBType type) {
		final Struct qualified = FormulaQualifier.qualifyStruct(term, type);
		removeFromMentalState(engine, qualified);

		logger.log(Level.FINEST, "Updating mental state; removing " +qualified);
	}

	public void removeTerm(QueryEngine engine, Struct qualifiedTerm) {
		KBType type = FormulaQualifier.getQualifier(qualifiedTerm);
		Struct term = (Struct) FormulaQualifier.getQualified(qualifiedTerm);

		removeTerm(engine, term, type);
	}

	public void insertInMentalState(QueryEngine engine, Struct contents) {
		engine.insert(mentalState, contents);			
		changed = true;
	}

	public void removeFromMentalState(QueryEngine engine, final Struct qualified) {
		engine.remove(mentalState, qualified);			
		changed = true;
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
