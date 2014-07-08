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
import aorta.kr.util.Qualifier;
import aorta.reasoning.action.ActAction;
import aorta.tracer.Tracer;
import aorta.ts.TransitionNotPossibleException;
import java.util.logging.Logger;

public class DropAction extends ActAction {
	
	private static final Logger logger = Logger.getLogger(DropAction.class.getName());
	
//	private Term agent;
	private Term objective;
	
	public DropAction(Term objective) {
//		this.agent = agent;
		this.objective = objective;
	}

	@Override
	protected AgentState executeAction(QueryEngine engine, AgentState state) throws AORTAException {
		AgentState newState = state;
		
		final Term clonedObjTerm = Term.createTerm(objective.toString());
				
		// Check that it is already a goal
		Term goalTerm = Qualifier.qualifyTerm(clonedObjTerm, KBType.GOAL.getType(), true);
		MentalState ms = state.getMentalState();
		SolveInfo result = engine.solve(ms, goalTerm);
		
		logger.fine("Attempting to drop: " + result.isSuccess());
		if (result.isSuccess()) {
			try {
				state.addBindings(result.getBindingVars());
			} catch (NoSolutionException ignore) {
				// not thrown because of isSuccess
			}
			
			engine.unify(ms, clonedObjTerm, state.getBindings());
			
			if (!clonedObjTerm.isGround()) {
				throw new AORTAException("Cannot execute action: term '" + clonedObjTerm + "' is not ground.");
			} else {
				Struct asStruct;
                if (clonedObjTerm instanceof Var) {
                    asStruct = (Struct) clonedObjTerm.getTerm();
                } else {
                    asStruct = (Struct) clonedObjTerm;
                }
				
				newState = state.clone();
				newState.removeTerm(engine, asStruct, KBType.GOAL);
				logger.info("[" + state.getAgent().getName() + "] Executing action: drop(" + asStruct + ")");
				Tracer.queue(state.getAgent().getName(), "drop(" + asStruct + ")");
			}
		} else {
			throw new TransitionNotPossibleException();
		}
		
		return newState;
	}
	
	@Override
	public String toString() {
		return "drop(" + objective + ")";
	}


}
