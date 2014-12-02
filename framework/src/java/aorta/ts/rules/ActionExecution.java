/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AORTAException;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.util.FormulaQualifier;
import aorta.logging.Logger;
import aorta.reasoning.ActionRule;
import aorta.reasoning.IfRule;
import aorta.reasoning.ReasoningRule;
import aorta.reasoning.fml.ConjunctFormula;
import aorta.reasoning.fml.Formula;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import aorta.ts.TransitionNotPossibleException;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author asj
 */
public class ActionExecution extends Transition<AgentState> {

	private static final Logger logger = Logger.getLogger(ActionExecution.class.getName());

	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = executeRules(engine, state, state.getRules(), null);
		if (newState == null) {
			return state;
		} else {
			return newState;
		}
	}

	private AgentState executeRules(QueryEngine engine, AgentState state, List<ReasoningRule> rules, Formula condition) {
		for (ReasoningRule rule : rules) {
			if (rule instanceof IfRule) {
				IfRule ir = (IfRule) rule;
				Formula conjunctCondition = mergeConditions(condition, ir.getCondition());
				AgentState newState = executeRules(engine, state, ir.getRules(), conjunctCondition);
				if (newState != null) {
					return newState;
				}
			} else if (rule instanceof ActionRule) {
				ActionRule ar = (ActionRule) rule;
				AgentState newState = actionExecuted(engine, ar, state, condition);

				if (newState != null) {
					return newState;
				}
			}
		}

		return null;
	}

	private Formula mergeConditions(Formula cond1, Formula cond2) {
		if (cond1 != null && cond2 != null) {
			return new ConjunctFormula(cond1, cond2);
		} else if (cond1 != null) {
			return cond1;
		} else {
			return cond2;
		}
	}

	private AgentState actionExecuted(QueryEngine engine, ActionRule ar, AgentState state, Formula ifConditions) {
		AgentState newState = state;
		try {
			Term option = Term.createTerm(ar.getOption().toString());
			Term qualifiedOption = FormulaQualifier.qualifyTerm(option, KBType.OPTION);

			MentalState ms = state.getMentalState();
			
			List<SolveInfo> solutions = engine.findAll(ms, qualifiedOption);
			for (SolveInfo optionSolution : solutions) {
				if (optionSolution.isSuccess()) {
					newState.clearBindings();
					Formula context = ar.getContext();
					Formula conjunctContext = mergeConditions(ifConditions, context);
					
					System.out.println(context + " becomes " + conjunctContext);
					
					Term qualified = FormulaQualifier.qualifyGoal(ms, conjunctContext);
					
					engine.unify(ms, qualified, optionSolution);
					SolveInfo contextSolution = engine.solve(ms, qualified);
					
					if (contextSolution.isSuccess()) {
						List<Var> bindings = AgentState.mergeBindings(optionSolution, contextSolution);
						engine.unify(ms, option, bindings);
						
						List<Var> prevBindings = newState.getBindings();
						try {
							Tracer.queue(state.getAgent().getName(), "(" + getName() + ") ");
							Tracer.queue(state.getAgent().getName(), option + " : " + qualified + " => ");

							newState.addBindings(bindings);
							newState = ar.getAction().execute(engine, option, newState);
							
							Tracer.queue(state.getAgent().getName(), "\n");
							Tracer.trace(state.getAgent().getName());

							return newState;
						} catch (TransitionNotPossibleException ex) {
							newState.setBindings(prevBindings);

							Tracer.clearQueue(state.getAgent().getName());
							logger.log(Level.FINE, "Transition was not possible (" + ex.getMessage() + ")");
						}
					}

				}
			}
		} catch (AORTAException ex) {
			Tracer.clearQueue(state.getAgent().getName());
			logger.log(Level.FINE, "Transition was not possible (" + ex.getMessage() + ")");
		}

		return null;
	}

	@Override
	public String getName() {
		return "Act-Exec";
	}

}
