/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import aorta.AgentState;
import aorta.AortaAgent;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.PrologLoader;
import aorta.kr.QueryEngine;
import aorta.ts.Transition;
import aorta.ts.strategy.AgentStrategy;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class EnactTest {
	
	@Test
	public void testTransition() throws Exception {
		Transition<AgentState> t = new EnactRule();
		
		QueryEngine engine = new QueryEngine();
		
		String theory = "" +
				"role(role1, [obj1, obj2]).\n" +
				"role(role2,[obj2]).\n" +
				"obj(obj1, [obj3]).\n" +
				"obj(obj2, []).\n" +
				"obj(obj3, []).";
		
		PrologLoader prologLoader = new PrologLoader();
		prologLoader.addTheory(new Theory(theory), KBType.ORGANIZATION);
		
		MentalState ms = new MentalState(prologLoader.load());
		AgentState state = new AgentState(new AortaAgent("agent", ms, null), ms, null);
		ms = null;
		
		state = t.executeTransition(engine, state);
		
		Term role1Option = Term.createTerm("opt(role(role1))");
		Term role2Option = Term.createTerm("opt(role(role2))");
		
		assertFalse(engine.exists(state.getMentalState(), role1Option));
		
		state.insertTerm(engine, new Struct("obj3"), KBType.GOAL);
		state = t.executeTransition(engine, state);

		assertTrue(engine.exists(state.getMentalState(), role1Option));
		
		state = t.executeTransition(engine, state);
		assertFalse(engine.exists(state.getMentalState(), role2Option));
		
		state.insertTerm(engine, new Struct("obj2"), KBType.GOAL);
		state = t.executeTransition(engine, state);
		assertTrue(engine.exists(state.getMentalState(), role2Option));
	}
	
}
