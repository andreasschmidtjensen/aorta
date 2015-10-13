	/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail;

import ajpf.util.AJPFLogger;
import aorta.AortaAgent;
import aorta.ail.abs.Abstract_AortaAgent;
import aorta.parser.helper.AortaBuilder;
import gov.nasa.jpf.annotation.MJI;
import gov.nasa.jpf.vm.MJIEnv;
import gov.nasa.jpf.vm.NativePeer;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class JPF_aorta_ail_AortaAILAgent extends NativePeer {

	@MJI
	public void buildAortaAgent__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_String_2__V(MJIEnv env, int objRef, int nameRef, int fileRef, int orgRef) {
		String name = env.getStringObject(nameRef);
		String file = env.getStringObject(fileRef);
		String org = env.getStringObject(orgRef);
		
		AortaBuilder builder = new AortaBuilder();
		try {
			AortaAgent ag = builder.parseAgent(name, file, org, null);
			Abstract_AortaAgent absAortaAgent = new Abstract_AortaAgent(ag);
			
			int ref = absAortaAgent.newJPFObject(env);
			env.setReferenceField(objRef, "absAortaAgent", ref);
		} catch (Exception ex) {
			AJPFLogger.severe("JPF_aorta_ail_AortaAILAgent", "Could not parse AORTA program: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
