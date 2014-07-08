/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language.generic;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import aorta.kr.language.OrganizationImportException;
import aorta.kr.language.OrganizationLoader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 *
 * @author ascje
 */
public class GenericLoader implements OrganizationLoader {

	@Override
	public Theory getOrganizationTheory(InputStream organizationIs) throws OrganizationImportException {
		try {
			Theory theory = new Theory(organizationIs);
			Struct list = new Struct();
			Iterator<? extends Term> it = theory.iterator(new Prolog());
			while (it.hasNext()) {
				list.append(it.next());
			}
			return new Theory(list);
		} catch (IOException ex) {
			throw new OrganizationImportException("The theory could not be loaded", ex);
		} catch (InvalidTheoryException ex) {
			throw new OrganizationImportException("The theory was invalid", ex);
		}
	}
	
}
