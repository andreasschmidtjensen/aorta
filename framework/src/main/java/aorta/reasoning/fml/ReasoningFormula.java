package aorta.reasoning.fml;

import alice.tuprolog.Term;
import aorta.kr.util.Qualifier;
import aorta.kr.util.TermFormatter;

public abstract class ReasoningFormula extends Formula {

	protected Term formula;
	private String type;

	public ReasoningFormula(String type, Term formula) {
		this.type = type;
		this.formula = formula;
	}

	public ReasoningFormula(String type, String formula) {
		this(type, Term.createTerm(formula));
	}

	public Term getFormula() {
		return formula;
	}

	@Override
	public String toString() {
		return type + "(" + TermFormatter.toString(formula) + ")";
	}

	public String getType() {
		return type;
	}

	@Override
	public Term getAsTerm() {
		return Qualifier.qualifyFormula(this);
	}
}
