/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics.operationalrules.og;

import ail.semantics.AILAgent;
import ail.syntax.Literal;
import ajpf.util.AJPFLogger;
import aorta.semantics.AortaAgent;
import aorta.semantics.operationalrules.AortaOSRule;
import aorta.syntax.metamodel.Metamodel;
import aorta.syntax.metamodel.Violation;

/**
 *
 * @author asj
 */
public class ViolationOpt extends AortaOSRule {

	@Override
	public boolean checkPreconditions(AILAgent a) {
		AortaAgent ag = (AortaAgent)a;		
		return !ag.getMetamodel().getViolations().isEmpty();
	}

	@Override
	public void apply(AILAgent a) {
		AortaAgent ag = (AortaAgent)a;
		Metamodel mm = ag.getMetamodel();
		
		for (Violation viol : mm.getViolations()) {
			Literal n = viol.getAsLiteral();
			
			Literal opt = new Literal("viol");
			opt.addTerm(n.getTerm(0));
			opt.addTerm(n.getTerm(1));
			opt.addTerm(n.getTerm(2));
			opt.addTerm(n.getTerm(3));
					
			if (!ag.optContains(opt)) {
				ag.addOpt(opt);

				AJPFLogger.fine(getLoggerName(), "Adding option: " + opt);
				
				ag.aortaChanged();
			}				
			
		}
	}
	
}
