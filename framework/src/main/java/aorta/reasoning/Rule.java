package aorta.reasoning;

import aorta.reasoning.action.Action;
import aorta.reasoning.fml.Formula;

public abstract class Rule {

	protected Formula rule;
	protected Action action;
	
	public Rule(Formula rule, Action action) {
		this.rule = rule;
		this.action = action;
	}

    public Formula getRule() {
        return rule;
    }

    public Action getAction() {
        return action;
    }

	@Override
	public String toString() {
		return rule + " ==> " + action;
	}
	
}
