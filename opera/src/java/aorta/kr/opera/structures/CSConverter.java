/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.opera.structures;

import alice.tuprolog.Term;
import aorta.kr.language.MetaLanguage;
import aorta.kr.language.model.Metamodel;
import aorta.kr.language.model.Rule;
import aorta.kr.opera.ConversionUtils;
import aorta.kr.opera.OperAImportException;
import java.util.HashMap;
import java.util.Map;
import net.sf.ictalive.operetta.OM.CS;
import net.sf.ictalive.operetta.OM.CountsAs;
import net.sf.ictalive.operetta.OM.PartialStateDescription;

/**
 *
 * @author asj
 */
public class CSConverter {

	private static MetaLanguage ml = new MetaLanguage();
	
	public static void convert(CS cs, Metamodel mm) throws OperAImportException {
		Map<String, PartialStateDescription> fmls = new HashMap<>();
		for (PartialStateDescription psd : cs.getFormulas()) {
			fmls.put(psd.write(), psd);
		}
		for (CountsAs countsAs : cs.getCountsAsRules()) {
			Term head = ConversionUtils.stateDescriptionToStruct(fmls.get(countsAs.getConcreteFact().write()));
			Term body = ConversionUtils.stateDescriptionToStruct(countsAs.getAbstractFact());
			mm.getRules().add(new Rule(head, body));
		}
		
	}
	
}
