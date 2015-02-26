/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics.actions;

import ail.syntax.Action;
import ail.syntax.Predicate;

/**
 *
 * @author asj
 */
public class EnactAction extends Action {
	
	private final Predicate role;

	public EnactAction(Predicate role) {
		super("enact", normalAction);
		this.role = role;
	}

	public Predicate getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "enact(" + role + ")";
	}
	
	
}
