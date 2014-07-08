/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.reasoning.action.coord;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AORTAException;
import aorta.AgentState;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.msg.OutgoingOrganizationalMessage;
import aorta.reasoning.action.ActAction;
import aorta.reasoning.fml.Formula;
import aorta.tracer.Tracer;
import aorta.ts.TransitionNotPossibleException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author ascje
 */
public class SendAction extends ActAction {

	private static final Logger logger = Logger.getLogger(SendAction.class.getName());

	protected Var recipients;
	protected Formula message;

	public SendAction(Var recipient, Formula message) {
		this.recipients = recipient;
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "send(" + recipients + ", " + message + ")";
	}

	public Formula getMessage() {
		return message;
	}

	public Var getRecipient() {
		return recipients;
	}

	@Override
	protected AgentState executeAction(QueryEngine engine, AgentState state) throws AORTAException {
		AgentState newState = state;
		
		MentalState ms = state.getMentalState();
		
		final Term clonedMsgTerm = Term.createTerm(message.toString());
		
		engine.unify(ms, clonedMsgTerm, state.getBindings());
		
		Term recipientsTerm = new Var(recipients.getOriginalName());
		engine.unify(ms, recipientsTerm, state.getBindings());
		
		List<Term> rcpList = new ArrayList<>();
		if (recipientsTerm.getTerm().isList()) {
			Struct lt = (Struct) recipientsTerm.getTerm();
			Iterator<? extends Term> it = lt.listIterator();
			while (it.hasNext()) {
				rcpList.add(it.next());
			}		
		} else {
			rcpList.add(recipientsTerm.getTerm());
		}
		
		if (!clonedMsgTerm.isGround()) {
			throw new AORTAException("Cannot execute action: term '" + clonedMsgTerm + "' is not ground.");
		} else {
			newState = state.clone();
			
			sendMessage(rcpList, clonedMsgTerm, newState);
		}
		
		return newState;
	}

	protected void sendMessage(List<Term> recipientList, Term message, AgentState state) throws TransitionNotPossibleException {
		OutgoingOrganizationalMessage msg = new OutgoingOrganizationalMessage(recipientList, message);
		state.sendMessage(msg);
		
		logger.info("[" + state.getAgent().getName() + "] Executing action: send(" + recipientList + "," + message + ")");
		Tracer.queue(state.getAgent().getName(), "send(" + recipientList + "," + message + ")");
	}
	
}
