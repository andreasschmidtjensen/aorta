/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason.infra;

import aorta.organization.EnvironmentSensor;
import eis.EnvironmentInterfaceStandard;
import jason.JasonException;
import jason.eis.EISAdapter;
import jason.environment.Environment;
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
	
	private EnvironmentSensor sensor;
	
	public AortaEnvironment(ClassParameters userEnvArgs, AortaLauncher masRunner) throws JasonException {
		super(userEnvArgs, masRunner);
		this.masRunner = masRunner;
		
		Environment env = getUserEnvironment();
		if (env != null) {
			if (env instanceof EnvironmentSensor) {
				sensor = (EnvironmentSensor) env;
			} else if (env instanceof EISAdapter) {
				try {
					EISAdapter eisAdapter = (EISAdapter) env;
					Field privateStringField = EISAdapter.class.getDeclaredField("ei");
					privateStringField.setAccessible(true);
					EnvironmentInterfaceStandard eis = (EnvironmentInterfaceStandard) privateStringField.get(eisAdapter);

					if (eis instanceof EnvironmentSensor) {
						sensor = (EnvironmentSensor) eis;
					}
				} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException ex) {
					throw new JasonException("Could not check EIS environment", ex);
				}
			}
		}
	}

	public EnvironmentSensor getSensor() {
		return sensor;
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
