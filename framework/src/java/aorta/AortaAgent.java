package aorta;

import alice.tuprolog.Struct;
import aorta.inspector.AgentWebInspector;
import aorta.inspector.Inspector;
import aorta.kr.KBType;
import java.util.List;

import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.util.FormulaQualifier;
import aorta.msg.OutgoingOrganizationalMessage;
import aorta.ts.strategy.Strategy;
import aorta.ts.strategy.StrategyFailedException;
import java.util.Iterator;
import java.util.logging.Level;
import aorta.logging.Logger;
import aorta.organization.AortaArtifactAgent;
import aorta.reasoning.MessageFunction;
import aorta.reasoning.ReasoningRule;
import aorta.tracer.Tracer;
import aorta.ts.strategy.AgentStrategy;
import cartago.ArtifactId;
import cartago.ArtifactObsProperty;
import cartago.CartagoException;
import cartago.util.agent.Percept;
import java.util.ArrayList;

public class AortaAgent {

	private int cycle;
	
	public static final Logger logger = Logger.getLogger(AortaAgent.class.getName());
	private String name;
	private AgentState state;
	private Aorta aorta;
	private Strategy<AgentState> strategy;
	
	private AortaArtifactAgent artifactAgent;
    
    private String lastTrace;
    
	private final List<Inspector> inspectors = new ArrayList<>();
	
	private boolean lastCycleChangedState = true;

	public AortaAgent(String name, MentalState mentalState, List<ReasoningRule> rules) {
		this.name = name;
		this.strategy = new AgentStrategy(false);

		state = new AgentState(this, mentalState, rules);

		setup();
	}

	public void addInspector(Inspector inspector) {
		inspectors.add(inspector);
	}
	
	public AortaAgent() {
	}

	private void setup() {
		state.getMentalState().addAgentOwnName(name);
	}

	public AgentState getState() {
		return state;
	}

	public void addAgentToBeliefs(String agentName) {
		Struct qualified = FormulaQualifier.qualifyStruct(new Struct("agent", new Struct(agentName)), KBType.BELIEF);
		state.insertTerm(new QueryEngine(), qualified);
	}

	public void removeAgentFromBeliefs(String agentName) {
		Struct qualified = FormulaQualifier.qualifyStruct(new Struct("agent", new Struct(agentName)), KBType.BELIEF.getType());
		state.removeTerm(new QueryEngine(), qualified);
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public String getName() {
		return name;
	}

	public AortaArtifactAgent getArtifactAgent() {
		return artifactAgent;
	}

	public void setArtifact(ArtifactId artifact) {
		this.strategy = new AgentStrategy(true);
		
		try {
			System.out.println("setting up artifact agent");
			artifactAgent = new AortaArtifactAgent(name, artifact) {
				@Override
				public List<Struct>[] handlePercept(Percept p) {
					try {
						if (p.obsPropChanges()) {
							ArtifactObsProperty prop = (ArtifactObsProperty) p.getPropChanged()[0];
							List<Struct> orgStructs = (List<Struct>) prop.getValue();
							QueryEngine engine = new QueryEngine();
							return engine.mergeKBs(state.getMentalState(), KBType.ORGANIZATION, orgStructs);
						}
					} catch (NullPointerException ex) {
						// event in percept is null if no percept... no way to check it
					}
					return new List[] { new ArrayList(), new ArrayList() };
				}				
			};
		} catch (CartagoException ex) {
			throw new RuntimeException("Could not set artifact", ex);
		}
	}
	
	public void setAorta(Aorta aorta) {
		this.aorta = aorta;
	}

	public Aorta getAorta() {
		return aorta;
	}

	public void newCycle() throws StrategyFailedException {
		cycle++;
		
		if (AgentWebInspector.isRunning()) {
			Tracer.clearTrace(name);
		}
        
		long start = System.currentTimeMillis();
		logger.log(Level.FINEST, "(" + getName() + ") New AORTA cycle [" + cycle + "]. Strategy: " + strategy.getClass().getName());
		state.newCycle();
		state = strategy.execute(state);
		
		lastCycleChangedState = state.hasChanged();
		
		if (!state.getOut().isEmpty()) {
			logger.log(Level.FINEST, "(" + getName() + ") Sending " + state.getOut().size() + " messages.");
			Iterator<OutgoingOrganizationalMessage> it = state.getOut().iterator();
			while (it.hasNext()) {
				OutgoingOrganizationalMessage msg = it.next();
				if (state.getBridge() != null) {
					state.getBridge().sendMessage(msg);
				}
				it.remove();
			}
		}

        lastTrace = Tracer.printTrace(name);
        
		if (lastCycleChangedState) {
			for (Inspector i : inspectors) {
				i.addAgentState(this);
			}
		}
		logger.log(Level.FINEST, "(" + getName() + ") Cycle done [" + cycle + "] (time: " + (System.currentTimeMillis() - start) + " ms)");
	}

    public String getLastTrace() {
        return lastTrace;
    }
	
	public boolean hasChanged() {
		return lastCycleChangedState || state.getExternalAgent().containsChanges();
	}

	@Override
	public String toString() {
		String actRules = "";
		for (ReasoningRule r : state.getRules()) {
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

	public MessageFunction getMessageFunction() {
		return new MessageFunction();
	}
	
}
