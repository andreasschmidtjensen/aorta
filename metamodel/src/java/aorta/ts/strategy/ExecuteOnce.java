/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.strategy;

import aorta.State;
import aorta.ts.Transition;

/**
 *
 * @author Andreas
 */
public class ExecuteOnce extends Executor {

	public ExecuteOnce(Transition transition) {
		super(transition);
	}

	@Override
	State execute(State state) {
		state.prepareForTransition();
		return transition.executeTransition(engine, state);
	}
	
}
