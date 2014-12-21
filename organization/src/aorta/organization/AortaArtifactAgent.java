/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.organization;

import alice.tuprolog.Struct;
import cartago.ArtifactId;
import cartago.CartagoException;
import cartago.Op;
import cartago.util.agent.Agent;
import cartago.util.agent.Percept;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asj
 */
public abstract class AortaArtifactAgent extends Agent {

	public AortaArtifactAgent(String agentName, ArtifactId id) throws CartagoException {
		super(agentName);
		focus(id);
	}

	public void enact(String role) throws CartagoException {
		Op enact = new Op("enact", getAgentName(), role);
		doAction(enact);
	}

	public void deact(String role) throws CartagoException {
		Op enact = new Op("deact", getAgentName(), role);
		doAction(enact);
	}

	public List<Struct>[] perceive() throws Exception {
		Percept p = fetchPercept();
		
		if (p != null) {
			return handlePercept(p);
		}
		return new List[] { new ArrayList(), new ArrayList() };
	}
	
	public abstract List<Struct>[] handlePercept(Percept p);
}
