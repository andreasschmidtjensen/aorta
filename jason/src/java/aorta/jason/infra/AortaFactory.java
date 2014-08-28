/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason.infra;

import jason.infra.InfrastructureFactory;
import jason.jeditplugin.MASLauncherInfraTier;
import jason.runtime.RuntimeServicesInfraTier;

/**
 *
 * @author ascje
 */
public class AortaFactory implements InfrastructureFactory {

	@Override
	public MASLauncherInfraTier createMASLauncher() {
		return new AortaMASLauncherAnt();
	}

	@Override
	public RuntimeServicesInfraTier createRuntimeServices() {
		return new AortaRuntimeServices(AortaLauncher.getRunner());
	}
	
}
