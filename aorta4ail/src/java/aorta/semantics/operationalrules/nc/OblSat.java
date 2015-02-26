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
public class OblSat extends AortaOSRule {

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
			
			if (ag.belContains(objective) && !ag.belContains(deadline)) {
				it.remove();
				
				ag.delOrg(n.getAsLiteral());
				
				Literal optObj = new Literal("obj");
				optObj.addTerm(objective);
				
				Literal nLit = n.getAsLiteral();
				Literal optObl = new Literal("norm");
				optObl.addTerm(nLit.getTerm(1));
				optObl.addTerm(nLit.getTerm(2));
				optObl.addTerm(nLit.getTerm(3));
				optObl.addTerm(nLit.getTerm(4));
				
				ag.delOpt(optObj);
				ag.delOpt(optObl);
				
				ag.aortaChanged();
			}
		}
	}
	
}
