/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.reasoning.coordination;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AgentState;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import aorta.logging.Logger;

/**
 *
 * @author asj
 */
public class MentalStateChange {
	
	private static final Logger logger = Logger.getLogger(MentalStateChange.class.getName());
	
	private List<ChangeTerm> changes;

	public MentalStateChange() {
		changes = new ArrayList<>();
	}

	public void inserted(Term t) {
		changes.add(new ChangeTerm(true, t));
	}
	
	public void removed(Term t) {
		changes.add(new ChangeTerm(false, t));
	}
	
	public void clear() {
		changes.clear();
	}
	
	public List<ChangeTerm> getChanges() {
		return changes;
	}

	public List<Var> contains(QueryEngine engine, MentalState ms, List<ChangeTerm> changeCondition) {
		List<Var> currentBindings = new ArrayList<>();
		for (ChangeTerm condition : changeCondition) {
			boolean matched = false;
			final Term conditionTerm = Term.createTerm(condition.getTerm().toString());
			engine.unify(ms, conditionTerm, currentBindings);
			
			for (ChangeTerm ct : changes) {
				if (condition.isAddition() == ct.isAddition()) {
					Term changeTerm = Term.createTerm(ct.getTerm().toString());
					boolean unifies = ms.getProlog().unify(conditionTerm, changeTerm);
					if (unifies) {
						matched = true;
						List<Var> boundVars = engine.getVars(conditionTerm);
						combineBindings(currentBindings, boundVars);
						break;
					}
				}
			}
			
			if (!matched) {
				return null;
			}
		}
		
		return currentBindings;
	}
		
	private void combineBindings(List<Var> prevBindings, List<Var> newBindings) {
		for (Var var : newBindings) {
			Iterator<Var> it  = prevBindings.iterator();
			while (it.hasNext()) {
				Var currVar = it.next();
				if (var.getOriginalName().equals(currVar.getOriginalName())) {
					it.remove();
				}
			}
		}
		prevBindings.addAll(newBindings);
	}

	@Override
	public String toString() {
		return changes.toString();
	}	

	@Override
	public MentalStateChange clone() {
		MentalStateChange clone = new MentalStateChange();
		clone.changes.addAll(changes);
		return clone;
	}
	
}
