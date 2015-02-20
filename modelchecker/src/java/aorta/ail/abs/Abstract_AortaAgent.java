/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Theory;
import aorta.AortaAgent;
import aorta.ail.abs.mm.Abstract_Metamodel;
import aorta.ail.abs.rule.Abstract_ReasoningRule;
import aorta.kr.MentalState;
import aorta.reasoning.ReasoningRule;
import gov.nasa.jpf.vm.MJIEnv;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_AortaAgent {
	
	private final String name;
	private final Abstract_ReasoningRule[] rules;
	private final String mentalState;
	private final Abstract_Metamodel metamodel;
	
	public Abstract_AortaAgent(String name, Abstract_ReasoningRule[] rules, String mentalState, Abstract_Metamodel metamodel) {
		this.name = name;
		this.rules = rules;
		this.mentalState = mentalState;
		this.metamodel = metamodel;
	}

	public Abstract_AortaAgent(AortaAgent aortaAgent) {
		name = aortaAgent.getName();
		mentalState = aortaAgent.getState().getMentalState().getProlog().getTheory().toString();
		List<ReasoningRule> reasoningRules = aortaAgent.getState().getRules();
		rules = new Abstract_ReasoningRule[reasoningRules.size()];
		for (int i = 0; i < reasoningRules.size(); i++) {
			rules[i] = Abstract_ReasoningRule.convert(reasoningRules.get(i));
		}
		metamodel = new Abstract_Metamodel(aortaAgent.getState().getMetamodel());
	}

	public String getMentalState() {
		return mentalState;
	}

	public String getName() {
		return name;
	}

	public Abstract_ReasoningRule[] getRules() {
		return rules;
	}
	
	public AortaAgent toAORTA() throws InvalidTheoryException {
		List<ReasoningRule> reasoningRules = new ArrayList<>();
		for (Abstract_ReasoningRule rr : rules) {
			reasoningRules.add(rr.toAORTA());
		}

		return new AortaAgent(name, new MentalState(mentalState), metamodel.toAORTA(), reasoningRules);
	}
	
	public int newJPFObject(MJIEnv env) {
    	int objref = env.newObject("aorta.ail.abs.Abstract_AortaAgent");
     	env.setReferenceField(objref, "name", env.newString(name));
     	int rulesRef = env.newObjectArray("aorta.ail.abs.Abstract_ActionRule", rules.length);
       	for (int i = 0; i < rules.length; i++) {
       		env.setReferenceArrayElement(rulesRef, i, rules[i].newJPFObject(env));
       	}
      	env.setReferenceField(objref, "rules", rulesRef);
     	env.setReferenceField(objref, "mentalState", env.newString(mentalState));
		env.setReferenceField(objref, "metamodel", metamodel.newJPFObject(env));
      	return objref;
   	
    }
	
}
