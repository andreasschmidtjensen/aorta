package aorta.reasoning.action.opt;

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
import aorta.kr.util.TermFormatter;
import aorta.reasoning.action.OptAction;
import aorta.reasoning.fml.OrganizationalFormula;
import aorta.tracer.Tracer;
import aorta.ts.TransitionNotPossibleException;
import java.util.logging.Level;
import aorta.logging.Logger;

public class DisregardAction extends OptAction {

	private static final Logger logger = Logger.getLogger(DisregardAction.class.getName());
	
	private Struct option;
	
	public DisregardAction(Struct option) {
		this.option = option;
	}

	@Override
	protected AgentState executeAction(QueryEngine engine, AgentState state) throws AORTAException {
		AgentState newState = state;
		
		MentalState ms = state.getMentalState();
		SolveInfo result = engine.solve(ms, new OrganizationalFormula(option.toString()));
		
		logger.log(Level.FINE, "Attempting to disregard " + option + ": " + result.isSuccess());
		if (result.isSuccess()) {
			try {
				state.addBindings(result.getBindingVars());
			} catch (NoSolutionException ignore) {
				// not thrown because of isSuccess
			}

            
			Term qualified = Qualifier.qualifyStruct(option, KBType.OPTION.getType());
			engine.unify(ms, qualified, state.getBindings());
			
			if (!qualified.isGround()) {
				throw new AORTAException("Cannot execute action: term '" + TermFormatter.toString(qualified) + "' is not ground.");
			} else if (qualified instanceof Struct) {
				newState = state.clone();
				newState.removeTerm(engine, (Struct) qualified);
				
				logger.info("[" + state.getAgent().getName() + "] Executing action: disregard(" + qualified + ")");
				Tracer.queue(state.getAgent().getName(), "disregard(" + qualified + ")");
			} else {
				throw new AORTAException("X in disregard(X) must be a Struct (was " + qualified.getClass() + ")");
			}
		} else {
			throw new TransitionNotPossibleException();
		}
		
		return newState;
	}

	@Override
	public String toString() {
		return "disregard(" + option + ")";
	}

}
