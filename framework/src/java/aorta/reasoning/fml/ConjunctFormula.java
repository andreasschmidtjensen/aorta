package aorta.reasoning.fml;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;

public class ConjunctFormula extends Formula {

	private Formula fml1;
	private Formula fml2;
	
	public ConjunctFormula(Formula fml1, Formula fml2) {
		this.fml1 = fml1;
		this.fml2 = fml2;
	}
	
	public Formula getFml1() {
		return fml1;
	}
	
	public Formula getFml2() {
		return fml2;
	}
	
	@Override
	public String toString() {
		return fml1 + " ^ " + fml2;
	}

	@Override
	public Term getAsTerm() {
		return new Struct(",", fml1.getAsTerm(), fml2.getAsTerm());
	}

}
