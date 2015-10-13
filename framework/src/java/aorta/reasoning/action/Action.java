package aorta.reasoning.action;

import alice.tuprolog.Term;
import aorta.AORTAException;
import aorta.AgentState;
import aorta.logging.Logger;

public abstract class Action {

	private static final Logger logger = Logger.getLogger(Action.class.getName());
	
	public Action() {
	}
    
    public final AgentState execute(Term option, AgentState state) throws AORTAException {
        final AgentState result = executeAction(option, state);
        if (result != null) {
            logger.finest("[" + state.getAgent().getName() + "] Executing action: " + this);
        }
        return result;
    }
    
	protected abstract AgentState executeAction(Term option, AgentState state) throws AORTAException;
	
}