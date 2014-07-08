/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language;

import alice.tuprolog.Theory;
import java.io.InputStream;

/**
 *
 * @author ascje
 */
public interface OrganizationLoader {
	
	public Theory getOrganizationTheory(InputStream organizationIs) throws OrganizationImportException;
	
}
