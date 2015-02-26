/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics.operationalrules.og;

import ail.semantics.AILAgent;
import ail.syntax.Literal;
import ail.syntax.Predicate;
import ail.syntax.Unifier;
import ajpf.util.AJPFLogger;
import aorta.semantics.AortaAgent;
import aorta.semantics.operationalrules.AortaOSRule;
import aorta.syntax.metamodel.Dependency;
import aorta.syntax.metamodel.Metamodel;
import java.util.Iterator;

/**
 *
 * @author asj
 */
public class Inform extends AortaOSRule {

	@Override
	public boolean checkPreconditions(AILAgent a) {
		AortaAgent ag = (AortaAgent)a;
		return !ag.getReas().isEmpty();
	}

	@Override
	public void apply(AILAgent a) {
		AortaAgent ag = (AortaAgent)a;
		Metamodel mm = ag.getMetamodel();
		
		for (String role : ag.getReas()) {
			for (Dependency dep : mm.getDependencies()) {
				Literal obj = dep.getObjective();
				if (dep.getDependee().equals(role) && ag.belContains(obj)) {
					Iterator<Literal> relevant = ag.getBB().getRelevant(obj);
					while (relevant.hasNext()) {
						Literal relLit = relevant.next();
						if (relLit.unifies(obj, new Unifier())) {
							
							Literal opt = new Literal("send");
							opt.addTerm(new Predicate(dep.getDependant()));
							opt.addTerm(new Predicate("tell"));
							opt.addTerm(relLit);

							if (!ag.optContains(opt)) {
								ag.addOpt(opt);

								AJPFLogger.fine(getLoggerName(), "Adding option: " + opt);						

								ag.aortaChanged();
								break;
							}
						}
					}
					
				}
			}
		}
	}
	
}
