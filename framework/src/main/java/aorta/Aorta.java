package aorta;

import alice.tuprolog.Prolog;
import aorta.kr.language.OrganizationType;
import aorta.kr.language.OrganizationalLanguage;
import java.util.ArrayList;
import java.util.List;

public class Aorta {

	public static final String ORG_MESSAGE_FUNCTOR = "om";
	
	private List<AortaAgent> agents;
	private OrganizationType organizationType;
    private OrganizationalLanguage organizationalSpecification;
	private String organizationLocation;
	
	public Aorta(OrganizationType organizationType, String organizationLocation) throws AORTAException {
		if (organizationType == null) {
			throw new AORTAException("No organization specified (infrastructure parameter missing: organization(\"path\", type))!");
		}
		agents = new ArrayList<>();
        
        organizationalSpecification = organizationType.getLanguage();
		this.organizationType = organizationType;
		this.organizationLocation = organizationLocation;
        
        Prolog prolog = new Prolog();
        if (!organizationalSpecification.isValid(prolog)) {
            throw new AORTAException("Organizational language is not valid!");
        }
	}

    public OrganizationalLanguage getOrganizationalSpecification() {
        return organizationalSpecification;
    }

	public String getOrganizationLocation() {
		return organizationLocation;
	}

	public OrganizationType getOrganizationType() {
		return organizationType;
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
