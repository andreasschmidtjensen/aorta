/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.metamodel;

import ail.syntax.Literal;
import ail.syntax.Predicate;
import java.util.Objects;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Norm implements MMPart {
	
	public static final String OBLIGATION = "obliged";
	public static final String PROHIBITION = "forbidden";
	
	private final String role;
	private final String agent;
	private final String deon;
	private final Literal objective;
	private final Literal deadline;

	public Norm(String agent, String role, String deon, Literal objective, Literal deadline) {
		this.role = role;
		this.agent = agent;
		this.deon = deon;
		this.objective = objective;
		this.deadline = deadline;
	}

	public String getAgent() {
		return agent;
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

	@Override
	public String toString() {
		return agent + "(" + role + ") [" + deon + "]: " + objective + " < " + deadline;			
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + Objects.hashCode(this.role);
		hash = 71 * hash + Objects.hashCode(this.agent);
		hash = 71 * hash + Objects.hashCode(this.deon);
		hash = 71 * hash + Objects.hashCode(this.objective);
		hash = 71 * hash + Objects.hashCode(this.deadline);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Norm other = (Norm) obj;
		if (!Objects.equals(this.role, other.role)) {
			return false;
		}
		if (!Objects.equals(this.agent, other.agent)) {
			return false;
		}
		if (!Objects.equals(this.deon, other.deon)) {
			return false;
		}
		if (!Objects.equals(this.objective, other.objective)) {
			return false;
		}
		if (!Objects.equals(this.deadline, other.deadline)) {
			return false;
		}
		return true;
	}

	@Override
	public Literal getAsLiteral() {
		Literal lit = new Literal("norm");
		lit.addTerm(new Predicate(agent));
		lit.addTerm(new Predicate(role));
		lit.addTerm(new Predicate(deon));
		lit.addTerm(objective);
		lit.addTerm(deadline);
		return lit;
	}
}
