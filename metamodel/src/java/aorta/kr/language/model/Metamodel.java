/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language.model;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import aorta.kr.language.OrganizationImportException;
import aorta.kr.language.OrganizationLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Metamodel {
	
	private List<Role> roles;
	private List<Objective> objectives;
	private List<Dependency> dependencies;
	private List<Norm> norms;
	private List<Rule> rules;

	public static Metamodel load(String location) throws IOException, OrganizationImportException {
		InputStream is;
		if (new File(location).exists()) {
			is = new FileInputStream(location);
		} else {
			is = Metamodel.class.getResourceAsStream("/" + location);
		}
		if (is == null) {
			return null;
		} else {
			return new OrganizationLoader().loadMetamodel(is);
		}
	}
	
	public Metamodel() {
		this.roles = new ArrayList<>();
		this.objectives = new ArrayList<>();
		this.dependencies = new ArrayList<>();
		this.norms = new ArrayList<>();
		this.rules = new ArrayList<>();
	}

	public Metamodel(List<Role> roles, List<Objective> objectives, List<Dependency> dependencies, List<Norm> norms, List<Rule> rules) {
		this.roles = roles;
		this.objectives = objectives;
		this.dependencies = dependencies;
		this.norms = norms;
		this.rules = rules;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public Role getRole(String roleName) {
		for (Role role : roles) {
			if (roleName.equals(role.getName())) {
				return role;
			}
		}
		return null;
	}
	
	public List<Objective> getObjectives() {
		return objectives;
	}

	public Objective getObjective(Term objective) {
		for (Objective obj : objectives) {
			if (obj.getObjective().equals(objective)) {
				return obj;
			}
		}
		return null;
	}
	
	public List<Dependency> getDependencies() {
		return dependencies;
	}

	public List<Norm> getNorms() {
		return norms;
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
		for (Norm n : norms) {
			termList.append(n.toProlog());
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
		sb.append("\nOBJECTIVES:\n");
		for (Objective o : objectives) {
			sb.append(o).append("\n");
		}
		if (!dependencies.isEmpty()) {
			sb.append("\nDEPENDENCIES:\n");
			for (Dependency d : dependencies) {
				sb.append(d).append("\n");
			}
		}
		if (!norms.isEmpty()) {
			Collections.sort(norms);
			sb.append("\nNORMS:\n");
			for (Norm n : norms) {
				sb.append(n).append("\n");
			}
		}
		if (!rules.isEmpty()) {
			sb.append("\nRULES:\n");
			for (Rule r : rules) {
				sb.append(r).append("\n");
			}
		}
		
		return sb.toString();
	}
	
}
