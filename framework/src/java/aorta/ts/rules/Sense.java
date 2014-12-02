/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta.ts.rules;

import alice.tuprolog.Struct;
import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.logging.Logger;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Andreas
 */
public class Sense extends Transition<AgentState> {

	private static final Logger logger = Logger.getLogger(Sense.class.getName());
	
	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		try {
			List<Struct>[] result = state.getAgent().getArtifactAgent().perceive();
			List<Struct> added = result[0];
			List<Struct> removed = result[1];
			
			if (added.size() > 0 || removed.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (Struct s : added) {
					sb.append("+").append(s).append("; ");
				}
				for (Struct s : removed) {
					sb.append("-").append(s).append("; ");
				}
				Tracer.trace(state.getIdentifier(), getName(), sb.toString());
			}	
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Could not perceive artifact", ex);
		}
		return state;
	}

	@Override
	public String getName() {
		return "Sense";
	}
	
}
