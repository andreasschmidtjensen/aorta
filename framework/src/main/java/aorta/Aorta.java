package aorta;

import java.util.ArrayList;
import java.util.List;

public class Aorta {

	public static final String ORG_MESSAGE_FUNCTOR = "om";
	
	private List<AortaAgent> agents;
	private String organizationLocation;
	
	public Aorta(String organizationLocation) throws AORTAException {
		agents = new ArrayList<>();
        
		this.organizationLocation = organizationLocation;
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
        newAgent.addAgentToBeliefs(newAgent);
        for (AortaAgent agent : agents) {
			if (agent.getName().equals(newAgent.getName())) {
				throw new IllegalStateException("Agent " + newAgent.getName() + " already exists in AORTA!");
			}
            agent.addAgentToBeliefs(newAgent);
            newAgent.addAgentToBeliefs(agent);
        }
        
        newAgent.setAorta(this);
		agents.add(newAgent);
	}
    
    public void removeAgent(AortaAgent removedAgent) {
        if (agents.remove(removedAgent)) {
            for (AortaAgent agent : agents) {
                agent.removeAgentFromBeliefs(removedAgent);
            }
        }        
    }
    
}
