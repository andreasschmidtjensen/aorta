/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta.organization.action;

import aorta.organization.ArtifactState;

/**
 *
 * @author Andreas
 */
public abstract class Operation {
	
	protected String agent;

	public Operation(String agent) {
		this.agent = agent;
	}

	public String getAgent() {
		return agent;
	}

	public abstract boolean execute(ArtifactState state);
	
}
