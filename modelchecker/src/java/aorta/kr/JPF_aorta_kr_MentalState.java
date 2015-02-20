/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr;

import alice.tuprolog.EngineRunner;
import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.MJIConverter;
import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Parser;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import alice.tuprolog.TheoryManager;
import alice.tuprolog.Var;
import aorta.kr.util.TermVisitor;
import gov.nasa.jpf.annotation.MJI;
import gov.nasa.jpf.vm.ElementInfo;
import gov.nasa.jpf.vm.MJIEnv;
import gov.nasa.jpf.vm.NativePeer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author asj
 */
public class JPF_aorta_kr_MentalState extends NativePeer {

	private Prolog prolog;
//
//	@MJI
//	public void $init__Ljava_lang_String_2__V(MJIEnv env, int objRef,
//			int rString) {
//		String ms = env.getStringObject(rString);
//		try {
//			prolog = new Prolog();
//			prolog.addTheory(new Theory(ms));
//		} catch (InvalidTheoryException ex) {
//			throw new RuntimeException(ex);
//		}
//	}
//
//	// TODO: Check if this one is necessary (shouldn't be, since ms should already contain the agent's own name)
//	// should however be here
//	@MJI
//	public int addAgentOwnName__Ljava_lang_String_2__Lalice_tuprolog_Struct_2(
//			MJIEnv env, int objRef, int rString0) {
//		int rStruct = MJIEnv.NULL;
//		return rStruct;
//	}
//
//	@MJI
//	public int solve__Lalice_tuprolog_Term_2__Lalice_tuprolog_SolveInfo_2(
//			MJIEnv env, int objRef, int rTerm0) {
//		Term term = MJIConverter.getTerm(env, env.getElementInfo(rTerm0));
//
//		SolveInfo info = prolog.solve(term);
//
//		int rSolveInfo = createSolveInfo(env, info);
//		return rSolveInfo;
//	}
//
//	@MJI
//	public int solve__Ljava_lang_String_2__Lalice_tuprolog_SolveInfo_2(
//			MJIEnv env, int objRef, int rString0) throws MalformedGoalException {
//		String query = env.getStringObject(rString0);
//
//		SolveInfo info = prolog.solve(query);
//
//		int rSolveInfo = createSolveInfo(env, info);
//
//		return rSolveInfo;
//	}
//
//	@MJI
//	public boolean hasMoreSolutions____Z(MJIEnv env, int objRef) {
//		boolean v = prolog.hasOpenAlternatives();
//		return v;
//	}
//
//	@MJI
//	public int solveNext____Lalice_tuprolog_SolveInfo_2(MJIEnv env, int objRef) {
//		SolveInfo info = null;
//		if (prolog.hasOpenAlternatives()) {
//			try {
//				info = prolog.solveNext();
//			} catch (NoMoreSolutionException e) {
//				throw new Error("Cannot throw NoMoreSolutionException!", e);
//			}
//		} else {
//			try {
//				info = prolog.solve("fail.");
//			} catch (MalformedGoalException ex) {
//				// since "fail." is not malformed, this will not be thrown
//				throw new RuntimeException(ex);
//			}
//		}
//		int rSolveInfo = createSolveInfo(env, info);
//		return rSolveInfo;
//	}
//
//	@MJI
//	public int performUnify__Lalice_tuprolog_Term_2_3Lalice_tuprolog_Var_2__Lalice_tuprolog_Term_2(
//			MJIEnv env, int objRef, int rTerm0, int rVar1) {
//
//		Term term = MJIConverter.getTerm(env, env.getElementInfo(rTerm0));
//
//		int[] varRefs = env.getReferenceArrayObject(rVar1);
//		Var[] vars = new Var[varRefs.length];
//		for (int i = 0; i < vars.length; i++) {
//			ElementInfo eiArg = env.getElementInfo(varRefs[i]);
//			vars[i] = (Var) MJIConverter.getTerm(env, eiArg);
//		}
//		
//		List<Var> list = Arrays.asList(vars);
//		new TermVisitor(prolog).unify(term, list);
//				
//		int rTerm = MJIConverter.createTerm(env, term);
//		return rTerm;
//	}
//
//	@MJI
//	public int performGetVars__Lalice_tuprolog_Term_2___3Lalice_tuprolog_Var_2(
//			MJIEnv env, int objRef, int rTerm0) {
//		Term term = MJIConverter.getTerm(env, env.getElementInfo(rTerm0));
//		List<Var> vars = getVars(term);
//
//		int rVar = env.newObjectArray(Var.class.getName(), vars.size());
//		for (int i = 0; i < vars.size(); i++) {
//			env.setReferenceArrayElement(rVar, i, MJIConverter.createTerm(env,
//					vars.get(i)));
//		}
//		return rVar;
//	}
//
//	private List<Var> getVars(Term term) {
//		List<Var> result = new ArrayList<>();
//
//		if (term instanceof Var) {
//			result.add((Var) term);
//		} else if (term instanceof Struct) {
//			Struct s = (Struct) term;
//			for (int i = 0; i < s.getArity(); i++) {
//				result.addAll(getVars(s.getArg(i)));
//			}
//		}
//
//		return result;
//	}
//
//	@MJI
//	public int performFindAll__Lalice_tuprolog_Term_2___3Lalice_tuprolog_SolveInfo_2(
//			MJIEnv env, int objRef, int rTerm0) {
//		List<SolveInfo> result = new ArrayList<>();
//		Term term = MJIConverter.getTerm(env, env.getElementInfo(rTerm0));
//
//		SolveInfo solve = prolog.solve(term);
//		result.add(solve);
//		while (prolog.hasOpenAlternatives()) {
//			try {
//				SolveInfo solveInfo = prolog.solveNext();
//				if (solveInfo.isSuccess()) {
//					result.add(solveInfo);
//				}
//			} catch (NoMoreSolutionException e) {
//				// not thrown since hasOpenAlternatives has been checked.
//			}
//		}
//
//		int rSolveInfo = env.newObjectArray(SolveInfo.class.getName(), result.
//				size());
//		for (int i = 0; i < result.size(); i++) {
//			env.setReferenceArrayElement(rSolveInfo, i, createSolveInfo(env,
//					result.get(i)));
//		}
//		return rSolveInfo;
//	}
//
//	@MJI
//	public int performFindAll__Ljava_lang_String_2___3Lalice_tuprolog_SolveInfo_2(
//			MJIEnv env, int objRef, int rString0) throws MalformedGoalException {
//		List<SolveInfo> result = new ArrayList<>();
//		String query = env.getStringObject(rString0);
//
//		SolveInfo solve = prolog.solve(query);
//		result.add(solve);
//		while (prolog.hasOpenAlternatives()) {
//			try {
//				SolveInfo solveInfo = prolog.solveNext();
//				if (solveInfo.isSuccess()) {
//					result.add(solveInfo);
//				}
//			} catch (NoMoreSolutionException e) {
//				// not thrown since hasOpenAlternatives has been checked.
//			}
//		}
//
//		int rSolveInfo = env.newObjectArray(SolveInfo.class.getName(), result.
//				size());
//		for (int i = 0; i < result.size(); i++) {
//			env.setReferenceArrayElement(rSolveInfo, i, createSolveInfo(env,
//					result.get(i)));
//		}
//		return rSolveInfo;
//	}
//
//	@MJI
//	public boolean remove__Lalice_tuprolog_Struct_2__Z(MJIEnv env, int objRef,
//			int rStruct0) {
//		Struct term = (Struct) MJIConverter.getTerm(env, env.getElementInfo(
//				rStruct0));
//		if (exists(term)) {
//			TheoryManager theoryManager = prolog.getTheoryManager();
//			return theoryManager.retract(term) != null;
//		} else {
//			return false;
//		}
//	}
//
//	@MJI
//	public int toString____Ljava_lang_String_2(MJIEnv env, int objRef) {
//		String theory = prolog.getTheory().toString();
//		theory = theory.replace("\n\n", "\n");
//
//		int rString = env.newString(theory);
//		return rString;
//	}
//
//	@MJI
//	public void insert__Lalice_tuprolog_Struct_2__V(MJIEnv env, int objRef,
//			int rStruct0) {
//		Struct term = (Struct) MJIConverter.getTerm(env, env.getElementInfo(
//				rStruct0));
//		if (!exists(term)) {
//			TheoryManager theoryManager = prolog.getTheoryManager();
//			theoryManager.assertZ(term, true, null, true);
//		}
//	}
//
//	@MJI
//	public boolean exists__Lalice_tuprolog_Term_2__Z(MJIEnv env, int objRef,
//			int rTerm0) {
//		Term term = MJIConverter.getTerm(env, env.getElementInfo(rTerm0));
//		return exists(term);
//	}
//
//	private boolean exists(Term term) throws NullPointerException {
//		if (term == null) {
//			throw new NullPointerException("Term was null!");
//		}
//		if (term instanceof Var && ((Var)term).getTerm() instanceof Var) {
//			return false;
//		}
//
//		SolveInfo solve = prolog.solve(term);
//		return solve.isSuccess();
//	}
//
//	@MJI
//	public int parseTerm__Ljava_lang_String_2__Lalice_tuprolog_Term_2(MJIEnv env,
//			int objRef, int rString0) {
//		String query = env.getStringObject(rString0);
//
//		Parser parser = new Parser(prolog.getOperatorManager(), query);
//		Term term = parser.nextTerm(false);
//
//		int rTerm = MJIConverter.createTerm(env, term);
//		return rTerm;
//	}
//
//	private int createSolveInfo(MJIEnv env, SolveInfo info) {
//		int rSolveInfo = env.newObject(SolveInfo.class.getName());
//		int endState = EngineRunner.TRUE;
//		if (info.isHalted()) {
//			endState = EngineRunner.HALT;
//		} else if (!info.isSuccess()) {
//			endState = EngineRunner.FALSE;
//		} else if (info.hasOpenAlternatives()) {
//			endState = EngineRunner.TRUE_CP;
//		}
//		env.setIntField(rSolveInfo, "endState", endState);
//		env.setBooleanField(rSolveInfo, "isSuccess", info.isSuccess());
//		env.setReferenceField(rSolveInfo, "query", MJIConverter.createTerm(env,
//				info.getQuery()));
//		if (info.isSuccess()) {
//			try {
//				Struct goal = (Struct) info.getSolution();
//				int goalRef = MJIConverter.createTerm(env, goal);
//
//				env.setReferenceField(rSolveInfo, "goal", goalRef);
//
//				List<Var> bindingVars = info.getBindingVars();
//				int bindingsRef = env.newObject(ArrayList.class.getName());
//				env.setIntField(bindingsRef, "size", bindingVars.size());
//				int elementDataRef = env.newObjectArray(Var.class.getName(),
//						bindingVars.size());
//				env.
//						setReferenceField(bindingsRef, "elementData",
//						elementDataRef);
//
//				for (int i = 0; i < bindingVars.size(); i++) {
//					Var var = bindingVars.get(i);
//					int varRef = MJIConverter.createTerm(env, var);
//					env.setReferenceArrayElement(elementDataRef, i, varRef);
//				}
//
//				env.setReferenceField(rSolveInfo, "bindings", bindingsRef);
//			} catch (NoSolutionException ex) {
//			}
//		}
//		return rSolveInfo;
//	}
}
