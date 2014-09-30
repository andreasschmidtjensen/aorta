/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr;

import alice.tuprolog.EngineRunner;
import alice.tuprolog.Int;
import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import alice.tuprolog.MJIConverter;
import alice.tuprolog.Var;
import gov.nasa.jpf.annotation.MJI;
import gov.nasa.jpf.vm.ClassInfo;
import gov.nasa.jpf.vm.ElementInfo;
import gov.nasa.jpf.vm.FieldInfo;
import gov.nasa.jpf.vm.MJIEnv;
import gov.nasa.jpf.vm.NativePeer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class JPF_aorta_kr_QueryEngine extends NativePeer {

	private static Prolog lastProlog = null;
	private static Struct lastTheory = null;
	
//	@MJI
//	public int solve__Laorta_kr_MentalState_2Ljava_lang_String_2__Lalice_tuprolog_SolveInfo_2(MJIEnv env, int objRef, int msRef, int queryRef) throws MalformedGoalException {
//		Prolog prolog = getProlog(env, msRef);
//
//		String query = env.getStringObject(queryRef);
//
//		SolveInfo info = prolog.solve(query);
//		int rSolveInfo = createSolveInfo(env, info);
//		
//		return rSolveInfo;
//	}
//	
//	@MJI
//	public int solve__Laorta_kr_MentalState_2Lalice_tuprolog_Term_2__Lalice_tuprolog_SolveInfo_2(MJIEnv env, int objRef, int msRef, int termRef) {
//		Prolog prolog = getProlog(env, msRef);
//
//		ElementInfo ei = env.getElementInfo(termRef);
//		Term term = MJIConverter.getTerm(env, ei);
//
//		SolveInfo info = prolog.solve(term);
//		int rSolveInfo = createSolveInfo(env, info);
//		
//		return rSolveInfo;
//	}

	private Prolog getProlog(MJIEnv env, int msRef) {
		ClassInfo ci = env.getClassInfo(msRef);
		FieldInfo fi = ci.getInstanceField("prolog");
		int plRef = env.getReferenceField(msRef, fi);

		int tmRef = env.getReferenceField(plRef, "theoryManager");
		int dbRef = env.getReferenceField(tmRef, "dynamicDBase");
		int tableRef = env.getReferenceField(dbRef, "table");

		ElementInfo ei = env.getElementInfo(tableRef);
		
		Struct clauses = new Struct();
		
		int[] tableRefs = ei.asReferenceArray(); // Entry<String, FamilyClauseList>[]
		for (int i = 0; i < tableRefs.length; i++) {
			int entryRef = tableRefs[i]; // Entry<String, FamilyClauseList>
			if (entryRef != MJIEnv.NULL) {
				int valueRef = env.getReferenceField(entryRef, "value"); // FamilyClauseList (LinkedList<ClauseInfo>)

				int nodeRef = env.getReferenceField(valueRef, "first"); // Node<ClauseInfo>
				while (nodeRef != MJIEnv.NULL) {
					int itemRef = env.getReferenceField(nodeRef, "item"); // ClauseInfo
					int structRef = env.getReferenceField(itemRef, "clause"); // Struct
					Term clause = MJIConverter.getTerm(env, env.getElementInfo(structRef));
					clauses.append(clause);
					nodeRef = env.getReferenceField(nodeRef, "next"); // Node<ClauseInfo>
				}
			}
		}
		int agentRef = env.getReferenceField(msRef, "agent");
		int aortaRef = env.getReferenceField(agentRef, "aorta");
		int agentsRef = env.getReferenceField(aortaRef, "agents");
		int dataRef = env.getReferenceField(agentsRef, "elementData");
		
		String me = env.getStringField(agentRef, "name");
		clauses.append(Term.createTerm("bel(me(" + me + "))"));
		ei = env.getElementInfo(dataRef);
		tableRefs = ei.asReferenceArray(); // Object[]
		for (int i = 0; i < tableRefs.length; i++) {
			int objRef = tableRefs[i];
			if (objRef != MJIEnv.NULL) {
				String agName = env.getStringField(objRef, "name");
				clauses.append(Term.createTerm("bel(agent(" + agName + "))"));
			}
		}
		
		Prolog prolog;
		if (clauses.equals(lastTheory)) {
			prolog = lastProlog;
		} else {
			prolog = new Prolog();
			try {
				prolog.addTheory(new Theory(clauses));
			} catch (InvalidTheoryException ex) {
				ex.printStackTrace();
			}
			lastProlog = prolog;
			lastTheory = clauses;
		}
		
		return prolog;
	}

	private int createSolveInfo(MJIEnv env, SolveInfo info) {
		int rSolveInfo = env.newObject(SolveInfo.class.getName());
		int endState = EngineRunner.TRUE;
		if (info.isHalted()) {
			endState = EngineRunner.HALT;
		} else if (!info.isSuccess()) {
			endState = EngineRunner.FALSE;
		} else if (info.hasOpenAlternatives()) {
			endState = EngineRunner.TRUE_CP;
		}
		env.setIntField(rSolveInfo, "endState", endState);
		env.setBooleanField(rSolveInfo, "isSuccess", info.isSuccess());
		env.setReferenceField(rSolveInfo, "query", MJIConverter.createTerm(env, info.getQuery()));
		if (info.isSuccess()) {
			try {
				Struct goal = (Struct) info.getSolution();
				int goalRef = MJIConverter.createTerm(env, goal);
				
				env.setReferenceField(rSolveInfo, "goal", goalRef);
				
				List<Var> bindingVars = info.getBindingVars();
				int bindingsRef = env.newObject(ArrayList.class.getName());
				env.setIntField(bindingsRef, "size", bindingVars.size());
				int elementDataRef = env.newObjectArray(Var.class.getName(), bindingVars.size());
				env.setReferenceField(bindingsRef, "elementData", elementDataRef);
				
				for (int i = 0; i < bindingVars.size(); i++) {
					Var var = bindingVars.get(i);
					int varRef = MJIConverter.createTerm(env, var);
					env.setReferenceArrayElement(elementDataRef, i, varRef);
				}
				
				env.setReferenceField(rSolveInfo, "bindings", bindingsRef);
			} catch (NoSolutionException ex) {}
		}
		return rSolveInfo;
	}
}
