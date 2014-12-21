/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.Term;
import aorta.AgentState;
import aorta.AortaAgent;
import aorta.kr.MentalState;
import aorta.kr.PrologLoader;
import aorta.kr.QueryEngine;
import aorta.msg.IncomingOrganizationalMessage;
import aorta.ts.Transition;
import aorta.ts.strategy.AgentStrategy;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class CheckTest {
	
	@Test
	public void testTransition() throws Exception {
		Transition<AgentState> t = new Check();
		
		QueryEngine engine = new QueryEngine();
		
		PrologLoader prologLoader = new PrologLoader();
		
		MentalState ms = new MentalState(prologLoader.load());
		AgentState state = new AgentState(new AortaAgent("agent", ms, null), ms, null);
		ms = null;
		
		AgentState newState = t.executeTransition(engine, state);
		
		assertEquals(state, newState);
		Term bel = Term.createTerm("bel(message)");
		Term org = Term.createTerm("org(message)");
		Term opt = Term.createTerm("opt(message)");
		Term goal = Term.createTerm("goal(message)");
				
		state.getExternalAgent().receiveMessage(new IncomingOrganizationalMessage("sender", bel));
		state.getExternalAgent().receiveMessage(new IncomingOrganizationalMessage("sender", org));
		state.getExternalAgent().receiveMessage(new IncomingOrganizationalMessage("sender", opt));
		state.getExternalAgent().receiveMessage(new IncomingOrganizationalMessage("sender", goal));
		
		state = t.executeTransition(engine, state);
		assertTrue(engine.exists(state.getMentalState(), bel));
		
		state = t.executeTransition(engine, state);
		assertTrue(engine.exists(state.getMentalState(), org));
		
		state = t.executeTransition(engine, state);
		assertTrue(engine.exists(state.getMentalState(), opt));
		
		state = t.executeTransition(engine, state);
		assertTrue(engine.exists(state.getMentalState(), goal));
	}
	
}