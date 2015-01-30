/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

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
import java.util.logging.Logger;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public abstract class NormActivated extends Transition {

	private static final Logger logger = Logger.getLogger(NormActivated.class.getName());

	public abstract Struct getDeon();
	
	@Override
	protected State execute(QueryEngine engine, State state) {
		MentalState ms = state.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct rea = language.rea(new Var("A"), new Var("R"));
		Struct cond = language.condition(new Var("R"), getDeon(), new Var("O"), new Var("D"), new Var("C"));

		Struct orgRea = FormulaQualifier.qualifyStruct(rea, KBType.ORGANIZATION);
		Struct orgCond = FormulaQualifier.qualifyStruct(cond, KBType.ORGANIZATION);
		
		// org(rea(A,R)), org(cond(R,O,D,C)), C, \+ O, \+ org(obl(A,R,O,D))
		Term term = Term.createTerm(orgRea + ", " + orgCond + ", C");
		
		List<SolveInfo> conditionals = engine.findAll(ms, term);
		
		for (SolveInfo conditional : conditionals) {
			if (conditional.isSuccess()) {
				Var objective = new Var("O");
				engine.unify(ms, objective, conditional);
				
				Struct norm = language.norm(new Var("A"), new Var("R"), getDeon(), new Var("O"), new Var("D"));
				Struct orgNorm = FormulaQualifier.qualifyStruct(norm, KBType.ORGANIZATION);
				engine.unify(ms, orgNorm, conditional);
				
				if (!engine.exists(ms, objective.getTerm())
						&& !engine.exists(ms, orgNorm)) {

					if (!objective.isGround()) {
						logger.warning("[" + state.getDescription() + "] Norm state - " + objective + " - is not ground");
					}
					state.insertTerm(engine, orgNorm);

					logger.fine("[" + state.getDescription() + "] Adding obligation: " + orgNorm);
					Tracer.trace(state.getIdentifier(), getName(), orgNorm.getArg(0).toString());

					break;
				}

			}
		}

		return state;
	}
	
}
