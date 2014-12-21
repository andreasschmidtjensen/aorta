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
import aorta.State;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.FormulaQualifier;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ObligationActivated extends Transition {

	private static final Logger logger = Logger.getLogger(ObligationActivated.class.getName());

	@Override
	protected State execute(QueryEngine engine, State state) {
		MentalState ms = state.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct rea = language.rea(new Var("A"), new Var("R"));
		Struct cond = language.condition(new Var("R"), new Var("O"), new Var("D"), new Var("C"));

		Struct orgRea = FormulaQualifier.qualifyStruct(rea, KBType.ORGANIZATION);
		Struct orgCond = FormulaQualifier.qualifyStruct(cond, KBType.ORGANIZATION);
		
		// org(rea(A,R)), org(cond(R,O,D,C)), C, \+ O, \+ org(obl(A,R,O,D))
		Term term = Term.createTerm(orgRea + ", " + orgCond + ", C");
		
		List<SolveInfo> conditionals = engine.findAll(ms, term);
		
		for (SolveInfo conditional : conditionals) {
			if (conditional.isSuccess()) {
				Var objective = new Var("O");
				engine.unify(ms, objective, conditional);
				
				Struct obl = language.obligation(new Var("A"), new Var("R"), new Var("O"), new Var("D"));
				Struct orgObl = FormulaQualifier.qualifyStruct(obl, KBType.ORGANIZATION);
				engine.unify(ms, orgObl, conditional);
				
				if (!engine.exists(ms, objective.getTerm())
						&& !engine.exists(ms, orgObl)) {

					if (!objective.isGround()) {
						logger.warning("[" + state.getDescription() + "] Objective - " + objective + " - is not ground");
					}
					state.insertTerm(engine, orgObl);

					logger.fine("[" + state.getDescription() + "] Adding obligation: " + orgObl);
					Tracer.trace(state.getIdentifier(), getName(), orgObl.getArg(0).toString());

					break;
				}

			}
		}

		return state;
	}
	
	@Override
	public String getName() {
		return "Obl-Activated";
	}
}
