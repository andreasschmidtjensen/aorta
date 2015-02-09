/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.strategy;

import aorta.State;
import aorta.ts.TransitionRule;

/**
 *
 * @author Andreas
 */
public class ExecuteStar extends Executor {

	public ExecuteStar(TransitionRule transition) {
		super(transition);
	}

	@Override
	State execute(State state) {
		boolean changed = false;
		boolean cont = true;
		while (cont) {
			state.prepareForTransition();
			state = transition.executeTransition(engine, state);

			cont = state.hasChanged();
			if (state.hasChanged()) {
				changed = true;
			}
		}
		
		state.setChanged(changed);
		return state;
	}
	
}
