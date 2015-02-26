/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.metamodel;

import ail.syntax.Literal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Metamodel {
	
	// static
	private final List<Role> roles;
	private final List<Objective> objectives;
	private final List<Dependency> dependencies;
	private final List<ConditionalNorm> conditionalNorms;
	
	// dynamic
	private final Map<String,List<Rea>> reas = new HashMap<>();
	private final Set<Norm> norms = new HashSet<>();
	private final Set<Violation> violations = new HashSet<>();

	public Metamodel(List<Role> roles, List<Objective> objectives, List<Dependency> dependencies, List<ConditionalNorm> norms) {
		this.roles = roles;
		this.objectives = objectives;
		this.dependencies = dependencies;
		this.conditionalNorms = norms;
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

	public List<ConditionalNorm> getConditionalNorms() {
		return conditionalNorms;
	}

	public List<Rea> getReas(String role) {
		return reas.get(role);
	}

	public Set<Norm> getNorms() {
		return norms;
	}

	public Set<Violation> getViolations() {
		return violations;
	}

	public void addRea(Rea rea) {
		if (!reas.containsKey(rea.getRole())) {
			reas.put(rea.getRole(), new ArrayList<Rea>());
		}
		reas.get(rea.getRole()).add(rea);
	}
	
	public void addNorm(Norm norm) {
		norms.add(norm);
	}
	
	public void addViolation(Violation viol) {
		violations.add(viol);
	}
	
	public List<Literal> getAsLiterals() {
		List<Literal> list = new ArrayList<>();
		list.addAll(getAsLiterals(roles));
		list.addAll(getAsLiterals(objectives));
		list.addAll(getAsLiterals(dependencies));
		list.addAll(getAsLiterals(conditionalNorms));
		for (List<Rea> rea : reas.values()) {
			list.addAll(getAsLiterals(rea));
		}
		list.addAll(getAsLiterals(norms));
		list.addAll(getAsLiterals(violations));
		return list;
	}
	
	private List<Literal> getAsLiterals(Collection<? extends MMPart> part) {
		List<Literal> list = new ArrayList<>();
		for (MMPart p : part) {
			list.add(p.getAsLiteral());
		}
		return list;
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
		if (!conditionalNorms.isEmpty()) {
			Collections.sort(conditionalNorms);
			sb.append("\nNORMS:\n");
			for (ConditionalNorm n : conditionalNorms) {
				sb.append(n).append("\n");
			}
		}
		sb.append(dynToString());
		return sb.toString();
	}

	public String dynToString() {
		StringBuilder sb = new StringBuilder();
		if (!reas.isEmpty()) {
			sb.append("\nREAS:\n");
			for (List<Rea> rea : reas.values()) {
				sb.append(rea).append("\n");
			}
		}
		if (!norms.isEmpty()) {
			sb.append("\nACTIVE NORMS:\n");
			for (Norm norm : norms) {
				sb.append(norm).append("\n");
			}
		}
		if (!violations.isEmpty()) {
			sb.append("\nVIOLATIONS:\n");
			for (Violation viol : violations) {
				sb.append(viol).append("\n");
			}
		}
		return sb.toString();
	}
	
}
