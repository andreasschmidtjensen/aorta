/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics.operationalrules.nc;

import ail.semantics.AILAgent;
import ail.syntax.GBelief;
import ail.syntax.Guard;
import ail.syntax.Literal;
import ail.syntax.Predicate;
import ail.syntax.Unifier;
import ajpf.util.AJPFLogger;
import aorta.semantics.AortaAgent;
import aorta.semantics.operationalrules.AortaOSRule;
import aorta.syntax.metamodel.ConditionalNorm;
import aorta.syntax.metamodel.Metamodel;
import aorta.syntax.metamodel.Norm;
import aorta.syntax.metamodel.Rea;
import java.util.Iterator;

/**
 *
 * @author asj
 */
public abstract class NormAct extends AortaOSRule {
	
	protected abstract String getDeon();
	
	@Override
	public boolean checkPreconditions(AILAgent a) {
		AortaAgent ag = (AortaAgent) a;
		return !ag.getMetamodel().getConditionalNorms().isEmpty();
	}

	@Override
	public void apply(AILAgent a) {		
		AortaAgent ag = (AortaAgent) a;
		Metamodel mm = ag.getMetamodel();
		
		for (ConditionalNorm cn : mm.getConditionalNorms()) {
			// not correct kind of norm
			if (!cn.getDeon().equals(getDeon())) {
				continue;
			}
			
			// no agents enact the role of the norm
			if (mm.getReas(cn.getRole()) == null) {
				continue;
			}
			
			for (Rea rea : mm.getReas(cn.getRole())) {
				Unifier unif = new Unifier();
				if (cn.hasAgentVar()) {
					unif.unifyTerms(new Predicate(rea.getAgName()), cn.getAgentVar());
				}

				Literal condition = cn.getCondition();
				
				if (Literal.PTrue.equals(condition)) {
					activateNorm(ag, cn, unif, rea, mm);
					
				} else {
					Iterator<Literal> relevant = ag.getBB().getRelevant(condition);
					while (relevant.hasNext()) {
						Literal literal = relevant.next();
						
						if (condition.unifies(literal, unif)) {

							if (activateNorm(ag, cn, unif, rea, mm)) {
								break;
							}
						}
					}
				}
			}
		}
		
	}

	private boolean activateNorm(AortaAgent ag, ConditionalNorm cn, Unifier unif, Rea rea, Metamodel mm) {
		Literal objective = cn.getObjective().clone();
		objective.apply(unif);
		
		Literal deadline = cn.getDeadline().clone();
		deadline.apply(unif);
		
		Norm norm = new Norm(rea.getAgName(), cn.getRole(), cn.getDeon(), objective, deadline);
		
		if (!ag.belContains(objective) && !mm.getNorms().contains(norm)) {
			mm.addNorm(norm);

			ag.addOrg(norm.getAsLiteral());
		
			AJPFLogger.fine(getLoggerName(), "Norm activated: " + norm.getAsLiteral() + " for " + ag.getAgName());
			
			ag.aortaChanged();			
			return true;
		}
		
		return false;
	}
	
}
