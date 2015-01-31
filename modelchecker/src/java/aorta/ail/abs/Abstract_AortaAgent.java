/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Theory;
import aorta.AortaAgent;
import aorta.kr.MentalState;
import aorta.reasoning.ActionRule;
import aorta.reasoning.ReasoningRule;
import aorta.ts.strategy.AgentStrategy;
import gov.nasa.jpf.vm.MJIEnv;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_AortaAgent {
	
	private String name;
	private Abstract_ActionRule[] rules;
	private String mentalState;

	// TODO: Abstract_Metamodel 
	
	public Abstract_AortaAgent(String name, Abstract_ActionRule[] rules, String mentalState) {
		this.name = name;
		this.rules = rules;
		this.mentalState = mentalState;
	}

	public Abstract_AortaAgent(AortaAgent aortaAgent) {
		name = aortaAgent.getName();
		mentalState = aortaAgent.getState().getMentalState().getProlog().getTheory().toString();
		List<ReasoningRule> actionRules = aortaAgent.getState().getRules();
		rules = new Abstract_ActionRule[actionRules.size()];
		for (int i = 0; i < actionRules.size(); i++) {
            if (actionRules.get(i) instanceof ActionRule) { //TODO Include ifRule
                rules[i] = new Abstract_ActionRule((ActionRule) actionRules.get(i));
            }
                
		}
	}

	public String getMentalState() {
		return mentalState;
	}

	public String getName() {
		return name;
	}

	public Abstract_ActionRule[] getRules() {
		return rules;
	}
	
	public AortaAgent toAORTA() throws InvalidTheoryException {
		Prolog prolog = new Prolog();
		prolog.addTheory(new Theory(mentalState));
		List<ReasoningRule> arList = new ArrayList<>();
		for (Abstract_ActionRule ar : rules) {
			arList.add(ar.toAORTA());
		}
		AortaAgent agent = new AortaAgent(name, new MentalState(prolog), null, arList);
		return agent;
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
      	return objref;
   	
    }
	
}
