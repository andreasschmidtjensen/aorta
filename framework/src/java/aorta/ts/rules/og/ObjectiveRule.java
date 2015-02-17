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
public class ObjectiveRule extends TransitionRule<AgentState> {

	private static final Logger logger = Logger.getLogger(ObjectiveRule.class.getName());

	@Override
	protected AgentState execute(AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();

		MetaLanguage language = new MetaLanguage();
		Struct obl = language.norm(new Struct(state.getAgent().getName()), new Var("R"), new Struct(Norm.OBLIGATION), new Var("O"), new Var("D"));
		Struct objective = language.objective(new Var("O"), new Var());
		Struct rea = language.rea(new Struct(state.getAgent().getName()), new Var("R"));

		Struct orgObl = FormulaQualifier.qualifyStruct(obl, KBType.ORGANIZATION);
		Struct orgObjective = FormulaQualifier.qualifyStruct(objective, KBType.ORGANIZATION);
		Struct orgRea = FormulaQualifier.qualifyStruct(rea, KBType.ORGANIZATION);

		Term test = Term.createTerm(orgObl + ", " + orgObjective + ", " + orgRea);
		List<SolveInfo> conditionals = ms.findAll(test);
		for (SolveInfo conditional : conditionals) {
			if (conditional.isSuccess()) {
				Struct obj = language.obj(new Var("O"));
				Struct optObj = FormulaQualifier.qualifyStruct(obj, KBType.OPTION);
				
				ms.unify(optObj, conditional);
				
				Struct result = optObj;
//				Term objectiveArg = obj.getArg(0);
//				if (objectiveArg instanceof Var && ((Var)objectiveArg).getTerm() instanceof Struct) {
//					objectiveArg = ((Var)objectiveArg).getTerm();
//				}
//				if (objectiveArg instanceof Struct) {
//					if (TermQualifier.isQualified((Struct)objectiveArg)) {
//						result = (Struct) TermQualifier.qualifyTerm(language.obj(FormulaQualifier.getQualified((Struct)objectiveArg)), KBType.OPTION.getType());
//					}
//				}
				
				if (!ms.exists(result)) {
					add(newState, result);

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
		return "Objective";
	}
}
