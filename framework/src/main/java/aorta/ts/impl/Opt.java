/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.impl;

import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.reasoning.OptionRule;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import aorta.ts.TransitionNotPossibleException;
import aorta.ts.TransitionRule;
import aorta.ts.rules.LogicalConsequence;
import aorta.ts.rules.TransitionFunction;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import aorta.logging.Logger;

/**
 *
 * @author asj
 */
public class Opt extends Transition {
	
	private static final Logger logger = Logger.getLogger(Opt.class.getName());
	
	@Override
	public AgentState execute(QueryEngine engine, AgentState state) {
		logger.fine("Executing transition: (Opt)");

		AgentState newState = null;
		for (OptionRule rule : state.getOptionRules()) {
			logger.log(Level.FINE, "OR: " + rule);
			Queue<TransitionRule> queue = new LinkedList<>();
			queue.add(new LogicalConsequence(rule.getRule()));
			queue.add(new TransitionFunction<>(rule.getAction()));

			try {
				Tracer.queue(state.getAgent().getName(), "(Opt)");
				
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
	
}
