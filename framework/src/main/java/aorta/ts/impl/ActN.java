/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.impl;

import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.reasoning.ActionRule;
import aorta.ts.TransitionRule;
import aorta.ts.rules.LogicalConsequence;
import aorta.ts.rules.OptionRemovalFunction;
import aorta.ts.rules.TransitionFunction;
import java.util.LinkedList;
import java.util.Queue;
import aorta.logging.Logger;

/**
 *
 * @author asj
 */
public class ActN extends Act {

	private static final Logger logger = Logger.getLogger(ActN.class.getName());

	@Override
	public AgentState execute(QueryEngine engine, AgentState state) {
		logger.fine("Executing transition: (Act-N)");
		return super.execute(engine, state);
	}

	@Override
	protected Queue<TransitionRule> getRules(AgentState state, ActionRule rule) {
		Queue<TransitionRule> queue = new LinkedList<>();
		queue.add(new LogicalConsequence(rule.getRule()));
		queue.add(new TransitionFunction<>(rule.getAction()));
		queue.add(new OptionRemovalFunction());
		return queue;
	}
}
