/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
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
import aorta.reasoning.fml.Formula;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import aorta.ts.TransitionNotPossibleException;
import java.util.ArrayList;
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
		AgentState newState = executeRules(engine, state, state.getRules(), new ArrayList<Var>());
		if (newState == null) {
			return state;
		} else {
			return newState;
		}
	}

	private AgentState executeRules(QueryEngine engine, AgentState state, List<ReasoningRule> rules, List<Var> vars) {
		for (ReasoningRule rule : rules) {
			if (rule instanceof IfRule) {
				IfRule ir = (IfRule) rule;
				List<Var> irVars = checkCondition(engine, state, ir.getCondition(), vars);
				if (irVars != null) { // returns null if unsuccessful
					irVars.addAll(vars);
					AgentState newState = executeRules(engine, state, ir.getRules(), irVars);
					if (newState != null) {
						return newState;
					}
				}
			} else if (rule instanceof ActionRule) {
				// TODO: use vars
				ActionRule ar = (ActionRule) rule;
				AgentState newState = actionExecuted(engine, ar, state, vars);

				if (newState != null) {
					return newState;
				}
			}
		}

		return null;
	}

	private List<Var> checkCondition(QueryEngine engine, AgentState state, Formula condition, List<Var> vars) {
		MentalState ms = state.getMentalState();
		Term qualified = FormulaQualifier.qualifyGoal(ms, condition);
		
		SolveInfo info = engine.solve(ms, qualified);
		
		if (info.isSuccess()) {
			try {
				return info.getBindingVars();
			} catch (NoSolutionException ex) {
				// will not happen since we check isSuccess() first.
				return null;
			}
		} else {
			return null;
		}
	}

	private AgentState actionExecuted(QueryEngine engine, ActionRule ar, AgentState state, List<Var> vars) {
		AgentState newState = state;
		try {
			Term option = Term.createTerm(ar.getOption().toString());
			Term qualifiedOption = FormulaQualifier.qualifyTerm(option, KBType.OPTION);

			MentalState ms = state.getMentalState();
			
			engine.unify(ms, qualifiedOption, vars);

			List<SolveInfo> solutions = engine.findAll(ms, qualifiedOption);
			for (SolveInfo optionSolution : solutions) {
				if (optionSolution.isSuccess()) {
					newState.clearBindings();
					Formula context = ar.getContext();
					Term qualified = FormulaQualifier.qualifyGoal(ms, context);

					engine.unify(ms, qualified, optionSolution);
					engine.unify(ms, qualified, vars);
					SolveInfo contextSolution = engine.solve(ms, qualified);

					if (contextSolution.isSuccess()) {
						List<Var> bindings = AgentState.mergeBindings(optionSolution, contextSolution);
						engine.unify(ms, option, bindings);
						engine.unify(ms, option, vars);
						
						List<Var> prevBindings = newState.getBindings();
						try {
							Tracer.queue(state.getAgent().getName(), "(" + getName() + ") ");
							Tracer.queue(state.getAgent().getName(), option + " : " + qualified + " => ");

							newState.addBindings(bindings);
							newState.addBindings(vars);
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
