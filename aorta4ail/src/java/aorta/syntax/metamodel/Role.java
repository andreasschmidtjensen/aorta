/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.metamodel;

import ail.syntax.ListTerm;
import ail.syntax.ListTermImpl;
import ail.syntax.Literal;
import ail.syntax.Predicate;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Role implements MMPart {
	
	private final String name;
	private final List<Literal> objectives;

	public Role(String name, List<Literal> objectives) {
		this.name = name;
		this.objectives = objectives;
	}

	public String getName() {
		return name;
	}

	public List<Literal> getObjectives() {
		return objectives;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		if (!objectives.isEmpty()) {
			sb.append(":\n");
			for (int i = 0; i < objectives.size(); i++) {
				if (i > 0) {
					sb.append(";\n");
				}
				sb.append("\t").append(objectives.get(i));
			}
		}
		sb.append(".");
		return sb.toString();
	}
		
	@Override
	public Literal getAsLiteral() {
		Literal lit = new Literal("role");
		lit.addTerm(new Predicate(name));
		ListTerm lt = new ListTermImpl();
		for (Literal l : objectives) {
			lt.add(l);
		}
		lit.addTerm(lt);
		return lit;
	}	
}
