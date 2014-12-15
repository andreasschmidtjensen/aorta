/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.opera;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Theory;
import aorta.kr.language.model.Metamodel;
import aorta.kr.opera.structures.CSConverter;
import aorta.kr.opera.structures.ISConverter;
import aorta.kr.opera.structures.NSConverter;
import aorta.kr.opera.structures.SSConverter;
import java.io.InputStream;
import net.sf.ictalive.operetta.OM.OM;
import net.sf.ictalive.operetta.OM.OperAModel;

/**
 *
 * @author asj
 */
public class OperAConverter {

	public Theory getAortaTheory(InputStream is) throws OperAImportException {
		try {
			return getAortaMetamodel(is).createTheory();
		} catch (InvalidTheoryException ex) {
			throw new OperAImportException(ex);
		}
	}
	
	public Metamodel getAortaMetamodel(InputStream is) throws OperAImportException {
		OperAModel model = OperALoader.getModel(is);
		
		return convertToMetamodel(model);
	}

	private Metamodel convertToMetamodel(OperAModel model) throws OperAImportException {
		Metamodel mm = new Metamodel();
		OM om = model.getOm();
		SSConverter.convert(om.getSs(), mm);
		ISConverter.convert(om.getIs(), mm);
		NSConverter.convert(om.getNs(), om.getSs(), mm);
		CSConverter.convert(om.getCs(), mm);
		
		return mm;
	}
	
}
