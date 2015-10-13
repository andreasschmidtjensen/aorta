/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.mm;

import alice.tuprolog.Term;
import aorta.kr.language.model.Dependency;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_Dependency extends Abstract_MMPart {

	private String dependant;
	private String dependee;
	private String objective;

	public Abstract_Dependency(String dependant, String dependee, String objective) {
		this.dependant = dependant;
		this.dependee = dependee;
		this.objective = objective;
	}
	
	public Abstract_Dependency(Dependency dep) {
		dependant = dep.getDependant();
		dependee = dep.getDependee();
		objective = dep.getObjective().toString();
	}

	@Override
	public Dependency toAorta() {
		return new Dependency(dependant, dependee, Term.createTerm(objective));
	}

	@Override
	public int newJPFObject(MJIEnv env) {		
    	int objref = env.newObject("aorta.ail.abs.mm.Abstract_Dependency");
     	env.setReferenceField(objref, "dependant", env.newString(dependant));
     	env.setReferenceField(objref, "dependee", env.newString(dependee));
     	env.setReferenceField(objref, "objective", env.newString(objective));
      	return objref;
	}
	
}
