package aorta.reasoning.fml;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;

public class NegatedFormula extends Formula {

	private Formula formula; 
	
	public NegatedFormula(Formula formula) {
		this.formula = formula;
	}
	
	public Formula getFormula() {
		return formula;
	}
	
	@Override
	public String toString() {
		return "~(" + formula.toString() + ")";
	}

	@Override
	public Term getAsTerm() {
		return new Struct("\\+", formula.getAsTerm());
	}

}
