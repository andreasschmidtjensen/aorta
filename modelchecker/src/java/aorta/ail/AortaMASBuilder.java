/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail;

import ail.mas.MAS;
import ail.mas.MASBuilder;
import ail.util.AILConfig;
import ajpf.MCAPLcontroller;
import ajpf.util.AJPFException;
import ajpf.util.AJPFLogger;
import aorta.AORTAException;

/**
 *
 * @author asj
 */
public class AortaMASBuilder implements MASBuilder {

	MAS mas;
	AortaMAS aortaMas;
		
	@Override
	public MAS getMAS(String filename) {
		try {
			AILConfig config = new AILConfig(filename);

			String masBuilder = config.getProperty("mas.builder");
			String masFile = config.getProperty("mas.file");
			String abs_filename = null;
			try {
				abs_filename = MCAPLcontroller.getFilename(masFile);
			} catch (AJPFException e) {
				AJPFLogger.severe("ail.mas.AIL", e.getMessage());
			}
			
			buildMas(masBuilder, abs_filename);
			buildAortaMas(config);
			
			return aortaMas;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private void buildMas(String masBuilder, String abs_filename) {
		try {
			MASBuilder masbuilder = (MASBuilder) (Class.forName(masBuilder)).newInstance();
			mas = masbuilder.getMAS(abs_filename);
		} catch (Exception e) {
			AJPFLogger.severe("ail.mas.AIL", "Could not load wrapped MAS: " + e.getMessage());
		}
	}

	private void buildAortaMas(AILConfig config) {
		try {
			aortaMas = new AortaMAS(mas, config);
		} catch (AORTAException ex) {
			AJPFLogger.severe("ail.mas.AIL", "Could not load AORTA MAS: " + ex.getMessage());
		}
	}
	
}
