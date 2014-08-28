/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason;

import alice.tuprolog.Term;
import aorta.AortaBridge;
import aorta.msg.OutgoingOrganizationalMessage;
import jason.ReceiverNotFoundException;
import jason.RevisionFailedException;
import jason.asSemantics.Event;
import jason.asSemantics.Intention;
import jason.asSemantics.Message;
import jason.asSyntax.Atom;
import jason.asSyntax.Literal;
import jason.asSyntax.Trigger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asj
 */
public class AortaJasonBridge implements AortaBridge {

	private static final Logger logger = Logger.getLogger(AortaJasonBridge.class.getName());
	
	private AortaAgentArch agentArch;

	public AortaJasonBridge(AortaAgentArch agentArch) {
		this.agentArch = agentArch;
	}
	
	@Override
	public boolean addBelief(Term belief) {
		boolean added = false;
		try {
			Literal jasonBel = TermConverter.toLiteral(belief);
			added = agentArch.getAortaJasonAgent().addBel(jasonBel);
			logger.log(Level.FINE, "(" + agentArch.getAgName() + "): +" + jasonBel + " (was added: " + added + ")");
		} catch (RevisionFailedException ex) {
			logger.log(Level.SEVERE, "Could not add Aorta-belief", ex);
		}
		return added;
	}

	@Override
	public boolean addReceivedBelief(String sender, Term msg) {
		boolean added = false;
		try {
			Literal jasonMsg = TermConverter.toLiteral(msg);
			jasonMsg.addSource(new Atom(sender));
			jasonMsg.addAnnot(AortaBB.SILENT);
			added = agentArch.getAortaJasonAgent().addBel(jasonMsg);
			
			logger.log(Level.FINE, "(" + agentArch.getAgName() + "): message " + jasonMsg + " from " + sender + " (was added: " + added + ")");
		} catch (RevisionFailedException ex) {
			logger.log(Level.SEVERE, "Could not add Aorta-msg", ex);
		}
		return added;
	}
	
	@Override
	public boolean removeBelief(Term belief) {
		boolean dropped = false;
		try {
			logger.log(Level.FINE, "(" + agentArch.getAgName() + "): -" + belief);
			Literal jasonBel = TermConverter.toLiteral(belief);
			agentArch.getAortaJasonAgent().delBel(jasonBel);

			List<Event> toRemove = new ArrayList<>();
			Iterator<Event> it = agentArch.getTS().getC().getEventsPlusAtomic();
			while (it.hasNext()) {
				Event e = it.next();
				Literal triggerLiteral = (Literal) e.getTrigger().getLiteral().clone();
				triggerLiteral.clearAnnots();
				e.getTrigger().getLiteral().clearAnnots();

				if (e.getTrigger().isAddition() && e.getTrigger().getLiteral().equals(jasonBel)) {
					toRemove.add(e);
				}
			}

			for (Event e : toRemove) {
				dropped = dropped || agentArch.getTS().getC().removeEvent(e);
				logger.log(Level.INFO, "(" + agentArch.getAgName() + "): Dropping event (" + e + "): " + dropped);

				// create goal deletion event
				Trigger te = new Trigger(Trigger.TEOperator.del, Trigger.TEType.belief, e.getTrigger().getLiteral());
				agentArch.getTS().updateEvents(new Event(te, Intention.EmptyInt));
			}
		} catch (RevisionFailedException ex) {
			logger.log(Level.SEVERE, "Could not remove Aorta-belief", ex);
		}
		return dropped;
	}

	@Override
	public boolean addGoal(Term goal) {
		logger.log(Level.FINE, "(" + agentArch.getAgName() + "): +!" + goal);
		Literal jasonGoal = TermConverter.toLiteral(goal);
		agentArch.getTS().getC().addAchvGoal(jasonGoal, Intention.EmptyInt);
		return true;
	}

	@Override
	public boolean addReceivedGoal(String sender, Term msg) {
		logger.log(Level.FINE, "(" + agentArch.getAgName() + "): message " + msg + " from " + sender + " was added as goal!");
		Literal jasonGoal = TermConverter.toLiteral(msg);
		jasonGoal.addSource(new Atom(sender));
		jasonGoal.addAnnot(AortaBB.SILENT);
		agentArch.getTS().getC().addAchvGoal(jasonGoal, Intention.EmptyInt);
		return true;
	}

	@Override
	public boolean removeGoal(Term goal) {
		boolean dropped = false;
		
		Literal goalLiteral = TermConverter.toLiteral(goal);
		logger.log(Level.FINE, "(" + agentArch.getAgName() + "): -!" + goal);

		List<Event> toRemove = new ArrayList<>();
		Iterator<Event> it = agentArch.getTS().getC().getEventsPlusAtomic();
		while (it.hasNext()) {
			Event e = it.next();
			Literal triggerLiteral = (Literal) e.getTrigger().getLiteral().clone();
			triggerLiteral.clearAnnots();
			e.getTrigger().getLiteral().clearAnnots();
			
			if (e.getTrigger().isGoal() && e.getTrigger().getLiteral().equals(goalLiteral)) {
				toRemove.add(e);
			}
		}
		
		for (Event e : toRemove) {
			dropped = dropped || agentArch.getTS().getC().removeEvent(e);
			logger.log(Level.INFO, "(" + agentArch.getAgName() + "): Dropping event (" + e + "): " + dropped);
			
			// create goal deletion event
			Trigger te = new Trigger(Trigger.TEOperator.del, Trigger.TEType.achieve, e.getTrigger().getLiteral());
			agentArch.getTS().updateEvents(new Event(te, Intention.EmptyInt));
		}
		
		return dropped;
	}

	@Override
	public void sendMessage(OutgoingOrganizationalMessage msg) {
		for (Term agentName : msg.getRecipients()) {
			Message outMsg = new Message("tell", null, agentName.toString(), TermConverter.toLiteral(msg.getMessage()));
			try {
				agentArch.sendMsg(outMsg);
			} catch (ReceiverNotFoundException ex) {
				logger.log(Level.SEVERE, "Could not send message: " + msg, ex);
			}
		}
	}

}
