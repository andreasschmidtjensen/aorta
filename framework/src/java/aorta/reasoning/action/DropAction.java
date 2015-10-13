package aorta.reasoning.action;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AORTAException;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.util.FormulaQualifier;
import aorta.tracer.Tracer;
import aorta.logging.Logger;
import aorta.ts.rules.ActionExecution;

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
	protected AgentState executeAction(Term option, AgentState state) throws AORTAException {
		AgentState newState = state;
		
		Term clonedObjTerm = Term.createTerm(objective.toString());
				
		// Check that it is already a goal
		Term goalTerm = FormulaQualifier.qualifyTerm(clonedObjTerm, KBType.GOAL.getType(), true);
		MentalState ms = state.getMentalState();
		SolveInfo result = ms.solve(goalTerm);
		
		logger.finest("Attempting to drop: " + result.isSuccess());
		if (result.isSuccess()) {
			state.addBindings(result);
			
			clonedObjTerm = ms.unify(clonedObjTerm, state.getBindings());
			
			if (!clonedObjTerm.isGround()) {
				throw new AORTAException("Cannot execute action: term '" + clonedObjTerm + "' is not ground.");
			} else {
				Struct asStruct;
                if (clonedObjTerm instanceof Var) {
                    asStruct = (Struct) clonedObjTerm.getTerm();
                } else {
                    asStruct = (Struct) clonedObjTerm;
                }
				
				ActionExecution tr = new ActionExecution();
				tr.remove(newState, asStruct, KBType.GOAL);
				logger.finer("[" + state.getAgent().getName() + "] Executing action: drop(" + asStruct + ")");
				Tracer.queue(state.getAgent().getName(), "drop(" + asStruct + ")");
			}
		} else {
			return null;
		}
		
		return newState;
	}
	
	@Override
	public String toString() {
		return "drop(" + objective + ")";
	}


}
