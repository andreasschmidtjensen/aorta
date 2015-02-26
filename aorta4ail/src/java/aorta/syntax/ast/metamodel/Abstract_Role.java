/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.ast.metamodel;

import ail.syntax.Literal;
import ail.syntax.ast.Abstract_Literal;
import aorta.syntax.metamodel.Role;
import gov.nasa.jpf.vm.MJIEnv;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_Role extends Abstract_MMPart {

	private String name;
	private Abstract_Literal[] objectives = new Abstract_Literal[0];

	public void setName(String name) {
		this.name = name;
	}

	public void addObjective(Abstract_Literal l) {
		Abstract_Literal[] newArr = new Abstract_Literal[objectives.length + 1];
		System.arraycopy(objectives, 0, newArr, 0, objectives.length);
		newArr[objectives.length] = l;
		objectives = newArr;
	}
	
	@Override
	public Role toMCAPL() {
		List<Literal> obj = new ArrayList<>();
		for (Abstract_Literal s : objectives) {
			obj.add(s.toMCAPL());
		}
		return new Role(name, obj);
	}

	@Override
	public int newJPFObject(MJIEnv env) {		
    	int objref = env.newObject("aorta.syntax.ast.metamodel.Abstract_Role");
     	env.setReferenceField(objref, "name", env.newString(name));
     	env.setReferenceField(objref, "objectives", addArray(env, "ail.syntax.ast.Abstract_Literal", objectives));
      	return objref;
	}
	
	private int addArray(MJIEnv env, String cls, Abstract_Literal[] array) {		
		int arrayRef = env.newObjectArray(cls, array.length);
       	for (int i = 0; i < array.length; i++) {
       		env.setReferenceArrayElement(arrayRef, i, array[i].newJPFObject(env));
       	}
      	return arrayRef;
	}
	
}
