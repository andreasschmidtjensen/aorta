/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.strategy;

import aorta.State;
import aorta.kr.QueryEngine;
import aorta.ts.Transition;

/**
 *
 * @author Andreas
 * @param <T>
 */
public abstract class Executor<T extends State> {

	protected QueryEngine engine = new QueryEngine();
	protected Transition transition;

	public Executor(Transition transition) {
		this.transition = transition;
	}

	abstract T execute(T state);
}
