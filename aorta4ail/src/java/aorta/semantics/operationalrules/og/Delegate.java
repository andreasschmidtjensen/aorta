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
public class Delegate extends AortaOSRule {

	@Override
	public boolean checkPreconditions(AILAgent a) {
		AortaAgent ag = (AortaAgent) a;
		return !ag.getReas().isEmpty();
	}

	@Override
	public void apply(AILAgent a) {
		AortaAgent ag = (AortaAgent) a;
		Metamodel mm = ag.getMetamodel();

		for (String role : ag.getReas()) {
			for (Dependency dep : mm.getDependencies()) {
				Literal objective = dep.getObjective();
				Literal obj = new Literal("obj");
				obj.addTerm(objective);

				if (dep.getDependant().equals(role) && ag.optContains(obj)) {
					
					Iterator<Literal> it = ag.getOptBB().getRelevant(obj);
					while (it.hasNext()) {
						Literal relevant = it.next();
						if (relevant.unifies(obj, new Unifier())) {
							
							Literal opt = new Literal("send");
							opt.addTerm(new Predicate(dep.getDependee()));
							opt.addTerm(new Predicate("achieve"));
							opt.addTerm(relevant.getTerm(0));
							
							if (!ag.optContains(opt)) {
								ag.addOpt(opt);

								AJPFLogger.fine(getLoggerName(),
										"Adding option: " + opt);

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
