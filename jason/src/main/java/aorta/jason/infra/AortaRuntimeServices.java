/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason.infra;

import aorta.AORTAException;
import aorta.Aorta;
import aorta.AortaAgent;
import aorta.gui.AortaGui;
import aorta.jason.AortaAgentArch;
import aorta.jason.AortaJasonBridge;
import aorta.kr.language.OrganizationImportException;
import aorta.kr.language.OrganizationType;
import aorta.parser.helper.AortaBuilder;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.Literal;
import jason.asSyntax.StringTerm;
import jason.asSyntax.parser.ParseException;
import jason.infra.centralised.CentralisedAgArch;
import jason.infra.centralised.CentralisedRuntimeServices;
import jason.infra.centralised.RunCentralisedMAS;
import jason.mas2j.ClassParameters;
import jason.runtime.Settings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ascje
 */
public class AortaRuntimeServices extends CentralisedRuntimeServices {

	private static final Logger logger = Logger.getLogger(AortaRuntimeServices.class.getName());
	
	private Aorta aorta;
	private AortaGui gui;
	
	private List<AortaAgentArch> agents = new ArrayList<AortaAgentArch>();
	
	public AortaRuntimeServices(RunCentralisedMAS masRunner) {
		this(masRunner, true);
	}
	
	public AortaRuntimeServices(RunCentralisedMAS masRunner, boolean useGui) {
		super(masRunner);
				
		if (useGui) {
			gui = new AortaGui();
		}
		
		OrganizationType orgType;
		String location;
		
		try {
			String org = masRunner.getProject().getInfrastructure().getParameter("organization");
			Literal orgLiteral = ASSyntax.parseLiteral(org);
			location = ((StringTerm) orgLiteral.getTerm(0)).getString();
			String type;
			if (orgLiteral.getTerm(1) instanceof StringTerm) {
				type = ((StringTerm) orgLiteral.getTerm(1)).getString();
			} else {
				type = orgLiteral.getTerm(1).toString();
			}
			
			orgType = OrganizationType.get(type);
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
		
		try {
			aorta = new Aorta(orgType, location);
		} catch (AORTAException ex) {
			logger.log(Level.SEVERE, "Could not launch AORTA; exiting.", ex);
			throw new RuntimeException(ex);
		}
	}

	public String createAgent(String agName, String numberedAgName, String agSource, String agClass, List<String> archClasses, ClassParameters bbPars, Settings stts) throws Exception {
		String actualAgName = createAgent(numberedAgName, agSource, agClass, archClasses, bbPars, stts);
		AortaAgentArch agentArch = (AortaAgentArch) masRunner.getAg(actualAgName);

		try {
			String aortaLocation = stts.getUserParameter("aorta");
			if (aortaLocation == null) {
				aortaLocation = agName + ".aorta";
			}
			
			aortaLocation = aortaLocation.replaceAll("\\\\", "/");
				
			AortaBuilder builder = new AortaBuilder();			
			AortaAgent aortaAgent = builder.parseAgent(actualAgName, aortaLocation, aorta, new AortaJasonBridge(agentArch));
			
			if (aortaAgent != null) {
				aorta.addAgent(aortaAgent);

				agentArch.setAortaAgent(aortaAgent);
				agentArch.setAorta(aorta);

				if (gui != null) {
					gui.addAgent(aortaAgent);
				}
			}
			
		} catch (IOException | OrganizationImportException ex) {
			logger.log(Level.SEVERE, "Error creating AORTA agent " + numberedAgName, ex);
		}
		
		agents.add(agentArch);
		
		return actualAgName;
	}

	public List<AortaAgentArch> getAgents() {
		return agents;
	}

	@Override
	public boolean killAgent(String agName, String byAg) {
		AortaAgentArch agentArch = (AortaAgentArch) masRunner.getAg(agName);
		AortaAgent aortaAgent = agentArch.getAortaAgent();
		aorta.removeAgent(aortaAgent);
		
		return super.killAgent(agName, byAg);
	}
	
	@Override
	protected CentralisedAgArch newAgInstance() {
		return new AortaAgentArch();
	}
	
	

}
