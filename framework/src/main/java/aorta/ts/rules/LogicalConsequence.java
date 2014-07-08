/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.kr.util.Qualifier;
import aorta.reasoning.fml.Formula;
import aorta.tracer.Tracer;
import aorta.ts.TransitionNotPossibleException;
import aorta.ts.TransitionRule;
import java.util.logging.Logger;

/**
 *
 * @author asj
 */
public class LogicalConsequence implements TransitionRule {
	private static final Logger logger = Logger.getLogger(LogicalConsequence.class.getName());
	private Formula fml;

	public LogicalConsequence(Formula fml) {
		this.fml = fml;
	}

	@Override
	public AgentState execute(QueryEngine engine, AgentState state) throws TransitionNotPossibleException {
		SolveInfo info = engine.solve(state.getMentalState(), Qualifier.qualifyFormula(fml, true));
		if (info.isSuccess()) {
			try {
				state.addBindings(info.getBindingVars());
				
				Term qualified = Qualifier.qualifyGoal(fml);				
				engine.unify(state.getMentalState(), qualified, state.getBindings());
				
				Tracer.queue(state.getAgent().getName(), qualified.toString() + " =>");
			} catch (NoSolutionException ignore) {
				// not thrown since we check isSuccess()
			}
		} else {
			throw new TransitionNotPossibleException();
		}
		
		return state;
	}
	
}
