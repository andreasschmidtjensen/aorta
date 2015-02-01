/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts;

import alice.tuprolog.Struct;
import aorta.State;
import aorta.kr.KBType;
import aorta.kr.QueryEngine;
import aorta.kr.util.FormulaQualifier;
import aorta.logging.Logger;

/**
 *
 * @author asj
 * @param <T>
 */
public abstract class Transition<T extends State> {
 
	private static final Logger logger = Logger.getLogger(Transition.class.getName());
	
	protected abstract T execute(QueryEngine engine, T state);
	
	public T executeTransition(QueryEngine engine, T state) {
		logger.finest("[" + state.getDescription() + "] Executing transition: " + getName());
		state.prepareForTransition(); // clears bindings
		return execute(engine, state);
	}
    
	public abstract String getName();
	
	public void add(State state, QueryEngine engine, Struct term, KBType type) {
		state.insertTerm(engine, term, type);
		state.notifyTermAdded(getName(), FormulaQualifier.qualifyStruct(term, type));
	}
	
	public void add(State state, QueryEngine engine, Struct term) {
		state.insertTerm(engine, term);
		state.notifyTermAdded(getName(), term);
	}
	
	public void insertInMs(State state, QueryEngine engine, Struct term) {
		state.insertInMentalState(engine, term);
		state.notifyTermAdded(getName(), term);
	}
	
	public void remove(State state, QueryEngine engine, Struct term, KBType type) {
		state.removeTerm(engine, term, type);
		state.notifyTermRemoved(getName(), FormulaQualifier.qualifyStruct(term, type));
	}
	
	public void remove(State state, QueryEngine engine, Struct term) {
		state.removeTerm(engine, term);
		state.notifyTermRemoved(getName(), term);
	}
	
	public void removeFromMs(State state, QueryEngine engine, Struct term) {
		state.removeFromMentalState(engine, term);
		state.notifyTermRemoved(getName(), term);
	}
	
}