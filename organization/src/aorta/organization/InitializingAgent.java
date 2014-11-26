/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.organization;

import aorta.kr.language.model.Metamodel;
import cartago.ArtifactId;
import cartago.CartagoException;
import cartago.util.agent.Agent;

/**
 *
 * @author asj
 */
public class InitializingAgent extends Agent {
	
	public InitializingAgent(String agentName) {
		super(agentName);
	}
	
	public ArtifactId makeArtifact(Metamodel mm, EnvironmentSensor env) throws CartagoException {
		ArtifactId id = makeArtifact("aorta", "aorta.organization.AortaArtifact", new Object[]{ mm, env });
		return id;
	}
}
