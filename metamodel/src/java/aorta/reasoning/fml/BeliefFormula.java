package aorta.reasoning.fml;

import alice.tuprolog.Term;



public class BeliefFormula extends ReasoningFormula {

	public BeliefFormula(String formula) {
		super("bel", formula);
	}
	
	public BeliefFormula(Term formula) {
		super("bel", formula);
	}

}
