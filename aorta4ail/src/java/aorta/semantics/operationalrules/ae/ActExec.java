/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics.operationalrules.ae;

import ail.semantics.AILAgent;
import ail.syntax.Literal;
import ail.syntax.Unifier;
import ajpf.util.AJPFLogger;
import aorta.semantics.ActionFunction;
import aorta.semantics.AortaAgent;
import aorta.semantics.AortaRule;
import aorta.semantics.operationalrules.AortaOSRule;
import java.util.Iterator;

/**
 *
 * @author asj
 */
public class ActExec extends AortaOSRule {

	@Override
	public boolean checkPreconditions(AILAgent a) {
		AortaAgent ag = (AortaAgent) a;
		return ag.getOptBB().size() > 0;
	}

	@Override
	public void apply(AILAgent a) {
		AortaAgent ag = (AortaAgent) a;

		rule:
		for (AortaRule rule : ag.getAortaRules()) {
			Literal opt = rule.getOption();
			
			if (Literal.PTrue.equals(opt)) {
				if (checkCondition(ag, rule, new Unifier())) {
					break rule;
				}
			} else {
				Iterator<Literal> optIt = ag.getOptBB().getRelevant(opt);

				opt:
				while (optIt.hasNext()) {
					Unifier optUnif = new Unifier();
					Literal potentialOption = optIt.next();

					if (opt.unifies(potentialOption, optUnif)) {
						if (checkCondition(ag, rule, optUnif)) {
							break rule;
						}
					}
				}
			}
		}
	}

	private boolean checkCondition(AortaAgent ag, AortaRule rule, Unifier unif) {
		Iterator<Unifier> condIt = ag.believes(rule.getCondition(), unif);
		while (condIt.hasNext()) {
			Unifier condUnif = condIt.next();
			if (ActionFunction.executes(ag, rule.getAction(), condUnif)) {
				AJPFLogger.fine(getLoggerName(), "Executed rule: " + rule);
				ag.aortaChanged();
				// only execute one rule
				return true;
			}
		}
		return false;
	}

}
