/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason.infra;

import jason.infra.centralised.CentralisedMASLauncherAnt;
import jason.jeditplugin.Config;

/**
 *
 * @author ascje
 */
public class AortaMASLauncherAnt extends CentralisedMASLauncherAnt {

	@Override
	protected String replaceMarks(String script, boolean debug) {
		 script = replace(script, "<PROJECT-RUNNER-CLASS>", AortaLauncher.class.getName());

		String lib = "";
		lib = lib + "        <pathelement location=\"" + Config.get().getJasonHome() + "/lib/aorta-standalone.jar\"/>\n";

		script = replace(script, "<PATH-LIB>", lib + "\n<PATH-LIB>");

		return super.replaceMarks(script, debug); 
	}
	
}
