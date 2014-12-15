/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import aorta.ts.rules.og.Inform;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import aorta.AgentState;
import aorta.AortaAgent;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.PrologLoader;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.ts.Transition;
import aorta.ts.strategy.AgentStrategy;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class InformTest {
	
	@Test
	public void testTransition() throws Exception {
		Transition<AgentState> t = new Inform();
		
		QueryEngine engine = new QueryEngine();
		
		String theory = "" 
				+ "role(role1, [obj1, obj2]).\n" 
				+ "role(role2,[obj2]).\n"
				+ "obj(obj1, []).\n" 
				+ "obj(obj2, []).\n"
				+ "dep(role2, role1, bel(obj2)).\n"
				+ "cond(role1,bel(obj1),bel(obj1_deadline),bel(obj1_condition)).\n";
		
		PrologLoader prologLoader = new PrologLoader();
		prologLoader.addTheory(new Theory(theory), KBType.ORGANIZATION);
		
		MentalState ms = new MentalState(prologLoader.load());
		AgentState state = new AgentState(new AortaAgent("agent", ms, null), ms, null);
		ms = null;
		
		state = t.executeTransition(engine, state);
		
		Term inform = new Struct("opt", new MetaLanguage().send(new Struct("role2"), new Struct("tell"), new Struct("bel", new Struct("obj2"))));
				
		state.insertTerm(engine, new Struct("obj2"), KBType.BELIEF);
		state.insertTerm(engine, (Struct) Term.createTerm("org(rea(agent, role1))"));
		
		state = t.executeTransition(engine, state);
		assertTrue(engine.exists(state.getMentalState(), inform));
	}
	
}