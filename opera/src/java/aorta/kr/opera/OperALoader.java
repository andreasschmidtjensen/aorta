/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.opera;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import java.io.InputStream;
import java.util.Set;
import java.util.jar.Attributes;
import net.sf.ictalive.operetta.OM.OMPackage;
import net.sf.ictalive.operetta.OM.OperAModel;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 *
 * @author ascje
 */
public class OperALoader {

	@SuppressWarnings("unused")
	public static OperAModel getModel(InputStream is) throws OperAImportException {		
		// register model packages
		OMPackage omPackageInstance = OMPackage.eINSTANCE;
		NotationPackage notationPackageInstance = NotationPackage.eINSTANCE;
		
		OperAModel operaModel = loadOperAModel(is);
		
		return operaModel;
	}
		
	private static OperAModel loadOperAModel(InputStream in) throws OperAImportException {

		OperAModel model;

		Resource resource = new XMIResourceImpl();

		try {
			Attributes settings = new Attributes();
			resource.load(in, settings);

			// XMIModel : Create a class for XMI Model
			model = (OperAModel) resource.getContents().get(0);

		} catch (Exception e) {
			throw new OperAImportException("Could not import OperA organization", e);
		}

		return model;
	}
	
}