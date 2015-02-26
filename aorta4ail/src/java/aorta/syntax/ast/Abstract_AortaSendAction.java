/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.syntax.ast;

import ail.syntax.Action;
import ail.syntax.SendAction;
import ail.syntax.Term;
import ail.syntax.ast.Abstract_Action;
import ail.syntax.ast.Abstract_Predicate;
import ail.syntax.ast.Abstract_Term;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Abstract_AortaSendAction extends Abstract_Action {

	private final Abstract_Term agent;
	private final Abstract_Predicate message;

	public Abstract_AortaSendAction(Abstract_Term agent, Abstract_Predicate message) {
		super("send");
		this.agent = agent;
		this.message = message;
	}

	@Override
	public Action toMCAPL() {
		SendAction act = new SendAction((Term) agent.toMCAPL(), 0, message.toMCAPL());
		return act;
	}
	
	@Override
	public String toString() {
		return "send(" + agent + ", " + message + ")";
	}
	
}
