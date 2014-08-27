/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.Qualifier;
import aorta.logging.Logger;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ObligationActivated extends Transition {

	private static final Logger logger = Logger.getLogger(ObligationActivated.class.getName());

	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();

		MetaLanguage language = new MetaLanguage();
		Struct rea = language.rea(new Var("A"), new Var("R"));
		Struct cond = language.condition(new Var("R"), new Var("O"), new Var("D"), new Var("C"));
		Struct obl = language.obligation(new Var("A"), new Var("R"), new Var("O"), new Var("D"));

		Struct orgRea = Qualifier.qualifyStruct(rea, KBType.ORGANIZATION);
		Struct orgCond = Qualifier.qualifyStruct(cond, KBType.ORGANIZATION);
		Struct orgObl = Qualifier.qualifyStruct(obl, KBType.ORGANIZATION);
		
		Term term = Term.createTerm(orgRea + ", " + orgCond + ", C, \\+ O, \\+ " + orgObl);
		
		List<SolveInfo> conditionals = engine.findAll(ms, term);
		for (SolveInfo conditional : conditionals) {
			if (conditional.isSuccess()) {
				Var objective = new Var("O");
				engine.unify(ms, objective, conditional);

				if (objective.isGround()) {
					engine.unify(ms, orgObl, conditional);

					if (!engine.exists(ms, objective.getTerm())
							&& !engine.exists(ms, orgObl)) {
						//XXX: newState = newState.clone();
						newState.insertTerm(engine, orgObl);

						logger.info("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Adding obligation: " + objective.getTerm());
						Tracer.trace(state.getAgent().getName(), "(" + getName() + ") " + orgObl.getArg(0) + "\n");

						break;
					}
				}

			}
		}

		return newState;
	}

	@Override
	public String getName() {
		return "Obl-Activated";
	}
}
