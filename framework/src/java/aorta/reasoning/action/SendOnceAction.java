/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.reasoning.action;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.util.FormulaQualifier;
import aorta.msg.OutgoingOrganizationalMessage;
import aorta.reasoning.fml.BeliefFormula;
import aorta.tracer.Tracer;
import aorta.ts.TransitionNotPossibleException;
import java.util.ArrayList;
import java.util.List;
import aorta.logging.Logger;
import aorta.reasoning.fml.Formula;
import aorta.ts.rules.ActionExecution;

/**
 *
 * @author ascje
 */
public class SendOnceAction extends SendAction {

	private static final Logger logger = Logger.getLogger(SendOnceAction.class.getName());

	public SendOnceAction(Var recipient, Formula message) {
		super(recipient, message);
	}
	
	@Override
	public String toString() {
		return "sendonce(" + recipients + ", " + message + ")";
	}

	@Override
	protected void sendMessage(List<Term> recipientList, Term message, AgentState state) throws TransitionNotPossibleException {
		List<Term> newRecipients = new ArrayList<>();
		for (Term recipient : recipientList) {
			// message should only be send once to each recipient
			Struct sent = new Struct("sent", recipient, message);
			SolveInfo info = state.getMentalState().solve(new BeliefFormula(sent));
			if (!info.isSuccess()) {
				newRecipients.add(recipient);
				state.insertInMentalState(FormulaQualifier.qualifyStruct(sent, KBType.BELIEF));
				state.notifyTermAdded(new ActionExecution().getName(), sent);
			}
		}
		
		if (newRecipients.isEmpty()) {
			throw new TransitionNotPossibleException();
		} else {
			OutgoingOrganizationalMessage msg = new OutgoingOrganizationalMessage(recipientList, message);
			state.sendMessage(msg);

			logger.finer("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Executing action: sendonce(" + recipientList + "," + message + ")");
			Tracer.queue(state.getAgent().getName(), "sendonce(" + recipientList + "," + message + ")");
		}
	}
	
}
