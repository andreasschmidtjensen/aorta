package aorta.reasoning.fml;

import alice.tuprolog.Term;



public class OptionFormula extends ReasoningFormula {

	public OptionFormula(String formula) {
		super("opt", formula);
	}

	public OptionFormula(Term formula) {
		super("opt", formula);
	}
		
}
