/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics.operationalrules.nc;

import ail.semantics.AILAgent;
import ail.syntax.Literal;
import ajpf.util.AJPFLogger;
import aorta.semantics.AortaAgent;
import aorta.semantics.operationalrules.AortaOSRule;
import aorta.syntax.metamodel.Metamodel;
import aorta.syntax.metamodel.Norm;
import aorta.syntax.metamodel.Violation;
import java.util.Iterator;

/**
 *
 * @author asj
 */
public class OblViol extends AortaOSRule {

	@Override
	public boolean checkPreconditions(AILAgent a) {
		AortaAgent ag = (AortaAgent)a;
		return !ag.getMetamodel().getNorms().isEmpty();
	}

	@Override
	public void apply(AILAgent a) {
		AortaAgent ag = (AortaAgent) a;
		Metamodel mm = ag.getMetamodel();
		
		Iterator<Norm> it = mm.getNorms().iterator();
		while (it.hasNext()) {
			Norm n = it.next();
			// not correct kind of norm
			if (!n.getDeon().equals(Norm.OBLIGATION)) {
				continue;
			}
			
			Literal objective = n.getObjective();
			Literal deadline = n.getDeadline();
			
			if (!ag.belContains(objective) && ag.belContains(deadline)) {
				Violation viol = new Violation(n.getAgent(), n.getRole(), n.getDeon(), n.getObjective());
				
				if (!mm.getViolations().contains(viol)) {
					mm.addViolation(viol);
					ag.addOrg(viol.getAsLiteral());	
					
					AJPFLogger.fine(getLoggerName(), "Adding violation " + viol.getAsLiteral() + " for " + ag.getAgName());

					ag.aortaChanged();
				}
			}
		}
	}
	
}
