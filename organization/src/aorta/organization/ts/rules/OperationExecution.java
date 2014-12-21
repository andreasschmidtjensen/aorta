/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta.organization.ts.rules;

import aorta.kr.QueryEngine;
import aorta.organization.ArtifactState;
import aorta.organization.action.Operation;
import aorta.tracer.Tracer;
import aorta.ts.Transition;

/**
 *
 * @author Andreas
 */
public class OperationExecution extends Transition<ArtifactState> {

	@Override
	protected ArtifactState execute(QueryEngine engine, ArtifactState state) {
		if (state.getQueue().peek() != null) {
			Operation op = state.getQueue().poll();
			boolean result = op.execute(state);
			Tracer.trace(state.getIdentifier(), getName(), op + (result ? "" : " [FAILED]"));
			if (!result) {
				state.getArtifact().operationFailed(op);
			}
		}
		
		return state;
	}

	@Override
	public String getName() {
		return "Op-Exec";
	}
	
}
