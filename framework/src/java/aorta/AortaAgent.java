package aorta;

import alice.tuprolog.Struct;
import aorta.kr.KBType;
import java.util.List;

import aorta.kr.MentalState;
import aorta.kr.util.Qualifier;
import aorta.msg.OutgoingOrganizationalMessage;
import aorta.reasoning.ActionRule;
import aorta.ts.strategy.Strategy;
import aorta.ts.strategy.StrategyFailedException;
import java.util.Iterator;
import java.util.logging.Level;
import aorta.logging.Logger;

public class AortaAgent {

	private int cycle;
	
	public static final Logger logger = Logger.getLogger(AortaAgent.class.getName());
	private String name;
	private AgentState state;
	private Aorta aorta;
	private Strategy strategy;
	
	private boolean lastCycleChangedState = true;

	public AortaAgent(String name, MentalState mentalState, List<ActionRule> actionRules, Strategy strategy) {
		this.name = name;
		this.strategy = strategy;

		state = new AgentState(this, mentalState, actionRules);

		setup();
	}

	private void setup() {
		state.getMentalState().setAgent(this);
	}

	public AgentState getState() {
		return state;
	}

	public void addAgentToBeliefs(AortaAgent agent) {
		Struct qualified = Qualifier.qualifyStruct(new Struct("agent", new Struct(agent.name)), KBType.BELIEF.getType());
		state.getMentalState().getProlog().getTheoryManager().assertZ(qualified, true, null, true);
	}

	public void removeAgentFromBeliefs(AortaAgent agent) {
		Struct qualified = Qualifier.qualifyStruct(new Struct("agent", new Struct(agent.name)), KBType.BELIEF.getType());
		state.getMentalState().getProlog().getTheoryManager().retract(qualified);
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public String getName() {
		return name;
	}

	public void setAorta(Aorta aorta) {
		this.aorta = aorta;
	}

	public Aorta getAorta() {
		return aorta;
	}

	public void newCycle() throws StrategyFailedException {
		cycle++;
		long start = System.currentTimeMillis();
		logger.log(Level.FINE, "(" + getName() + ") New AORTA cycle [" + cycle + "]. Strategy: " + strategy.getClass().getName());
		state.newCycle();
		AgentState newState = strategy.execute(state);
		
		lastCycleChangedState = state != newState;
		
		state = newState;
		
		
		if (!state.getOut().isEmpty()) {
			logger.log(Level.FINE, "(" + getName() + ") Sending " + state.getOut().size() + " messages.");
			Iterator<OutgoingOrganizationalMessage> it = state.getOut().iterator();
			while (it.hasNext()) {
				OutgoingOrganizationalMessage msg = it.next();
				if (state.getBridge() != null) {
					state.getBridge().sendMessage(msg);
				}
				it.remove();
			}
		}

		logger.log(Level.FINE, "(" + getName() + ") Cycle done [" + cycle + "] (time: " + (System.currentTimeMillis() - start) + " ms)");
	}
	
	public boolean hasChanged() {
		return lastCycleChangedState || state.getExternalAgent().containsChanges();
	}

	@Override
	public String toString() {
		String actRules = "";
		for (ActionRule r : state.getActionRules()) {
			actRules += " > " + r.toString() + "\n";
		}
		return "Agent: " + name + "\n"
				+ "Strategy: " + strategy.getClass().getName() + "\n"
				+ "MentalState: \n" + state.getMentalState() + "\n"
				+ "In: " + state.getExternalAgent().getIncomingMessages() + "\n"
				+ "Out: " + state.getOut() + "\n"
				+ "Act: \n" + actRules;
	}

	public int getCycle() {
		return cycle;
	}
	
}
