package aorta.kr;

import alice.tuprolog.ClauseInfo;
import alice.tuprolog.Prolog;
import alice.tuprolog.Struct;
import alice.tuprolog.Var;
import aorta.AortaAgent;
import aorta.kr.util.FormulaQualifier;

public class MentalState {

    private Prolog prolog;
    private AortaAgent agent;

    public MentalState(Prolog prolog) {
        this.prolog = prolog;
    }
	
    public void setAgent(AortaAgent agent) {
        this.agent = agent;
        
        // removes from the knowledgebase all instances of me/1
        Struct qualified = FormulaQualifier.qualifyStruct(new Struct("me", new Struct(agent.getName())), KBType.BELIEF.getType());
        if (!prolog.solve(qualified).isSuccess()) {
            qualified = FormulaQualifier.qualifyStruct(new Struct("me", new Var()), KBType.BELIEF.getType());
            ClauseInfo ci;
			try {
				while ((ci = prolog.getTheoryManager().retract(qualified)) != null) {
					// retracting all
				}
			} catch (Exception ex) {}

            // adds to the knowledgebase the agents own name
            qualified = FormulaQualifier.qualifyStruct(new Struct("me", new Struct(agent.getName())), KBType.BELIEF.getType());
            prolog.getTheoryManager().assertZ(qualified, true, null, true);
        }
    }

    public AortaAgent getAgent() {
        return agent;
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
