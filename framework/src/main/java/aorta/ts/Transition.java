/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts;

import aorta.AgentState;
import aorta.kr.QueryEngine;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asj
 */
public abstract class Transition {
 
	private static final Logger logger = Logger.getLogger(Transition.class.getName());
	
	public abstract AgentState execute(QueryEngine engine, AgentState state);
    
    protected final AgentState executeRules(QueryEngine engine, Queue<TransitionRule> rules, AgentState state) throws TransitionNotPossibleException {
		AgentState newState = state.clone();
		for (TransitionRule rule : rules) {
			logger.log(Level.FINE, "Executing TR: " + rule.getClass().getSimpleName());
			newState = rule.execute(engine, newState);
			
			if (newState == null) {
				throw new TransitionNotPossibleException();
			}
		}
		
		return newState;
	}
    
}