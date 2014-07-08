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
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommitAction extends ActAction {

	private static final Logger logger = Logger.getLogger(CommitAction.class.getName());
//	private Term agent;
	private Term objective;

	public CommitAction(Term objective) {
//		this.agent = agent;
		this.objective = objective;
	}

	@Override
	protected AgentState executeAction(QueryEngine engine, AgentState state) throws AORTAException {
		AgentState newState = state;
		
		MentalState ms = state.getMentalState();
		
		final Term clonedObjTerm = Term.createTerm(objective.toString());
		
		String bef = clonedObjTerm.toString();
		engine.unify(ms, clonedObjTerm, state.getBindings());
	
		Term belTerm = Qualifier.qualifyTerm(clonedObjTerm, KBType.BELIEF);
		Term goalTerm = Qualifier.qualifyTerm(clonedObjTerm, KBType.GOAL);

		Term test = Term.createTerm("\\+ " + belTerm + ", \\+ " + goalTerm);

		SolveInfo result = engine.solve(ms, test);
 
		logger.log(Level.FINE, "Attempting to commit: " + result.isSuccess());
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
				newState = state.clone();
				Struct asStruct;
				if (clonedObjTerm instanceof Var) {
					asStruct = (Struct) clonedObjTerm.getTerm();
				} else {
					asStruct = (Struct) clonedObjTerm;
				}

				newState.insertTerm(engine, asStruct, KBType.GOAL);
				
				logger.info("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Executing action: commit(" + asStruct + ")");
				Tracer.queue(state.getAgent().getName(), "commit(" + asStruct + ")");
			}
		} else {
			throw new TransitionNotPossibleException();
		}
		
		return newState;
	}

	@Override
	public String toString() {
		return "commit(" + objective + ")";
	}
}
