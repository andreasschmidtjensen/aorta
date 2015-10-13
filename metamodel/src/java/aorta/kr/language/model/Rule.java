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
public class Rule {
	
	private MetaLanguage ml = new MetaLanguage();
	private Term head;
	private Term body;

	public Rule(Term head, Term body) {
		this.head = head;
		this.body = body;
	}

	public Term getHead() {
		return head;
	}

	public Term getBody() {
		return body;
	}

	public Term toProlog() {
		return new Struct(":-", ml.qualify(head), ml.qualify(body));
	}	

	@Override
	public String toString() {
		return head + " :- " + TermFormatter.toString(body) + ".";
	}

}
