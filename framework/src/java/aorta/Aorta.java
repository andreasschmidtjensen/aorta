package aorta;

import aorta.kr.language.OrganizationImportException;
import aorta.kr.language.model.Metamodel;
import aorta.organization.EnvironmentSensor;
import aorta.organization.InitializingAgent;
import cartago.ArtifactId;
import cartago.CartagoException;
import cartago.CartagoService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aorta {

	public static final String ORG_MESSAGE_FUNCTOR = "om";
	
	private List<AortaAgent> agents;
	private final String organizationLocation;
	
	private static ArtifactId artifact;
	
	public Aorta(String organizationLocation) throws AORTAException {
		agents = new ArrayList<>();
		this.organizationLocation = organizationLocation;
	}

	public boolean artifactInitialized() {
		return artifact != null;
	}
	
	public void setupArtifact(EnvironmentSensor sensor) throws AORTAException {
		if (sensor != null) {
			try {
				if (artifact == null) {
					CartagoService.startNode();
					CartagoService.installInfrastructureLayer("default");
					Metamodel mm = Metamodel.load(organizationLocation);
					artifact = new InitializingAgent("aorta_initializer").makeArtifact(mm, sensor);
				}
			} catch (IOException | OrganizationImportException | CartagoException ex) {
				throw new AORTAException("Could not initialize CArtAgO artifact", ex);
			}
		}
	}

	public String getOrganizationLocation() {
		return organizationLocation;
	}

	public AortaAgent getAgent(String name) {
		for (AortaAgent agent : agents) {
			if (agent.getName().equals(name)) {
				return agent;
			}
		}
		return null;
	}
	
	public void addAgent(AortaAgent newAgent) {		
        newAgent.addAgentToBeliefs(newAgent.getName());
        for (AortaAgent agent : agents) {
			if (agent.getName().equals(newAgent.getName())) {
				throw new IllegalStateException("Agent " + newAgent.getName() + " already exists in AORTA!");
			}
            agent.addAgentToBeliefs(newAgent.getName());
            newAgent.addAgentToBeliefs(agent.getName());
        }
        
        newAgent.setAorta(this);
		agents.add(newAgent);

		if (artifact != null) {
			newAgent.setArtifact(artifact);
		}
	}
	
	public void addIgnorantAgent(String agentName) {
        for (AortaAgent agent : agents) {
			if (agent.getName().equals(agentName)) {
				throw new IllegalStateException("Agent " + agentName + " already exists in AORTA!");
			}
            agent.addAgentToBeliefs(agentName);
        }
	}
    
    public void removeAgent(AortaAgent removedAgent) {
        if (agents.remove(removedAgent)) {
            for (AortaAgent agent : agents) {
                agent.removeAgentFromBeliefs(removedAgent.getName());
            }
        }        
    }
    
	public void removeIgnorantAgent(String agentName) {
		for (AortaAgent agent : agents) {
			agent.removeAgentFromBeliefs(agentName);
		}
    }
}
