/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics.operationalrules.og;

import ail.semantics.AILAgent;
import ail.syntax.Literal;
import ail.syntax.Predicate;
import ajpf.util.AJPFLogger;
import aorta.semantics.AortaAgent;
import aorta.semantics.operationalrules.AortaOSRule;
import aorta.syntax.metamodel.Metamodel;
import aorta.syntax.metamodel.Role;

/**
 *
 * @author asj
 */
public class Enact extends AortaOSRule {

	@Override
	public boolean checkPreconditions(AILAgent a) {
		return true;
	}

	@Override
	public void apply(AILAgent a) {
		AortaAgent ag = (AortaAgent)a;
		Metamodel mm = ag.getMetamodel();
		
		role:
		for (Role r : mm.getRoles()) {
			if (!ag.enacts(r.getName())) {
				for (Literal o : r.getObjectives()) {
					if (ag.isCapableOf(o)) {
						
						Literal opt = new Literal("role");
						opt.addTerm(new Predicate(r.getName()));
												
						if (!ag.optContains(opt)) {
							AJPFLogger.fine(getLoggerName(), "Adding " + opt + " as option for " + ag.getAgName());
							ag.addOpt(opt);

							ag.aortaChanged();
							
							continue role;
						}
					}
				}
			}
		}
	}
	
}
