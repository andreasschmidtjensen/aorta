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
import java.util.Iterator;

/**
 *
 * @author asj
 */
public class Deact extends AortaOSRule {

	@Override
	public boolean checkPreconditions(AILAgent a) {
		return true;
	}

	@Override
	public void apply(AILAgent a) {
		AortaAgent ag = (AortaAgent) a;
		Metamodel mm = ag.getMetamodel();

		for (Role r : mm.getRoles()) {
			if (ag.enacts(r.getName())) {
				boolean completed = true;
				for (Literal o : r.getObjectives()) {
					if (!ag.getBB().getRelevant(o).hasNext()) {
						completed = false;
						break;
					}
				}
				if (completed) {
					Literal opt = new Literal(false, "role");
					opt.addTerm(new Predicate(r.getName()));

					if (!ag.optContains(opt)) {
						AJPFLogger.fine(getLoggerName(), "Adding ~" + r + " as option");

						ag.addOpt(opt);

						ag.aortaChanged();
					}
				}
			}
		}
	}

}
