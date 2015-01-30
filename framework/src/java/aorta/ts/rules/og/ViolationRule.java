/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules.og;

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
import aorta.kr.util.TermQualifier;
import aorta.logging.Logger;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ViolationRule extends Transition<AgentState> {

	private static final Logger logger = Logger.getLogger(ViolationRule.class.getName());

	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();

		MetaLanguage language = new MetaLanguage();
		Struct viol = language.violation(new Var("A"), new Var("R"), new Var("Deon"), new Var("O"));
		Struct orgViol = FormulaQualifier.qualifyStruct(viol, KBType.ORGANIZATION);

		List<SolveInfo> conditionals = engine.findAll(ms, orgViol);
		for (SolveInfo conditional : conditionals) {
			if (conditional.isSuccess()) {
				Struct opt = language.viol(new Var("A"), new Var("R"), new Var("Deon"), new Var("O"));
				Struct optViol = FormulaQualifier.qualifyStruct(opt, KBType.OPTION);
				
				engine.unify(ms, optViol, conditional);
				
				Struct result = optViol;
				
				if (!engine.exists(ms, result)) {
					newState.insertTerm(engine, result);

					logger.fine("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Adding option: " + result);
					Tracer.trace(state.getAgent().getName(), getName(), result.toString());

					break;
				
				}
			}
		}
		return newState;
	}

	@Override
	public String getName() {
		return "Violation";
	}
}
