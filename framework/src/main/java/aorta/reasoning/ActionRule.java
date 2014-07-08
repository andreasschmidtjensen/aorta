package aorta.reasoning;

import aorta.reasoning.action.ActAction;
import aorta.reasoning.fml.Formula;

public class ActionRule extends Rule {

	public ActionRule(Formula rule, ActAction action) {
		super(rule, action);
	}

    @Override
    public ActAction getAction() {
        return (ActAction) super.getAction();
    }

}
