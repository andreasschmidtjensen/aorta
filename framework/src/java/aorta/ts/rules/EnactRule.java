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
import java.util.logging.Level;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class EnactRule extends Transition {

	private static final Logger logger = Logger.getLogger(EnactRule.class.getName());

	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();

		MetaLanguage language = new MetaLanguage();
		Struct role = language.role(new Var("R"), new Var("Os"));
		Struct oRole = language.role(new Var("R"));
		Struct rea = language.rea(new Var("A"), new Var("R"));

		Struct orgRole = Qualifier.qualifyStruct(role, KBType.ORGANIZATION);
		Struct orgRea = Qualifier.qualifyStruct(rea, KBType.ORGANIZATION);
		Struct optRole = Qualifier.qualifyStruct(oRole, KBType.OPTION);

		String test = orgRole.toString() + ", \\+ " + orgRea.toString() + ", \\+ " + optRole.toString() + ", cl(Os, Out), member(G,Out), goal(G).";
		try {
			// org(role(R,Os)), \+ org(rea(Ag,R)), \+ opt(role(R,Os)), cl(Os,OsOut), member(G,OsOut), goal(G)
			SolveInfo result = engine.solve(ms, test);
			if (result.isSuccess()) {
				engine.unify(ms, optRole, result);

				if (optRole.isGround()) {
					//XXX: newState = state.clone();;
					newState.insertTerm(engine, optRole);

					logger.info("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Adding option: " + optRole);
					Tracer.trace(state.getAgent().getName(), "(" + getName() + ") " + optRole.getArg(0) + "\n");
				} else {
					logger.warning("Failed to consider role, not ground: " + optRole);
				}
			}
		} catch (MalformedGoalException ex) {
			logger.log(Level.SEVERE, "Could not parse " + test, ex);
		}

		return newState;
	}

	@Override
	public String getName() {
		return "Enact";
	}
}
