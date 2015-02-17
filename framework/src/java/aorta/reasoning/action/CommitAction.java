package aorta.reasoning.action;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AORTAException;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.util.FormulaQualifier;
import aorta.tracer.Tracer;
import aorta.ts.TransitionNotPossibleException;
import java.util.logging.Level;
import aorta.logging.Logger;
import aorta.ts.rules.ActionExecution;

public class CommitAction extends Action {

	private static final Logger logger = Logger.getLogger(CommitAction.class.getName());
	private Term objective;

	public CommitAction(Term objective) {
		this.objective = objective;
	}

	public Term getObjective() {
		return objective;
	}

	@Override
	protected AgentState executeAction(QueryEngine engine, Term option, AgentState state) throws AORTAException {
		AgentState newState = state;

		MentalState ms = state.getMentalState();

		final Term clonedObjTerm = Term.createTerm(objective.toString());

		String bef = clonedObjTerm.toString();
		engine.unify(ms, clonedObjTerm, state.getBindings());

		Term belTerm = FormulaQualifier.qualifyTerm(clonedObjTerm, KBType.BELIEF);
		Term goalTerm = FormulaQualifier.qualifyTerm(clonedObjTerm, KBType.GOAL);

		Term test = Term.createTerm("\\+ " + belTerm + ", \\+ " + goalTerm);

		SolveInfo result = engine.solve(ms, test);

		logger.log(Level.FINEST, "Attempting to commit: " + result.isSuccess());
		if (result.isSuccess()) {
			state.addBindings(result);

			engine.unify(ms, clonedObjTerm, state.getBindings());

			Struct asStruct;
			if (clonedObjTerm instanceof Var) {
				asStruct = (Struct) clonedObjTerm.getTerm();
			} else {
				asStruct = (Struct) clonedObjTerm;
			}

			ActionExecution tr = new ActionExecution();
			tr.add(newState, engine, asStruct, KBType.GOAL);

			logger.fine("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Executing action: commit(" + asStruct + ")");
			Tracer.queue(state.getAgent().getName(), "commit(" + asStruct + ")");
		} else {
			return null;
		}

		return newState;
	}

	@Override
	public String toString() {
		return "commit(" + objective + ")";
	}
}
