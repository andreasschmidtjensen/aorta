/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules.og;

import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Var;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.FormulaQualifier;
import aorta.logging.Logger;
import aorta.tracer.Tracer;
import aorta.ts.TransitionRule;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class EnactRule extends TransitionRule<AgentState> {

	private static final Logger logger = Logger.getLogger(EnactRule.class.getName());

	@Override
	protected AgentState execute(AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();

		MetaLanguage language = new MetaLanguage();
		Struct role = language.role(new Var("R"), new Var("Os"));
		Struct oRole = language.role(new Var("R"));
		Struct rea = language.rea(new Var("A"), new Var("R"));

		Struct orgRole = FormulaQualifier.qualifyStruct(role, KBType.ORGANIZATION);
		Struct orgRea = FormulaQualifier.qualifyStruct(rea, KBType.ORGANIZATION);
		Struct optRole = FormulaQualifier.qualifyStruct(oRole, KBType.OPTION);
		
		String test = orgRole.toString() + ", bel(me(A)), \\+ " + orgRea.toString() + ", \\+ " + optRole.toString() + ", member(bel(G),Os), cap(G).";
		try {
			// org(role(R,Os)), \+ org(rea(Ag,R)), \+ opt(role(R,Os)), member(G,Os), cap(G)
			List<SolveInfo> results = ms.findAll(test);
			for (SolveInfo result : results) {
				if (result.isSuccess()) {
					optRole = (Struct) ms.unify(optRole, result);

					if (optRole.isGround()) {
						add(newState, optRole);

						logger.finer("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Adding option: " + optRole);
						Tracer.trace(state.getAgent().getName(), getName(), optRole.getArg(0).toString());
					} else {
						logger.warning("Failed to consider role, not ground: " + optRole);
					}
					
					break;
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
