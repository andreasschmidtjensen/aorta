/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics.operationalrules.nc;

import ail.semantics.AILAgent;
import ail.syntax.Literal;
import ail.syntax.Unifier;
import aorta.semantics.AortaAgent;
import aorta.semantics.operationalrules.AortaOSRule;
import aorta.syntax.metamodel.Metamodel;
import aorta.syntax.metamodel.Norm;
import java.util.Iterator;

/**
 *
 * @author asj
 */
public class ProExp extends AortaOSRule {

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
			if (!n.getDeon().equals(Norm.PROHIBITION)) {
				continue;
			}
			
			Literal prohibition = n.getObjective();
			Literal expiration = n.getDeadline();
			
			if (!ag.belContains(prohibition) && ag.belContains(expiration)) {
				it.remove();
				
				ag.delOrg(n.getAsLiteral());
				
				Literal nLit = n.getAsLiteral();
				Literal optPro = new Literal("norm");
				optPro.addTerm(nLit.getTerm(1));
				optPro.addTerm(nLit.getTerm(2));
				optPro.addTerm(nLit.getTerm(3));
				optPro.addTerm(nLit.getTerm(4));
				
				ag.delOpt(optPro);
				
				ag.aortaChanged();
			}
		}
	}
	
}
