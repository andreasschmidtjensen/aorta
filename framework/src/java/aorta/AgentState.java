/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Var;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.util.FormulaQualifier;
import aorta.msg.IncomingOrganizationalMessage;
import aorta.msg.OutgoingOrganizationalMessage;
import aorta.reasoning.ActionRule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import aorta.logging.Logger;

/**
 *
 * @author ascje
 */
public class AgentState {

	private static final Logger logger = Logger.getLogger(AgentState.class.getName());
	
	private AortaAgent agent;
	private MentalState mentalState;
	private List<ActionRule> actionRules;
	private Queue<OutgoingOrganizationalMessage> out;
	private ExternalAgent externalAgent;
	private AortaBridge bridge;
	private List<Var> bindings;
	
	public AgentState(AortaAgent agent, MentalState mentalState, List<ActionRule> actionRules) {
		this.agent = agent;
		this.mentalState = mentalState;
		this.actionRules = actionRules;

		out = new LinkedList<>();

		externalAgent = new ExternalAgent();

		bindings = new ArrayList<>();
	}

	public void newCycle() {
	}

	public final void prepareForTransition() {
		bindings.clear();
	}

	public void setMentalState(MentalState mentalState) {
		this.mentalState = mentalState;
	}

	public void setBridge(AortaBridge bridge) {
		this.bridge = bridge;
	}

	public AortaBridge getBridge() {
		return bridge;
	}

	public void insertTerm(QueryEngine engine, Struct term, KBType type) {
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
			final Struct qualified = FormulaQualifier.qualifyStruct(term, type);
			insertInMentalState(engine, qualified);

			logger.log(Level.FINE, "Updating mental state; inserting " + qualified);
		}
	}

	public void insertMessage(QueryEngine engine, IncomingOrganizationalMessage msg) {
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
			engine.insert(mentalState, contents);
		}
	}

	public void insertTerm(QueryEngine engine, Struct qualifiedTerm) {
		if (!FormulaQualifier.isQualified(qualifiedTerm)) {
			throw new IllegalArgumentException("Provided term was not qualified: " + qualifiedTerm);
		}

		try {
			KBType type = FormulaQualifier.getQualifier(qualifiedTerm);
			Struct term = (Struct) FormulaQualifier.getQualified(qualifiedTerm);

			insertTerm(engine, term, type);
		} catch (NullPointerException ex) {
			logger.log(Level.SEVERE, qualifiedTerm + " threw NPE for insertTerm(" + qualifiedTerm + ")", ex);
		}
	}

	public void removeTerm(QueryEngine engine, Struct term, KBType type) {
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
			final Struct qualified = FormulaQualifier.qualifyStruct(term, type);
			removeFromMentalState(engine, qualified);

			logger.log(Level.FINE, "Updating mental state; removing " +qualified);
		}
	}

	public void removeTerm(QueryEngine engine, Struct qualifiedTerm) {
		if (!FormulaQualifier.isQualified(qualifiedTerm)) {
			throw new IllegalArgumentException("Provided term was not qualified: " + qualifiedTerm);
		}

		KBType type = FormulaQualifier.getQualifier(qualifiedTerm);
		Struct term = (Struct) FormulaQualifier.getQualified(qualifiedTerm);

		removeTerm(engine, term, type);
	}

	public void insertInMentalState(QueryEngine engine, Struct contents) {
		engine.insert(mentalState, contents);
	}

	public void removeFromMentalState(QueryEngine engine, final Struct qualified) {
		engine.remove(mentalState, qualified);
	}
	
	public List<Var> getBindings() {
		return bindings;
	}

	public void setBindings(List<Var> bindings) {
		this.bindings = bindings;
	}

	public void clearBindings() {
		bindings.clear();
	}
	
	public void addBindings(List<Var> bindings) {
		this.bindings = mergeBindings(this.bindings, bindings);
	}
	
	public void addBindings(SolveInfo info) {
		if (info.isSuccess()) {
			try {
				bindings = mergeBindings(bindings, info.getBindingVars());
			} catch (NoSolutionException ex) {
				// ignore because of isSuccess
			}
		}
	}

	public MentalState getMentalState() {
		return mentalState;
	}

	public void sendMessage(OutgoingOrganizationalMessage msg) {
		out.add(msg);
	}
	
	Queue<OutgoingOrganizationalMessage> getOut() {
		return out;
	}

	public List<ActionRule> getActionRules() {
		return Collections.unmodifiableList(actionRules);
	}

	public synchronized ExternalAgent getExternalAgent() {
		return externalAgent;
	}

	public AortaAgent getAgent() {
		return agent;
	}

	public static List<Var> mergeBindings(List<Var> currentBindings, List<Var> newBindings) {
		List<Var> result = new ArrayList<>(currentBindings);
		for (Var var : newBindings) {
			boolean exists = false;
			for (Var currVar : result) {
				if (var.getOriginalName().equals(currVar.getOriginalName())) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				result.add(var);
			}
		}
		return result;
	}

}
