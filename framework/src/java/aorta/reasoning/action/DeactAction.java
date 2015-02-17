package aorta.reasoning.action;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AORTAException;
import aorta.AgentState;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.util.FormulaQualifier;
import aorta.kr.language.MetaLanguage;
import aorta.tracer.Tracer;
import aorta.logging.Logger;
import aorta.ts.rules.ActionExecution;
import cartago.CartagoException;

public class DeactAction extends Action {
	public static final Logger logger = Logger.getLogger(DeactAction.class.getName());
	
	private Term role;
	
	public DeactAction(Term role) {
		this.role = role;
	}

	public Term getRole() {
		return role;
	}

	@Override
	protected AgentState executeAction(Term option, AgentState state)
			throws AORTAException {
		AgentState newState = state;
		
		final Term agent = Term.createTerm(state.getAgent().getName());
		final Term clonedRoleTerm = Term.createTerm(role.toString());
		
		MetaLanguage ml = new MetaLanguage();
				
		Term reaDef = ml.rea(agent, clonedRoleTerm);
		
		Term term = FormulaQualifier.qualifyTerm(reaDef, KBType.ORGANIZATION.getType());
		MentalState ms = state.getMentalState();

		SolveInfo result = ms.solve(term);
		
		logger.finest("Attempting to deact: " + result.isSuccess());
		
		if (result.isSuccess()) {
			state.addBindings(result);
			
			Term qualified = FormulaQualifier.qualifyTerm(reaDef, KBType.ORGANIZATION.getType());
			ms.unify(qualified, state.getBindings());
			
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
						state.getAgent().getArtifactAgent().deact(roleName);
						
						Tracer.queue(state.getAgent().getName(), "deact(" + qualified + ")");
					} catch (CartagoException ex) {
						throw new AORTAException("The artifact could not deact", ex);
					}
				} else {
					ActionExecution tr = new ActionExecution();
					tr.remove(newState, (Struct) qualified);

					logger.fine("[" + state.getAgent().getName() + "] Executing action: deact(" + qualified + ")");
					Tracer.queue(state.getAgent().getName(), "deact(" + qualified + ")");
				}
			} else {
				throw new AORTAException("X in deact(X) must be a Struct (was " + qualified.getClass() + ")");
			}
		} else {
			return null;
		}
		
		return newState;
	}
	
	@Override
	public String toString() {
		return "deact(" + role + ")";
	}

}
