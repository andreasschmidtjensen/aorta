/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.formula;

import aorta.ail.abs.Abstract_Formula;
import aorta.reasoning.fml.Formula;
import aorta.reasoning.fml.OptionFormula;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_OptionFormula extends Abstract_Formula {

	private String formula;

	public Abstract_OptionFormula(String formula) {
		this.formula = formula;
	}
	
	@Override
	public Formula toAORTA() {
		return new OptionFormula(formula);
	}

	@Override
	public int newJPFObject(MJIEnv env) {
		int objref = env.newObject("aorta.ail.abs.formula.Abstract_OptionFormula");
		env.setReferenceField(objref, "formula", env.newString(formula));
		return objref;
	}
	
}
