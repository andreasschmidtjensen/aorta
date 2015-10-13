/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.formula;

import aorta.ail.abs.Abstract_Formula;
import aorta.reasoning.fml.ConjunctFormula;
import aorta.reasoning.fml.Formula;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_ConjunctFormula extends Abstract_Formula {

	private Abstract_Formula formula1;
	private Abstract_Formula formula2;

	public Abstract_ConjunctFormula(Abstract_Formula formula1, Abstract_Formula formula2) {
		this.formula1 = formula1;
		this.formula2 = formula2;
	}
	
	@Override
	public Formula toAORTA() {
		return new ConjunctFormula(formula1.toAORTA(), formula2.toAORTA());
	}

	@Override
	public int newJPFObject(MJIEnv env) {
		int objref = env.newObject("aorta.ail.abs.formula.Abstract_ConjunctFormula");
		env.setReferenceField(objref, "formula1", formula1.newJPFObject(env));
		env.setReferenceField(objref, "formula2", formula2.newJPFObject(env));
		return objref;
	}
	
}
