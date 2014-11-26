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
public class ObligationSatisfied extends Transition {

	private static final Logger logger = Logger.getLogger(ObligationSatisfied.class.getName());

	@Override
	protected AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;
		MentalState ms = newState.getMentalState();

		MetaLanguage language = new MetaLanguage();
		Struct obl = language.obligation(new Var("A"), new Var("R"), new Var("O"), new Var("D"));
		Struct orgObl = FormulaQualifier.qualifyStruct(obl, KBType.ORGANIZATION);

		List<SolveInfo> obligations = engine.findAll(ms, orgObl);
		for (SolveInfo obligation : obligations) {
			if (obligation.isSuccess()) {
				
				Struct obj = language.obj(new Var("O"));
				Struct optObj = FormulaQualifier.qualifyStruct(obj, KBType.OPTION);
				engine.unify(ms, optObj, obligation);
				
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

				if (engine.exists(ms, objectiveArg)) {
					//XXX: newState = state.clone();
					engine.unify(ms, orgObl, obligation);
					engine.unify(ms, optObj, obligation);
					newState.removeTerm(engine, orgObl);
					newState.removeTerm(engine, optObj);

					logger.fine("[" + state.getAgent().getName() + "/" + state.getAgent().getCycle() + "] Removing obligation: " + orgObl);
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
