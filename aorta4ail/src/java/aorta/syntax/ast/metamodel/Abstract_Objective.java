/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.ast.metamodel;

import ail.syntax.Literal;
import ail.syntax.ast.Abstract_Literal;
import aorta.syntax.metamodel.Objective;
import gov.nasa.jpf.vm.MJIEnv;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_Objective extends Abstract_MMPart {

	private Abstract_Literal objective;
	private Abstract_Literal[] subObjectives = new Abstract_Literal[0];

	public void setObjective(Abstract_Literal objective) {
		this.objective = objective;
	}
		
	public void addSubObjective(Abstract_Literal l) {
		Abstract_Literal[] newArr = new Abstract_Literal[subObjectives.length + 1];
		System.arraycopy(subObjectives, 0, newArr, 0, subObjectives.length);
		newArr[subObjectives.length] = l;
		subObjectives = newArr;
	}
	
	@Override
	public Objective toMCAPL() {
		List<Literal> obj = new ArrayList<>();
		for (Abstract_Literal s : subObjectives) {
			obj.add(s.toMCAPL());
		}
		return new Objective(objective.toMCAPL(), obj);
	}

	@Override
	public int newJPFObject(MJIEnv env) {	
    	int objref = env.newObject("aorta.syntax.ast.metamodel.Abstract_Objective");
     	env.setReferenceField(objref, "objective", objective.newJPFObject(env));
     	env.setReferenceField(objref, "subObjectives", addArray(env, "ail.syntax.ast.Abstract_Literal", subObjectives));
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
