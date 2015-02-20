/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail;

import ail.semantics.AILAgent;
import ail.syntax.Event;
import ail.syntax.Goal;
import ail.syntax.Intention;
import ail.syntax.Literal;
import ail.syntax.SendAction;
import ail.syntax.annotation.SourceAnnotation;
import ail.util.AILexception;
import ajpf.util.AJPFLogger;
import alice.tuprolog.Term;
import aorta.AortaBridge;
import aorta.msg.OutgoingOrganizationalMessage;

/**
 *
 * @author asj
 */
public class AILBridge implements AortaBridge {

	private static final SourceAnnotation SOURCE_AORTA = new SourceAnnotation(new Literal("aorta"));
	private AILAgent agent;

	public AILBridge(AILAgent agent) {
		this.agent = agent;
	}

	@Override
	public boolean addBelief(Term belief) {
		agent.addBel(TermConverter.toLiteral(belief), SOURCE_AORTA);
		
		return true;
	}

	@Override
	public boolean addReceivedBelief(String sender, Term msg) {
		boolean addBel = addBelief(msg);
		agent.addNewIntention(new Intention(new Event(Event.AILAddition, Event.AILBel, TermConverter.toLiteral(msg)), SOURCE_AORTA));
		agent.tellawake();
		
		for (Intention i: agent.getIntentions()) {
			i.unsuspendFor(TermConverter.toLiteral(msg));
		}
		return addBel;
	}

	@Override
	public boolean removeBelief(Term belief) {
		agent.delBel(TermConverter.toLiteral(belief));
		return true;
	}

	@Override
	public boolean addGoal(Term goal) {
		agent.addNewIntention(new Intention(new Goal(TermConverter.toLiteral(goal), Goal.achieveGoal), SOURCE_AORTA));
		agent.tellawake();
		
		return true;
	}

	@Override
	public boolean addReceivedGoal(String sender, Term msg) {
		return addGoal(msg);
	}

	@Override
	public boolean removeGoal(Term goal) {
		agent.removeGoal(new Goal(TermConverter.toLiteral(goal), Goal.achieveGoal));
		return true;
	}

	@Override
	public void sendMessage(OutgoingOrganizationalMessage msg) {
		AJPFLogger.info(AILBridge.class.getName(), "Sending organizational message: " + msg.getMessage() + " to " + msg.getRecipients());
		for (Term recipient : msg.getRecipients()) {
			try {
				agent.getEnv().executeAction(agent.getAgName(), new SendAction(TermConverter.toLiteral(recipient), 0, TermConverter.toLiteral(msg.getMessage())));
			} catch (AILexception ex) {
				ex.printStackTrace();
			}
		}
	}
}
