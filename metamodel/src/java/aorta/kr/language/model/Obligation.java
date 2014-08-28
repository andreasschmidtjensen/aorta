/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language.model;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.kr.language.MetaLanguage;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Obligation {
	
	private MetaLanguage ml = new MetaLanguage();
	private String role;
	private Term objective;
	private Term deadline;
	private Term condition;

	public Obligation(String role, Term objective, Term deadline, Term condition) {
		this.role = role;
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
		return ml.condition(new Struct(role), ml.qualify(objective), ml.qualify(deadline), ml.qualify(condition));
	}	

	@Override
	public String toString() {
		return role + ": " + objective + " < " + deadline + " | " + condition + ".";
	}
		
}
