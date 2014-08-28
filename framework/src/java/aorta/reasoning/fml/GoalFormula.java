package aorta.reasoning.fml;

import alice.tuprolog.Term;



public class GoalFormula extends ReasoningFormula {

	public GoalFormula(String formula) {
		super("goal", formula);
	}

	public GoalFormula(Term formula) {
		super("goal", formula);
	}

}
