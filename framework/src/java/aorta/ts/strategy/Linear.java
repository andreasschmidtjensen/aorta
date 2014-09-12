/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.strategy;

import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import aorta.ts.rules.Check;
import aorta.ts.rules.Ext;
import aorta.logging.Logger;
import aorta.ts.rules.ActionExecution;
import aorta.ts.rules.DeactRule;
import aorta.ts.rules.Delegate;
import aorta.ts.rules.Inform;
import aorta.ts.rules.ObligationActivated;
import aorta.ts.rules.ObligationSatisfied;
import aorta.ts.rules.ObligationViolated;
import aorta.ts.rules.EnactRule;
import aorta.ts.rules.ObjectiveRule;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asj
 */
public class Linear implements Strategy {

	private static final Logger logger = Logger.getLogger(Linear.class.getName());
	
	private QueryEngine engine = new QueryEngine();
	private List<Executor> executors = new ArrayList<>();
	{
		executors.add(new ExecuteStar(new Check()));
		executors.add(new ExecuteOnce(new Ext()));
		executors.add(new ExecuteStar(new ObligationActivated()));
		executors.add(new ExecuteStar(new ObligationSatisfied()));
		executors.add(new ExecuteStar(new ObligationViolated()));
		executors.add(new ExecuteStar(new EnactRule()));
		executors.add(new ExecuteStar(new DeactRule()));
		executors.add(new ExecuteStar(new ObjectiveRule()));
		executors.add(new ExecuteStar(new Delegate()));
		executors.add(new ExecuteStar(new Inform()));
		executors.add(new ExecuteOnce(new ActionExecution()));
	}

	@Override
	public AgentState execute(AgentState s0) throws StrategyFailedException {
		AgentState next = s0;
		
		long total = 0;
		for (Executor exec : executors) {
			long ms = System.currentTimeMillis();
			next = exec.execute(next);
			long elapsed = System.currentTimeMillis() - ms;
			total += elapsed;
//			System.out.println("* " + exec.transition.getName() + ": " + elapsed + "ms");
		}
//		System.out.println("Total: " + total + "ms");

		if (next != s0) {
			Tracer.trace(s0.getAgent().getName(), "--------------------\n");
		}
		return next;
	}
	
	abstract class Executor {
		protected Transition transition;

		public Executor(Transition transition) {
			this.transition = transition;
		}
		
		abstract AgentState execute(AgentState state);
	}
	class ExecuteOnce extends Executor {

		public ExecuteOnce(Transition transition) {
			super(transition);
		}
		
		@Override
		AgentState execute(AgentState state) {
			return executeOnce(transition, state);
		}
	}
	class ExecuteStar extends Executor {

		public ExecuteStar(Transition transition) {
			super(transition);
		}
		
		@Override
		AgentState execute(AgentState state) {
			AgentState newState = null;
			while (state != newState) {
				AgentState nextState = executeOnce(transition, state);
				newState = state;
				state = nextState;
			}
			return newState;
		}
	}
	
	private AgentState executeOnce(Transition transition, AgentState currentState) {
		AgentState newState = transition.executeTransition(engine, currentState);
		if (newState != null) {
			newState.prepareForTransition();
			return newState;
		} else {
			return currentState;
		}
	}
		
}
