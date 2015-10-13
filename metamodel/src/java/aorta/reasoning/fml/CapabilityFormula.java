/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.reasoning.fml;

import alice.tuprolog.Term;
import aorta.kr.util.TermFormatter;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class CapabilityFormula extends Formula {

	protected Term capability;
	
	public CapabilityFormula(Term capability) {
		this.capability = capability;
	}
	@Override
	public Term getAsTerm() {
		return capability;
	}

	@Override
	public String toString() {
		return "cap(" + TermFormatter.toString(capability) + ")";
	}
	
}
