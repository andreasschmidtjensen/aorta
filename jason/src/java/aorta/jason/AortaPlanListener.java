/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.AortaAgent;
import jason.asSyntax.Plan;
import jason.asSyntax.Trigger;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class AortaPlanListener implements PlanListener {
	private AortaAgent agent;

	public AortaPlanListener(AortaAgent agent) {
		this.agent = agent;
	}
	
	@Override
	public void planAdded(Plan plan, boolean first){
		if (isGoalAchievement(plan)) {
			Term term = TermConverter.convertToTerm(plan.getTrigger().getLiteral());
			if (term instanceof Struct) {
				Struct capability = (Struct) term;
				agent.getState().getExternalAgent().addCapability(capability);			
			}
		}
	}
	
	@Override
	public void planRemoved(Plan plan, boolean last){
		if (isGoalAchievement(plan)) {
			Term term = TermConverter.convertToTerm(plan.getTrigger().getLiteral());
			if (term instanceof Struct) {
				Struct capability = (Struct) term;
				agent.getState().getExternalAgent().removeCapability(capability);			
			}
		}
	}

	private boolean isGoalAchievement(Plan plan) {
		return plan.getTrigger().getType() == Trigger.TEType.achieve && plan.getTrigger().getOperator() == Trigger.TEOperator.add;
	}
}
