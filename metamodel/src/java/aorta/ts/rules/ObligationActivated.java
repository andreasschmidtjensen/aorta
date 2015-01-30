/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.Struct;
import aorta.kr.language.model.Norm;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class ObligationActivated extends NormActivated {

	@Override
	public Struct getDeon() {
		return new Struct(Norm.OBLIGATION);
	}

	
	@Override
	public String getName() {
		return "Obl-Activated";
	}
}
