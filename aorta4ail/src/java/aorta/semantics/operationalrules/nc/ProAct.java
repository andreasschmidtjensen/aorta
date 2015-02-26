/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics.operationalrules.nc;

import aorta.syntax.metamodel.Norm;

/**
 *
 * @author asj
 */
public class ProAct extends NormAct {

	@Override
	protected String getDeon() {
		return Norm.PROHIBITION;
	}

}
