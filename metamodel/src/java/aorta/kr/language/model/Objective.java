/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language.model;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.TermFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Objective {
	
	private Term objective;
	private List<Term> subObjectives;

	public Objective(Term objective) {
		this.objective = objective;
		this.subObjectives = new ArrayList<>();
	}

	public Objective(Term objective, List<Term> subObjectives) {
		this.objective = objective;
		this.subObjectives = subObjectives;
	}

	public Term getObjective() {
		return objective;
	}

	public List<Term> getSubObjectives() {
		return subObjectives;
	}
	
	public Term toProlog() {
		MetaLanguage ml = new MetaLanguage();
		Struct subObjs = new Struct();
		for (Term o : subObjectives) {
			subObjs.append(ml.qualify(o));
		}
		return ml.objective(ml.qualify(objective), subObjs);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(TermFormatter.toString(objective));
		if (!subObjectives.isEmpty()) {
			sb.append(": ");
			for (int i = 0; i < subObjectives.size(); i++) {
				if (i > 0) {
					sb.append("; ");
				}
				sb.append(TermFormatter.toString(subObjectives.get(i)));
			}
		}
		sb.append(".");
		return sb.toString();
	}
}
