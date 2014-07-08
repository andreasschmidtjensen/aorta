/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta;

import alice.tuprolog.Struct;
import aorta.msg.IncomingOrganizationalMessage;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author asj
 */
public class ExternalAgent {
    
    protected Queue<Struct> newBeliefs;
    protected Queue<Struct> removedBeliefs;
    protected Queue<Struct> newGoals;
    protected Queue<Struct> removedGoals;
	
	protected Queue<IncomingOrganizationalMessage> incomingMessages;

    public ExternalAgent() {
        newBeliefs = new LinkedList<>();
        removedBeliefs = new LinkedList<>();
        newGoals = new LinkedList<>();
        removedGoals = new LinkedList<>();
		
		incomingMessages = new LinkedList<>();
    }
    
	public synchronized boolean containsBBChanges() {
		return newBeliefs.size() + removedBeliefs.size() > 0;
	}
	
	public synchronized boolean containsGBChanges() {
		return newGoals.size() + removedGoals.size() > 0;
	}
	
	public synchronized boolean containsMsgs() {
		return incomingMessages.size() > 0;
	}
	
	public synchronized boolean containsChanges() {
		return containsBBChanges() || containsGBChanges() || containsMsgs();
	}
	
    public synchronized void addBelief(Struct struct) {
        newBeliefs.add(struct);
		removedBeliefs.remove(struct);
    }
    
    public synchronized void removeBelief(Struct struct) {
        removedBeliefs.add(struct);
		newBeliefs.remove(struct);
    }
    
    public synchronized void addGoal(Struct struct) {
        newGoals.add(struct);
		removedGoals.remove(struct);
    }
    
    public synchronized void removeGoal(Struct struct) {
        removedGoals.add(struct);
		newGoals.remove(struct);
    }
	
	public synchronized void receiveMessage(IncomingOrganizationalMessage msg) {
		incomingMessages.add(msg);
	}
	
	public synchronized Struct getNewBelief() {
		Struct struct = newBeliefs.poll();
		return struct;
	}
	
	public synchronized Struct getRemovedBelief() {
		Struct struct = removedBeliefs.poll();
		return struct;
	}
	
	public synchronized Struct getNewGoal() {
		return newGoals.poll();
	}
	
	public synchronized Struct getRemovedGoal() {
		return removedGoals.poll();
	}

	public synchronized IncomingOrganizationalMessage getIncomingMessage() {
		IncomingOrganizationalMessage msg = incomingMessages.poll();
		return msg;
	}

	Queue<IncomingOrganizationalMessage> getIncomingMessages() {
		return incomingMessages;
	}
		
    @Override
    protected synchronized ExternalAgent clone() {
        ExternalAgent clone = new ExternalAgent();
        clone.newBeliefs = new LinkedList<>(newBeliefs);
        clone.removedBeliefs = new LinkedList<>(removedBeliefs);
        clone.newGoals = new LinkedList<>(newGoals);
        clone.removedGoals = new LinkedList<>(removedGoals);
		clone.incomingMessages = new LinkedList<>(incomingMessages);
        return clone;
    }
    
}
