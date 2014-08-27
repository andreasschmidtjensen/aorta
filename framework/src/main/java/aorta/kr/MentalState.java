package aorta.kr;

import alice.tuprolog.ClauseInfo;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import alice.tuprolog.Var;
import aorta.AortaAgent;
import aorta.kr.util.Qualifier;
import aorta.kr.util.TermVisitor;
import aorta.reasoning.fml.Formula;
import aorta.reasoning.fml.OrganizationalFormula;

public class MentalState {

    private Prolog prolog;
    private AortaAgent agent;
    private TermVisitor tv;

    public MentalState(Prolog prolog) {
        this.prolog = prolog;

        tv = new TermVisitor(prolog);
    }
	
    public void setAgent(AortaAgent agent) {
        this.agent = agent;
        
        // removes from the knowledgebase all instances of me/1
        Struct qualified = Qualifier.qualifyStruct(new Struct("me", new Struct(agent.getName())), KBType.BELIEF.getType());
        if (!prolog.solve(qualified).isSuccess()) {
            qualified = Qualifier.qualifyStruct(new Struct("me", new Var()), KBType.BELIEF.getType());
            ClauseInfo ci;
			try {
				while ((ci = prolog.getTheoryManager().retract(qualified)) != null) {
					// retracting all
				}
			} catch (Exception ex) {}

            // adds to the knowledgebase the agents own name
            qualified = Qualifier.qualifyStruct(new Struct("me", new Struct(agent.getName())), KBType.BELIEF.getType());
            prolog.getTheoryManager().assertZ(qualified, true, null, true);
        }
    }

    public AortaAgent getAgent() {
        return agent;
    }

    public Prolog getProlog() {
        return prolog;
    }

    public Theory getFacts() {
        return prolog.getTheory();
    }

	public TermVisitor getTv() {
		return tv;
	}

    public Struct getEnactingRoles() {
        Struct result = new Struct();
        
        Formula enactingRoles = new OrganizationalFormula("findall(Role, (role(Role,_),rea(" + Term.createTerm(agent.getName()).toString() + ", Role)), L)");
        SolveInfo info = prolog.solve(enactingRoles.getAsTerm());
        if (info.isSuccess()) {
            try {
                for (Var v : info.getBindingVars()) {
                    if (v.getName().equals("L")) {
                        result = (Struct) v.getTerm();
                    }
                }
            } catch (NoSolutionException ex) {
                // cannot happen (we have confirmed that it isSuccess()
            }
        }
        
        return result;
    }

    @Override
    public String toString() {
		String theory = prolog.getTheory().toString();
		theory = theory.replace("\n\n", "\n");
		return theory;
    }
}
