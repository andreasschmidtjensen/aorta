/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta;

import aorta.kr.KBType;

/**
 * <p>Internal action: <b><code>.opt(Literal)</code></b>.
 * 
 * <p>Description: succeeds if the literal follows from the agents AORTA option base.
 * 
 * <p>Parameters:<ul>
 * 
 * <li>+ literal: the formula that is evaluated.
 * <br/>
 * 
 * </ul>
 * 
 * <p>Examples:
 * 
 * <ul>
 * 
 * <li> <code>.opt(role(R))</code>: suceeds if role(R) is an option.</li>
 * 
 * @author asj
 */
public class opt extends KBQueryAction {

	public opt() {
		super(KBType.OPTION);
	}
	
}
