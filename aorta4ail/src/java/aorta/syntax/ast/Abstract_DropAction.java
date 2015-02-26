/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.ast;

import ail.syntax.Action;
import ail.syntax.ast.Abstract_Action;
import ail.syntax.ast.Abstract_Predicate;
import aorta.semantics.actions.DropAction;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_DropAction extends Abstract_Action {

	private final Abstract_Predicate obj;

	public Abstract_DropAction(Abstract_Predicate obj) {
		super(obj, normalAction);
		
		this.obj = obj;
	}

	@Override
	public Action toMCAPL() {
		return new DropAction(obj.toMCAPL());
	}
	
	@Override
	public String toString() {
		return "drop(" + super.toString() + ")";
	}
	
}
