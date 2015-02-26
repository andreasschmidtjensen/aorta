/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.metamodel;

import ail.syntax.Literal;
import ail.syntax.Predicate;

/**
 *
 * @author asj
 */
public class Rea implements MMPart {
	
	private final String agName;
	private final String role;

	public Rea(String agName, String role) {
		this.agName = agName;
		this.role = role;
	}

	public String getAgName() {
		return agName;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "rea(" + agName + ", " + role + ")";
	}
	
	@Override
	public Literal getAsLiteral() {
		return getAsLiteral(true);
	}
	
	public Literal getAsLiteral(boolean pos) {
		Literal lit = new Literal(pos, "rea");
		lit.addTerm(new Predicate(agName));
		lit.addTerm(new Predicate(role));
		return lit;
	}
}
