package aorta.reasoning;

import alice.tuprolog.Term;
import aorta.reasoning.action.Action;
import aorta.reasoning.fml.Formula;

public class ActionRule implements ReasoningRule {
	
	protected Term option;
	protected Formula context;
	protected Action action;

	public ActionRule(Term option, Formula context, Action action) {
		this.option = option;
		this.context = context;
		this.action = action;
	}

	public Term getOption() {
		return option;
	}

	public Formula getContext() {
		return context;
	}

	public Action getAction() {
		return action;
	}

	@Override
	public String toString() {
		return option + " : " + context + " => " + action;
	}
	

}
