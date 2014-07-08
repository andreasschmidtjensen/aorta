package aorta.reasoning;

import aorta.reasoning.action.OptAction;
import aorta.reasoning.fml.Formula;

public class OptionRule extends Rule {

	public OptionRule(Formula rule, OptAction action) {
		super(rule, action);
	}

    @Override
    public OptAction getAction() {
        return (OptAction) super.getAction(); 
    }
    
}
