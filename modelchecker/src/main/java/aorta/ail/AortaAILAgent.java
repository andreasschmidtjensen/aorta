/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail;

import ail.mas.AILEnv;
import ail.mas.MAS;
import ail.semantics.AILAgent;
import ail.semantics.OSRule;
import ail.semantics.RCStage;
import ail.semantics.ReasoningCycle;
import ail.syntax.AILAnnotation;
import ail.syntax.Action;
import ail.syntax.ApplicablePlan;
import ail.syntax.BeliefBase;
import ail.syntax.Deed;
import ail.syntax.Event;
import ail.syntax.Goal;
import ail.syntax.Guard;
import ail.syntax.Intention;
import ail.syntax.Literal;
import ail.syntax.Message;
import ail.syntax.Plan;
import ail.syntax.PlanLibrary;
import ail.syntax.PredicatewAnnotation;
import ail.syntax.Rule;
import ail.syntax.RuleBase;
import ail.syntax.StringTerm;
import ail.syntax.Term;
import ail.syntax.Unifier;
import ail.syntax.annotation.SourceAnnotation;
import ail.util.AILexception;
import ajpf.psl.MCAPLFormula;
import ajpf.psl.MCAPLPredicate;
import ajpf.util.AJPFLogger;
import alice.tuprolog.InvalidLibraryException;
import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Struct;
import aorta.AORTAException;
import aorta.Aorta;
import aorta.AortaAgent;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.OrganizationImportException;
import aorta.kr.util.Qualifier;
import aorta.msg.IncomingOrganizationalMessage;
import aorta.parser.helper.AortaBuilder;
import aorta.ts.strategy.StrategyFailedException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// TODO: Avoid large statespace in beginning when setting up agents
// TODO: Keep the existing AILAgent as a field and forward *all* method calls to this instead (if possible?).
/**
 *
 * @author asj
 */
public class AortaAILAgent extends AILAgent {

	private static final String logname = AortaAILAgent.class.getName();
	private AortaAgent aortaAgent;

	public AortaAILAgent(AILAgent ailAgent, Aorta aorta, String aortaFile) {
		setEnv(ailAgent.getEnv());
		setMAS(ailAgent.getMAS());
		setAgName(ailAgent.getAgName());
		setBeliefBase(ailAgent.getBB());
		setRuleBase(ailAgent.getRuleBase());
		setIntention(ailAgent.getIntention()); // can have at most one intention (the initial goal)
		setPlanLibrary(ailAgent.getPL());
		setReasoningCycle(ailAgent.getReasoningCycle());
		
		AortaBuilder builder = new AortaBuilder();
		try {
			aortaAgent = builder.parseAgent(ailAgent.getAgName(), aortaFile, aorta, new AILBridge(this));
			aortaAgent.setAorta(aorta);

			for (Literal b : getBB().getAll()) {
				aortaAgent.getState().getExternalAgent().addBelief(TermConverter.fromLiteral(b));
			}
		} catch (InvalidTheoryException | IOException | InvalidLibraryException | OrganizationImportException | AORTAException ex) {
			AJPFLogger.severe(logname, "Could not parse AORTA program: " + ex.getMessage());
			ex.printStackTrace();
		}
		
		AJPFLogger.info(logname, getAgName() + ": Initial goal: " + getIntention());
		
		// initial goal:
		Intention initialGoal = getIntention();
		if (initialGoal != null && !initialGoal.empty()) {
			Deed deed = initialGoal.deeds().get(0);
			Goal goal = deed.getGoal();
			addGoalToAorta(goal);
		}
	}

	public AortaAgent getAortaAgent() {
		return aortaAgent;
	}

	@Override
	public void newMessages(Set<Message> msgs) {
		Set<Message> aplMessages = new HashSet<>(); // messages not intended for AORTA
		for (Message msg : msgs) {
			if ("om".equals(msg.getPropCont().getFunctor())) {
				IncomingOrganizationalMessage im = new IncomingOrganizationalMessage(msg.getSender(), TermConverter.fromLiteral((Literal) msg.getPropCont()).getTerm(0));
				aortaAgent.getState().getExternalAgent().receiveMessage(im);
				tellawake();
			} else {
				aplMessages.add(msg);
			}
		}

		super.newMessages(aplMessages);
	}

	@Override
	public boolean MCAPLhasIntention(MCAPLFormula fmla) {
		return super.MCAPLhasIntention(fmla);
	}

	@Override
	public void MCAPLtellawake() {
		super.MCAPLtellawake();
	}

	@Override
	public boolean MCAPLwantstosleep() {
		return super.MCAPLwantstosleep();
	}

	@Override
	public boolean MCAPLhasOrganizationalBelief(MCAPLFormula phi) {
		MentalState ms = aortaAgent.getState().getMentalState();
		boolean hasOrgBel = new QueryEngine().exists(ms, Qualifier.qualifyStruct(TermConverter.fromLiteral(new Literal(Literal.LPos, new PredicatewAnnotation((MCAPLPredicate) phi))), KBType.ORGANIZATION));
		return hasOrgBel;
	}

	@Override
	public boolean MCAPLhasOrganizationalOption(MCAPLFormula phi) {
		MentalState ms = aortaAgent.getState().getMentalState();
		Struct aortaTerm = Qualifier.qualifyStruct(TermConverter.fromLiteral(new Literal(Literal.LPos, new PredicatewAnnotation((MCAPLPredicate) phi))), KBType.OPTION);
		boolean hasOption = new QueryEngine().exists(ms, aortaTerm);
		return hasOption;
	}

	@Override
	public void MCAPLreason(int flag) {
		try {
			aortaAgent.newCycle();
			System.out.println("[ " + getAgName() + "] new cycle: " + aortaAgent.getCycle());
		} catch (StrategyFailedException ex) {
			AJPFLogger.warning(logname, "Could not finish cycle: " + ex.getMessage());
		}

		super.MCAPLreason(flag);
	}

	@Override
	public boolean wantstosleep() {
		return super.wantstosleep() && !aortaAgent.hasChanged();
	}

	@Override
	public void tellawake() {
		super.tellawake();
	}

	@Override
	public void addBel(Literal bel, AILAnnotation s) {
		super.addBel(bel, s);

		aortaAgent.getState().getExternalAgent().addBelief(TermConverter.fromLiteral(bel));
	}

	@Override
	public void addBel(Literal bel, AILAnnotation s, String n) {
		super.addBel(bel, s, n);

		aortaAgent.getState().getExternalAgent().addBelief(TermConverter.fromLiteral(bel));
	}

	@Override
	public void addBel(Literal bel, AILAnnotation s, StringTerm n) {
		super.addBel(bel, s, n);

		aortaAgent.getState().getExternalAgent().addBelief(TermConverter.fromLiteral(bel));
	}

	@Override
	public void delBel(Literal bel) {
		super.delBel(bel);

		aortaAgent.getState().getExternalAgent().removeBelief(TermConverter.fromLiteral(bel));
	}

	@Override
	public void delBel(StringTerm dbnum, Literal bel) {
		super.delBel(dbnum, bel);

		aortaAgent.getState().getExternalAgent().removeBelief(TermConverter.fromLiteral(bel));
	}

	@Override
	public void addGoal(Goal g) {
		super.addGoal(g);

		addGoalToAorta(g);
	}

	private void addGoalToAorta(Goal g) {
		System.out.println(g + " = " + g.getLiteral());
		QueryEngine qe = new QueryEngine();
		Struct aortaGoal = TermConverter.fromLiteral(g.getLiteral());
		Struct qualifiedGoal = Qualifier.qualifyStruct(aortaGoal, KBType.GOAL);
		System.out.println("aortaGoal=" + aortaGoal);
		System.out.println("qualified=" + qualifiedGoal);
		
		if (!qe.exists(aortaAgent.getState().getMentalState(), qualifiedGoal)) {
			aortaAgent.getState().getExternalAgent().addGoal(aortaGoal);
		}
	}

	@Override
	public void removeGoal(Goal g) {
		super.removeGoal(g);

		aortaAgent.getState().getExternalAgent().removeGoal(TermConverter.fromLiteral(g.getLiteral()));
	}

}
