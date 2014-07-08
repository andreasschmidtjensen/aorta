/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.strategy;

/**
 *
 * @author asj
 */
public class StrategyFailedException extends Exception {

	public StrategyFailedException() {
	}

	public StrategyFailedException(String message) {
		super(message);
	}

	public StrategyFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public StrategyFailedException(Throwable cause) {
		super(cause);
	}
	
}
