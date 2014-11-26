/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.strategy;

import aorta.State;

/**
 *
 * @author asj
 * @param <T>
 */
public interface Strategy<T extends State> {
	
	public T execute(T state) throws StrategyFailedException;
	
}
