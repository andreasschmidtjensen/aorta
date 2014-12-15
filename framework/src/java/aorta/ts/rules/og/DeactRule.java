/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules.og;

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
import aorta.kr.util.FormulaQualifier;
import aorta.logging.Logger;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import java.util.logging.Level;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class DeactRule extends Transition<AgentState> {

	private static final Logger logger = Logger.getLogger(DeactRule.class.getName());

	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();

		MetaLanguage language = new MetaLanguage();
		Struct role = language.role(new Var("R"), new Var("Os"));
		Struct oRole = new Struct("~", language.role(new Var("R")));
		Struct rea = language.rea(new Var("A"), new Var("R"));

		Struct orgRole = FormulaQualifier.qualifyStruct(role, KBType.ORGANIZATION);
		Struct orgRea = FormulaQualifier.qualifyStruct(rea, KBType.ORGANIZATION);
		Struct optRole = FormulaQualifier.qualifyStruct(oRole, KBType.OPTION);

		// role(R,Os), rea(A,R), \+ ~role(R), \+ (member(O,Os), \+ bel(O)).
		String test = orgRole.toString() + ", " + orgRea.toString() + ", \\+ " + optRole.toString() + ", \\+ (member(O, Os), \\+ bel(O)).";
		try {
			// TODO: create DeactTest 
			SolveInfo result = engine.solve(ms, test);
			if (result.isSuccess()) {
				engine.unify(ms, optRole, result);

				if (optRole.isGround()) {
					//XXX: newState = state.clone();
					newState.insertTerm(engine, optRole);

					logger.fine("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Adding option: " + optRole);
					Tracer.trace(state.getAgent().getName(), getName(), optRole.getArg(0).toString());
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
		return "Deact";
	}
}
