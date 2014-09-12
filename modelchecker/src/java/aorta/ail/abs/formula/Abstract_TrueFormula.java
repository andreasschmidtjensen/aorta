/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.formula;

import aorta.ail.abs.Abstract_Formula;
import aorta.reasoning.fml.Formula;
import aorta.reasoning.fml.TrueFormula;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_TrueFormula extends Abstract_Formula {

	@Override
	public Formula toAORTA() {
		return new TrueFormula();
	}

	@Override
	public int newJPFObject(MJIEnv env) {
		int objref = env.newObject("aorta.ail.abs.formula.Abstract_TrueFormula");
		return objref;
	}
	
}
