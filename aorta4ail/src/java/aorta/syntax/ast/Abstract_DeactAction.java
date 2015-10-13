/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.ast;

import ail.syntax.Action;
import ail.syntax.ast.Abstract_Action;
import ail.syntax.ast.Abstract_Predicate;
import aorta.semantics.actions.DeactAction;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_DeactAction extends Abstract_Action {
	
	private final Abstract_Predicate role;

	public Abstract_DeactAction(Abstract_Predicate role) {
		super("deact");
		
		this.role = role;
		
		if (role.getTermSize() > 0) {
			throw new IllegalArgumentException("Role cannot have subterms: " + role);
		}
	}

	@Override
	public Action toMCAPL() {
		return new DeactAction(role.toMCAPL());
	}
	
	@Override
	public String toString() {
		return "deact(" + role + ")";
	}
	
}
