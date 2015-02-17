/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Var;
import aorta.State;
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
public class ProhibitionViolated extends TransitionRule {

	private static final Logger logger = Logger.getLogger(ProhibitionViolated.class.getName());
	
	@Override
	protected State execute(State state) {
		MentalState ms = state.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct prohib = language.norm(new Var("A"), new Var("R"), new Struct(Norm.PROHIBITION), new Var("O"), new Var("D"));
		
		Struct orgProhib = FormulaQualifier.qualifyStruct(prohib, KBType.ORGANIZATION);
		
		List<SolveInfo> obligations = ms.findAll(orgProhib);
		for (SolveInfo obligation : obligations) {
			if (obligation.isSuccess()) {
				
				Var forbiddenState = new Var("O");
				Var deadline = new Var("D");
				ms.unify(forbiddenState, obligation);
				ms.unify(deadline, obligation);
				
				Struct orgViol = FormulaQualifier.qualifyStruct(language.violation(new Var("A"), new Var("R"), new Struct(Norm.PROHIBITION), new Var("O")), KBType.ORGANIZATION);
				ms.unify(orgViol, obligation);

				if (ms.exists(forbiddenState.getTerm())  //prohibited state reached
						&& !ms.exists(deadline.getTerm())  //deadline not reached
						&& !ms.exists(orgViol)) { // violation not detected already

					add(state, orgViol);

					logger.fine("[" + state.getDescription() + "] Violated prohibition: " + orgViol);
					Tracer.trace(state.getIdentifier(), getName(), orgViol.getArg(0).toString());

					break;
				}
			}
		}
		
		return state;
	}

	@Override
	public String getName() {
		return "Obl-Violated";
	}
	
}
