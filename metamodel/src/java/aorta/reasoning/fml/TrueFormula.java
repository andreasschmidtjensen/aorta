package aorta.reasoning.fml;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;


public class TrueFormula extends Formula {

	public TrueFormula() {
	}

	@Override
	public String toString() {
		return "true";
	}

	@Override
	public Term getAsTerm() {
		return new Struct("true");
	}

}
