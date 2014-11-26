/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta.organization.ts.strategy;

import aorta.organization.ArtifactState;
import aorta.organization.ts.rules.OperationExecution;
import aorta.organization.ts.rules.Sense;
import aorta.ts.rules.ObligationActivated;
import aorta.ts.rules.ObligationSatisfied;
import aorta.ts.rules.ObligationViolated;
import aorta.ts.strategy.ExecuteOnce;
import aorta.ts.strategy.ExecuteStar;
import aorta.ts.strategy.Executor;
import aorta.ts.strategy.Linear;
import aorta.ts.strategy.Strategy;
import aorta.ts.strategy.StrategyFailedException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class ArtifactStrategy implements Strategy<ArtifactState> {

	private final List<Executor> executors = new ArrayList<>();
	{
		executors.add(new ExecuteOnce(new Sense()));
		executors.add(new ExecuteStar(new ObligationActivated()));
		executors.add(new ExecuteStar(new ObligationSatisfied()));
		executors.add(new ExecuteStar(new ObligationViolated()));
		executors.add(new ExecuteOnce(new OperationExecution()));
	}
	
	private Linear<ArtifactState> linear;

	public ArtifactStrategy() {
		linear = new Linear(executors);
	}

	@Override
	public ArtifactState execute(ArtifactState state) throws StrategyFailedException {
		return linear.execute(state);
	}
}
