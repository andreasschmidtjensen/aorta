/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.mm;

import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.kr.language.model.Norm;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_Norm extends Abstract_MMPart {

	private String role;
	private String agentVar;
	private String deon;
	private String objective;
	private String deadline;
	private String condition;

	public Abstract_Norm(String role, String agentVar, String deon, String objective, String deadline, String condition) {
		this.role = role;
		this.agentVar = agentVar;
		this.deon = deon;
		this.objective = objective;
		this.deadline = deadline;
		this.condition = condition;
	}
	
	public Abstract_Norm(Norm norm) {
		role = norm.getRole();
		agentVar = norm.getAgentVar() == null ? null : norm.getAgentVar().toString();
		deon = norm.getDeon();
		objective = norm.getObjective().toString();
		deadline = norm.getDeadline().toString();
		condition = norm.getCondition().toString();
	}

	@Override
	public Norm toAorta() {
		if (agentVar == null) {
			return new Norm(role, deon, Term.createTerm(objective), Term.createTerm(deadline), Term.createTerm(condition));
		} else {
			return new Norm(role, new Var(agentVar), deon, Term.createTerm(objective), Term.createTerm(deadline), Term.createTerm(condition));
		}
	}

	@Override
	public int newJPFObject(MJIEnv env) {		
    	int objref = env.newObject("aorta.ail.abs.mm.Abstract_Norm");
     	env.setReferenceField(objref, "role", env.newString(role));
		if (agentVar != null) {
			env.setReferenceField(objref, "agentVar", env.newString(agentVar));
		}
     	env.setReferenceField(objref, "deon", env.newString(deon));
     	env.setReferenceField(objref, "objective", env.newString(objective));
     	env.setReferenceField(objref, "deadline", env.newString(deadline));
     	env.setReferenceField(objref, "condition", env.newString(condition));
      	return objref;
	}
	
}
