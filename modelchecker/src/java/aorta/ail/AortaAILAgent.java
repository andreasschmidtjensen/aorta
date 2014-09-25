/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail;

import ail.semantics.AILAgent;
import ail.syntax.AILAnnotation;
import ail.syntax.Deed;
import ail.syntax.Goal;
import ail.syntax.Intention;
import ail.syntax.Literal;
import ail.syntax.Message;
import ail.syntax.PredicatewAnnotation;
import ail.syntax.StringTerm;
import ajpf.psl.MCAPLFormula;
import ajpf.psl.MCAPLPredicate;
import ajpf.util.AJPFLogger;
import alice.tuprolog.InvalidLibraryException;
import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Struct;
import aorta.AORTAException;
import aorta.Aorta;
import aorta.AortaAgent;
import aorta.ail.abs.Abstract_AortaAgent;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.OrganizationImportException;
import aorta.kr.util.FormulaQualifier;
import aorta.msg.IncomingOrganizationalMessage;
import aorta.parser.helper.AortaBuilder;
import aorta.ts.strategy.StrategyFailedException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

// TODO: Avoid large statespace in beginning when setting up agents
/**
 *
 * @author asj
 */
public class AortaAILAgent extends AILAgent {

	private static final String logname = AortaAILAgent.class.getName();
	private AortaAgent aortaAgent;
	private Abstract_AortaAgent absAortaAgent;

	public AortaAILAgent(AILAgent ailAgent, Aorta aorta, String aortaFile) {
		setEnv(ailAgent.getEnv());
		setMAS(ailAgent.getMAS());
		setAgName(ailAgent.getAgName());
		setBeliefBase(ailAgent.getBB());
		setRuleBase(ailAgent.getRuleBase());
		setIntention(ailAgent.getIntention()); // can have at most one intention (the initial goal)
		setPlanLibrary(ailAgent.getPL());
		setReasoningCycle(ailAgent.getReasoningCycle());
		
		buildAortaAgent(ailAgent.getAgName(), aortaFile, aorta.getOrganizationLocation());
		try {
			aortaAgent = absAortaAgent.toAORTA();
			aortaAgent.getState().setBridge(new AILBridge(this));
		} catch (InvalidTheoryException ex) {
			AJPFLogger.severe(logname, "Could not parse AORTA program: " + ex.getMessage());
			ex.printStackTrace();
		}	
		
		aortaAgent.setAorta(aorta);
		for (Literal b : getBB().getAll()) {
			aortaAgent.getState().getExternalAgent().addBelief(TermConverter.fromLiteral(b));
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
	
	public void buildAortaAgent(String agName, String aortaFile, String metamodelLocation) {
		AortaBuilder builder = new AortaBuilder();
		try {
			AortaAgent ag = builder.parseAgent(agName, aortaFile, metamodelLocation, new AILBridge(this));
			absAortaAgent = new Abstract_AortaAgent(ag);
		} catch (InvalidTheoryException | IOException | InvalidLibraryException | OrganizationImportException | AORTAException ex) {
			AJPFLogger.severe(logname, "Could not parse AORTA program: " + ex.getMessage());
			ex.printStackTrace();
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
		Struct aortaTerm = FormulaQualifier.qualifyStruct(TermConverter.fromLiteral(new Literal(Literal.LPos, new PredicatewAnnotation((MCAPLPredicate) phi))), KBType.ORGANIZATION);
		boolean hasOrgBel = new QueryEngine().exists(ms, aortaTerm);
		return hasOrgBel;
	}

	@Override
	public boolean MCAPLhasOrganizationalOption(MCAPLFormula phi) {
		MentalState ms = aortaAgent.getState().getMentalState();
		Struct aortaTerm = FormulaQualifier.qualifyStruct(TermConverter.fromLiteral(new Literal(Literal.LPos, new PredicatewAnnotation((MCAPLPredicate) phi))), KBType.OPTION);
		boolean hasOption = new QueryEngine().exists(ms, aortaTerm);
		return hasOption;
	}

	@Override
	public void MCAPLreason(int flag) {
		try {
			aortaAgent.newCycle();
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
		QueryEngine qe = new QueryEngine();
		Struct aortaGoal = TermConverter.fromLiteral(g.getLiteral());
		Struct qualifiedGoal = FormulaQualifier.qualifyStruct(aortaGoal, KBType.GOAL);
		
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
