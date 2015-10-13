/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.mm;

import alice.tuprolog.Term;
import aorta.kr.language.model.Objective;
import gov.nasa.jpf.vm.MJIEnv;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_Objective extends Abstract_MMPart {

	private String objective;
	private String[] subObjectives;

	public Abstract_Objective(String objective, String[] subObjectives) {
		this.objective = objective;
		this.subObjectives = subObjectives;
	}

	public Abstract_Objective(Objective obj) {
		objective = obj.getObjective().toString();
		subObjectives = new String[obj.getSubObjectives().size()];
		for (int i = 0; i < subObjectives.length; i++) {
			subObjectives[i] = obj.getSubObjectives().get(i).toString();
		}
	}
		
	@Override
	public Objective toAorta() {
		List<Term> obj = new ArrayList<>();
		for (String s : subObjectives) {
			obj.add(Term.createTerm(s));
		}
		return new Objective(Term.createTerm(objective), obj);
	}

	@Override
	public int newJPFObject(MJIEnv env) {	
    	int objref = env.newObject("aorta.ail.abs.mm.Abstract_Objective");
     	env.setReferenceField(objref, "objective", env.newString(objective));
     	env.setReferenceField(objref, "subObjectives", env.newStringArray(subObjectives));
      	return objref;
	}
	
}
