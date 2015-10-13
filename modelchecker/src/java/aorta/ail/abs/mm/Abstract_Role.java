/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.mm;

import alice.tuprolog.Term;
import aorta.kr.language.model.Role;
import gov.nasa.jpf.vm.MJIEnv;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_Role extends Abstract_MMPart {

	private String name;
	private String[] objectives;

	public Abstract_Role(String name, String[] objectives) {
		this.name = name;
		this.objectives = objectives;
	}

	public Abstract_Role(Role role) {
		name = role.getName();
		objectives = new String[role.getObjectives().size()];
		for (int i = 0; i < objectives.length; i++) {
			objectives[i] = role.getObjectives().get(i).toString();
		}
	}	
	
	@Override
	public Role toAorta() {
		List<Term> obj = new ArrayList<>();
		for (String s : objectives) {
			obj.add(Term.createTerm(s));
		}
		return new Role(name, obj);
	}

	@Override
	public int newJPFObject(MJIEnv env) {		
    	int objref = env.newObject("aorta.ail.abs.mm.Abstract_Role");
     	env.setReferenceField(objref, "name", env.newString(name));
     	env.setReferenceField(objref, "objectives", env.newStringArray(objectives));
      	return objref;
	}
	
}
