/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.metamodel;

import ail.syntax.Literal;
import ail.syntax.Predicate;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Dependency implements MMPart {
	
	private final String dependant;
	private final String dependee;
	private final Literal objective;

	public Dependency(String dependant, String dependee, Literal objective) {
		this.dependant = dependant;
		this.dependee = dependee;
		this.objective = objective;
	}

	public String getDependant() {
		return dependant;
	}

	public String getDependee() {
		return dependee;
	}

	public Literal getObjective() {
		return objective;
	}
	
	@Override
	public String toString() {
		return dependant + " > " + dependee + ": " + objective + ".";
	}
	
	@Override
	public Literal getAsLiteral() {
		Literal lit = new Literal("dep");
		lit.addTerm(new Predicate(dependant));
		lit.addTerm(new Predicate(dependee));
		lit.addTerm(objective);
		return lit;
	}
}
