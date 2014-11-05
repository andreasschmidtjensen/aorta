/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import alice.tuprolog.Prolog;
import alice.tuprolog.Theory;
import aorta.kr.language.model.Metamodel;
import aorta.kr.opera.OperAConverter;

/**
 *
 * @author asj
 */
public class ConversionExample {
	
	public static void main(String[] args) throws Exception {
		OperAConverter converter = new OperAConverter();
		Metamodel mm = converter.getAortaMetamodel(ConversionExample.class.getResourceAsStream("RTS.opera"));
		Theory theory = mm.createTheory();
		
		Prolog prolog = new Prolog();
		prolog.addTheory(theory);
		
		System.out.println(mm);
//		System.out.println("---");
//		System.out.println(prolog.getTheory().toString().replace("\n\n", "\n"));
	}
	
}
