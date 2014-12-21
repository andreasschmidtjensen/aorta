/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.organization;

import alice.tuprolog.InvalidLibraryException;
import alice.tuprolog.InvalidTheoryException;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.PrologLoader;
import aorta.kr.QueryEngine;
import aorta.kr.language.model.Metamodel;
import aorta.organization.action.Operation;
import aorta.organization.action.DeactOp;
import aorta.organization.action.EnactOp;
import aorta.organization.ts.strategy.ArtifactStrategy;
import aorta.ts.strategy.StrategyFailedException;
import cartago.Artifact;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.OperationException;
import java.io.IOException;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class AortaArtifact extends Artifact {

	private static AortaArtifact INSTANCE;
	
	private int cycle;
	private boolean stopped = false;

	private ArtifactState state;
	private ArtifactStrategy strategy;

	void init(Metamodel initialModel, EnvironmentSensor environment) throws InvalidTheoryException, InvalidLibraryException, IOException {
		if (INSTANCE != null) {
			throw new RuntimeException("Already instantiated!");
		}
		INSTANCE = this;
		
		PrologLoader loader = new PrologLoader();
		loader.addTheory(initialModel.createTheory(), KBType.ORGANIZATION);
		
		state = new ArtifactState(new MentalState(loader.load()), this, environment);
				
		strategy = new ArtifactStrategy();
		
		defineObsProperty("model", new QueryEngine().toStructs(state.getMentalState().getProlog(), KBType.ORGANIZATION));
		
		execInternalOp("cycle");
	}

	public static AortaArtifact get() {
		return INSTANCE;
	}
	
	@OPERATION void enact(String agent, String role) {
		state.addAgent(agent, getOpUserId());
		state.getQueue().offer(new EnactOp(agent, role));
	}
	
	@OPERATION void deact(String agent, String role) {
		state.addAgent(agent, getOpUserId());
		state.getQueue().offer(new DeactOp(agent, role));
	}
	
	@INTERNAL_OPERATION void cycle() throws OperationException {
		while (!stopped) {
			try {
				state = strategy.execute(state);
				cycle++;
				
				if (state.hasChanged()) {
					getObsProperty("model").updateValue(new QueryEngine().toStructs(state.getMentalState().getProlog(), KBType.ORGANIZATION));
				}
			} catch (StrategyFailedException ex) {
				ex.printStackTrace();
				throw new OperationException("Failed with: " + ex.getMessage());
			}
			
			await_time(500);
		}
	}

	public ArtifactState getState() {
		return state;
	}
	
	public void stop() {
		stopped = true;
	}
	
	public int getCycle() {
		return cycle;
	}

	public void operationFailed(Operation op) {
		signal(state.getAgent(op.getAgent()), "failure", op);
	}
	
	@Override
	public String toString() {
		StringBuilder queue = new StringBuilder();
		for (Operation o : state.getQueue()) {
			queue.append(" > ").append(o).append("\n");
		}
		return "AORTA Artifact\n"
				+ "MentalState: \n" + state.getMentalState() + "\n"
				+ "Queue: \n" + queue;
	}
	
}
