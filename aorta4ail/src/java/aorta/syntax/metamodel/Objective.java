/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.metamodel;

import ail.syntax.ListTerm;
import ail.syntax.ListTermImpl;
import ail.syntax.Literal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Objective implements MMPart {
	
	private final Literal objective;
	private final List<Literal> subObjectives;

	public Objective(Literal objective) {
		this.objective = objective;
		this.subObjectives = new ArrayList<>();
	}

	public Objective(Literal objective, List<Literal> subObjectives) {
		this.objective = objective;
		this.subObjectives = subObjectives;
	}

	public Literal getObjective() {
		return objective;
	}

	public List<Literal> getSubObjectives() {
		return subObjectives;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(objective);
		if (!subObjectives.isEmpty()) {
			sb.append(":\n");
			for (int i = 0; i < subObjectives.size(); i++) {
				if (i > 0) {
					sb.append(";\n");
				}
				sb.append("\t").append(subObjectives.get(i));
			}
		}
		sb.append(".");
		return sb.toString();
	}

	@Override
	public Literal getAsLiteral() {
		Literal lit = new Literal("obj");
		lit.addTerm(objective);
		ListTerm lt = new ListTermImpl();
		for (Literal l : subObjectives) {
			lt.add(l);
		}
		lit.addTerm(lt);
		return lit;
	}
}
