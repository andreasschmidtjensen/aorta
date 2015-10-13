/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.ast.metamodel;

import ail.syntax.VarTerm;
import ail.syntax.ast.Abstract_Literal;
import ail.syntax.ast.Abstract_VarTerm;
import aorta.syntax.metamodel.ConditionalNorm;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_ConditionalNorm extends Abstract_MMPart {

	private String role;
	private Abstract_VarTerm agentVar;
	private String deon;
	private Abstract_Literal objective;
	private Abstract_Literal deadline;
	private Abstract_Literal condition;

	public void setRole(String role) {
		this.role = role;
	}

	public void setAgentVar(Abstract_VarTerm agentVar) {
		this.agentVar = agentVar;
	}

	public void setDeon(String deon) {
		this.deon = deon;
	}

	public void setObjective(Abstract_Literal objective) {
		this.objective = objective;
	}

	public void setDeadline(Abstract_Literal deadline) {
		this.deadline = deadline;
	}

	public void setCondition(Abstract_Literal condition) {
		this.condition = condition;
	}

	@Override
	public ConditionalNorm toMCAPL() {
		if (agentVar == null) {
			return new ConditionalNorm(role, deon, objective.toMCAPL(), deadline.toMCAPL(), condition.toMCAPL());
		} else {
			return new ConditionalNorm(role, (VarTerm) agentVar.toMCAPL(), deon, objective.toMCAPL(), deadline.toMCAPL(), condition.toMCAPL());
		}
	}

	@Override
	public int newJPFObject(MJIEnv env) {		
    	int objref = env.newObject("aorta.syntax.ast.metamodel.Abstract_ConditionalNorm");
     	env.setReferenceField(objref, "role", env.newString(role));
		if (agentVar != null) {
			env.setReferenceField(objref, "agentVar", agentVar.newJPFObject(env));
		}
     	env.setReferenceField(objref, "deon", env.newString(deon));
     	env.setReferenceField(objref, "objective", objective.newJPFObject(env));
     	env.setReferenceField(objref, "deadline", deadline.newJPFObject(env));
     	env.setReferenceField(objref, "condition", condition.newJPFObject(env));
      	return objref;
	}
	
}
