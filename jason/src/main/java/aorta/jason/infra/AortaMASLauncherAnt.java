/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason.infra;

import jason.infra.centralised.CentralisedMASLauncherAnt;

/**
 *
 * @author ascje
 */
public class AortaMASLauncherAnt extends CentralisedMASLauncherAnt {

	@Override
	protected String replaceMarks(String script, boolean debug) {
		 script = replace(script, "<PROJECT-RUNNER-CLASS>", AortaLauncher.class.getName());

		String lib = "";
//		lib = lib + "        <pathelement location=\"" + Config.get().getJasonHome() + "/lib/cartago.jar\"/>\n";
		lib = lib + "        <pathelement location=\"C:/Dropbox/code/phd/AORTA/dist/AORTA.jar\"/>\n";
		lib = lib + "        <pathelement location=\"C:/Dropbox/code/phd/AORTAJason/dist/AORTAJason.jar\"/>\n";
		lib = lib + "        <pathelement location=\"C:/Dropbox/code/phd/AORTA/lib/tuprolog.jar\"/>\n";
		lib = lib + "        <pathelement location=\"C:/Dropbox/code/phd/AORTA/lib/antlr-4.1-complete.jar\"/>\n";

		script = replace(script, "<PATH-LIB>", lib + "\n<PATH-LIB>");

		return super.replaceMarks(script, debug); 
	}
	
}
