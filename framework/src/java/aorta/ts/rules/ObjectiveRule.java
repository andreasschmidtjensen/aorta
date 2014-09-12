/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.MalformedGoalException;
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
import java.util.logging.Level;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ObjectiveRule extends Transition {

	private static final Logger logger = Logger.getLogger(ObjectiveRule.class.getName());

	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();

		MetaLanguage language = new MetaLanguage();
		Struct obl = language.obligation(new Var("A"), new Var("R"), new Var("O"), new Var("D"));
		Struct rea = language.rea(new Var("A"), new Var("R"));
		Struct obj = language.obj(new Var("O"));

		Struct orgObl = Qualifier.qualifyStruct(obl, KBType.ORGANIZATION);
		Struct orgRea = Qualifier.qualifyStruct(rea, KBType.ORGANIZATION);
		Struct optObj = Qualifier.qualifyStruct(obj, KBType.OPTION);

		Term test = Term.createTerm(orgObl.toString() + ", " + orgRea.toString() + ", bel(me(A)), \\+ " + optObj.toString());
		List<SolveInfo> conditionals = engine.findAll(ms, test);
		for (SolveInfo conditional : conditionals) {
			if (conditional.isSuccess()) {
				engine.unify(ms, optObj, conditional);

				if (optObj.isGround()) {
					//XXX: newState = state.clone();;
					newState.insertTerm(engine, optObj);

					logger.info("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Adding option: " + optObj);
					Tracer.trace(state.getAgent().getName(), "(" + getName() + ") " + optObj.getArg(0) + "\n");

					break;
				} else {
					logger.warning("Failed to consider objective, not ground: " + optObj);
				}
			}
		}
		return newState;
	}

	@Override
	public String getName() {
		return "Objective";
	}
}
