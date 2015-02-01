/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.tracer;

import alice.tuprolog.Term;
import aorta.State;

/**
 *
 * @author asj
 */
public interface StateListener {
	
	public void termAdded(String name, Term term);
	public void termRemoved(String name, Term term);
	public void newState(State state);
	
}
