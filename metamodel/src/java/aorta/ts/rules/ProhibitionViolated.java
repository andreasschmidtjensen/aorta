/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Var;
import aorta.State;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.kr.language.model.Norm;
import aorta.kr.util.FormulaQualifier;
import aorta.logging.Logger;
import aorta.tracer.Tracer;
import aorta.ts.TransitionRule;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ProhibitionViolated extends TransitionRule {

	private static final Logger logger = Logger.getLogger(ProhibitionViolated.class.getName());
	
	@Override
	protected State execute(QueryEngine engine, State state) {
		MentalState ms = state.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct prohib = language.norm(new Var("A"), new Var("R"), new Struct(Norm.PROHIBITION), new Var("O"), new Var("D"));
		
		Struct orgProhib = FormulaQualifier.qualifyStruct(prohib, KBType.ORGANIZATION);
		
		List<SolveInfo> obligations = engine.findAll(ms, orgProhib);
		for (SolveInfo obligation : obligations) {
			if (obligation.isSuccess()) {
				
				Var forbiddenState = new Var("O");
				Var deadline = new Var("D");
				engine.unify(ms, forbiddenState, obligation);
				engine.unify(ms, deadline, obligation);
				
				Struct orgViol = FormulaQualifier.qualifyStruct(language.violation(new Var("A"), new Var("R"), new Struct(Norm.PROHIBITION), new Var("O")), KBType.ORGANIZATION);
				engine.unify(ms, orgViol, obligation);

				if (engine.exists(ms, forbiddenState.getTerm())  //prohibited state reached
						&& !engine.exists(ms, deadline.getTerm())  //deadline not reached
						&& !engine.exists(ms, orgViol)) { // violation not detected already

					add(state, engine, orgViol);

					logger.fine("[" + state.getDescription() + "] Violated prohibition: " + orgViol);
					Tracer.trace(state.getIdentifier(), getName(), orgViol.getArg(0).toString());

					break;
				}
			}
		}
		
		return state;
	}

	@Override
	public String getName() {
		return "Obl-Violated";
	}
	
}
