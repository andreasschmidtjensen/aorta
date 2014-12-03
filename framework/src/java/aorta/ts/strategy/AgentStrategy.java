/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.strategy;

import aorta.AgentState;
import aorta.ts.rules.Check;
import aorta.ts.rules.Ext;
import aorta.ts.rules.ActionExecution;
import aorta.ts.rules.DeactRule;
import aorta.ts.rules.Delegate;
import aorta.ts.rules.Inform;
import aorta.ts.rules.ObligationActivated;
import aorta.ts.rules.ObligationSatisfied;
import aorta.ts.rules.ObligationViolated;
import aorta.ts.rules.EnactRule;
import aorta.ts.rules.InformSubObj;
import aorta.ts.rules.ObjectiveRule;
import aorta.ts.rules.Sense;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asj
 */
public class AgentStrategy implements Strategy<AgentState> {
	
	private final List<Executor> executors = new ArrayList<>();
	
	private Linear<AgentState> linear;

	public AgentStrategy(boolean usingArtifact) {
		executors.add(new ExecuteStar(new Check()));
		executors.add(new ExecuteOnce(new Ext()));
		if (usingArtifact) {
			executors.add(new ExecuteOnce(new Sense()));
		} else {
			executors.add(new ExecuteStar(new ObligationActivated()));
			executors.add(new ExecuteStar(new ObligationSatisfied()));
			executors.add(new ExecuteStar(new ObligationViolated()));
		}
		executors.add(new ExecuteStar(new EnactRule()));
		executors.add(new ExecuteStar(new DeactRule()));
		executors.add(new ExecuteStar(new ObjectiveRule()));
		executors.add(new ExecuteStar(new Delegate()));
		executors.add(new ExecuteStar(new Inform()));
		executors.add(new ExecuteStar(new InformSubObj()));
		executors.add(new ExecuteOnce(new ActionExecution()));
	
		linear = new Linear(executors);
	}

	@Override
	public AgentState execute(AgentState state) throws StrategyFailedException {
		return linear.execute(state);
	}
		
}
