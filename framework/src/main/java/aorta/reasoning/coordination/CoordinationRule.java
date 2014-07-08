/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.reasoning.coordination;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AgentState;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.util.Qualifier;
import aorta.msg.OutgoingOrganizationalMessage;
import aorta.reasoning.action.coord.SendAction;
import aorta.reasoning.fml.Formula;
import aorta.tracer.Tracer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asj
 */
public class CoordinationRule {
	
	private static final Logger logger = Logger.getLogger(CoordinationRule.class.getName());
	
	private List<ChangeTerm> changeCondition;
	private Formula recipientsQuery;
	private SendAction sendAction;

	public CoordinationRule(List<ChangeTerm> mentalStateChanges, Formula recipientsQuery, SendAction sendAction) {
		this.changeCondition = mentalStateChanges;
		this.recipientsQuery = recipientsQuery;
		this.sendAction = sendAction;
	}
	
	public AgentState execute(QueryEngine engine, AgentState state, MentalStateChange msc) {
		AgentState result = state;
		MentalState ms = result.getMentalState();

		List<Var> vars = msc.contains(engine, ms, changeCondition);
		if (vars != null) {
			logger.log(Level.FINE, "Vars matching condition: " + vars);
			
			Term recipientsTerm = Qualifier.qualifyGoal(recipientsQuery);
			engine.unify(ms, recipientsTerm, vars);
			
			logger.log(Level.FINE, "Recipients must match: " + recipientsTerm);
	
			Term msg = Qualifier.qualifyFormula(sendAction.getMessage(), true);
			msg = Term.createTerm(msg.toString());
			engine.unify(result.getMentalState(), msg, vars);
			
			int recipients = 0;
			List<SolveInfo> recipientsInfo = engine.findAll(ms.getProlog(), recipientsTerm);
			Tracer.queue(state.getAgent().getName(), "(Coord) " + changeCondition + " : [" + recipientsTerm + "] => send([");
			for (SolveInfo rcpInfo : recipientsInfo) {
				if (result == state) {
					 result = state.clone();
				}
				
				try {
					Term recipient = rcpInfo.getVarValue(sendAction.getRecipient().getOriginalName());
					
					// don't send to self
					if (!recipient.toString().equals(state.getAgent().getName())) {
						logger.log(Level.INFO, "Sending " + msg + " to " + recipient);
						result.sendMessage(new OutgoingOrganizationalMessage(recipient, Term.createTerm(msg.toString())));
						
						Tracer.queue(state.getAgent().getName(), recipient.toString());
						
						recipients++;
					}
				} catch (NoSolutionException ex) {
				}
			}
			
			if (recipients > 0) {
				logger.info("[" + state.getAgent().getName() + "] Executing coordination: " + changeCondition + " : [" + recipientsTerm + "]");
				Tracer.queue(state.getAgent().getName(), "], " + msg.toString() + ")\n");
				Tracer.trace(state.getAgent().getName());
			} else {
				Tracer.clearQueue(state.getAgent().getName());
			}
		}
		
		return result;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		for (int i = 0; i < changeCondition.size(); i++) {
			if (i > 0) {
				s.append(",");
			}
			s.append(changeCondition.get(i));
		}
		s.append("] : [");
		s.append(recipientsQuery.toString());
		s.append("] => ");
		s.append(sendAction.toString());	
		
		return s.toString();
	}
	
}
