package aorta.kr;

import aorta.kr.util.Qualifier;
import java.util.ArrayList;
import java.util.List;

import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.reasoning.fml.Formula;

public class QueryEngine {
    
	public QueryEngine() {
	}
	
	public boolean exists(MentalState ms, Struct term) {
		return ms.getProlog().solve(term).isSuccess();
	}
		
	public void insert(MentalState ms, Struct term) {
		if (!exists(ms, term)) {
			ms.getProlog().getTheoryManager().assertZ(term, true, null, true);
		}
	}
	
	public boolean remove(MentalState ms, Struct term) {
		return ms.getProlog().getTheoryManager().retract(term) != null;
	}
	
	public SolveInfo solve(MentalState ms, Formula fml) {
		return solve(ms, Qualifier.qualifyGoal(fml));
	}
	
	public SolveInfo solve(MentalState ms, Term term) {
		return ms.getProlog().solve(term);
	}
	public boolean hasMoreSolutions(MentalState ms) {
		return ms.getProlog().hasOpenAlternatives();
	}
	
	public SolveInfo solveNext(MentalState ms) {
		if (ms.getProlog().hasOpenAlternatives()) {
			try {
				return ms.getProlog().solveNext();
			} catch (NoMoreSolutionException e) {
				throw new Error("Cannot throw NoMoreSolutionException!", e);
			}
		} else {
            try {
                return ms.getProlog().solve("fail.");
            } catch (MalformedGoalException ex) {
                // since "fail." is not malformed, this will not be thrown
                throw new RuntimeException(ex);
            }
		}
	}

	public List<SolveInfo> findAll(Prolog prolog, Formula fml) {
		return findAll(prolog, Qualifier.qualifyFormula(fml));		
	}
	
	public List<SolveInfo> findAll(MentalState ms, Formula fml) {
		return findAll(ms.getProlog(), Qualifier.qualifyFormula(fml));		
	}
	
	public List<SolveInfo> findAll(Prolog pl, Term term) {
		List<SolveInfo> result = new ArrayList<>();
		
		SolveInfo solve = pl.solve(term);
		result.add(solve);
		while (pl.hasOpenAlternatives()) {
			try {
				SolveInfo solveInfo = pl.solveNext();
				if (solveInfo.isSuccess()) {
					result.add(solveInfo);
				}
			} catch (NoMoreSolutionException e) {
				// not thrown since hasOpenAlternatives has been checked.
			}
		}
		return result;
	}

	public void unify(MentalState ms, Term qualified, List<Var> bindings) {
		ms.getTv().unify(qualified, bindings);
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
    

}