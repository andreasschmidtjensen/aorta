package aorta.reasoning.fml;

import alice.tuprolog.Term;

public abstract class Formula {

	public abstract Term getAsTerm();

	@Override
	public abstract String toString();
	

}
