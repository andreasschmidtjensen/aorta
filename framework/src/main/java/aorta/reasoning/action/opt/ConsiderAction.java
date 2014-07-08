package aorta.reasoning.action.opt;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AORTAException;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.OrganizationalLanguage;
import aorta.kr.util.Qualifier;
import aorta.reasoning.action.OptAction;
import aorta.tracer.Tracer;
import aorta.ts.TransitionNotPossibleException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsiderAction extends OptAction {
	public static final Logger logger = Logger.getLogger(ConsiderAction.class.getName());

	private Struct option;
	
	public ConsiderAction(Struct option) {
		this.option = option;
	}

	@Override
	protected AgentState executeAction(QueryEngine engine, AgentState state) throws AORTAException {
		AgentState newState = state;
		Struct optionTerm = (Struct) Term.createTerm(option.toString());
		
		MentalState ms = state.getMentalState();
				
		boolean inLang = ms.getAgent().getAorta().getOrganizationalSpecification().inLanguage(optionTerm);		
		
		logger.log(Level.FINE, "Considering: " + optionTerm);
		
		if (inLang) {
			Term qualified = Qualifier.qualifyStruct(optionTerm, KBType.OPTION.getType());
			engine.unify(ms, qualified, state.getBindings());
			
			logger.log(Level.FINE, "Qualified: " + qualified);
			
			boolean considered = engine.solve(ms, Qualifier.qualifyTerm(qualified, KBType.OPTION)).isSuccess();
			logger.log(Level.FINE, "Is already an option: " + considered);
			if (considered || !valid(engine, ms, qualified)) {
				throw new TransitionNotPossibleException();
			} else if (!qualified.isGround()) {
				throw new AORTAException("Cannot execute action: term '" + qualified + "' is not ground.");
			} else if (qualified instanceof Struct) {
				newState = state.clone();
				newState.insertTerm(engine, (Struct) qualified);
				
				logger.info("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Executing action: consider(" + qualified + ")");
				Tracer.queue(state.getAgent().getName(), "consider(" + qualified + ")");
			} else {
				throw new AORTAException("X in consider(X) must be a Struct (was " + qualified.getClass() + ")");
			}
		} else {
			throw new TransitionNotPossibleException();
		}
		
		return newState;
	}
	
	protected boolean valid(QueryEngine engine, MentalState ms, Term term) {
		OrganizationalLanguage lang = ms.getAgent().getAorta().getOrganizationalSpecification();
		
		final Var agentVar = new Var("A");
		final Var roleVar = new Var("R");
		final Var objectiveVar = new Var("O");
		Term rea = lang.getEnactment(agentVar, roleVar);
		Term reaOpt = Qualifier.qualifyTerm(rea, KBType.OPTION);
		
		Term objective = lang.getObjective(objectiveVar);
		Term objectiveOpt = Qualifier.qualifyTerm(objective, KBType.OPTION);
		
		if (reaOpt.match(term)) {
			reaOpt.unify(ms.getProlog(), term);
			
			Term role = lang.getRole((Struct) roleVar.getTerm());
			boolean isRole = engine.solve(ms, Qualifier.qualifyTerm(role, KBType.ORGANIZATION)).isSuccess();
			boolean enacts = engine.solve(ms, Qualifier.qualifyTerm(rea, KBType.ORGANIZATION)).isSuccess();
			
			logger.log(Level.FINE, "Considering roleenactment: isRole=" + isRole + "; enacts=" + enacts);
			return isRole && !enacts;
			
		} else if (objectiveOpt.match(term)) {
			objectiveOpt.unify(ms.getProlog(), term);
			
			boolean isObjective = engine.solve(ms, Qualifier.qualifyTerm(objective, KBType.ORGANIZATION)).isSuccess();
			boolean isBelieved = engine.solve(ms, Qualifier.qualifyTerm(objectiveVar.getTerm(), KBType.BELIEF)).isSuccess();
			boolean isGoal = engine.solve(ms, Qualifier.qualifyTerm(objectiveVar.getTerm(), KBType.GOAL)).isSuccess();
			
			logger.log(Level.FINE, "Considering objective: isObjective=" + isObjective + "; isBelieved=" + isBelieved + "; isGoal=" + isGoal);
			
			return isObjective && !isBelieved && !isGoal;
			
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "consider(" + option + ")";
	}

}
