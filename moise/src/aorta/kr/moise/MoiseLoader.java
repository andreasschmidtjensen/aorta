package aorta.kr.moise;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import moise.os.OS;

/**
 *
 * @author ascje
 */
public class MoiseLoader {

	public static OS getModel(String uri) throws MoiseImportException {		
		OS model = OS.loadOSFromURI(uri);
		return model;
	}
	
}