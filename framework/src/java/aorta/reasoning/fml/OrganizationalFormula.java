package aorta.reasoning.fml;

import alice.tuprolog.Term;



public class OrganizationalFormula extends ReasoningFormula {
	
	public OrganizationalFormula(String formula) {
		super("org", formula);
	}

	public OrganizationalFormula(Term formula) {
		super("org", formula);
	}
	
}
