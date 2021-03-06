/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta;

import alice.tuprolog.Struct;
import alice.tuprolog.Var;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.language.model.Metamodel;
import aorta.kr.util.FormulaQualifier;
import aorta.msg.IncomingOrganizationalMessage;
import aorta.msg.OutgoingOrganizationalMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import aorta.logging.Logger;
import aorta.reasoning.ReasoningRule;
import gov.nasa.jpf.annotation.FilterField;

/**
 *
 * @author ascje
 */
public class AgentState extends State {

	private static final Logger logger = Logger.getLogger(AgentState.class.getName());
	
	@FilterField
	private AortaAgent agent;
	
	@FilterField
	private List<ReasoningRule> rules;
	
	@FilterField
	private Queue<OutgoingOrganizationalMessage> out;
	
	@FilterField
	private ExternalAgent externalAgent;
	
	@FilterField
	private AortaBridge bridge;
		
	public AgentState(AortaAgent agent, MentalState mentalState, Metamodel metamodel, List<ReasoningRule> rules) {
		super(mentalState, metamodel);
		this.agent = agent;
		this.rules = rules;

		out = new LinkedList<>();

		externalAgent = new ExternalAgent();
	}

	public void setBridge(AortaBridge bridge) {
		this.bridge = bridge;
		
		bridge.addBelief(new Struct("me", new Struct(agent.getName())));
	}

	public AortaBridge getBridge() {
		return bridge;
	}

	@Override
	public void insertTerm(Struct term, KBType type) {
		boolean insert = true;
		if (bridge != null) {
			switch (type) {
				case BELIEF:
					insert = bridge.addBelief(term);
					break;
				case GOAL:
					insert = bridge.addGoal(term);
					break;
			}
		}

		if (insert) {
			super.insertTerm(term, type);
		}
	}

	public void insertMessage(IncomingOrganizationalMessage msg) {
		Struct om = (Struct) msg.getMessage();
		Struct contents = (Struct) om.getArg(0);
		boolean insert = true;
		if (bridge != null) {
			KBType type = FormulaQualifier.getQualifier(contents);
			if (type == KBType.BELIEF) {
				try {
					insert = bridge.addReceivedBelief(msg.getSender(), FormulaQualifier.getQualified(contents));
				} catch (IllegalArgumentException ex) {
					ex.printStackTrace();
					System.out.println("msg for exception: " + msg + ", type=" + type + ", cont= " + contents + ", qcont=" + FormulaQualifier.getQualified(contents));
				}
			} else if (type == KBType.GOAL) {
				try {
					insert = bridge.addReceivedGoal(msg.getSender(), FormulaQualifier.getQualified(contents));
				} catch (IllegalArgumentException ex) {
					ex.printStackTrace();
					System.out.println("msg for exception: " + msg + ", type=" + type + ", cont= " + contents + ", qcont=" + FormulaQualifier.getQualified(contents));
				}
			}
		}

		if (insert) {
			getMentalState().insert(contents);
			logger.finer("insertMessage(" + msg + ")");
			setChanged(true);
		}
	}

	@Override
	public void removeTerm(Struct term, KBType type) {
		boolean remove = true;
		if (bridge != null) {
			switch (type) {
				case BELIEF:
					remove = bridge.removeBelief(term);
					break;
				case GOAL:
					remove = bridge.removeGoal(term);
					break;
			}
		}

		if (remove) {
			super.removeTerm(term, type);
		}
	}

	public void sendMessage(OutgoingOrganizationalMessage msg) {
		out.add(msg);	
		logger.finer("sendMessage(" + msg + ")");
		setChanged(true);
	}
	
	Queue<OutgoingOrganizationalMessage> getOut() {
		return out;
	}

	public List<ReasoningRule> getRules() {
		return Collections.unmodifiableList(rules);
	}

	public synchronized ExternalAgent getExternalAgent() {
		return externalAgent;
	}

	public AortaAgent getAgent() {
		return agent;
	}

	@Override
	public String getIdentifier() {
		return getAgent().getName();
	}

	@Override
	public String getDescription() {
		return getAgent().getName() + "/" + getAgent().getCycle();
	}

}
