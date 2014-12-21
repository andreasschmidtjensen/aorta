/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta.ts.strategy;

import aorta.State;
import java.util.List;

/**
 *
 * @author Andreas
 * @param <T>
 */
public class Linear<T extends State> implements Strategy<T> {

	private final List<Executor> executors;

	public Linear(List<Executor> executors) {
		this.executors = executors;
	}
	
	@Override
	public T execute(T initial) throws StrategyFailedException {
		T next = initial;
		
		boolean changed = false;
		for (Executor<T> exec : executors) {
			next = exec.execute(next);
			if (next.hasChanged()) {
				changed = true;
			}
		}

		next.setChanged(changed);
		return next;
	}
	
}
