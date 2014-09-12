/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
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
public class ObligationSatisfied extends Transition {

	private static final Logger logger = Logger.getLogger(ObligationSatisfied.class.getName());

	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();

		MetaLanguage language = new MetaLanguage();
		Struct obl = language.obligation(new Var("A"), new Var("R"), new Var("O"), new Var("D"));
		Struct obj = language.obj(new Var("O"));

		Struct orgObl = Qualifier.qualifyStruct(obl, KBType.ORGANIZATION);
		Struct optObj = Qualifier.qualifyStruct(obj, KBType.OPTION);

		List<SolveInfo> obligations = engine.findAll(ms, orgObl);
		for (SolveInfo obligation : obligations) {
			if (obligation.isSuccess()) {
				Var var = new Var("O");
				engine.unify(ms, var, obligation);

				if (var.isGround() && engine.exists(ms, var.getTerm())) {
					//XXX: newState = state.clone();
					engine.unify(ms, orgObl, obligation);
					engine.unify(ms, optObj, obligation);
					newState.removeTerm(engine, orgObl);
					newState.removeTerm(engine, optObj);

					logger.info("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Removing obligation: " + orgObl);
					Tracer.trace(state.getAgent().getName(), "(" + getName() + ") Satisfied " + orgObl.getArg(0) + "\n");
					break;
				}
			}
		}

		return newState;
	}

	@Override
	public String getName() {
		return "Obl-Satisfied";
	}
}
