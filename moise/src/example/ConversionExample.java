/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import alice.tuprolog.Prolog;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import aorta.kr.language.model.Metamodel;
import aorta.kr.moise.MoiseConverter;
import java.util.Iterator;

/**
 *
 * @author asj
 */
public class ConversionExample {
	
	public static void main(String[] args) throws Exception {
		MoiseConverter converter = new MoiseConverter();
		Metamodel mm = converter.getAortaMetamodel("src/example/eah.xml");
		
		System.out.println(mm);
		
		Theory theory = mm.createTheory();
		
		Prolog prolog = new Prolog();
		prolog.addTheory(theory);
		
		Iterator<? extends Term> it = theory.iterator(prolog);
		while (it.hasNext()) {
			Term t = it.next();
			System.out.println(t);
		}
	}
	
}
