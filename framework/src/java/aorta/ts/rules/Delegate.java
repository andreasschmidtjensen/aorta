/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.FormulaQualifier;
import aorta.logging.Logger;
import aorta.tracer.Tracer;
import aorta.ts.Transition;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Delegate extends Transition {

	private static final Logger logger = Logger.getLogger(Delegate.class.getName());
	
	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct rea = language.rea(new Var("A"), new Var("R1"));
		Struct dep = language.dependency(new Var("R1"), new Var("R2"), new Var("O"));
		Struct obl = language.obligation(new Var("A"), new Var("R"), new Var("O"), new Var("D"));
		Struct del = language.send(new Var("R2"), new Struct("achieve"), new Var("O"));

		Struct orgRea = FormulaQualifier.qualifyStruct(rea, KBType.ORGANIZATION);
		Struct orgDep = FormulaQualifier.qualifyStruct(dep, KBType.ORGANIZATION);
		Struct orgObl = FormulaQualifier.qualifyStruct(obl, KBType.ORGANIZATION);
		Struct optDel = FormulaQualifier.qualifyStruct(del, KBType.OPTION);
		
		// org(rea(A,R1)), org(dependency(R1,R2,O)), opt(obligation(A,R1,O,D)), \+ opt(delegate(R2,O))
		Term test = Term.createTerm(orgRea + ", " + orgDep + ", " + orgObl + ", \\+ " + optDel);

		SolveInfo result = engine.solve(ms, test);
		if (result.isSuccess()) {
			try {
				state.addBindings(result.getBindingVars());
			} catch (NoSolutionException ignore) {
				// not thrown because of isSuccess
			}

			engine.unify(ms, optDel, state.getBindings());
			
			if (optDel.isGround()) {
				//XXX: newState = state.clone();;
				newState.insertTerm(engine, optDel);
				
				logger.info("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Added option: " + optDel);
				Tracer.trace(state.getAgent().getName(), optDel + " => Options\n");
			}
		}
		
		return newState;
	}

	@Override
	public String getName() {
		return "Delegate";
	}
	
}
