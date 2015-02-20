/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.mm;

import aorta.kr.language.model.Dependency;
import aorta.kr.language.model.Metamodel;
import aorta.kr.language.model.Norm;
import aorta.kr.language.model.Objective;
import aorta.kr.language.model.Role;
import aorta.kr.language.model.Rule;
import gov.nasa.jpf.vm.MJIEnv;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_Metamodel {
	
	private Abstract_Role[] roles;
	private Abstract_Objective[] objectives;
	private Abstract_Dependency[] dependencies;
	private Abstract_Norm[] norms;
	private Abstract_Rule[] rules;

	public Abstract_Metamodel(Abstract_Role[] roles, Abstract_Objective[] objectives, Abstract_Dependency[] dependencies, Abstract_Norm[] norms, Abstract_Rule[] rules) {
		this.roles = roles;
		this.objectives = objectives;
		this.dependencies = dependencies;
		this.norms = norms;
		this.rules = rules;
	}

	public Abstract_Metamodel(Metamodel mm) {
		roles = new Abstract_Role[mm.getRoles().size()];
		objectives = new Abstract_Objective[mm.getObjectives().size()];
		dependencies = new Abstract_Dependency[mm.getDependencies().size()];
		norms = new Abstract_Norm[mm.getNorms().size()];
		rules = new Abstract_Rule[mm.getRules().size()];
		
		for (int i = 0; i < roles.length; i++) {
			roles[i] = new Abstract_Role(mm.getRoles().get(i));
		}
		for (int i = 0; i < objectives.length; i++) {
			objectives[i] = new Abstract_Objective(mm.getObjectives().get(i));
		}
		for (int i = 0; i < dependencies.length; i++) {
			dependencies[i] = new Abstract_Dependency(mm.getDependencies().get(i));
		}
		for (int i = 0; i < norms.length; i++) {
			norms[i] = new Abstract_Norm(mm.getNorms().get(i));
		}
		for (int i = 0; i < rules.length; i++) {
			rules[i] = new Abstract_Rule(mm.getRules().get(i));
		}
	}
		
	public Metamodel toAORTA() {
		List<Role> aRoles = new ArrayList<>();
		List<Objective> aObj = new ArrayList<>();
		List<Dependency> aDep = new ArrayList<>();
		List<Norm> aNorms = new ArrayList<>();
		List<Rule> aRules = new ArrayList<>();
		for (Abstract_Role r : roles) {
			aRoles.add(r.toAorta());
		}
		for (Abstract_Objective r : objectives) {
			aObj.add(r.toAorta());
		}
		for (Abstract_Dependency r : dependencies) {
			aDep.add(r.toAorta());
		}
		for (Abstract_Norm r : norms) {
			aNorms.add(r.toAorta());
		}
		for (Abstract_Rule r : rules) {
			aRules.add(r.toAorta());
		}
		
		return new Metamodel(aRoles, aObj, aDep, aNorms, aRules);		
	}
	
	public int newJPFObject(MJIEnv env) {
    	int objref = env.newObject("aorta.ail.abs.mm.Abstract_Metamodel");

      	env.setReferenceField(objref, "roles", addArray(env, "aorta.ail.abs.mm.Abstract_Role", roles));
      	env.setReferenceField(objref, "objectives", addArray(env, "aorta.ail.abs.mm.Abstract_Objective", objectives));
      	env.setReferenceField(objref, "dependencies", addArray(env, "aorta.ail.abs.mm.Abstract_Dependency", dependencies));
      	env.setReferenceField(objref, "norms", addArray(env, "aorta.ail.abs.mm.Abstract_Norm", norms));
      	env.setReferenceField(objref, "rules", addArray(env, "aorta.ail.abs.mm.Abstract_Rule", rules));
		
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
