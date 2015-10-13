/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.action;

import alice.tuprolog.Term;
import aorta.ail.abs.Abstract_Action;
import aorta.ail.abs.Abstract_Formula;
import aorta.reasoning.action.Action;
import aorta.reasoning.action.SendAction;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_SendAction extends Abstract_Action {

	private String recipient;
	private Abstract_Formula formula;

	public Abstract_SendAction(String recipient, Abstract_Formula formula) {
		this.recipient = recipient;
		this.formula = formula;
	}

		
	@Override
	public Action toAORTA() {
		return new SendAction(Term.createTerm(recipient), formula.toAORTA());
	}

	@Override
	public int newJPFObject(MJIEnv env) {
		int objref = env.newObject("aorta.ail.abs.action.Abstract_SendAction");
		env.setReferenceField(objref, "recipient", env.newString(recipient));
		env.setReferenceField(objref, "formula", formula.newJPFObject(env));
		return objref;
	}
	
}
