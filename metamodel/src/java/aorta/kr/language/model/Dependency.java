/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language.model;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.kr.language.MetaLanguage;
import static aorta.kr.util.TermFormatter.toString;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Dependency {
	
	private String dependant;
	private String dependee;
	private Term objective;

	public Dependency(String dependant, String dependee, Term objective) {
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

	public Term getObjective() {
		return objective;
	}
	
	public Term toProlog() {
		MetaLanguage ml = new MetaLanguage();
		return ml.dependency(new Struct(dependant), new Struct(dependee), ml.qualify(objective));
	}	

	@Override
	public String toString() {
		return dependant + " > " + dependee + ": " + toString(objective) + ".";
	}
	
}
