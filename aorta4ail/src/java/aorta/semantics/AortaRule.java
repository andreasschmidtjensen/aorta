/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics;

import ail.syntax.Action;
import ail.syntax.Guard;
import ail.syntax.Literal;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class AortaRule {

	private Literal option;
	private Guard condition;
	private Action action;

	public AortaRule(Literal option, Guard condition, Action action) {
		this.option = option;
		this.condition = condition;
		this.action = action;
	}

	public Literal getOption() {
		return option;
	}
	
	public Guard getCondition() {
		return condition;
	}
	
	public Action getAction() {
		return action;
	}

	@Override
	public String toString() {
		return option + " : " + condition + " => " + action;
	}

}
