/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs;

import aorta.ail.abs.formula.Abstract_BeliefFormula;
import aorta.ail.abs.formula.Abstract_ConjunctFormula;
import aorta.ail.abs.formula.Abstract_GoalFormula;
import aorta.ail.abs.formula.Abstract_NegatedFormula;
import aorta.ail.abs.formula.Abstract_OptionFormula;
import aorta.ail.abs.formula.Abstract_OrganizationalFormula;
import aorta.ail.abs.formula.Abstract_TrueFormula;
import aorta.reasoning.fml.BeliefFormula;
import aorta.reasoning.fml.ConjunctFormula;
import aorta.reasoning.fml.Formula;
import aorta.reasoning.fml.GoalFormula;
import aorta.reasoning.fml.NegatedFormula;
import aorta.reasoning.fml.OptionFormula;
import aorta.reasoning.fml.OrganizationalFormula;
import aorta.reasoning.fml.TrueFormula;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public abstract class Abstract_Formula {

	static Abstract_Formula convert(Formula context) {
		if (context instanceof BeliefFormula) {
			return new Abstract_BeliefFormula(((BeliefFormula) context).getFormula().toString());
		} else if (context instanceof ConjunctFormula) {
			return new Abstract_ConjunctFormula(convert(((ConjunctFormula)context).getFml1()), convert(((ConjunctFormula)context).getFml2()));
		} else if (context instanceof GoalFormula) {
			return new Abstract_GoalFormula(((GoalFormula) context).getFormula().toString());
		} else if (context instanceof NegatedFormula) {
			return new Abstract_NegatedFormula(convert(((NegatedFormula) context).getFormula()));
		} else if (context instanceof OptionFormula) {
			return new Abstract_OptionFormula(((OptionFormula) context).getFormula().toString());
		} else if (context instanceof OrganizationalFormula) {
			return new Abstract_OrganizationalFormula(((OrganizationalFormula) context).getFormula().toString());
		} else if (context instanceof TrueFormula) {
			return new Abstract_TrueFormula();
		} else {
			throw new RuntimeException("Wrong kind of formula: " + context.getClass().getName());
		}
	}
	
	public abstract Formula toAORTA();	
	public abstract int newJPFObject(MJIEnv env);
}
