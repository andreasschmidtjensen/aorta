/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import aorta.AORTAException;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.util.FormulaQualifier;
import aorta.logging.Logger;
import aorta.reasoning.ActionRule;
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
public class ActionExecution extends Transition {

	private static final Logger logger = Logger.getLogger(ActionExecution.class.getName());

	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;

		for (ActionRule ar : state.getActionRules()) {
			try {
				Term option = Term.createTerm(ar.getOption().toString());
				Term qualifiedOption = FormulaQualifier.qualifyTerm(option, KBType.OPTION);
				
				MentalState ms = state.getMentalState();

				List<SolveInfo> solutions = engine.findAll(ms, qualifiedOption);
				for (SolveInfo info : solutions) {
					if (info.isSuccess()) {
						Formula context = ar.getContext();
						Term qualified = FormulaQualifier.qualifyGoal(ms, context);
						
						engine.unify(ms, qualified, info);
						SolveInfo contextSolution = engine.solve(ms, qualified);

						if (contextSolution.isSuccess()) {
							newState.clearBindings();
							newState.addBindings(info);
							newState.addBindings(contextSolution);

							engine.unify(ms, option, newState.getBindings());	
							try {
								Tracer.queue(state.getAgent().getName(), "(" + getName() + ") ");
								Tracer.queue(state.getAgent().getName(), option + " : " + qualified + " => ");
								
								newState = ar.getAction().execute(engine, option, newState);
								
								Tracer.queue(state.getAgent().getName(), "\n");
								Tracer.trace(state.getAgent().getName());
								
								break;
							} catch (TransitionNotPossibleException ex) {
								Tracer.clearQueue(state.getAgent().getName());
								logger.log(Level.FINEST, "Transition was not possible (" + ex.getMessage() + ")");								
							}
						}

					}
				}
			} catch (AORTAException ex) {
				Tracer.clearQueue(state.getAgent().getName());
				logger.log(Level.FINE, "Transition was not possible (" + ex.getMessage() + ")");

				newState = state;
			}

		}

		return newState;
	}

	@Override
	public String getName() {
		return "Act-Exec";
	}
}
