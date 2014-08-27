/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail;

import ail.mas.MAS;
import ail.semantics.AILAgent;
import ail.util.AILConfig;
import ajpf.util.AJPFLogger;
import aorta.AORTAException;
import aorta.Aorta;

/**
 *
 * @author asj
 */
public class AortaMAS extends MAS {

	private Aorta aorta;

	public AortaMAS(MAS mas, AILConfig config) throws AORTAException {
		setEnv(mas.getEnv());
		setController(mas.getController());
		String organization = config.getProperty("aorta.organization");
		aorta = new Aorta(organization);

		for (AILAgent ag : mas.getAgs().values()) {
			try {
				AortaAILAgent aortaAILAgent = new AortaAILAgent(ag, aorta, config.getProperty("aorta." + ag.getAgName()));
				aorta.addAgent(aortaAILAgent.getAortaAgent());
				addAg(aortaAILAgent);
			} catch (Exception ex) {
				AJPFLogger.severe("ail.mas.AIL", "Could not add agent: " + ag.getMCAPLAgName() + " (" + ex.getMessage() + ")");
				ex.printStackTrace();
//				System.exit(1);
			}
		}
	}

}
