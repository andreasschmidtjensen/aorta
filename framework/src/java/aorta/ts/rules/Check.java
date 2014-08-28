/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.Struct;
import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.msg.IncomingOrganizationalMessage;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import java.util.logging.Level;
import aorta.logging.Logger;

/**
 *
 * @author ascje
 */
public class Check extends Transition {

	private static final Logger logger = Logger.getLogger(Check.class.getName());
	
	@Override
	public AgentState execute(QueryEngine engine, AgentState state) {
		AgentState newState = state;
		
		IncomingOrganizationalMessage iom;
		
		if (state.getExternalAgent().containsMsgs()) {
			//XXX: newState = state.clone();;
			if ((iom = newState.getExternalAgent().getIncomingMessage()) != null) {
				Struct oMsg = (Struct) iom.getMessage();
				Struct msg = (Struct) oMsg.getArg(0);

				logger.log(Level.INFO, "Handling msg: " + msg + " from " + iom.getSender());

				Tracer.trace(state.getAgent().getName(), "(Chk) " + iom + "\n");
				
				newState.insertMessage(engine, iom);
			}
		}
		
		return newState;
	}

	@Override
	public String getName() {
		return "Check";
	}
	
}
