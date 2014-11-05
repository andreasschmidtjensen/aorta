/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.reasoning;

import aorta.reasoning.fml.Formula;
import java.util.List;

/**
 *
 * @author asj
 */
public class IfRule implements ReasoningRule {
    
    private Formula fmls;
    private List<ReasoningRule> rules;

    public IfRule(Formula fmls, List<ReasoningRule> rules) {
        this.fmls = fmls;
        this.rules = rules;
    }

    public Formula getCondition() {
        return fmls;
    }

    public List<ReasoningRule> getRules() {
        return rules;
    }

	@Override
	public String toString() {
		String s = "if " + fmls + " {\n" + rules + "\n}";
		return s;
	}
    	
}
