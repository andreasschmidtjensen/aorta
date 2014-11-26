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
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.FormulaQualifier;
import aorta.logging.Logger;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ObligationViolated extends Transition {

	private static final Logger logger = Logger.getLogger(ObligationViolated.class.getName());
	
	@Override
	protected State execute(QueryEngine engine, State state) {
		State newState = state;
		MentalState ms = newState.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct obl = language.obligation(new Var("A"), new Var("R"), new Var("O"), new Var("D"));
		
		Struct orgObl = FormulaQualifier.qualifyStruct(obl, KBType.ORGANIZATION);
		
		List<SolveInfo> obligations = engine.findAll(ms, orgObl);
		for (SolveInfo obligation : obligations) {
			if (obligation.isSuccess()) {
				
				Var objective = new Var("O");
				Var deadline = new Var("D");
				engine.unify(ms, objective, obligation);
				engine.unify(ms, deadline, obligation);
				
//				if (objective.isGround() && deadline.isGround()) {
					Struct orgViol = FormulaQualifier.qualifyStruct(language.violation(new Var("A"), new Var("R"), new Var("O")), KBType.ORGANIZATION);
					engine.unify(ms, orgViol, obligation);

					if (!engine.exists(ms, objective.getTerm())  //objective not completed
							&& engine.exists(ms, deadline.getTerm())  //deadline reached
							&& !engine.exists(ms, orgViol)) { // violation not detected already
						
						//XXX: newState = state.clone();;
						newState.insertTerm(engine, orgViol);

						logger.fine("[" + state.getDescription() + "] Violated obligation: " + orgViol);
						Tracer.trace(state.getIdentifier(), "(" + getName() + ") " + orgViol.getArg(0) + "\n");

						break;
					}
//				}
			}
		}
		
		return newState;
	}

	@Override
	public String getName() {
		return "Obl-Violated";
	}
	
}
