/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.moise;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import java.util.List;

/**
 *
 * @author asj
 */
public class ConversionUtils {
	
	public static Struct combine(String operator, List<Term> terms) {
		Struct first = null;
		Struct second;
		for (Term s : terms) {
			if (first == null) {
				first = (Struct) s;
			} else {
				second = (Struct) s;
				first = new Struct(operator, first, second);
			}
		}
		return first;
	}
	
}
