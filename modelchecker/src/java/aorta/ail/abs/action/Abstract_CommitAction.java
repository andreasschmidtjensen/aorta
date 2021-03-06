/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.action;

import alice.tuprolog.Term;
import aorta.ail.abs.Abstract_Action;
import aorta.reasoning.action.Action;
import aorta.reasoning.action.CommitAction;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_CommitAction extends Abstract_Action {

	private String objective;

	public Abstract_CommitAction(String objective) {
		this.objective = objective;
	}
		
	@Override
	public Action toAORTA() {
		return new CommitAction(Term.createTerm(objective));
	}

	@Override
	public int newJPFObject(MJIEnv env) {
		int objref = env.newObject("aorta.ail.abs.action.Abstract_CommitAction");
		env.setReferenceField(objref, "objective", env.newString(objective));
		return objref;
	}
	
}
