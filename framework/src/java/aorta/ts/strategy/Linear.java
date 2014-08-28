/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.strategy;

import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import aorta.ts.rules.Check;
import aorta.ts.rules.Ext;
import aorta.logging.Logger;
import aorta.ts.rules.ActionExecution;
import aorta.ts.rules.DeactRule;
import aorta.ts.rules.Delegate;
import aorta.ts.rules.Inform;
import aorta.ts.rules.ObligationActivated;
import aorta.ts.rules.ObligationSatisfied;
import aorta.ts.rules.ObligationViolated;
import aorta.ts.rules.EnactRule;
import aorta.ts.rules.ObjectiveRule;

/**
 *
 * @author asj
 */
public class Linear implements Strategy {

	private static final Logger logger = Logger.getLogger(Linear.class.getName());
	
	private QueryEngine engine = new QueryEngine();

	@Override
	public AgentState execute(AgentState s0) throws StrategyFailedException {
		AgentState next = s0;
		
		next = executeStar(new Check(), next); 
		next = executeOnce(new Ext(), next);
		next = executeStar(new ObligationActivated(), next);
		next = executeStar(new ObligationSatisfied(), next);
		next = executeStar(new ObligationViolated(), next);
		next = executeStar(new EnactRule(), next);
		next = executeStar(new DeactRule(), next);
		next = executeStar(new ObjectiveRule(), next);
		next = executeStar(new Delegate(), next);
		next = executeStar(new Inform(), next);
		next = executeOnce(new ActionExecution(), next);

		if (next != s0) {
			Tracer.trace(s0.getAgent().getName(), "--------------------\n");
		}
		return next;
	}
	
	private AgentState executeOnce(Transition transition, AgentState currentState) {
		AgentState newState = transition.executeTransition(engine, currentState);
		if (newState != null) {
			newState.prepareForTransition();
			return newState;
		} else {
			return currentState;
		}
	}
	
	private AgentState executeStar(Transition transition, AgentState currentState) {
		AgentState newState = null;
		while (currentState != newState) {
			AgentState nextState = executeOnce(transition, currentState);
			newState = currentState;
			currentState = nextState;
		}
		return newState;
	}
	
}
