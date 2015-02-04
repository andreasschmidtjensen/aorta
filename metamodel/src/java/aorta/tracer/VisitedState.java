/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.tracer;

import alice.tuprolog.Term;
import aorta.State;
import aorta.kr.MentalState;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author asj
 */
public class VisitedState implements StateListener, Iterable<FiredTransitionRule> {
	
	private final String mentalState;
	private int stateNum;
	
	private final Map<String, FiredTransitionRule> firedRules;

	public VisitedState(int stateNum, MentalState mentalState) {
		this.stateNum = stateNum;
		this.mentalState = mentalState.toString();
		firedRules = new LinkedHashMap<>();
	}

	public int getStateNum() {
		return stateNum;
	}

	public String getMentalState() {
		return mentalState;
	}

	@Override
	public void termAdded(String name, Term term) {
		FiredTransitionRule rule = get(name);
		rule.termAdded(term);
	}

	@Override
	public void termRemoved(String name, Term term) {
		FiredTransitionRule rule = get(name);
		rule.termRemoved(term);
	}
	
	private FiredTransitionRule get(String name) {
		if (!firedRules.containsKey(name)) {
			firedRules.put(name, new FiredTransitionRule(name));
		}
		return firedRules.get(name);
	}

	@Override
	public void newState(State state) {
		// not used here
	}

	@Override
	public Iterator<FiredTransitionRule> iterator() {
		return firedRules.values().iterator();
	}

	@Override
	public String toString() {
		return "s_" + stateNum;
	}
	
}
