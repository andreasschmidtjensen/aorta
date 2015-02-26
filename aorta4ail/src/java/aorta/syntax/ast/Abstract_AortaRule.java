/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.ast;

import ail.syntax.ast.Abstract_Action;
import ail.syntax.ast.Abstract_Guard;
import ail.syntax.ast.Abstract_Literal;
import ail.syntax.ast.Abstract_Predicate;
import aorta.semantics.AortaRule;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_AortaRule {
	
	private final Abstract_Literal option;
	private final Abstract_Guard condition;
	private final Abstract_Action action;

	public Abstract_AortaRule(Abstract_Literal option, Abstract_Guard condition,
			Abstract_Action act) {
		this.option = option;
		this.condition = condition;
		this.action = act;
	}
//	
//	private Abstract_Action createAction(Abstract_Literal lit) {
//		switch (lit.getFunctor()) {
//			case "enact":
//				return new Abstract_EnactAction(((Abstract_Predicate)lit.getTerm(0)).getFunctor());
//			case "deact":
//				return new Abstract_DeactAction(((Abstract_Predicate)lit.getTerm(0)).getFunctor());
//			case "commit":
//				return new Abstract_CommitAction((Abstract_Predicate)lit.getTerm(0));
//			case "drop":
//				return new Abstract_DropAction((Abstract_Predicate)lit.getTerm(0));
//			case "send":
//				return new Abstract_AortaSendAction(lit.getTerm(0), (Abstract_Predicate)lit.getTerm(1));
//		}
//		throw new IllegalArgumentException(lit + " not supported."); //To change body of generated methods, choose Tools | Templates.
//	}
	
    /*
     * (non-Javadoc)
     * @see ail.syntax.ast.Abstract_GBelief#toMCAPL()
     */
    public AortaRule toMCAPL() {
    	return new AortaRule(option.toMCAPL(), condition.toMCAPL(), action.toMCAPL());
    }
    
    /*
     * (non-Javadoc)
     * @see ail.syntax.ast.Abstract_GBelief#newJPFObject(gov.nasa.jpf.jvm.MJIEnv)
     */
    public int newJPFObject(MJIEnv env) {
    	int objref = env.newObject("aorta.syntax.ast.Abstract_AortaRule");
    	env.setReferenceField(objref, "option", option.newJPFObject(env));
    	env.setReferenceField(objref, "condition", condition.newJPFObject(env));
    	env.setReferenceField(objref, "action", action.newJPFObject(env));
		return objref;
    }

}
