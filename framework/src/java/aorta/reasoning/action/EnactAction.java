package aorta.reasoning.action;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AORTAException;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.FormulaQualifier;
import aorta.tracer.Tracer;
import aorta.ts.TransitionNotPossibleException;
import aorta.logging.Logger;
import aorta.ts.rules.ActionExecution;
import cartago.CartagoException;

public class EnactAction extends Action {
	
	public static final Logger logger = Logger.getLogger(EnactAction.class.getName());
	
	private Term role;
	
	public EnactAction(Term role) {
		this.role = role;
	}

	public Term getRole() {
		return role;
	}

	@Override
	protected AgentState executeAction(QueryEngine engine, Term option, AgentState state) throws AORTAException {
		AgentState newState = state;
		
		MentalState ms = state.getMentalState();

		final Term clonedRoleTerm = Term.createTerm(role.toString());
		final Term agent = Term.createTerm(state.getAgent().getName());

		MetaLanguage ml = new MetaLanguage();
        Term roleDef = ml.role(clonedRoleTerm, new Var());
        Term reaDef = ml.rea(agent, clonedRoleTerm);
        		
		Term t1 = FormulaQualifier.qualifyTerm(roleDef, KBType.ORGANIZATION.getType());
		Term t2 = FormulaQualifier.qualifyTerm(reaDef, KBType.ORGANIZATION.getType());

		Term test = Term.createTerm(t1 + ", \\+ " + t2);
		engine.unify(ms, test, state.getBindings());

		SolveInfo result = engine.solve(ms, test);
		
		logger.finest("Attempting to enact: " + result.isSuccess());
		if (result.isSuccess()) {
			state.addBindings(result);

			Term qualified = FormulaQualifier.qualifyTerm(reaDef, KBType.ORGANIZATION.getType());
			engine.unify(ms, qualified, state.getBindings());
			
			if (!qualified.isGround()) {
				throw new AORTAException("Cannot execute action: term '" + qualified + "' is not ground.");
			} else if (qualified instanceof Struct) {
				if (state.getAgent().getArtifactAgent() != null) {
					try {
						String roleName;
						if (clonedRoleTerm instanceof Var) {
							roleName = ((Var) clonedRoleTerm).getTerm().toString();
						} else {
							roleName = clonedRoleTerm.toString();
						}
						state.getAgent().getArtifactAgent().enact(roleName);
						ActionExecution tr = new ActionExecution();
						tr.remove(newState, engine, FormulaQualifier.qualifyStruct((Struct) option, KBType.OPTION));
						
						Tracer.queue(state.getAgent().getName(), "ARTIFACT.enact(" + qualified + ")");
					} catch (CartagoException ex) {
						throw new AORTAException("The artifact could not enact", ex);
					}
				}
				
				ActionExecution tr = new ActionExecution();
				tr.add(newState, engine, (Struct) qualified);
				tr.remove(newState, engine, FormulaQualifier.qualifyStruct((Struct) option, KBType.OPTION));

				Struct send = ml.send(Term.TRUE, new Struct("tell"), qualified);
				tr.add(newState, engine, FormulaQualifier.qualifyStruct(send, KBType.OPTION));

				logger.fine("[" + state.getAgent().getName() + "] Executing action: enact(" + qualified + ")");
				Tracer.queue(state.getAgent().getName(), "enact(" + qualified + ")");
				
			} else {
				throw new AORTAException("X in enact(X) must be a Struct (was " + qualified.getClass() + ")");
			}
		} else {
			return null;
		}
		
		return newState;
	}
	
	@Override
	public String toString() {
		return "enact(" + role + ")";
	}

}
