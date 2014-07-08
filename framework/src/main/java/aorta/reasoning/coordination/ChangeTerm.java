/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.reasoning.coordination;

import alice.tuprolog.Term;
import aorta.kr.util.Qualifier;
import aorta.kr.util.TermFormatter;
import aorta.reasoning.fml.ReasoningFormula;

/**
 *
 * @author asj
 */
public class ChangeTerm {
	
	private boolean addition;
	private Term term;

	public ChangeTerm(boolean addition, ReasoningFormula fml) {
		this.addition = addition;
		this.term = Qualifier.qualifyFormula(fml, true);
	}
	
	public ChangeTerm(boolean addition, Term term) {
		this.addition = addition;
		this.term = term;
	}

	public Term getTerm() {
		return term;
	}

	public boolean isAddition() {
		return addition;
	}
	
	public boolean isSubtraction() {
		return !addition;
	}

	@Override
	public String toString() {
		return (addition ? "+" : "-") + TermFormatter.toString(term);
	}

}
