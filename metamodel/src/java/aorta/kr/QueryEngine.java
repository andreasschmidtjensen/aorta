package aorta.kr;

import aorta.kr.util.FormulaQualifier;
import java.util.ArrayList;
import java.util.List;

import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.TheoryManager;
import alice.tuprolog.Var;
import aorta.kr.util.TermVisitor;
import aorta.reasoning.fml.Formula;
import java.util.Iterator;

public class QueryEngine {
    
	public QueryEngine() {
	}
	
	public boolean exists(MentalState ms, Term term) {
		if (ms == null) {
			throw new NullPointerException("Mental state was null!");
		}
		if (term == null) {
			throw new NullPointerException("Term was null!");
		}
		Prolog prolog = ms.getProlog();
		SolveInfo solve = prolog.solve(term);
		return solve.isSuccess();
	}
		
	public void insert(MentalState ms, Struct term) {
		if (!exists(ms, term)) {
			Prolog prolog = ms.getProlog();
			TheoryManager theoryManager = prolog.getTheoryManager();
			theoryManager.assertZ(term, true, null, true);
		}
	}
	
	public boolean remove(MentalState ms, Struct term) {
		if (exists(ms, term)) {
			Prolog prolog = ms.getProlog();
			TheoryManager theoryManager = prolog.getTheoryManager();
			return theoryManager.retract(term) != null;
		} else {
			return false;
		}
	}
	
	public List<Struct> toStructs(Prolog prolog, KBType type) {
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
	
	public List<Struct>[] mergeKBs(MentalState ms, KBType type, List<Struct> newStructs) {
		List<Struct> added = new ArrayList<>();
		List<Struct> removed = new ArrayList<>();
		
		List<Struct> currStructs = new QueryEngine().toStructs(ms.getProlog(), type);
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
				remove(ms, c);
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
				insert(ms, n);
			}
		}
		
		return new List[]{ added, removed };
	}
	
	public SolveInfo solve(MentalState ms, Formula fml) {
		return solve(ms, FormulaQualifier.qualifyGoal(ms, fml));
	}
	
	public SolveInfo solve(MentalState ms, Term term) {
		return ms.getProlog().solve(term);
	}
	
	public SolveInfo solve(MentalState ms, String query) throws MalformedGoalException {
		return ms.getProlog().solve(query);
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

	public List<SolveInfo> findAll(MentalState ms, Formula fml) {
		return findAll(ms, FormulaQualifier.qualifyFormula(fml));		
	}
	
	public List<SolveInfo> findAll(MentalState ms, Term term) {
		List<SolveInfo> result = new ArrayList<>();
		Prolog pl = ms.getProlog();
		
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

	public List<SolveInfo> findAll(MentalState ms, String query) throws MalformedGoalException {
		List<SolveInfo> result = new ArrayList<>();
		Prolog pl = ms.getProlog();
		
		SolveInfo solve = pl.solve(query);
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
		new TermVisitor(ms.getProlog()).unify(qualified, bindings);
	}
	
	public void unify(MentalState ms, Term qualified, SolveInfo solveInfo) {
		if (solveInfo.isSuccess()) { 
			try {
				unify(ms, qualified, solveInfo.getBindingVars());
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
    

}
