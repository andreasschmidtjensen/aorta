/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts;

import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.logging.Logger;

/**
 *
 * @author asj
 */
public abstract class Transition {
 
	private static final Logger logger = Logger.getLogger(Transition.class.getName());
	
	protected abstract AgentState execute(QueryEngine engine, AgentState state);
	
	public AgentState executeTransition(QueryEngine engine, AgentState state) {
		logger.finest("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Executing transition: " + getName());
		state.prepareForTransition(); // clears bindings
		return execute(engine, state);
	}
    
	public abstract String getName();
	    
}