/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.tracer;

import alice.tuprolog.Term;
import aorta.State;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ConsoleTrace implements StateListener {
	
	private final String agent;
	
	public ConsoleTrace(String agent) {
		this.agent = agent;
	}

	@Override
	public void termAdded(String name, Term term) {
		System.out.println("[" + agent + "] " + name + ": +" + term);
	}

	@Override
	public void termRemoved(String name, Term term) {
		System.out.println("[" + agent + "] " + name + ": -" + term);
	}

	@Override
	public void newState(State state) {
	}
	
}
