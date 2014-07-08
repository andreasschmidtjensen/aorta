/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.impl;

import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.reasoning.ActionRule;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import aorta.ts.TransitionNotPossibleException;
import aorta.ts.TransitionRule;
import aorta.ts.rules.LogicalConsequence;
import aorta.ts.rules.OptionRemovalFunction;
import aorta.ts.rules.TransitionFunction;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asj
 */
public class Act extends Transition {
	
	private static final Logger logger = Logger.getLogger(Act.class.getName());

	@Override
	public AgentState execute(QueryEngine engine, AgentState state) {
		logger.fine("Executing transition: (Act)");

		AgentState newState = null;
		for (ActionRule rule : state.getActionRules()) {
			logger.fine("AR: " + rule);
			Queue<TransitionRule> queue = getRules(state, rule);

			try {
				Tracer.queue(state.getAgent().getName(), "(Act)");
				
				newState = executeRules(engine, queue, state);
				
				Tracer.queue(state.getAgent().getName(), "\n");
				Tracer.trace(state.getAgent().getName());
				break;
			} catch (TransitionNotPossibleException ex) {
				Tracer.clearQueue(state.getAgent().getName());
				logger.log(Level.FINE, "Transition was not possible (" + ex.getMessage() + ")");
			}
		}

		return newState;
	}

	protected Queue<TransitionRule> getRules(AgentState state, ActionRule rule) {
		Queue<TransitionRule> queue = new LinkedList<>();
		queue.add(new LogicalConsequence(rule.getRule()));
		queue.add(new TransitionFunction<>(rule.getAction()));
		queue.add(new OptionRemovalFunction());
//		queue.add(new NormCompliance());
		return queue;
	}

}
