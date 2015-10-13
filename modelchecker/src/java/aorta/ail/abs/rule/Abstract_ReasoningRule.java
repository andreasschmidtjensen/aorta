/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.rule;

import aorta.reasoning.ActionRule;
import aorta.reasoning.IfRule;
import aorta.reasoning.ReasoningRule;
import aorta.reasoning.action.CommitAction;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public abstract class Abstract_ReasoningRule {
	
	public static Abstract_ReasoningRule convert(ReasoningRule rule) {
		if (rule instanceof IfRule) {
			return new Abstract_IfRule((IfRule)rule);
		} else if (rule instanceof ActionRule) {
			return new Abstract_ActionRule((ActionRule)rule);
		} else {
			throw new RuntimeException("Wrong kind of rule: " + rule.getClass().getName());
		}
	}
	public abstract ReasoningRule toAORTA();	
	public abstract int newJPFObject(MJIEnv env);	
}
