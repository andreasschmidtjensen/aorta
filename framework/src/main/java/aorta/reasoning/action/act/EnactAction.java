package aorta.reasoning.action.act;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
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
import aorta.reasoning.action.ActAction;
import aorta.tracer.Tracer;
import aorta.ts.TransitionNotPossibleException;
import java.util.logging.Logger;

public class EnactAction extends ActAction {
	
	public static final Logger logger = Logger.getLogger(EnactAction.class.getName());
	
//	private Term agent;
	private Term role;
	
	public EnactAction(Term role) {
//		this.agent = agent;
		this.role = role;
	}

	@Override
	protected AgentState executeAction(QueryEngine engine, AgentState state) throws AORTAException {
		AgentState newState = state;
		
		MentalState ms = state.getMentalState();

		final Term clonedRoleTerm = Term.createTerm(role.toString());
		final Term agent = Term.createTerm(state.getAgent().getName());
				
        final OrganizationalLanguage organizationalSpecification = ms.getAgent().getAorta().getOrganizationalSpecification();
        Term roleDef = organizationalSpecification.getRole(clonedRoleTerm);
        Term reaDef = organizationalSpecification.getEnactment(agent, clonedRoleTerm);
        
		Var conflictVar = new Var("C" + System.currentTimeMillis());
        Term reaOtherDef = organizationalSpecification.getEnactment(agent, conflictVar);
        Term conflictDef = organizationalSpecification.getConflict(clonedRoleTerm, conflictVar);
        
		Term t1 = Qualifier.qualifyTerm(roleDef, KBType.ORGANIZATION.getType());
		Term t2 = Qualifier.qualifyTerm(reaDef, KBType.ORGANIZATION.getType());
		Term t3 = Qualifier.qualifyTerm(reaOtherDef, KBType.ORGANIZATION.getType());
		Term t4 = Qualifier.qualifyTerm(conflictDef, KBType.ORGANIZATION.getType());

		Term test = Term.createTerm(t1 + ", \\+ " + t2 + ", \\+(" + t3 + "," + t4 + ")");
		engine.unify(ms, test, state.getBindings());

		SolveInfo result = engine.solve(ms, test);
		
		logger.fine("Attempting to enact: " + result.isSuccess());
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
				newState.insertTerm(engine, (Struct) qualified);
				logger.info("[" + state.getAgent().getName() + "] Executing action: enact(" + qualified + ")");
				Tracer.queue(state.getAgent().getName(), "enact(" + qualified + ")");
			} else {
				throw new AORTAException("X in enact(X) must be a Struct (was " + qualified.getClass() + ")");
			}
		} else {
			throw new TransitionNotPossibleException();
		}
		
		return newState;
	}
	
	@Override
	public String toString() {
		return "enact(" + role + ")";
	}

}
