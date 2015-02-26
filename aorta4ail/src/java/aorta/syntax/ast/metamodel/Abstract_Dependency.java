/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.ast.metamodel;

import ail.syntax.Term;
import ail.syntax.ast.Abstract_Literal;
import aorta.syntax.metamodel.Dependency;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_Dependency extends Abstract_MMPart {

	private String dependant;
	private String dependee;
	private Abstract_Literal objective;

	public void setObjective(Abstract_Literal objective) {
		this.objective = objective;
	}

	public void setDependee(String dependee) {
		this.dependee = dependee;
	}

	public void setDependant(String dependant) {
		this.dependant = dependant;
	}

	@Override
	public Dependency toMCAPL() {
		return new Dependency(dependant, dependee, objective.toMCAPL());
	}

	@Override
	public int newJPFObject(MJIEnv env) {		
    	int objref = env.newObject("aorta.syntax.ast.metamodel.Abstract_Dependency");
     	env.setReferenceField(objref, "dependant", env.newString(dependant));
     	env.setReferenceField(objref, "dependee", env.newString(dependee));
     	env.setReferenceField(objref, "objective", objective.newJPFObject(env));
      	return objref;
	}
	
}
