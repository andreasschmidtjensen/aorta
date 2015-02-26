/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.metamodel;

import ail.syntax.Literal;
import ail.syntax.Predicate;
import ail.syntax.VarTerm;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ConditionalNorm implements MMPart, Comparable<ConditionalNorm> {
	
	private String role;
	private VarTerm agentVar;
	private String deon;
	private Literal objective;
	private Literal deadline;
	private Literal condition;

	public ConditionalNorm() {
	}

	public ConditionalNorm(String role, VarTerm agentVar, String deon, Literal objective, Literal deadline, Literal condition) {
		this(role, deon, objective, deadline, condition);
		this.agentVar = agentVar;
	}
	
	public ConditionalNorm(String role, String deon, Literal objective, Literal deadline, Literal condition) {
		setData(role, deon, objective, deadline, condition);
	}

	public final void setData(String role, String deon, Literal objective, Literal deadline, Literal condition) {
		this.role = role;
		this.deon = deon;
		this.objective = objective;
		this.deadline = deadline;
		this.condition = condition;
	}

	public boolean hasAgentVar() {
		return agentVar != null;
	}
	
	public VarTerm getAgentVar() {
		return agentVar;
	}

	public void setAgentVar(VarTerm agentVar) {
		this.agentVar = agentVar;
	}

	public String getDeon() {
		return deon;
	}

	public String getRole() {
		return role;
	}

	public Literal getObjective() {
		return objective;
	}

	public Literal getDeadline() {
		return deadline;
	}

	public Literal getCondition() {
		return condition;
	}
	
	@Override
	public String toString() {
		return role + (hasAgentVar() ? "=" + agentVar : "") + " [" + deon + "]: " + objective + " < " + deadline + " | " + condition + ".";			
	}

	@Override
	public int compareTo(ConditionalNorm o) {
		return role.compareTo(o.role);
	}

	@Override
	public Literal getAsLiteral() {
		Literal lit = new Literal("cond");
		lit.addTerm(new Predicate(role));
		lit.addTerm(new Predicate(deon));
		lit.addTerm(objective);
		lit.addTerm(deadline);
		lit.addTerm(condition);
		return lit;
	}
		
}
