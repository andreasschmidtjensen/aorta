/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.formula;

import aorta.ail.abs.Abstract_Formula;
import aorta.reasoning.fml.Formula;
import aorta.reasoning.fml.NegatedFormula;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_NegatedFormula extends Abstract_Formula {

	private Abstract_Formula formula;

	public Abstract_NegatedFormula(Abstract_Formula formula) {
		this.formula = formula;
	}
	
	@Override
	public Formula toAORTA() {
		return new NegatedFormula(formula.toAORTA());
	}

	@Override
	public int newJPFObject(MJIEnv env) {
		int objref = env.newObject("aorta.ail.abs.formula.Abstract_NegatedFormula");
		env.setReferenceField(objref, "formula", formula.newJPFObject(env));
		return objref;
	}
	
}
