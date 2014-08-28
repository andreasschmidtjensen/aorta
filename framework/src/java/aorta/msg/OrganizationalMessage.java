/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.msg;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;

/**
 *
 * @author ascje
 */
public abstract class OrganizationalMessage {

	protected Term message;

	public OrganizationalMessage(Term message) {
		this.message = new Struct("om", message);
	}

	public Term getMessage() {
		return message;
	}

}
