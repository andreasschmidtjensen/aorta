/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts;

import aorta.State;
import aorta.kr.QueryEngine;
import aorta.logging.Logger;

/**
 *
 * @author asj
 * @param <T>
 */
public abstract class Transition<T extends State> {
 
	private static final Logger logger = Logger.getLogger(Transition.class.getName());
	
	protected abstract T execute(QueryEngine engine, T state);
	
	public T executeTransition(QueryEngine engine, T state) {
		logger.finest("[" + state.getDescription() + "] Executing transition: " + getName());
		state.prepareForTransition(); // clears bindings
		return execute(engine, state);
	}
    
	public abstract String getName();
	    
}