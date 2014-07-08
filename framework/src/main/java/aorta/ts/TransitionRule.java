/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts;

import aorta.AgentState;
import aorta.kr.QueryEngine;

/**
 *
 * @author asj
 */
public interface TransitionRule {
	
	public AgentState execute(QueryEngine engine, AgentState state) throws TransitionNotPossibleException;
	
}
