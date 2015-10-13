/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bw4t;

import aorta.jason.AortaJasonAgent;
import jason.asSemantics.Event;
import jason.asSemantics.Intention;
import jason.asSemantics.Unifier;
import jason.asSyntax.Atom;
import jason.asSyntax.Literal;
import jason.asSyntax.LiteralImpl;
import jason.asSyntax.PredicateIndicator;
import jason.asSyntax.Term;
import jason.asSyntax.Trigger;
import jason.asSyntax.VarTerm;
import jason.bb.BeliefBase;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author asj
 */
public class BW4TAgent extends AortaJasonAgent {
		
	@Override
	public void buf(List<Literal> percepts) {
		if (percepts == null) {
			return;
		}
		
		Iterator<Literal> it = percepts.iterator();
		while(it.hasNext()) {
			Literal mentalNote = null;
			
			Literal l = it.next();
			switch (l.getFunctor()) {
				// 1: SEND ONCE
				case "navpoint":
				case "sequence":
				case "place":
				case "ownName":
				case "robot":
					mentalNote = (Literal) l.clone();
					break;
				// 2: SEND ON CHANGE
				case "at":
					String name = getTS().getUserAgArch().getAgName();
					Term loc = l.getTerm(0);
					
					// remove all percepts of agents at that location
					Literal oldAt = new LiteralImpl("at");
					oldAt.addTerm(new VarTerm("A"));
					oldAt.addTerm(loc);
					Iterator<Literal> oldAts = getBB().getCandidateBeliefs(oldAt, null);
					List<Literal> toRemove = new ArrayList<>();
					if (oldAts != null) {
						while (oldAts.hasNext()) {
							Literal next = oldAts.next();
							if (new Unifier().unifies(oldAt, next)) {
								toRemove.add(next);
							}
						}
					}
					for (Literal li : toRemove) {
						getBB().remove(li);
					}
					
					// perceive all agents at same location
					Locations.set(name, loc);
					for (String agName : Locations.getAgsAtLocation(l.getTerm(0))) {
						if (!agName.equals(name)) {
							Literal at = new LiteralImpl("at");
							at.addTerm(new Atom(agName));
							at.addTerm(loc);
							getBB().add(at);
						}
					}
					
					
				case "sequenceIndex":
				case "state": 
				{
					mentalNote = (Literal) l.clone();
					getBB().abolish(new PredicateIndicator(l.getFunctor(), l.getArity()));
					
					if (triggers.contains(l.getFunctor())) {
						Trigger te = new Trigger(Trigger.TEOperator.del, Trigger.TEType.belief, l);
						ts.getC().addEvent(new Event(te, Intention.EmptyInt));
					}
					break;
				}
				// 3: SEND ON CHANGE WITH NEGATION
				case "in":
				case "atBlock":
				case "occupied":
				case "holding":
				case "player":
					mentalNote = (Literal) l.clone();
					break;
				case "not": 
				{
					Literal wrapped = (Literal) l.getTerm(0);
					Iterator<Literal> candidates = getBB().getCandidateBeliefs(new PredicateIndicator(wrapped.getFunctor(), wrapped.getArity()));
					if (candidates != null) {
						while (candidates.hasNext()) {
							Literal candidate = candidates.next();
							if (candidate.equalsAsStructure(wrapped)) {
								getBB().remove(candidate);
								
								if (triggers.contains(candidate.getFunctor())) {
									Trigger te = new Trigger(Trigger.TEOperator.del, Trigger.TEType.belief, candidate);
									ts.getC().addEvent(new Event(te, Intention.EmptyInt));
								}
								break;
							}
						}
					}
					it.remove();
					break;
				}
				// 4: SEND ALWAYS
				case "color":
					break;
				// 5: IGNORE
				case "position":
				case "location":
					it.remove();
					break;
			}
			
			if (mentalNote != null) {
				mentalNote.addAnnot(BeliefBase.TSelf);
				it.remove();
				getBB().add(mentalNote);

				if (triggers.contains(mentalNote.getFunctor())) {
					Trigger te = new Trigger(Trigger.TEOperator.add, Trigger.TEType.belief, mentalNote);
					ts.updateEvents(new Event(te, Intention.EmptyInt));
				}
			}	
		}
		
		
		super.buf(percepts);
	}
		
	private static final Set<String> triggers = new HashSet<>();
	static {
		triggers.add("state");
		triggers.add("at");
	}

}
