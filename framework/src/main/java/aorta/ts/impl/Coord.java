/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.impl;

import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.reasoning.coordination.CoordinationRule;
import aorta.ts.Transition;

/**
 *
 * @author ascje
 */
public class Coord extends Transition {

	@Override
	public AgentState execute(QueryEngine engine, AgentState state) {
		AgentState result = state;
		for (CoordinationRule rule : state.getCoordinationRules()) {
			result = rule.execute(engine, result, state.getMentalStateChange());
		}
		result.getMentalStateChange().clear();
		
		return result;
	}
	
}
