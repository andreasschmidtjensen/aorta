package aorta.reasoning.action;

import aorta.AORTAException;
import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.logging.Logger;

public abstract class Action {

	private static final Logger logger = Logger.getLogger(Action.class.getName());
	
	public Action() {
	}
    
    public final AgentState execute(QueryEngine engine, AgentState state) throws AORTAException {
        final AgentState result = executeAction(engine, state);
        if (result != null) {
            logger.fine("[" + state.getAgent().getName() + "] Executing action: " + this);
        }
        return result;
    }
    
	protected abstract AgentState executeAction(QueryEngine engine, AgentState state) throws AORTAException;
	
}