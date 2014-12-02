/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason.infra;

import aorta.AORTAException;
import aorta.Aorta;
import aorta.AortaAgent;
import aorta.inspector.AgentWebInspector;
import aorta.inspector.AortaGui;
import aorta.jason.AortaAgentArch;
import aorta.jason.AortaJasonBridge;
import aorta.kr.language.OrganizationImportException;
import aorta.organization.AortaArtifact;
import aorta.organization.EnvironmentSensor;
import aorta.parser.helper.AortaBuilder;
import aorta.tracer.Tracer;
import jason.NoValueForVarException;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.Literal;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.StringTerm;
import jason.asSyntax.Term;
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
	
	private boolean useWebInspector = false;
	private int agSleepTime = 0;
	
	private List<AortaAgentArch> agents = new ArrayList<>();
	
	private boolean useArtifact;
	
	public AortaRuntimeServices(RunCentralisedMAS masRunner) {
		this(masRunner, true);
	}
	
	public AortaRuntimeServices(RunCentralisedMAS masRunner, boolean useGui) {
		super(masRunner);

		if (useGui) {
			gui = new AortaGui();
		}
		
		String location;
		
		ClassParameters infrastructure = masRunner.getProject().getInfrastructure();
		
		try {
			String org = infrastructure.getParameter("organization");
			Literal orgLiteral = ASSyntax.parseLiteral(org);
			location = ((StringTerm) orgLiteral.getTerm(0)).getString();
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
		
		useWebInspector = infrastructure.getParameter("inspector") != null;
		useArtifact = infrastructure.getParameter("artifact") != null;
		
		String sleep = infrastructure.getParameter("sleep");
		if (sleep != null) {
			try {
				Literal sleepLit = ASSyntax.parseLiteral(sleep);
				agSleepTime = (int) ((NumberTerm) sleepLit.getTerm(0)).solve();
			} catch (ParseException | NoValueForVarException ex) {
				throw new RuntimeException(ex);
			}
		}
		
		String ignore = infrastructure.getParameter("notrace");
		if (ignore != null) {
			try {
				Literal ignoreLit = ASSyntax.parseLiteral(ignore);
				for (Term t : ignoreLit.getTermsArray()) {
					if (t.isString()) {
						Tracer.ignore(((StringTerm) t).getString());
					}
				}
			} catch (ParseException ex) {
				throw new RuntimeException(ex);
			}
		}
		
		if (useWebInspector) {
			AgentWebInspector.get();
		}
		
		try {
			aorta = new Aorta(location);
		} catch (AORTAException ex) {
			logger.log(Level.SEVERE, "Could not launch AORTA; exiting.", ex);
			throw new RuntimeException(ex);
		}
	}

	/**
	 * This flag is used to ensure that the createAgent/6 method is always called from the createAgent/8 method.
	 * The first creates the agent using the centralised runtime services - the second creates the aorta agent.
	 * This is useful when external systems uses the runtime services to create agents (e.g. EIS).
	 */
	private boolean fromAORTA = false;
	
	@Override
	public String createAgent(String agName, String agSource, String agClass, List<String> archClasses, ClassParameters bbPars, Settings stts) throws Exception {
		if (useArtifact && !aorta.artifactInitialized()) {
			EnvironmentSensor sensor = ((AortaEnvironment) masRunner.getEnvironmentInfraTier()).getSensor();
			if (sensor != null) {
				try {
					aorta.setupArtifact(sensor);
					
					if (gui != null) {
						gui.addArtifact(AortaArtifact.get());
					}
				} catch (AORTAException ex) {
					throw new RuntimeException("Could not setup artifact", ex);
				}
			} else {
				logger.warning("useArtifact was specified, but environment does not support it");
			}
		}
		
		if (fromAORTA) {
			return super.createAgent(agName, agSource, agClass, archClasses, bbPars, stts);			
		} else {
			return createAgent(agName, agName, agSource, agClass, archClasses, bbPars, stts, (AortaEnvironment) masRunner.getEnvironmentInfraTier());
		}
	}

	public String createAgent(String agName, String numberedAgName, String agSource, String agClass, List<String> archClasses, ClassParameters bbPars, Settings stts, AortaEnvironment env) throws Exception {
		System.out.println("creating agent: " + agName);
		
		fromAORTA = true;
		String actualAgName = createAgent(numberedAgName, agSource, agClass, archClasses, bbPars, stts);
		fromAORTA = false;
		
		AortaAgentArch agentArch = (AortaAgentArch) masRunner.getAg(actualAgName);
		agentArch.setEnvInfraTier(env);

		try {
			String aortaLocation = stts.getUserParameter("aorta");
			if (aortaLocation == null) {
				aortaLocation = agName + ".aorta";
			}
			
			aortaLocation = aortaLocation.replaceAll("\\\\", "/");
				
			AortaBuilder builder = new AortaBuilder();			
			AortaAgent aortaAgent = builder.parseAgent(actualAgName, aortaLocation, aorta.getOrganizationLocation(), new AortaJasonBridge(agentArch));
			
			if (aortaAgent != null) {
				aorta.addAgent(aortaAgent);

				agentArch.setAortaAgent(aortaAgent);
				agentArch.setAorta(aorta);

				if (gui != null) {
					gui.addAgent(aortaAgent);
				}
				if (useWebInspector) {
					AgentWebInspector.get().registerAgent(aortaAgent);
					aortaAgent.addInspector(AgentWebInspector.get());
				}
				if (agSleepTime > 0) {
					agentArch.setSleepTime(agSleepTime);
				}
			}
			
		} catch (IOException | OrganizationImportException ex) {
			logger.log(Level.SEVERE, "Error creating AORTA agent " + numberedAgName, ex);
		}
		
		agents.add(agentArch);
		
		String logEndState = stts.getUserParameter("logEndState");
		agentArch.setSaveToFile("true".equals(logEndState));
		
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
