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
public class Violation implements MMPart {
	
	private final String role;
	private final String agent;
	private final String deon;
	private final Literal objective;

	public Violation(String agent, String role, String deon, Literal objective) {
		this.role = role;
		this.agent = agent;
		this.deon = deon;
		this.objective = objective;
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

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 83 * hash + Objects.hashCode(this.role);
		hash = 83 * hash + Objects.hashCode(this.agent);
		hash = 83 * hash + Objects.hashCode(this.deon);
		hash = 83 * hash + Objects.hashCode(this.objective);
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
		final Violation other = (Violation) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "viol(" + agent + ", " + role + ", " + deon + "," + objective + ")";
	}

	@Override
	public Literal getAsLiteral() {
		Literal lit = new Literal("viol");
		lit.addTerm(new Predicate(agent));
		lit.addTerm(new Predicate(role));
		lit.addTerm(new Predicate(deon));
		lit.addTerm(objective);
		return lit;
	}
}
