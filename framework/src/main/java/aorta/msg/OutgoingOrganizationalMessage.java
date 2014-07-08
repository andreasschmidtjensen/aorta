/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.msg;

import alice.tuprolog.Term;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ascje
 */
public class OutgoingOrganizationalMessage extends OrganizationalMessage {
	
	private List<Term> recipients;

	public OutgoingOrganizationalMessage(List<Term> recipients, Term message) {
		super(message);
		this.recipients = recipients;
	}

	public OutgoingOrganizationalMessage(Term recipient, Term message) {
		super(message);
		this.recipients = new ArrayList<>();
		this.recipients.add(recipient);
	}

	public List<Term> getRecipients() {
		return recipients;
	}

	@Override
	public String toString() {
		StringBuilder rcps = new StringBuilder();
		rcps.append("[");
		for (int i = 0; i < recipients.size(); i++) {
			if (i > 0) {
				rcps.append(",");
			}
			rcps.append(recipients.get(i).toString());
		}
		rcps.append("]");
		return "msg(" + rcps + ", " + message + ")";
	}

}
