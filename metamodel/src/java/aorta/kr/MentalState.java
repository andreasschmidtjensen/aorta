package aorta.kr;

import alice.tuprolog.ClauseInfo;
import alice.tuprolog.Prolog;
import alice.tuprolog.Struct;
import alice.tuprolog.Var;
import aorta.kr.util.FormulaQualifier;
import aorta.kr.util.TermFormatter;

public class MentalState {

    private Prolog prolog;

    public MentalState(Prolog prolog) {
        this.prolog = prolog;
    }
	
    public void addAgentOwnName(String name) {
        // removes from the knowledgebase all instances of me/1
        Struct qualified = FormulaQualifier.qualifyStruct(new Struct("me", new Struct(name)), KBType.BELIEF.getType());
        if (!prolog.solve(qualified).isSuccess()) {
            qualified = FormulaQualifier.qualifyStruct(new Struct("me", new Var()), KBType.BELIEF.getType());
            ClauseInfo ci;
			try {
				while ((ci = prolog.getTheoryManager().retract(qualified)) != null) {
					// retracting all
				}
			} catch (Exception ex) {}

            // adds to the knowledgebase the agents own name
            qualified = FormulaQualifier.qualifyStruct(new Struct("me", new Struct(name)), KBType.BELIEF.getType());
            prolog.getTheoryManager().assertZ(qualified, true, null, true);
        }
    }

    public Prolog getProlog() {
        return prolog;
    }

    @Override
    public String toString() {
		String theory = prolog.getTheory().toString();
		theory = theory.replace("\n\n", "\n");
		return theory;
    }
}
