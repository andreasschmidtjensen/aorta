/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language.model;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Struct;
import alice.tuprolog.Theory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Metamodel {
	
	private List<Role> roles;
	private List<Objective> objectives;
	private List<Dependency> dependencies;
	private List<Obligation> obligations;
	private List<Rule> rules;

	public Metamodel() {
		this.roles = new ArrayList<>();
		this.objectives = new ArrayList<>();
		this.dependencies = new ArrayList<>();
		this.obligations = new ArrayList<>();
		this.rules = new ArrayList<>();
	}

	public Metamodel(List<Role> roles, List<Objective> objectives, List<Dependency> dependencies, List<Obligation> obligations, List<Rule> rules) {
		this.roles = roles;
		this.objectives = objectives;
		this.dependencies = dependencies;
		this.obligations = obligations;
		this.rules = rules;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public List<Objective> getObjectives() {
		return objectives;
	}

	public List<Dependency> getDependencies() {
		return dependencies;
	}

	public List<Obligation> getObligations() {
		return obligations;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public Theory createTheory() throws InvalidTheoryException {
		Struct termList = new Struct();
		for (Role r : roles) {
			termList.append(r.toProlog());
		}
		for (Objective o : objectives) {
			termList.append(o.toProlog());
		}
		for (Dependency d : dependencies) {
			termList.append(d.toProlog());
		}
		for (Obligation o : obligations) {
			termList.append(o.toProlog());
		}
		for (Rule r : rules) {
			termList.append(r.toProlog());
		}
		return new Theory(termList);		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ROLES:\n");
		for (Role r : roles) {
			sb.append(r).append("\n");
		}
		sb.append("OBJECTIVES:\n");
		for (Objective o : objectives) {
			sb.append(o).append("\n");
		}
		if (!dependencies.isEmpty()) {
			sb.append("DEPENDENCIES:\n");
			for (Dependency d : dependencies) {
				sb.append(d).append("\n");
			}
		}
		if (!obligations.isEmpty()) {
			sb.append("OBLIGATIONS:\n");
			for (Obligation o : obligations) {
				sb.append(o).append("\n");
			}
		}
		if (!rules.isEmpty()) {
			sb.append("RULES:\n");
			for (Rule r : rules) {
				sb.append(r).append("\n");
			}
		}
		
		return sb.toString();
	}
	
}
