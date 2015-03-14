/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics;

import ail.mas.DefaultEnvironment;
import ail.semantics.AILAgent;
import ail.syntax.Action;
import ail.syntax.Goal;
import ail.syntax.Intention;
import ail.syntax.Literal;
import ail.syntax.Message;
import ail.syntax.Predicate;
import ail.syntax.PredicatewAnnotation;
import ail.syntax.SendAction;
import ail.syntax.Term;
import ail.syntax.Unifier;
import ail.syntax.annotation.SourceAnnotation;
import ail.util.AILexception;
import ajpf.util.AJPFLogger;
import aorta.semantics.actions.CommitAction;
import aorta.semantics.actions.DeactAction;
import aorta.semantics.actions.DropAction;
import aorta.semantics.actions.EnactAction;
import aorta.syntax.metamodel.Metamodel;
import aorta.syntax.metamodel.Rea;
import aorta.syntax.metamodel.Role;

/**
 *
 * @author asj
 */
public class ActionFunction {

	public static boolean executes(AortaAgent ag, Action action, Unifier unif) {
		Metamodel mm = ag.getMetamodel();
		
		switch (action.getFunctor()) {
			case "enact": {
				EnactAction act = (EnactAction) action;
				Predicate role = act.getRole().clone();
				role.apply(unif);

				if (role.isGround() && isRole(mm, role) && !ag.enacts(role.getFunctor())) {					
					Rea rea = new Rea(ag.getAgName(), role.getFunctor());
					
					Literal rOpt = new Literal("role");
					rOpt.addTerm(role);
					
					Literal sOpt = new Literal("send");
					sOpt.addTerm(Literal.PTrue);
					sOpt.addTerm(new Predicate("tell"));
					sOpt.addTerm(rea.getAsLiteral());
					
					ag.delOpt(rOpt);
					ag.addOpt(sOpt);
					
					ag.addOrg(rea.getAsLiteral());
					ag.doEnact(role.getFunctor());
					
					return true;
				}
				return false;

			}
			case "deact": {
				DeactAction act = (DeactAction) action;
				Predicate role = act.getRole().clone();
				role.apply(unif);

				if (role.isGround() && isRole(mm, role) && !ag.enacts(role.getFunctor())) {					
					Rea rea = new Rea(ag.getAgName(), role.getFunctor());
					
					Literal rOpt = new Literal(false, "role");
					rOpt.addTerm(role);
					
					Literal sOpt = new Literal("send");
					sOpt.addTerm(Literal.PTrue);
					sOpt.addTerm(new Predicate("tell"));
					sOpt.addTerm(rea.getAsLiteral(false));
					
					ag.delOpt(rOpt);
					ag.addOpt(sOpt);
					
					Literal orgRea = new Literal("org");
					orgRea.addTerm(rea.getAsLiteral());
					ag.delOrg(orgRea);
					mm.addRea(rea);
					ag.doDeact(role.getFunctor());
					
					return true;
				}
				return false;
			}
			case "commit": {
				CommitAction act = (CommitAction) action;
				Predicate obj = act.getObj().clone();
				obj.apply(unif);
				
				Literal literal = new Literal(true, new PredicatewAnnotation(obj));
				
				boolean hasBel = ag.belContains(literal);
				boolean hasGoal = ag.goalContains(literal);

				if (obj.isGround() && !hasBel && !hasGoal) {
					Goal goal = new Goal(literal, Goal.achieveGoal);
					AJPFLogger.fine(ActionFunction.class.getName(), "Adding " + goal + " as goal for " + ag.getAgName());
					
					ag.addNewIntention(new Intention(goal, new SourceAnnotation(new Predicate("aorta"))));
					
					return true;
				}
				return false;
			}
			case "drop": {
				DropAction act = (DropAction) action;
				Predicate obj = act.getObj().clone();
				obj.apply(unif);
				
				Literal literal = new Literal(true, new PredicatewAnnotation(obj));
				
				boolean hasGoal = ag.goalContains(literal);
				
				if (obj.isGround() && hasGoal) {
					ag.removeGoal(new Goal(literal, Goal.achieveGoal));					
					return true;
				}
				return false;
			}
			case "send": {
				SendAction act = ((SendAction) action).clone();
				Term content = act.getContent();
				content.apply(unif);
				
				Literal sent = new Literal("sent");
				sent.addTerm(act.getReceiver());
				sent.addTerm(content);
				sent.apply(unif);
				ag.addBel(sent, new SourceAnnotation(new Predicate("self")));
				
				Message msg = act.getMessage(ag.getAgName());
				msg.apply(unif);
			
				if (ag.getEnv() instanceof DefaultEnvironment) {
					DefaultEnvironment env = (DefaultEnvironment)ag.getEnv();				
					AortaAgent otherAg = (AortaAgent)(env).agentmap.get(act.getReceiver().getFunctor());
					otherAg.receiveMessage(msg);
					otherAg.aortaChanged();
					otherAg.tellawake();
					env.notifyListeners(otherAg.getAgName());
				} else {
					try {
						ag.getEnv().executeAction(ag.getAgName(), act);
						return true;
					} catch (AILexception ex) {
						ex.printStackTrace(System.err);
						return false;
					}
				}
			}

		}
		return false;
	}

	private static boolean isRole(Metamodel mm, Predicate role) {
		for (Role r : mm.getRoles()) {
			if (r.getName().equals(role.getFunctor())) {
				return true;
			}
		}
		return false;
	}

}
