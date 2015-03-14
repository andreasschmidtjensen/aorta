/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail;

import ail.semantics.AILAgent;
import ail.semantics.RCStage;
import ail.syntax.AILAnnotation;
import ail.syntax.Deed;
import ail.syntax.Event;
import ail.syntax.Goal;
import ail.syntax.Intention;
import ail.syntax.Literal;
import ail.syntax.Message;
import ail.syntax.Plan;
import ail.syntax.PredicatewAnnotation;
import ail.syntax.StringTerm;
import ail.syntax.annotation.SourceAnnotation;
import ajpf.psl.MCAPLFormula;
import ajpf.psl.MCAPLPredicate;
import ajpf.util.AJPFLogger;
import alice.tuprolog.InvalidLibraryException;
import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.AORTAException;
import aorta.AgentState;
import aorta.Aorta;
import aorta.AortaAgent;
import aorta.ExternalAgent;
import aorta.ail.abs.Abstract_AortaAgent;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.language.OrganizationImportException;
import aorta.kr.util.FormulaQualifier;
import aorta.msg.IncomingOrganizationalMessage;
import aorta.parser.helper.AortaBuilder;
import aorta.ts.strategy.StrategyFailedException;
import gov.nasa.jpf.annotation.FilterField;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author asj
 */
public class AortaAILAgent extends AILAgent {

	private static final String logname = AortaAILAgent.class.getName();
	
	private AortaAgent aortaAgent;
	
	@FilterField
	private Abstract_AortaAgent absAortaAgent;
	
	@FilterField
	private final RCStage initialRCStage;

	public AortaAILAgent(AILAgent ailAgent, Aorta aorta, String aortaFile) {
		setEnv(ailAgent.getEnv());
		setMAS(ailAgent.getMAS());
		setAgName(ailAgent.getAgName());
		setBeliefBase(ailAgent.getBB());
		setRuleBase(ailAgent.getRuleBase());
		setIntention(ailAgent.getIntention()); // can have at most one intention (the initial goal)
		setPlanLibrary(ailAgent.getPL());
		setReasoningCycle(ailAgent.getReasoningCycle());
		setTrackPlanUsage(ailAgent.getTrackPlanUsage());
		
		initialRCStage = getReasoningCycle().getStage();
		
		buildAortaAgent(ailAgent.getAgName(), aortaFile, aorta.getOrganizationLocation());
		try {
			aortaAgent = absAortaAgent.toAORTA();
			aortaAgent.getState().setBridge(new AILBridge(this));
		} catch (InvalidTheoryException ex) {
			throw new RuntimeException(ex);
		}	
		
		aortaAgent.setAorta(aorta);
		for (Literal b : getBB().getAll()) {
			aortaAgent.getState().getExternalAgent().addBelief(TermConverter.fromLiteral(b));
		}
		
		Literal me = TermConverter.toLiteral(new Struct("me", new Struct(fAgName)));
		addBel(me, new SourceAnnotation(new Literal("aorta")));
		
		// initial goal:
		Intention initialGoal = getIntention();
		if (initialGoal != null && !initialGoal.empty()) {
			Deed deed = initialGoal.deeds().get(0);
			Goal goal = deed.getGoal();
			addGoalToAorta(goal);
		}
		
		// capabilities
		for (Plan plan : getPL().getPlans()) {
			Event te = plan.getTriggerEvent();
			if (te.isAddition() && te.referstoGoal()) {
				Goal goal = te.getGoal();
				Term capTerm = TermConverter.convertToTerm(goal);
				if (capTerm instanceof Struct) {
					aortaAgent.getState().getExternalAgent().addCapability((Struct) capTerm);
				}
			}
		}
	}
	
	public final void buildAortaAgent(String agName, String aortaFile, String metamodelLocation) {
		AortaBuilder builder = new AortaBuilder();
		try {
			AortaAgent ag = builder.parseAgent(agName, aortaFile, metamodelLocation, new AILBridge(this));
			absAortaAgent = new Abstract_AortaAgent(ag);
		} catch (InvalidTheoryException | IOException | InvalidLibraryException | OrganizationImportException | AORTAException ex) {
			AJPFLogger.severe(logname, "Could not parse AORTA program: " + ex.getMessage());
			throw new RuntimeException(ex);
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
	public boolean MCAPLhasOrganizationalBelief(MCAPLFormula phi) {
		MentalState ms = aortaAgent.getState().getMentalState();
		Struct aortaTerm = FormulaQualifier.qualifyStruct(TermConverter.fromLiteral(new Literal(Literal.LPos, new PredicatewAnnotation((MCAPLPredicate) phi))), KBType.ORGANIZATION);
		boolean hasOrgBel = ms.exists(aortaTerm);
		return hasOrgBel;
	}

	@Override
	public boolean MCAPLhasOrganizationalOption(MCAPLFormula phi) {
		MentalState ms = aortaAgent.getState().getMentalState();
		Struct aortaTerm = FormulaQualifier.qualifyStruct(TermConverter.fromLiteral(new Literal(Literal.LPos, new PredicatewAnnotation((MCAPLPredicate) phi))), KBType.OPTION);
		boolean hasOption = ms.exists(aortaTerm);
		return hasOption;
	}

	private String lastPrint = "";
	@Override
	public void MCAPLreason(int flag) {
		if (initialRCStage == null || getReasoningCycle().getStage() == initialRCStage) {
			try {
				aortaAgent.newCycle();
			} catch (StrategyFailedException ex) {
				AJPFLogger.warning(logname, "Could not finish cycle: " + ex.getMessage());
			}
			
		}
		
		super.MCAPLreason(flag);
//		
//		if (getGoalBase().size() > 0) {
//			String print = fAgName + ": " + getGoalBase();
//			if (!print.equals(lastPrint)) {
//				System.out.println(print);
//				lastPrint = print;
//			}
//		}
	}

	@Override
	public boolean wantstosleep() {
		return super.wantstosleep() && !aortaAgent.hasChanged();
	}

	@Override
	public final void addBel(Literal bel, AILAnnotation s) {
		super.addBel(bel, s);
		
		if (aortaAgent != null) {
			AgentState state = aortaAgent.getState();
			ExternalAgent externalAgent = state.getExternalAgent();
			Struct fromLiteral = TermConverter.fromLiteral(bel);
			externalAgent.addBelief(fromLiteral);
		}
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
		Struct aortaGoal = TermConverter.fromLiteral(g.getLiteral());
		Struct qualifiedGoal = FormulaQualifier.qualifyStruct(aortaGoal, KBType.GOAL);
		
		AgentState state = aortaAgent.getState();		
		MentalState ms = state.getMentalState();
		
		if (!ms.exists(qualifiedGoal)) {
			state.getExternalAgent().addGoal(aortaGoal);
		}
	}

	@Override
	public void removeGoal(Goal g) {
		super.removeGoal(g);

		aortaAgent.getState().getExternalAgent().removeGoal(TermConverter.fromLiteral(g.getLiteral()));
	}

}
