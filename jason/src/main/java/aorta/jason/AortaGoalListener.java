/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason;

import alice.tuprolog.Struct;
import aorta.AortaAgent;
import jason.asSemantics.Event;
import jason.asSemantics.GoalListener;
import jason.asSyntax.Trigger;
import java.util.logging.Logger;

/**
 *
 * @author asj
 */
public class AortaGoalListener implements GoalListener {

	private static final Logger logger = Logger.getLogger(AortaGoalListener.class.getName());
	
	private AortaAgent agent;

	public AortaGoalListener(AortaAgent agent) {
		this.agent = agent;
	}
	
	@Override
	public void goalStarted(Event goal) {
		Struct fromLiteral = TermConverter.fromLiteral(goal.getTrigger().getLiteral());
		agent.getState().getExternalAgent().addGoal(fromLiteral);
		logger.fine("goalStarted=" + goal);
	}

	@Override
	public void goalFinished(Trigger goal) {
		logger.fine("goalFinished=" + goal);
		agent.getState().getExternalAgent().removeGoal(TermConverter.fromLiteral(goal.getLiteral()));
	}

	@Override
	public void goalFailed(Trigger goal) {
		logger.fine("goalFailed=" + goal);
		agent.getState().getExternalAgent().removeGoal(TermConverter.fromLiteral(goal.getLiteral()));
	}

	@Override
	public void goalSuspended(Trigger goal, String reason) {
		logger.fine("goalSuspended=" + goal + " (reason=" + reason + ")");
	}

	@Override
	public void goalResumed(Trigger goal) {
		logger.fine("goalResumed=" + goal);
	}
}
