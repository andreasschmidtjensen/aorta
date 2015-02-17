/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.State;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.kr.language.model.Metamodel;
import aorta.kr.language.model.Norm;
import aorta.kr.util.FormulaQualifier;
import aorta.tracer.Tracer;
import aorta.ts.TransitionRule;
import java.util.List;
import aorta.logging.Logger;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public abstract class NormActivated extends TransitionRule {

	private static final Logger logger = Logger.getLogger(NormActivated.class.getName());

	public abstract Struct getDeon();

	@Override
	protected State execute(QueryEngine engine, State state) {
		MentalState ms = state.getMentalState();

		Metamodel metamodel = state.getMetamodel();
		MetaLanguage ml = new MetaLanguage();

		for (Norm norm : metamodel.getNorms()) {
			Term r = new Struct(norm.getRole());
			Term deon = new Struct(norm.getDeon());

			if (!deon.equals(getDeon())) {
				continue;
			}
			
			// For each agent rea(agent, r) set agVar = agent
			//   if c => activate norm for rea(agent, r)
			List<SolveInfo> reas = engine.findAll(ms, FormulaQualifier.qualifyStruct(ml.rea(new Var("A"), r), KBType.ORGANIZATION));
			for (SolveInfo rea : reas) {
				if (rea.isSuccess()) {
					Term a = null;
					try {
						a = rea.getVarValue("A");
					} catch (NoSolutionException ex) {
						// never happens (we check for isSuccess())
					}
					
					Term p = Term.createTerm(ml.qualify(norm.getObjective()).toString());
					Term d = Term.createTerm(ml.qualify(norm.getDeadline()).toString());
					Term c = Term.createTerm(ml.qualify(norm.getCondition()).toString());
					
					Term condition;
					if (norm.hasAgentVar()) {
						Term agVar = Term.createTerm(norm.getAgentVar().toString());
						condition = new Struct(",", new Struct("=", agVar, a), c);
					} else {
						condition = c;
					}
					
					Term test = new Struct(",", 
											condition, 
											new Struct("\\+", p));

					SolveInfo solution = engine.solve(ms, test);
										
					if (solution.isSuccess()) {						
						engine.unify(ms, p, solution);
						engine.unify(ms, d, solution);
						
						Struct activatedNorm = ml.norm(a, r, deon, p, d);
						Struct orgNorm = FormulaQualifier.qualifyStruct(activatedNorm, KBType.ORGANIZATION);
						
						if (!engine.exists(ms, orgNorm)) {
							if (!p.isGround()) {
								logger.warning("[" + state.getDescription() + "] Norm state - " + p + " - is not ground");
							}
							add(state, engine, orgNorm);

							logger.fine("[" + state.getDescription() + "] Adding norm: " + orgNorm);
							Tracer.trace(state.getIdentifier(), getName(), orgNorm.getArg(0).toString());

							break;
						}
					}
				}
			}

		}

		return state;
	}

}
