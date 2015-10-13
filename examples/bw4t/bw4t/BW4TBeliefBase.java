/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bw4t;

import aorta.jason.AortaBB;
import jason.asSemantics.Unifier;
import jason.asSyntax.Literal;
import jason.asSyntax.Term;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author ascje
 */
public class BW4TBeliefBase extends AortaBB {

	private Map<String, Literal> uniqueBels = new HashMap<>();
	private Unifier u = new Unifier();

	public BW4TBeliefBase() {
		uniqueBels.put("at", Literal.parseLiteral("at(key,_)"));
		uniqueBels.put("holding", Literal.parseLiteral("holding(key,_)"));
	}

	@Override
	public boolean add(Literal bel, boolean addInEnd) {
		Literal kb = uniqueBels.get(bel.getFunctor());
			boolean remove = false;
		if (kb != null && kb.getArity() == bel.getArity()) {

			// find the bel in BB and eventually remove it
			u.clear();
			Literal linbb = null;

			Iterator<Literal> relevant = getCandidateBeliefs(bel, null);
			if (relevant != null) {
				while (relevant.hasNext() && !remove) {
					linbb = relevant.next();

					boolean equals = true;
					for (int i = 0; i < kb.getArity(); i++) {
						Term kbt = kb.getTerm(i);
						if (!kbt.isVar()) {
							if (!u.unifies(bel.getTerm(i), linbb.getTerm(i))) {
								equals = false;
								break;
							}
						}
					}
					if (equals) {
						remove = true;
					}
				}
			}
			if (remove) {
				remove(linbb);
			}
		}
		return super.add(bel, addInEnd);
	}
}
