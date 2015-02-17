/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.InvalidTermException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.State;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.kr.language.model.Norm;
import aorta.kr.util.FormulaQualifier;
import aorta.kr.util.TermQualifier;
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
	protected State execute(QueryEngine engine, State state) {
		MentalState ms = state.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct obl = language.norm(new Var("A"), new Var("R"), new Struct(Norm.OBLIGATION), new Var("O"), new Var("D"));
		Struct orgObl = FormulaQualifier.qualifyStruct(obl, KBType.ORGANIZATION);

		List<SolveInfo> obligations = engine.findAll(ms, orgObl);
		for (SolveInfo obligation : obligations) {
			if (obligation.isSuccess()) {
				
				Struct obj = language.obj(new Var("O"));
				Struct optObj = FormulaQualifier.qualifyStruct(obj, KBType.OPTION);
				engine.unify(ms, optObj, obligation);
				
				Struct oblForOpt = language.norm(new Var("R"), new Struct(Norm.OBLIGATION), new Var("O"), new Var("D"));
				Struct optObl = FormulaQualifier.qualifyStruct(oblForOpt, KBType.OPTION);
				engine.unify(ms, optObl, obligation);
				
				Term objectiveArg = obj.getArg(0);
				if (objectiveArg instanceof Var && ((Var)objectiveArg).getTerm() instanceof Struct) {
					objectiveArg = ((Var)objectiveArg).getTerm();
				}

				if (engine.exists(ms, objectiveArg)) {
					//XXX: newState = state.clone();
					engine.unify(ms, orgObl, obligation);
					engine.unify(ms, optObj, obligation);
					engine.unify(ms, optObl, obligation);
					remove(state, engine, orgObl);
					remove(state, engine, optObj);
					remove(state, engine, optObl);

					logger.fine("[" + state.getDescription() + "] Removing obligation: " + orgObl);
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
