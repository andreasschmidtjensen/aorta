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
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class InformSubObj extends Transition {

	private static final Logger logger = Logger.getLogger(InformSubObj.class.getName());
	
	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct rea = language.rea(new Struct(state.getAgent().getName()), new Var("R1"));
		Struct dep = language.dependency(new Var("R1"), new Var("R2"), new Struct("bel", new Var("O")));

		Struct orgRea = FormulaQualifier.qualifyStruct(rea, KBType.ORGANIZATION);
		Struct orgDep = FormulaQualifier.qualifyStruct(dep, KBType.ORGANIZATION);
		
		// org(rea(A,R1)), org(dep(R1,R2,bel(O))), cl([O], SO), member(Oprime, SO), bel(Oprime)))
		Term test = Term.createTerm(orgRea + ", " + orgDep + ", cl([O], SO), member(Oprime,SO), bel(Oprime)");
		List<SolveInfo> results = engine.findAll(ms, test);
		for (SolveInfo result : results) {
			if (result.isSuccess()) {
				Struct inf = language.send(new Var("R2"), new Struct("tell"), new Struct("bel", new Var("Oprime")));
				Struct optInf = FormulaQualifier.qualifyStruct(inf, KBType.OPTION);
				engine.unify(ms, optInf, result);

				if (!engine.exists(ms, optInf) && optInf.isGround()) {
					//XXX: newState = state.clone();;
					newState.insertTerm(engine, optInf);

					logger.fine("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Added option: " + optInf);
					Tracer.trace(state.getAgent().getName(), optInf + " => Options\n");
					
					break;
				}
			}
		}
		return newState;
	}

	@Override
	public String getName() {
		return "Inform-SubObjectives";
	}
	
}
