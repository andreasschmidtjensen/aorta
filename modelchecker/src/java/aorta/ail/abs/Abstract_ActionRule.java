/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs;

import alice.tuprolog.Term;
import aorta.reasoning.ActionRule;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_ActionRule {

	private String option;
	private Abstract_Formula context;
	private Abstract_Action action;

	public Abstract_ActionRule(String option, Abstract_Formula context, Abstract_Action action) {
		this.option = option;
		this.context = context;
		this.action = action;
	}

	Abstract_ActionRule(ActionRule ar) {
		option = ar.getOption().toString();
		context = Abstract_Formula.convert(ar.getContext());
		action = Abstract_Action.convert(ar.getAction());
	}

	public Abstract_Action getAction() {
		return action;
	}

	public Abstract_Formula getContext() {
		return context;
	}

	public String getOption() {
		return option;
	}
	
	public ActionRule toAORTA() {
		ActionRule ar = new ActionRule(Term.createTerm(option), context.toAORTA(), action.toAORTA());
		return ar;
	}

	public int newJPFObject(MJIEnv env) {
		int objref = env.newObject("aorta.ail.abs.Abstract_ActionRule");
		env.setReferenceField(objref, "option", env.newString(option));
		env.setReferenceField(objref, "context", context.newJPFObject(env));
		env.setReferenceField(objref, "action", action.newJPFObject(env));
		return objref;

	}
}
