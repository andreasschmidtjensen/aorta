/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta.organization;

import alice.tuprolog.InvalidLibraryException;
import alice.tuprolog.InvalidTheoryException;
import aorta.State;
import aorta.kr.MentalState;
import aorta.kr.language.model.Metamodel;
import aorta.organization.action.Operation;
import cartago.AgentId;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Andreas
 */
public class ArtifactState extends State {
	
	private AortaArtifact artifact;
	private EnvironmentSensor environment;
		
	private Map<String, AgentId> agents;
	private Queue<Operation> queue;
	
	public ArtifactState(MentalState mentalState, Metamodel metamodel, AortaArtifact artifact, EnvironmentSensor environment) throws InvalidTheoryException, IOException, InvalidLibraryException {
		super(mentalState, metamodel);
		
		this.artifact = artifact;
		this.environment = environment;
		
		queue = new LinkedList<>();
		agents = new HashMap<>();
	}

	public void addAgent(String agentName, AgentId id) {
		if (!agents.containsKey(agentName)) {
			agents.put(agentName, id);
		}
	}

	public AgentId getAgent(String agentName) {
		return agents.get(agentName);
	}

	public Queue<Operation> getQueue() {
		return queue;
	}

	public EnvironmentSensor getEnvironment() {
		return environment;
	}
		
	public AortaArtifact getArtifact() {
		return artifact;
	}

	@Override
	public String getIdentifier() {
		return "ARTIFACT";
	}
	
	@Override
	public String getDescription() {
		return ""+getArtifact().getCycle();
	}
}
