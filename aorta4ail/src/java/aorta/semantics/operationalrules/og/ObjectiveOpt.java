/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics.operationalrules.og;

import ail.semantics.AILAgent;
import ail.syntax.Literal;
import ail.syntax.Unifier;
import ajpf.util.AJPFLogger;
import aorta.semantics.AortaAgent;
import aorta.semantics.operationalrules.AortaOSRule;
import aorta.syntax.metamodel.Metamodel;
import aorta.syntax.metamodel.Norm;
import aorta.syntax.metamodel.Objective;

/**
 *
 * @author asj
 */
public class ObjectiveOpt extends AortaOSRule {

	@Override
	public boolean checkPreconditions(AILAgent a) {
		AortaAgent ag = (AortaAgent)a;		
		return !ag.getMetamodel().getNorms().isEmpty();
	}

	@Override
	public void apply(AILAgent a) {
		AortaAgent ag = (AortaAgent)a;
		Metamodel mm = ag.getMetamodel();
		
		for (Norm norm : mm.getNorms()) {
			if (!norm.getAgent().equals(ag.getAgName())) {
				continue;
			}
			
			Literal normO = norm.getObjective();
			for (Objective obj : mm.getObjectives()) {
				if (normO.match(obj.getObjective(), new Unifier())) {
					Literal opt = new Literal("obj");
					opt.addTerm(normO);
					
					if (!ag.optContains(opt)) {
						ag.addOpt(opt);
						
						AJPFLogger.fine(getLoggerName(), "Adding option: " + opt + " for " + a.getAgName());
						
						ag.aortaChanged();
					}
				}
			}
		}
	}
	
}
