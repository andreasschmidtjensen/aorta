/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language.model;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.kr.language.MetaLanguage;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Role {
	
	private String name;
	private List<Term> objectives;

	public Role(String name, List<Term> objectives) {
		this.name = name;
		this.objectives = objectives;
	}

	public String getName() {
		return name;
	}

	public List<Term> getObjectives() {
		return objectives;
	}

	public Term toProlog() {
		MetaLanguage ml = new MetaLanguage();
		
		Struct objs = new Struct();
		for (Term o : objectives) {
			objs.append(ml.qualify(o));
		}
		return ml.role(new Struct(name), objs);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		if (!objectives.isEmpty()) {
			sb.append(": ");
			for (int i = 0; i < objectives.size(); i++) {
				if (i > 0) {
					sb.append("; ");
				}
				sb.append(objectives.get(i));
			}
		}
		sb.append(".");
		return sb.toString();
	}
			
}
