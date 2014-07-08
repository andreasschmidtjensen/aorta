/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason.infra;

import jason.JasonException;
import jason.infra.centralised.RunCentralisedMAS;
import jason.mas2j.AgentParameters;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ascje
 */
public class AortaLauncher extends RunCentralisedMAS {

	private static final Logger logger = Logger.getLogger(AortaLauncher.class.getName());

	public static void main(String[] args) throws JasonException {
		runner = new AortaLauncher();
		runner.init(args);
		runner.create();
		runner.start();
		runner.waitEnd();
		runner.finish();
	}
	
	private boolean useGui = true;
	private AortaRuntimeServices creator;

	public AortaLauncher(boolean useGui) {
		this.useGui = useGui;
	}

	public AortaLauncher() {
	}
	
	@Override
	public void create() throws JasonException {
		creator = new AortaRuntimeServices(this, useGui);
		createEnvironment();
		createAgs();
		createController();
	}

	public AortaRuntimeServices getCreator() {
		return creator;
	}

	@Override
	public void createAgs() throws JasonException {
		for (AgentParameters ap : getProject().getAgents()) {
			try {
				String agName = ap.name;
				
				for (int cAg = 0; cAg < ap.qty; cAg++) {
					String numberedAg = agName;
					if (ap.qty > 1) {
						numberedAg = numberedAg + (cAg + 1);
					}

					logger.log(Level.FINE, "Creating agent " + numberedAg + " (" + (cAg + 1) + "/" + ap.qty + ")");
					List<String> archs = ap.getAgArchClasses();
					creator.createAgent(agName, numberedAg, ap.asSource.toString(), ap.agClass.getClassName(), archs, ap.getBBClass(), ap.getAsSetts(isDebug(), getProject().getControlClass() != null));
				}
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Error creating agent " + ap.name, e);
			}
		}
	}
}
