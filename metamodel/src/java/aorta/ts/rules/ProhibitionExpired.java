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
import aorta.ts.Transition;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ProhibitionExpired extends Transition {

	private static final Logger logger = Logger.getLogger(ProhibitionExpired.class.getName());

	@Override
	protected State execute(QueryEngine engine, State state) {
		MentalState ms = state.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct obl = language.norm(new Var("A"), new Var("R"), new Struct(Norm.PROHIBITION), new Var("O"), new Var("D"));
		Struct orgObl = FormulaQualifier.qualifyStruct(obl, KBType.ORGANIZATION);

		Term test = Term.createTerm(orgObl + ", D");
		
		// check: norm(A,R,forbidden,O,D), D
		List<SolveInfo> obligations = engine.findAll(ms, test);
		for (SolveInfo obligation : obligations) {
			if (obligation.isSuccess()) {				
				Struct obj = language.obj(new Var("O"));
				Struct optObj = FormulaQualifier.qualifyStruct(obj, KBType.OPTION);
				engine.unify(ms, optObj, obligation);
				
				Struct proForOpt = language.norm(new Var("R"), new Struct(Norm.PROHIBITION), new Var("O"), new Var("D"));
				Struct optPro = FormulaQualifier.qualifyStruct(proForOpt, KBType.OPTION);
				engine.unify(ms, optPro, obligation);
				
				Term objectiveArg = obj.getArg(0);
				if (objectiveArg instanceof Var && ((Var)objectiveArg).getTerm() instanceof Struct) {
					objectiveArg = ((Var)objectiveArg).getTerm();
				}
				if (objectiveArg instanceof Struct) {
					if (TermQualifier.isQualified((Struct)objectiveArg)) {
						try {
							Term qualifiedArg = FormulaQualifier.getQualified((Struct)objectiveArg);
							if (qualifiedArg == null) {
								// ugly fix
								int tries = 5;
								while (tries > 0 && qualifiedArg == null)  {
									qualifiedArg = FormulaQualifier.getQualified((Struct)objectiveArg);
									tries--;
								}
							}
							optObj = (Struct) TermQualifier.qualifyTerm(language.obj(qualifiedArg), KBType.OPTION.getType());							
						} catch (InvalidTermException ex) {
							System.out.println("InvalidTermException for " + obj);
						}
					}
				}

				if (!engine.exists(ms, objectiveArg)) {
					//XXX: newState = state.clone();
					engine.unify(ms, orgObl, obligation);
					engine.unify(ms, optObj, obligation);
					engine.unify(ms, optPro, obligation);
					remove(state, engine, orgObl);
					remove(state, engine, optObj);
					remove(state, engine, optPro);

					logger.fine("[" + state.getDescription() + "] Prohibition expired (removing): " + orgObl);
					Tracer.trace(state.getIdentifier(), getName(), "Expired " + orgObl.getArg(0));
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
