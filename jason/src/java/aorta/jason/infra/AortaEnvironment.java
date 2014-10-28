/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason.infra;

import jason.JasonException;
import jason.infra.centralised.CentralisedEnvironment;
import jason.infra.centralised.CentralisedRuntimeServices;
import jason.infra.centralised.RunCentralisedMAS;
import jason.mas2j.ClassParameters;
import jason.runtime.RuntimeServicesInfraTier;
import java.lang.reflect.Field;

/**
 *	
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class AortaEnvironment extends CentralisedEnvironment {

	private AortaLauncher masRunner;
	
	public AortaEnvironment(ClassParameters userEnvArgs, AortaLauncher masRunner) throws JasonException {
		super(userEnvArgs, masRunner);
		this.masRunner = masRunner;
	}

	@Override
	public RuntimeServicesInfraTier getRuntimeServices() {
		if (masRunner != null) {
			return new AortaRuntimeServices(masRunner);
		} else {
			try {
				Field privateStringField = CentralisedRuntimeServices.class.getDeclaredField("masRunner");
				privateStringField.setAccessible(true);
				RunCentralisedMAS runner = (RunCentralisedMAS) privateStringField.get(super.getRuntimeServices());
				return new AortaRuntimeServices(runner);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
	
}
