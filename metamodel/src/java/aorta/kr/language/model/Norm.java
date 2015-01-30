/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language.model;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.TermFormatter;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Norm implements Comparable<Norm> {
	
	public static final String OBLIGATION = "obliged";
	public static final String PROHIBITION = "forbidden";
	
	private MetaLanguage ml = new MetaLanguage();
	private String role;
	private String deon;
	private Term objective;
	private Term deadline;
	private Term condition;

	public Norm(String role, String deon, Term objective, Term deadline, Term condition) {
		this.role = role;
		this.deon = deon;
		this.objective = objective;
		this.deadline = deadline;
		this.condition = condition;
	}

	public String getRole() {
		return role;
	}

	public Term getObjective() {
		return objective;
	}

	public Term getDeadline() {
		return deadline;
	}

	public Term getCondition() {
		return condition;
	}
	
	public Term toProlog() {
		return ml.condition(new Struct(role), new Struct(deon), ml.qualify(objective), ml.qualify(deadline), ml.qualify(condition));
	}	

	@Override
	public String toString() {
		return role + " [" + deon + "]: " + TermFormatter.toString(objective) + " < " + TermFormatter.toString(deadline) + " | " + TermFormatter.toString(condition) + ".";			
	}

	@Override
	public int compareTo(Norm o) {
		return role.compareTo(o.role);
	}
		
}
