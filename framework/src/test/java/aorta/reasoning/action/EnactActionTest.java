/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.reasoning.action;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.AgentState;
import aorta.AortaAgent;
import aorta.kr.MentalState;
import aorta.kr.PrologLoader;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.ts.TransitionNotPossibleException;
import aorta.ts.strategy.Linear;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author asj
 */
public class EnactActionTest {
	
	@Test
	public void testAction() throws Exception {
		Action action = new EnactAction(new Struct("role1"));
		MetaLanguage ml = new MetaLanguage();
		
		QueryEngine engine = new QueryEngine();
		
		PrologLoader prologLoader = new PrologLoader();		
		MentalState ms = new MentalState(prologLoader.load());
		AgentState state = new AgentState(new AortaAgent("agent", ms, null, new Linear()), ms, null);
		
		try {
			action.execute(engine, null, state);
			Assert.fail("Should have thrown TransitionNotPossibleException");
		} catch (TransitionNotPossibleException ex) {
		}
		
		Struct org = (Struct) Term.createTerm("org(role(role1,[]))");
		Struct opt = (Struct) Term.createTerm("opt(role(role1,[]))");
		Term rea = Term.createTerm("org(rea(agent, role1))");
		
		state.insertInMentalState(engine, org);
		state.insertInMentalState(engine, opt);
		
		action.execute(engine, opt, state);
		
		Assert.assertTrue(engine.exists(ms, rea));
		Assert.assertFalse(engine.exists(ms, opt));
		
		state.insertInMentalState(engine, opt);
		try {
			action.execute(engine, opt, state);
			Assert.fail("Should have thrown TransitionNotPossibleException");
		} catch (TransitionNotPossibleException ex) {
		}
		
	}
}
