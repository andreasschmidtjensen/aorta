/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.tracer;

import alice.tuprolog.Term;
import aorta.State;
import aorta.kr.MentalState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author asj
 */
public class ExecutionTrace implements StateListener, Iterable<VisitedState> {
	
	private final String agent;
	private final List<VisitedState> states;
	
	private int stateNum = 0;
	private VisitedState currentState;

	public ExecutionTrace(String agent) {
		this.agent = agent;
		
		states = new ArrayList<>();
	}

	private void newState(MentalState state) {
		currentState = new VisitedState(stateNum++, state);
		states.add(currentState);
	}
	
	public String getAgent() {
		return agent;
	}

	@Override
	public String toString() {
		return agent;
	}

	@Override
	public void termAdded(String name, Term term) {
		currentState.termAdded(name, term);
	}

	@Override
	public void termRemoved(String name, Term term) {
		currentState.termRemoved(name, term);
	}

	@Override
	public void newState(State state) {
		newState(state.getMentalState());
	}

	@Override
	public Iterator<VisitedState> iterator() {
		return states.iterator();
	}
	
}
