/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta;

import alice.tuprolog.Term;
import aorta.msg.OutgoingOrganizationalMessage;

/**
 *
 * @author ascje
 */
public interface AortaBridge {
	
	public boolean addBelief(Term belief);
	public boolean addReceivedBelief(String sender, Term msg);
	public boolean removeBelief(Term belief);
	
	public boolean addGoal(Term goal);
	public boolean addReceivedGoal(String sender, Term msg);
	public boolean removeGoal(Term goal);
	
	public void sendMessage(OutgoingOrganizationalMessage msg);
	
}
