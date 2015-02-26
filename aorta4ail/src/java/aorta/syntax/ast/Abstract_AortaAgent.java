/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.ast;

import ail.semantics.AILAgent;
import ail.syntax.ast.Abstract_Agent;
import aorta.semantics.AortaAgent;
import aorta.syntax.ast.metamodel.Abstract_Metamodel;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_AortaAgent extends Abstract_Agent {

	private Abstract_AortaRule[] aortaRules = new Abstract_AortaRule[0];
	private Abstract_Metamodel model;

	public void addAortaRule(Abstract_AortaRule r) {
		int newsize = aortaRules.length + 1;
		Abstract_AortaRule[] newrules = new Abstract_AortaRule[newsize];
		System.arraycopy(aortaRules, 0, newrules, 0, aortaRules.length);
		newrules[aortaRules.length] = r;
		aortaRules = newrules;
	}

	public AILAgent toMCAPL(AILAgent original) {
		AortaAgent agent = new AortaAgent(original);
		
		for (Abstract_AortaRule ar : aortaRules) {
			agent.addAortaRule(ar.toMCAPL());
		}
		agent.setMetamodel(model.toMCAPL());

		return agent;		
	}

	public void setModel(Abstract_Metamodel model) {
		this.model = model;
	}
	
}
