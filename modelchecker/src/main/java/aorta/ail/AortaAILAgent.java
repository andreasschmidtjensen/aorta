/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail;

import ail.semantics.AILAgent;
import ail.syntax.AILAnnotation;
import ail.syntax.Goal;
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
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.OrganizationImportException;
import aorta.kr.util.Qualifier;
import aorta.msg.IncomingOrganizationalMessage;
import aorta.parser.helper.AortaBuilder;
import aorta.ts.strategy.StrategyFailedException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
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
		Iterator<Goal> goalIt = ailAgent.getGoals();
		while (goalIt.hasNext()) {
			addGoal(goalIt.next());
		}
		setPlanLibrary(ailAgent.getPL());
		setReasoningCycle(ailAgent.getReasoningCycle());
		
		AortaBuilder builder = new AortaBuilder();
		try {
			aortaAgent = builder.parseAgent(getAgName(), aortaFile, aorta, new AILBridge(this));
			aortaAgent.setAorta(aorta);
			
			for (Literal b : getBB().getAll()) {
				aortaAgent.getState().getExternalAgent().addBelief(TermConverter.fromLiteral(b));
			}
		} catch (InvalidTheoryException | IOException | InvalidLibraryException | OrganizationImportException | AORTAException ex) {
			AJPFLogger.warning(logname, "Could not parse AORTA program: " + ex.getMessage());
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
				AJPFLogger.fine(logname, "[" +getAgName() + "] new message: " + msg);
				IncomingOrganizationalMessage im = new IncomingOrganizationalMessage(msg.getSender(), TermConverter.fromLiteral((Literal) msg.getPropCont()).getTerm(0));
				aortaAgent.getState().getExternalAgent().receiveMessage(im);
			} else {
				aplMessages.add(msg);
			}
		}
		
		super.newMessages(aplMessages);
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
		} catch (StrategyFailedException ex) {
			AJPFLogger.warning(logname, "Could not finish cycle: " + ex.getMessage());
		}
		
//		
//		if (aortaAgent.hasChanged()) {
//			System.out.println("waking up");
//			getMAS().getController().getAgent(getAgName()).wakeUP();
//		}
		
		super.MCAPLreason(flag);
		
//		if (aortaAgent.hasChanged()) {
////			System.out.println("[" + getAgName() + "/" + aortaAgent.getCycle() + "] hasChanged()");
//			getMAS().getController().getScheduler().isActive(fAgName);
//		}
//		System.out.println(""+aortaAgent.getState().getMentalState());
//		System.out.println("[" + getAgName() + "] reasoning");
//		System.out.println(aortaAgent.hasChanged() + "; " + wantstosleep() + "; " + getIntention());
//		if (!aortaAgent.hasChanged() && wantstosleep() && getIntention() == null) {
//			System.out.println("[" + getAgName() + "]: should stop now");
//		} 
		
//		System.out.println("[" + getAgName() + "]: " + aortaAgent.getState().getMentalState());
//		if (getAgName().equals("ag2"))
//			System.out.println("[" + getAgName() + "]: " + super.wantstosleep() + " ; " + aortaAgent.hasChanged());
	}

	private String printGoals() {
		StringBuilder sb = new StringBuilder();
		Iterator<Goal> it = getGoals();
		while (it.hasNext()) {
			sb.append(it.next()).append(";");
		}
		return sb.toString();
	}

//	@Override
//	public void sleep() {
//		if (!aortaAgent.hasChanged()) { // only allow sleep if AORTA did nothing
//			super.sleep();
//		}
//	}
	
	@Override
	public boolean wantstosleep() {
		return super.wantstosleep() && !aortaAgent.hasChanged();
	}

	@Override
	public void tellawake() {
		super.tellawake();
//		if (!wantstosleep()) {
//			getMAS().getController().addAwake(getAgName());
//			getMAS().getController().getScheduler().isActive(getAgName());
//		}
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
	public final void addGoal(Goal g) {
		super.addGoal(g);

		QueryEngine qe = new QueryEngine();
		if (!qe.exists(aortaAgent.getState().getMentalState(), Qualifier.qualifyStruct(TermConverter.fromLiteral(g.getLiteral()), KBType.GOAL))) {
			aortaAgent.getState().getExternalAgent().addGoal(TermConverter.fromLiteral(g.getLiteral()));
		}
	}

	@Override
	public void removeGoal(Goal g) {
		super.removeGoal(g);
		
		aortaAgent.getState().getExternalAgent().removeGoal(TermConverter.fromLiteral(g.getLiteral()));
	}
		
}
