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
import aorta.kr.util.Qualifier;
import aorta.ts.Transition;
import aorta.ts.strategy.Linear;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ExtTest {
	
	@Test
	public void testTransition() throws Exception {
		Transition t = new Ext();
		
		QueryEngine engine = new QueryEngine();
		
		
		PrologLoader prologLoader = new PrologLoader();
		MentalState ms = new MentalState(prologLoader.load());
		AgentState state = new AgentState(new AortaAgent("agent", ms, null, new Linear()), ms, null);
		ms = null;
		
		Struct bel = new Struct("raining");
		Struct goal = new Struct("hold_umbrella");
		
		assertFalse(engine.exists(state.getMentalState(), Qualifier.qualifyTerm(bel, KBType.BELIEF)));
		assertFalse(engine.exists(state.getMentalState(), Qualifier.qualifyTerm(goal, KBType.GOAL)));
		
		state.getExternalAgent().addBelief(bel);
		state.getExternalAgent().addGoal(goal);
		state = t.executeTransition(engine, state);
		state = t.executeTransition(engine, state);
		
		assertTrue(engine.exists(state.getMentalState(), Qualifier.qualifyTerm(bel, KBType.BELIEF)));
		assertTrue(engine.exists(state.getMentalState(), Qualifier.qualifyTerm(goal, KBType.GOAL)));
		
		state.getExternalAgent().removeBelief(bel);
		state.getExternalAgent().removeGoal(goal);
		state = t.executeTransition(engine, state);
		state = t.executeTransition(engine, state);
		
		assertFalse(engine.exists(state.getMentalState(), Qualifier.qualifyTerm(bel, KBType.BELIEF)));
		assertFalse(engine.exists(state.getMentalState(), Qualifier.qualifyTerm(goal, KBType.GOAL)));
		
	}
	
}