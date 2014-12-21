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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ObligationSatisfiedTest {
	
	@Test
	public void testTransition() throws Exception {
		Transition<AgentState> t = new ObligationSatisfied();
		
		QueryEngine engine = new QueryEngine();
		
		String theory = "" 
				+ "role(role1, [obj1, obj2]).\n" 
				+ "role(role2,[obj2]).\n"
				+ "obj(obj1, []).\n" 
				+ "obj(obj2, []).\n" 
				+ "cond(role1,bel(obj1),bel(obj1_deadline),bel(obj1_condition)).\n";
		
		PrologLoader prologLoader = new PrologLoader();
		prologLoader.addTheory(new Theory(theory), KBType.ORGANIZATION);
		
		MentalState ms = new MentalState(prologLoader.load());
		AgentState state = new AgentState(new AortaAgent("agent", ms, null), ms, null);
		ms = null;
		
		Term orgObl = Term.createTerm("org(obl(agent, role1, bel(obj1), bel(obj1_deadline)))");
		
		state.insertTerm(engine, new Struct("obj1_condition"), KBType.BELIEF);
		state.insertTerm(engine, (Struct) Term.createTerm("org(rea(agent, role1))"));
		
		state = (AgentState) new ObligationActivated().executeTransition(engine, state);
		assertTrue(engine.exists(state.getMentalState(), orgObl));
		
		state.insertTerm(engine, new Struct("obj1"), KBType.BELIEF);
		
		state = t.executeTransition(engine, state);
		
		assertFalse(engine.exists(state.getMentalState(), orgObl));
	}
	
}