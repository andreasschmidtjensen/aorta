/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.strategy;

import aorta.AgentState;

/**
 *
 * @author asj
 */
public interface Strategy {
	
	public AgentState execute(AgentState state) throws StrategyFailedException;
	
}
