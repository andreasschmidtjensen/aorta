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
public class Inform extends Transition<AgentState> {

	private static final Logger logger = Logger.getLogger(Inform.class.getName());
	
	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct rea = language.rea(new Struct(state.getAgent().getName()), new Var("R2"));
		Struct dep = language.dependency(new Var("R1"), new Var("R2"), new Var("O"));
		Struct inf = language.send(new Var("R1"), new Struct("tell"), new Var("O"));

		Struct orgRea = FormulaQualifier.qualifyStruct(rea, KBType.ORGANIZATION);
		Struct orgDep = FormulaQualifier.qualifyStruct(dep, KBType.ORGANIZATION);
		Struct optInf = FormulaQualifier.qualifyStruct(inf, KBType.OPTION);
		
		
		// TODO: O in dependency IS NOT QUALIFIED so O does not work!!!w 
		// org(rea(A,R2)), org(dep(R1,R2,O)), bel(O), \+ opt(inform(R1, O))
		Term test = Term.createTerm(orgRea + ", " + orgDep + ", O, \\+ " + optInf);

		SolveInfo result = engine.solve(ms, test);
		if (result.isSuccess()) {
			try {
				state.addBindings(result.getBindingVars());
			} catch (NoSolutionException ignore) {
				// not thrown because of isSuccess
			}

			engine.unify(ms, optInf, state.getBindings());
			
			if (optInf.isGround()) {
				//XXX: newState = state.clone();;
				newState.insertTerm(engine, optInf);
				
				logger.fine("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Added option: " + optInf);
				Tracer.trace(state.getAgent().getName(), optInf + " => Options\n");
			}
		}
		
		return newState;
	}

	@Override
	public String getName() {
		return "Inform";
	}
	
}
