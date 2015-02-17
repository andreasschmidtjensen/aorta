package aorta.kr;

import alice.tuprolog.ClauseInfo;
import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.TheoryManager;
import alice.tuprolog.Var;
import aorta.kr.util.FormulaQualifier;
import aorta.kr.util.TermVisitor;
import aorta.reasoning.fml.Formula;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MentalState {

    private Prolog prolog;

    public MentalState(Prolog prolog) {
        this.prolog = prolog;
    }
	
    public Struct addAgentOwnName(String name) {
		Struct myName = new Struct("me", new Struct(name));
        // removes from the knowledgebase all instances of me/1
        Struct qualified = FormulaQualifier.qualifyStruct(myName, KBType.BELIEF.getType());
        if (!prolog.solve(qualified).isSuccess()) {
            qualified = FormulaQualifier.qualifyStruct(new Struct("me", new Var()), KBType.BELIEF.getType());
            ClauseInfo ci;
			try {
				while ((ci = prolog.getTheoryManager().retract(qualified)) != null) {
					// retracting all
				}
			} catch (Exception ex) {}

            // adds to the knowledgebase the agents own name
            qualified = FormulaQualifier.qualifyStruct(myName, KBType.BELIEF.getType());
            prolog.getTheoryManager().assertZ(qualified, true, null, true);
        }
		return myName;
    }

    public Prolog getProlog() {
        return prolog;
    }

	public boolean exists(Term term) {
		if (term == null) {
			throw new NullPointerException("Term was null!");
		}
		SolveInfo solve = prolog.solve(term);
		return solve.isSuccess();
	}
		
	public void insert(Struct term) {
		if (!exists(term)) {
			TheoryManager theoryManager = prolog.getTheoryManager();
			theoryManager.assertZ(term, true, null, true);
		}
	}
	
	public boolean remove(Struct term) {
		if (exists(term)) {
			TheoryManager theoryManager = prolog.getTheoryManager();
			return theoryManager.retract(term) != null;
		} else {
			return false;
		}
	}
	
	public List<Struct> toStructs(KBType type) {
		Struct org = new Struct(type.getType(), new Var());
		List<Struct> result = new ArrayList<>();
		Iterator<? extends Term> it = prolog.getTheory().iterator(prolog);
		while (it.hasNext()) {
			Term t = it.next();
			if (t instanceof Struct) {
				if (t.match(org)) {
					result.add((Struct) t);
				}
			}
		}
		
		return result;
	}
	
	public List<Struct>[] mergeKBs(KBType type, List<Struct> newStructs) {
		List<Struct> added = new ArrayList<>();
		List<Struct> removed = new ArrayList<>();
		
		List<Struct> currStructs = toStructs(type);
		for (Struct c : currStructs) {
			boolean found = false;
			for (Struct n : newStructs) {
				if (n.match(c)) {
					found = true;
					break;
				}
			}
			if (!found) {
				removed.add(c);
				remove(c);
			}
		}		
		
		for (Struct n : newStructs) {
			boolean found = false;
			for (Struct c : currStructs) {
				if (c.match(n)) {
					found = true;
					break;
				}
			}
			if (!found) {
				added.add(n);
				insert(n);
			}
		}
		
		return new List[]{ added, removed };
	}
	
	public SolveInfo solve(Formula fml) {
		return solve(FormulaQualifier.qualifyGoal(this, fml));
	}
	
	public SolveInfo solve(Term term) {
		return prolog.solve(term);
	}
	
	public SolveInfo solve(String query) throws MalformedGoalException {
		return prolog.solve(query);
	}
	
	public boolean hasMoreSolutions() {
		return prolog.hasOpenAlternatives();
	}
	
	public SolveInfo solveNext() {
		if (prolog.hasOpenAlternatives()) {
			try {
				return prolog.solveNext();
			} catch (NoMoreSolutionException e) {
				throw new Error("Cannot throw NoMoreSolutionException!", e);
			}
		} else {
            try {
                return prolog.solve("fail.");
            } catch (MalformedGoalException ex) {
                // since "fail." is not malformed, this will not be thrown
                throw new RuntimeException(ex);
            }
		}
	}

	public List<SolveInfo> findAll(Formula fml) {
		return findAll(FormulaQualifier.qualifyFormula(fml));		
	}
	
	public List<SolveInfo> findAll(Term term) {
		List<SolveInfo> result = new ArrayList<>();
		
		SolveInfo solve = prolog.solve(term);
		result.add(solve);
		while (prolog.hasOpenAlternatives()) {
			try {
				SolveInfo solveInfo = prolog.solveNext();
				if (solveInfo.isSuccess()) {
					result.add(solveInfo);
				}
			} catch (NoMoreSolutionException e) {
				// not thrown since hasOpenAlternatives has been checked.
			}
		}
		return result;
	}

	public List<SolveInfo> findAll(String query) throws MalformedGoalException {
		List<SolveInfo> result = new ArrayList<>();
		
		SolveInfo solve = prolog.solve(query);
		result.add(solve);
		while (prolog.hasOpenAlternatives()) {
			try {
				SolveInfo solveInfo = prolog.solveNext();
				if (solveInfo.isSuccess()) {
					result.add(solveInfo);
				}
			} catch (NoMoreSolutionException e) {
				// not thrown since hasOpenAlternatives has been checked.
			}
		}
		return result;
	}

	public void unify(Term qualified, List<Var> bindings) {
		new TermVisitor(prolog).unify(qualified, bindings);
	}
	
	public void unify(Term qualified, SolveInfo solveInfo) {
		if (solveInfo.isSuccess()) { 
			try {
				unify(qualified, solveInfo.getBindingVars());
			} catch (NoSolutionException ex) {
				// ignore because of isSuccess
			}
		}
	}

	public List<Var> getVars(Term term) {
		List<Var> result = new ArrayList<>();
		
		if (term instanceof Var) {
			result.add((Var) term);
		} else if (term instanceof Struct) {
			Struct s = (Struct) term;
			for (int i = 0; i < s.getArity(); i++) {
				result.addAll(getVars(s.getArg(i)));
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
