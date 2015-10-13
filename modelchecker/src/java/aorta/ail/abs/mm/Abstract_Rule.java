/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.mm;

import alice.tuprolog.Term;
import aorta.kr.language.model.Rule;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_Rule extends Abstract_MMPart {
	
	private String head;
	private String body;

	public Abstract_Rule(String head, String body) {
		this.head = head;
		this.body = body;
	}

	public Abstract_Rule(Rule rule) {
		head = rule.getHead().toString();
		body = rule.getBody().toString();
	}
	
	@Override
	public Rule toAorta() {
		return new Rule(Term.createTerm(head), Term.createTerm(body));
	}

	@Override
	public int newJPFObject(MJIEnv env) {
    	int objref = env.newObject("aorta.ail.abs.mm.Abstract_Rule");
     	env.setReferenceField(objref, "head", env.newString(head));
     	env.setReferenceField(objref, "body", env.newString(body));
      	return objref;
	}
	
}
