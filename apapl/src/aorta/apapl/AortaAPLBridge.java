/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.apapl;

import alice.tuprolog.Term;
import aorta.AortaBridge;
import aorta.msg.OutgoingOrganizationalMessage;
import apapl.data.APLFunction;
import apapl.data.Literal;
import apapl.messaging.APLMessage;

/**
 *
 * @author asj
 */
public class AortaAPLBridge implements AortaBridge {

	private final AortaAPLModule aortaModule;

	public AortaAPLBridge(AortaAPLModule aortaModule) {
		this.aortaModule = aortaModule;
	}
	
	@Override
	public boolean addBelief(Term belief) {
		Literal lit = TermConverter.aorta2apl(belief);
		aortaModule.getBeliefbase().assertFromAorta(lit);
		return true;
	}

	@Override
	public boolean addReceivedBelief(String sender, Term msg) {
		Literal lit = TermConverter.aorta2apl(msg);
		aortaModule.getBeliefbase().assertFromAorta(lit);
		return true;
	}

	@Override
	public boolean removeBelief(Term belief) {
		Literal lit = TermConverter.aorta2apl(belief);
		lit.setSign(false);
		aortaModule.getBeliefbase().assertFromAorta(lit);
		return true;
	}

	@Override
	public boolean addGoal(Term goal) {
		Literal lit = TermConverter.aorta2apl(goal);
		aortaModule.getGoalbase().assertFromAorta(lit);
		return true;
	}

	@Override
	public boolean addReceivedGoal(String sender, Term msg) {
		Literal lit = TermConverter.aorta2apl(msg);
		aortaModule.getGoalbase().assertFromAorta(lit);
		return true;
	}

	@Override
	public boolean removeGoal(Term goal) {		
		Literal lit = TermConverter.aorta2apl(goal);
		aortaModule.getGoalbase().removedByAorta(lit);
		return true;
	}

	@Override
	public void sendMessage(OutgoingOrganizationalMessage msg) {
		APLMessage aplMessage = new APLMessage();
		aplMessage.setContent((APLFunction) TermConverter.aorta2apl(msg.getMessage()).getBody());
		aplMessage.setSender(aortaModule.getAgentName());
		aplMessage.setPerformative("tell");
		for (Term receiver : msg.getRecipients()) {
			aplMessage.setReceiver(receiver.toString());
		
			aortaModule.getMessenger().sendMessage(aplMessage);
		}
	}
	
}
