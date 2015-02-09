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
public class ObligationViolated extends TransitionRule {

	private static final Logger logger = Logger.getLogger(ObligationViolated.class.getName());
	
	@Override
	protected State execute(QueryEngine engine, State state) {
		MentalState ms = state.getMentalState();
		
		MetaLanguage language = new MetaLanguage();
		Struct obl = language.norm(new Var("A"), new Var("R"), new Struct(Norm.OBLIGATION), new Var("O"), new Var("D"));
		
		Struct orgObl = FormulaQualifier.qualifyStruct(obl, KBType.ORGANIZATION);
		
		List<SolveInfo> obligations = engine.findAll(ms, orgObl);
		for (SolveInfo obligation : obligations) {
			if (obligation.isSuccess()) {
				
				Var objective = new Var("O");
				Var deadline = new Var("D");
				engine.unify(ms, objective, obligation);
				engine.unify(ms, deadline, obligation);
				
//				if (objective.isGround() && deadline.isGround()) {
					Struct orgViol = FormulaQualifier.qualifyStruct(language.violation(new Var("A"), new Var("R"), new Struct(Norm.OBLIGATION), new Var("O")), KBType.ORGANIZATION);
					engine.unify(ms, orgViol, obligation);

					if (!engine.exists(ms, objective.getTerm())  //objective not completed
							&& engine.exists(ms, deadline.getTerm())  //deadline reached
							&& !engine.exists(ms, orgViol)) { // violation not detected already
						
						//XXX: newState = state.clone();;
						add(state, engine, orgViol);

						logger.fine("[" + state.getDescription() + "] Violated obligation: " + orgViol);
						Tracer.trace(state.getIdentifier(), getName(), orgViol.getArg(0).toString());

						break;
					}
//				}
			}
		}
		
		return state;
	}

	@Override
	public String getName() {
		return "Obl-Violated";
	}
	
}
