/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.action;

import alice.tuprolog.Term;
import aorta.ail.abs.Abstract_Action;
import aorta.reasoning.action.Action;
import aorta.reasoning.action.DeactAction;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_DeactAction extends Abstract_Action {

	private String role;

	public Abstract_DeactAction(String role) {
		this.role = role;
	}
		
	@Override
	public Action toAORTA() {
		return new DeactAction(Term.createTerm(role));
	}

	@Override
	public int newJPFObject(MJIEnv env) {
		int objref = env.newObject("aorta.ail.abs.action.Abstract_DeactAction");
		env.setReferenceField(objref, "role", env.newString(role));
		return objref;
	}
	
}
