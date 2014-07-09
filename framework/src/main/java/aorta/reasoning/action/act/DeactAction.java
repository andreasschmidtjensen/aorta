package aorta.reasoning.action.act;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.AORTAException;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.util.Qualifier;
import aorta.kr.QueryEngine;
import aorta.reasoning.action.ActAction;
import aorta.tracer.Tracer;
import aorta.ts.TransitionNotPossibleException;
import aorta.logging.Logger;

public class DeactAction extends ActAction {
	public static final Logger logger = Logger.getLogger(DeactAction.class.getName());
	
//	private Term agent;
	private Term role;
	
	public DeactAction(Term role) {
//		this.agent = agent;
		this.role = role;
	}

	@Override
	protected AgentState executeAction(QueryEngine engine, AgentState state)
			throws AORTAException {
		AgentState newState = state;
		
		final Term agent = Term.createTerm(state.getAgent().getName());
		final Term clonedRoleTerm = Term.createTerm(role.toString());
				
		Term reaDef = state.getAgent().getAorta().getOrganizationalSpecification().getEnactment(agent, clonedRoleTerm);
		
		Term term = Qualifier.qualifyTerm(reaDef, KBType.ORGANIZATION.getType());
		MentalState ms = state.getMentalState();

		SolveInfo result = engine.solve(ms, term);
		
		logger.fine("Attempting to deact: " + result.isSuccess());
		
		if (result.isSuccess()) {
			
			try {
				state.addBindings(result.getBindingVars());
			} catch (NoSolutionException ignore) {
				// not thrown because of isSuccess
			}
			
			Term qualified = Qualifier.qualifyTerm(reaDef, KBType.ORGANIZATION.getType());
			engine.unify(ms, qualified, state.getBindings());
			
			if (!qualified.isGround()) {
				throw new AORTAException("Cannot execute action: term '" + qualified + "' is not ground.");
			} else if (qualified instanceof Struct) {
				newState = state.clone();
				newState.removeTerm(engine, (Struct) qualified);
				
				logger.info("[" + state.getAgent().getName() + "] Executing action: deact(" + qualified + ")");
				Tracer.queue(state.getAgent().getName(), "deact(" + qualified + ")");
			} else {
				throw new AORTAException("X in deact(X) must be a Struct (was " + qualified.getClass() + ")");
			}
		} else {
			throw new TransitionNotPossibleException();
		}
		
		return newState;
	}
	
	@Override
	public String toString() {
		return "deact(" + role + ")";
	}

}
