/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.ast.metamodel;

import aorta.syntax.ast.Abstract_AortaRule;
import aorta.syntax.metamodel.Dependency;
import aorta.syntax.metamodel.Metamodel;
import aorta.syntax.metamodel.ConditionalNorm;
import aorta.syntax.metamodel.Objective;
import aorta.syntax.metamodel.Role;
import gov.nasa.jpf.vm.MJIEnv;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_Metamodel {
	
	private Abstract_Role[] roles = new Abstract_Role[0];
	private Abstract_Objective[] objectives = new Abstract_Objective[0];
	private Abstract_Dependency[] dependencies = new Abstract_Dependency[0];
	private Abstract_ConditionalNorm[] conditionalNorms = new Abstract_ConditionalNorm[0];

	public void addRole(Abstract_Role r) {
		Abstract_Role[] newArr = new Abstract_Role[roles.length + 1];
		System.arraycopy(roles, 0, newArr, 0, roles.length);
		newArr[roles.length] = r;
		roles = newArr;
	}
	
	public void addObjective(Abstract_Objective o) {
		Abstract_Objective[] newArr = new Abstract_Objective[objectives.length + 1];
		System.arraycopy(objectives, 0, newArr, 0, objectives.length);
		newArr[objectives.length] = o;
		objectives = newArr;
	}
	
	public void addDependency(Abstract_Dependency d) {
		Abstract_Dependency[] newArr = new Abstract_Dependency[dependencies.length + 1];
		System.arraycopy(dependencies, 0, newArr, 0, dependencies.length);
		newArr[dependencies.length] = d;
		dependencies = newArr;
	}
	
	public void addConditionalNorm(Abstract_ConditionalNorm c) {
		Abstract_ConditionalNorm[] newArr = new Abstract_ConditionalNorm[conditionalNorms.length + 1];
		System.arraycopy(conditionalNorms, 0, newArr, 0, conditionalNorms.length);
		newArr[conditionalNorms.length] = c;
		conditionalNorms = newArr;
	}
	
	public Metamodel toMCAPL() {
		List<Role> aRoles = new ArrayList<>();
		List<Objective> aObj = new ArrayList<>();
		List<Dependency> aDep = new ArrayList<>();
		List<ConditionalNorm> aNorms = new ArrayList<>();
		for (Abstract_Role r : roles) {
			aRoles.add(r.toMCAPL());
		}
		for (Abstract_Objective r : objectives) {
			aObj.add(r.toMCAPL());
		}
		for (Abstract_Dependency r : dependencies) {
			aDep.add(r.toMCAPL());
		}
		for (Abstract_ConditionalNorm r : conditionalNorms) {
			aNorms.add(r.toMCAPL());
		}
		
		return new Metamodel(aRoles, aObj, aDep, aNorms);		
	}
	
	public int newJPFObject(MJIEnv env) {
    	int objref = env.newObject("aorta.syntax.ast.metamodel.Abstract_Metamodel");

      	env.setReferenceField(objref, "roles", addArray(env, "aorta.syntax.ast.metamodel.Abstract_Role", roles));
      	env.setReferenceField(objref, "objectives", addArray(env, "aorta.syntax.ast.metamodel.Abstract_Objective", objectives));
      	env.setReferenceField(objref, "dependencies", addArray(env, "aorta.syntax.ast.metamodel.Abstract_Dependency", dependencies));
      	env.setReferenceField(objref, "norms", addArray(env, "aorta.syntax.ast.metamodel.Abstract_Norm", conditionalNorms));
		
      	return objref;
	}
	
	private int addArray(MJIEnv env, String cls, Abstract_MMPart[] array) {		
		int arrayRef = env.newObjectArray(cls, array.length);
       	for (int i = 0; i < array.length; i++) {
       		env.setReferenceArrayElement(arrayRef, i, array[i].newJPFObject(env));
       	}
      	return arrayRef;
	}
	
}
