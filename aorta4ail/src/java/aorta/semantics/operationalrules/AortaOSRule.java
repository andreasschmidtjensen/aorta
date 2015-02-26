/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics.operationalrules;

import ail.semantics.OSRule;

/**
 *
 * @author asj
 */
public abstract class AortaOSRule implements OSRule {

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
	
	protected String getLoggerName() {
		return getClass().getName();
	}
	
}
