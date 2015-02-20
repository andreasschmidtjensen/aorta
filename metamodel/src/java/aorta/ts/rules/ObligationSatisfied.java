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
public class ObligationSatisfied extends TransitionRule {

	private static final Logger logger = Logger.getLogger(ObligationSatisfied.class.getName());

	@Override
	protected State execute(State state) {
		MentalState ms = state.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct obl = language.norm(new Var("A"), new Var("R"), new Struct(Norm.OBLIGATION), new Var("O"), new Var("D"));
		Struct orgObl = FormulaQualifier.qualifyStruct(obl, KBType.ORGANIZATION);

		List<SolveInfo> obligations = ms.findAll(orgObl);
		for (SolveInfo obligation : obligations) {
			if (obligation.isSuccess()) {
				
				Struct obj = language.obj(new Var("O"));
				Struct optObj = FormulaQualifier.qualifyStruct(obj, KBType.OPTION);
				optObj = (Struct) ms.unify(optObj, obligation);
				
				Struct oblForOpt = language.norm(new Var("R"), new Struct(Norm.OBLIGATION), new Var("O"), new Var("D"));
				Struct optObl = FormulaQualifier.qualifyStruct(oblForOpt, KBType.OPTION);
				optObl = (Struct) ms.unify(optObl, obligation);
				
				Term objectiveArg = obj.getArg(0);
				if (objectiveArg instanceof Var && ((Var)objectiveArg).getTerm() instanceof Struct) {
					objectiveArg = ((Var)objectiveArg).getTerm();
				}

				if (ms.exists(objectiveArg)) {
					//XXX: newState = state.clone();
					orgObl = (Struct) ms.unify(orgObl, obligation);
					optObj = (Struct) ms.unify(optObj, obligation);
					optObl = (Struct) ms.unify(optObl, obligation);
					remove(state, orgObl);
					remove(state, optObj);
					remove(state, optObl);

					logger.finer("[" + state.getDescription() + "] Removing obligation: " + orgObl);
					Tracer.trace(state.getIdentifier(), getName(), "Satisfied " + orgObl.getArg(0));
					break;
				}
			}
		}

		return state;
	}

	@Override
	public String getName() {
		return "Obl-Satisfied";
	}
}
