/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import aorta.AORTAException;
import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.reasoning.action.Action;
import aorta.ts.TransitionNotPossibleException;
import aorta.ts.TransitionRule;

/**
 *
 * @author asj
 */
public class TransitionFunction<T extends Action> implements TransitionRule {
	
	private T action;

	public TransitionFunction(T action) {
		this.action = action;
	}

	@Override
	public AgentState execute(QueryEngine engine, AgentState state) throws TransitionNotPossibleException {
		try {
			return action.execute(engine, state);
		} catch (AORTAException ex) {
			throw new TransitionNotPossibleException(ex);
		}
	}

}
