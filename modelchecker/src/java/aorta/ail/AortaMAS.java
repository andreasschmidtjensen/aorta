/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail;

import ail.mas.MAS;
import ail.semantics.AILAgent;
import ail.util.AILConfig;
import ajpf.MCAPLLanguageAgent;
import ajpf.util.AJPFLogger;
import aorta.AORTAException;
import aorta.Aorta;
import aorta.gui.AortaGui;
import gov.nasa.jpf.vm.Verify;

/**
 *
 * @author asj
 */
public class AortaMAS extends MAS {

	private Aorta aorta;
	private AortaGui gui;

	public AortaMAS(MAS mas, AILConfig config) throws AORTAException {
		if (!Verify.isRunningInJPF()) {
			gui = new AortaGui();
		}

		setEnv(mas.getEnv());
		setController(mas.getController());
		String organization = config.getProperty("aorta.organization");
		aorta = new Aorta(organization);
		
		for (MCAPLLanguageAgent mcaplAg : mas.getMCAPLAgents()) {
			if (mcaplAg instanceof AILAgent) {
				AILAgent ag = (AILAgent) mcaplAg;
				try {
					AortaAILAgent aortaAILAgent = new AortaAILAgent(ag, aorta, config.getProperty("aorta." + ag.getAgName()));
					aorta.addAgent(aortaAILAgent.getAortaAgent());
					addAg(aortaAILAgent);

					if (gui != null) {
						gui.addAgent(aortaAILAgent.getAortaAgent());
					}
				} catch (Exception ex) {
					AJPFLogger.severe("ail.mas.AIL", "Could not add agent: " + ag.getMCAPLAgName() + " (" + ex.getMessage() + ")");
					ex.printStackTrace();
				}
			}
		}
	}
}
