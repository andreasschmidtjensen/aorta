/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.strategy;

import aorta.AgentState;
import aorta.ts.rules.Check;
import aorta.ts.rules.Ext;
import aorta.ts.rules.ActionExecution;
import aorta.ts.rules.og.DeactRule;
import aorta.ts.rules.og.Delegate;
import aorta.ts.rules.og.Inform;
import aorta.ts.rules.ObligationActivated;
import aorta.ts.rules.ObligationSatisfied;
import aorta.ts.rules.ObligationViolated;
import aorta.ts.rules.ProhibitionActivated;
import aorta.ts.rules.ProhibitionExpired;
import aorta.ts.rules.ProhibitionViolated;
import aorta.ts.rules.og.EnactRule;
import aorta.ts.rules.og.ObjectiveRule;
import aorta.ts.rules.Sense;
import aorta.ts.rules.og.NormRule;
import aorta.ts.rules.og.ViolationRule;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asj
 */
public class AgentStrategy implements Strategy<AgentState> {
	
	private final List<Executor> executors = new ArrayList<>();
	
	private final Linear<AgentState> linear;

	public AgentStrategy(boolean usingArtifact) {
		executors.add(new ExecuteStar(new Check()));
		executors.add(new ExecuteOnce(new Ext()));
		if (usingArtifact) {
			executors.add(new ExecuteOnce(new Sense()));
		} else {
			executors.add(new ExecuteStar(new ObligationActivated()));
			executors.add(new ExecuteStar(new ObligationSatisfied()));
			executors.add(new ExecuteStar(new ObligationViolated()));
			executors.add(new ExecuteStar(new ProhibitionActivated()));
			executors.add(new ExecuteStar(new ProhibitionExpired()));
			executors.add(new ExecuteStar(new ProhibitionViolated()));
		}
		executors.add(new ExecuteStar(new EnactRule()));
		executors.add(new ExecuteStar(new DeactRule()));
		executors.add(new ExecuteStar(new ObjectiveRule()));
		executors.add(new ExecuteStar(new NormRule()));
		executors.add(new ExecuteStar(new ViolationRule()));
		executors.add(new ExecuteStar(new Delegate()));
		executors.add(new ExecuteStar(new Inform()));
		executors.add(new ExecuteOnce(new ActionExecution()));
	
		linear = new Linear(executors);
	}

	@Override
	public AgentState execute(AgentState state) throws StrategyFailedException {
		return linear.execute(state);
	}
		
}
