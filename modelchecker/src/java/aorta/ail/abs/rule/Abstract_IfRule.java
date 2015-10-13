/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.rule;

import aorta.ail.abs.Abstract_Formula;
import aorta.reasoning.IfRule;
import aorta.reasoning.ReasoningRule;
import gov.nasa.jpf.vm.MJIEnv;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_IfRule extends Abstract_ReasoningRule {
	
	private Abstract_Formula fml;
	private Abstract_ReasoningRule[] rules;

	public Abstract_IfRule(Abstract_Formula fml, Abstract_ReasoningRule[] rules) {
		this.fml = fml;
		this.rules = rules;
	}

	Abstract_IfRule(IfRule ir) {
		fml = Abstract_Formula.convert(ir.getCondition());
		rules = new Abstract_ReasoningRule[ir.getRules().size()];
		for (int i = 0; i < rules.length; i++) {
			rules[i] = Abstract_ReasoningRule.convert(ir.getRules().get(i));
		}
	}

	public Abstract_Formula getFml() {
		return fml;
	}

	public Abstract_ReasoningRule[] getRules() {
		return rules;
	}

	@Override
	public IfRule toAORTA() {
		List<ReasoningRule> aRules = new ArrayList<>();
		for (Abstract_ReasoningRule arr : rules) {
			aRules.add(arr.toAORTA());
		}
		return new IfRule(fml.toAORTA(), aRules);
	}

	@Override
	public int newJPFObject(MJIEnv env) {
		int objref = env.newObject("aorta.ail.abs.rule.Abstract_IfRule");
		env.setReferenceField(objref, "fml", fml.newJPFObject(env));
		
		int rulesRef = env.newObjectArray("aorta.ail.abs.rule.Abstract_ReasoningRule", rules.length);
       	for (int i = 0; i < rules.length; i++) {
       		env.setReferenceArrayElement(rulesRef, i, rules[i].newJPFObject(env));
       	}
      	env.setReferenceField(objref, "rules", rulesRef);
		
		return objref;

	}
}
