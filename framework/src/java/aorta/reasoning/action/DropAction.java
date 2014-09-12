package aorta.reasoning.action;

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
import aorta.tracer.Tracer;
import aorta.ts.TransitionNotPossibleException;
import aorta.logging.Logger;
import aorta.reasoning.action.Action;

public class DropAction extends Action {
	
	private static final Logger logger = Logger.getLogger(DropAction.class.getName());
	
	private Term objective;
	
	public DropAction(Term objective) {
		this.objective = objective;
	}

	public Term getObjective() {
		return objective;
	}

	@Override
	protected AgentState executeAction(QueryEngine engine, Term option, AgentState state) throws AORTAException {
		AgentState newState = state;
		
		final Term clonedObjTerm = Term.createTerm(objective.toString());
				
		// Check that it is already a goal
		Term goalTerm = Qualifier.qualifyTerm(clonedObjTerm, KBType.GOAL.getType(), true);
		MentalState ms = state.getMentalState();
		SolveInfo result = engine.solve(ms, goalTerm);
		
		logger.fine("Attempting to drop: " + result.isSuccess());
		if (result.isSuccess()) {
			state.addBindings(result);
			
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
				
				//XXX: newState = state.clone();;
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
