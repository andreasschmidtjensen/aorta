/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.util.Qualifier;
import aorta.reasoning.fml.BeliefFormula;
import aorta.reasoning.fml.GoalFormula;
import aorta.ts.TransitionNotPossibleException;
import aorta.ts.TransitionRule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author asj
 */
public class OptionRemovalFunction implements TransitionRule {

	@Override
	public AgentState execute(QueryEngine engine, AgentState state) throws TransitionNotPossibleException {
		AgentState result = state;
		
		MentalState ms = state.getMentalState();
		
		List<Term> toRemove = new ArrayList<>();
		Iterator<? extends Term> it = ms.getFacts().iterator(ms.getProlog());
		while (it.hasNext()) {
			Term fact = it.next();
			if (fact instanceof Struct) {
				Struct s = (Struct) fact;
				Term option;
				if ((option = Qualifier.getQualified(s)) != null && Qualifier.getQualifier(s) == KBType.OPTION) {
					boolean remove = false;
					Term asOrg = Qualifier.qualifyTerm(option, KBType.ORGANIZATION);
					if (engine.solve(ms, asOrg).isSuccess()) {
						// the option is part of the org. instance now -> remove
						remove = true;
					} else {
						Var objVar = new Var("Obj");
						Term obj = ms.getAgent().getAorta().getOrganizationalSpecification().getObjective(objVar);
						if (obj.unify(ms.getProlog(), option)) {
							Term objective = objVar.getTerm();
							if (engine.solve(ms, new BeliefFormula(objective.toString())).isSuccess()) {
								// is believed (i.e. achieved)
								remove = true;
							} else if (engine.solve(ms, new GoalFormula(objective.toString())).isSuccess()) {
								// is goal (i.e. committed)
								remove = true;
							}
						}
					}

					if (remove) {
						toRemove.add(s);
					}
				}
			}
		}
		if (!toRemove.isEmpty()) {
			result = state.clone();
			ms = result.getMentalState();
			for (Term s : toRemove) {
				engine.remove(ms, (Struct) s);
			}
		}
		
		return result;
	}
}
