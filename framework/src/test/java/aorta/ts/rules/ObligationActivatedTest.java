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
import aorta.ts.strategy.Linear;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ObligationActivatedTest {
	
	@Test
	public void testTransition() throws Exception {
		Transition t = new ObligationActivated();
		
		QueryEngine engine = new QueryEngine();
		
		String theory = "" 
				+ "role(role1, [obj1, obj2]).\n" 
				+ "role(role2,[obj2]).\n"
				+ "obj(obj1, []).\n" 
				+ "obj(obj2, []).\n" 
				+ "cond(role1,bel(obj1),bel(obj1_deadline),bel(obj1_condition)).\n"
				+ "cond(role1,bel(obj2),bel(obj2_deadline),bel(obj2_condition)).\n"
				+ "cond(role2,bel(obj2),bel(obj2_deadline),bel(obj2_condition)).\n";
		
		PrologLoader prologLoader = new PrologLoader();
		prologLoader.addTheory(new Theory(theory), KBType.ORGANIZATION);
		
		MentalState ms = new MentalState(prologLoader.load());
		AgentState state = new AgentState(new AortaAgent("agent", ms, null, new Linear()), ms, null);
		ms = null;
		
		state = t.executeTransition(engine, state);
		
		Term org1 = Term.createTerm("org(obl(agent, role1, bel(obj1),bel(obj1_deadline)))");
		Term org2 = Term.createTerm("org(obl(agent, role1, bel(obj2),bel(obj2_deadline)))");
		Term org3 = Term.createTerm("org(obl(agent, role2, bel(obj2),bel(obj2_deadline)))");
		
		assertFalse(engine.exists(state.getMentalState(), org1));
		assertFalse(engine.exists(state.getMentalState(), org2));
		assertFalse(engine.exists(state.getMentalState(), org3));
				
		state.insertTerm(engine, new Struct("obj1_condition"), KBType.BELIEF);
		state = t.executeTransition(engine, state);
		// Still false, not enacting role
		assertFalse(engine.exists(state.getMentalState(), org1));
		
		state.insertTerm(engine, (Struct) Term.createTerm("org(rea(agent, role1))"));
				
		state = t.executeTransition(engine, state);
		
		assertTrue(engine.exists(state.getMentalState(), org1));
		
		state.insertTerm(engine, (Struct) Term.createTerm("org(rea(agent, role2))"));
		// Still false, condition not met
		assertFalse(engine.exists(state.getMentalState(), org3));
		
		state.insertTerm(engine, new Struct("obj2_condition"), KBType.BELIEF);
		
		state = t.executeTransition(engine, state);
		state = t.executeTransition(engine, state);

		assertTrue(engine.exists(state.getMentalState(), org2));
		assertTrue(engine.exists(state.getMentalState(), org3));
		
		
	}
	
	@Test
	public void testWithPredicate() throws Exception {
		Transition t = new ObligationActivated();
		
		QueryEngine engine = new QueryEngine();
		
		String theory = "" +
				"role(borrower, [borrowed(Book), returned(Book)]).\n" +
				"obj(borrowed(Book), []).\n" +
				"obj(returned(Book), []).\n" +
				"cond(borrower, bel(returned(Book)), bel(day(ReturnDate)), bel(borrowed(Book, ReturnDate))).\n" +
				"cond(borrower, bel(paidFine(Book)), bel(day(FineDate)), org(viol(Agent, borrower, bel(returned(Book)), FineDate))).\n" +
				"bel(borrowed(Book,ReturnDate)) :- bel(borrowed(Book)), bel(returnDate(Book,ReturnDate)).\n" +
				"org(viol(Agent, borrower, bel(returned(Book)), FineDate)) :- org(viol(Agent, borrower, bel(returned(Book)))), bel(borrowed(Book,ReturnDate)), FineDate is ReturnDate+2.\n";
		
		PrologLoader prologLoader = new PrologLoader();
		prologLoader.addTheory(new Theory(theory), KBType.ORGANIZATION);
		
		MentalState ms = new MentalState(prologLoader.load());
		AgentState state = new AgentState(new AortaAgent("agent", ms, null, new Linear()), ms, null);
		ms = null;
		
		state = t.executeTransition(engine, state);
		
		Term orgObl = Term.createTerm("org(obl(agent, borrower, bel(returned(test)), bel(day(X))))");
		
		assertFalse(engine.exists(state.getMentalState(), orgObl));
				
		state.insertTerm(engine, (Struct) Term.createTerm("bel(borrowed(test))"));
		state.insertTerm(engine, (Struct) Term.createTerm("bel(day(0))"));
		state.insertTerm(engine, (Struct) Term.createTerm("bel(returnDate(test, 4))"));
		
		state.insertTerm(engine, (Struct) Term.createTerm("org(rea(agent, borrower))"));

		state = t.executeTransition(engine, state);
		assertTrue(engine.exists(state.getMentalState(), orgObl));

	}
}