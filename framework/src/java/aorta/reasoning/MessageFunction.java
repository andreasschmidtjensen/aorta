/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.reasoning;

import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.msg.IncomingOrganizationalMessage;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class MessageFunction {

	public MessageFunction() {
	}
	
	public AgentState process(QueryEngine engine, IncomingOrganizationalMessage message, AgentState state) {
		AgentState newState = state;
		newState.insertMessage(engine, message);
		return newState;
	}
	
}
