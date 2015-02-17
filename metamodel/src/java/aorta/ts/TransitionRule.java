/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts;

import alice.tuprolog.Struct;
import aorta.State;
import aorta.kr.KBType;
import aorta.kr.util.FormulaQualifier;
import aorta.logging.Logger;

/**
 *
 * @author asj
 * @param <T>
 */
public abstract class TransitionRule<T extends State> {
 
	private static final Logger logger = Logger.getLogger(TransitionRule.class.getName());
	
	protected abstract T execute(T state);
	
	public T executeTransition(T state) {
		logger.finest("[" + state.getDescription() + "] Executing transition: " + getName());
		state.prepareForTransition(); // clears bindings
		return execute(state);
	}
    
	public abstract String getName();
	
	public void add(State state, Struct term, KBType type) {
		state.insertTerm(term, type);
		state.notifyTermAdded(getName(), FormulaQualifier.qualifyStruct(term, type));
	}
	
	public void add(State state, Struct term) {
		state.insertTerm(term);
		state.notifyTermAdded(getName(), term);
	}
	
	public void insertInMs(State state, Struct term) {
		state.insertInMentalState(term);
		state.notifyTermAdded(getName(), term);
	}
	
	public void remove(State state, Struct term, KBType type) {
		state.removeTerm(term, type);
		state.notifyTermRemoved(getName(), FormulaQualifier.qualifyStruct(term, type));
	}
	
	public void remove(State state, Struct term) {
		state.removeTerm(term);
		state.notifyTermRemoved(getName(), term);
	}
	
	public void removeFromMs(State state, Struct term) {
		state.removeFromMentalState(term);
		state.notifyTermRemoved(getName(), term);
	}
	
}