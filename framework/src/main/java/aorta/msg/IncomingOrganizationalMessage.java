/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.msg;

import alice.tuprolog.Term;

/**
 *
 * @author ascje
 */
public class IncomingOrganizationalMessage extends OrganizationalMessage {
	
	private String sender;

	public IncomingOrganizationalMessage(String sender, Term message) {
		super(message);
		this.sender = sender;
	}

	public String getSender() {
		return sender;
	}

	@Override
	public String toString() {
		return "msg(" + sender + ", " + message + ")";
	}
	
}
