/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics;

import ail.semantics.AILAgent;
import ail.syntax.BeliefBase;
import ail.syntax.Deed;
import ail.syntax.Event;
import ail.syntax.GBelief;
import ail.syntax.Goal;
import ail.syntax.Guard;
import ail.syntax.Intention;
import ail.syntax.Literal;
import ail.syntax.Message;
import ail.syntax.Plan;
import ail.syntax.Predicate;
import ail.syntax.PredicatewAnnotation;
import ail.syntax.StringTermImpl;
import ail.syntax.Unifier;
import ail.syntax.annotation.BeliefBaseAnnotation;
import ail.syntax.annotation.SourceAnnotation;
import ajpf.psl.MCAPLFormula;
import ajpf.psl.MCAPLPredicate;
import aorta.syntax.metamodel.Metamodel;
import aorta.syntax.metamodel.Norm;
import aorta.syntax.metamodel.Rea;
import aorta.syntax.metamodel.Violation;
import gov.nasa.jpf.annotation.FilterField;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class AortaAgent extends AILAgent {

	private static final String BB_ORG = "org";
	private static final String BB_OPT = "opt";

	@FilterField
	protected List<AortaRule> aortaRules = new ArrayList<>();
	
	@FilterField
	private Metamodel metamodel;

	@FilterField
	private final Set<String> reas = new HashSet<>();
	private final Set<Literal> capabilities = new HashSet<>();

	@FilterField
	private boolean allowedToSleep = true;
	
	@FilterField
	private boolean lastRCStageChangedAgent = true;

	public AortaAgent(AILAgent original) {
		super();

		setEnv(original.getEnv());
		setMAS(original.getMAS());
		setAgName(original.getAgName());
		setBeliefBase(original.getBB());
		setRuleBase(original.getRuleBase());
		setIntention(original.getIntention());
		setPlanLibrary(original.getPL());
		setReasoningCycle(new AortaRC(original.getReasoningCycle()));
		setTrackPlanUsage(original.getTrackPlanUsage());

		addBeliefBase(new BeliefBase(), BB_ORG);
		addBeliefBase(new BeliefBase(), BB_OPT);

		// capabilities
		for (Plan plan : getPL().getPlans()) {
			Event te = plan.getTriggerEvent();
			if (te.isAddition() && te.referstoGoal()) {
				Goal goal = te.getGoal();
				capabilities.add(goal.getLiteral());
			}
		}
	}

	public void aortaChanged() {
		lastRCStageChangedAgent = true;
		setAllowedToSleep(false);
		tellawake();
	}

	public boolean lastRCStageChangedAgent() {
		boolean b = lastRCStageChangedAgent;
		lastRCStageChangedAgent = false;
		return b;
	}	

	public void setAllowedToSleep(boolean allowedToSleep) {
		this.allowedToSleep = allowedToSleep;
	}

	public List<AortaRule> getAortaRules() {
		return aortaRules;
	}

	public boolean doEnact(String role) {
		return reas.add(role);
	}

	public boolean enacts(String role) {
		return reas.contains(role);
	}

	public boolean doDeact(String role) {
		return reas.remove(role);
	}

	public Set<String> getReas() {
		return reas;
	}

	public boolean isCapableOf(Literal goal) {
		for (Literal cap : capabilities) {
			if (cap.clone().matchNG(goal.clone(), new Unifier())) {
				return true;
			}
		}
		return false;
	}

	public void addAortaRule(AortaRule rule) {
		aortaRules.add(rule);
	}

	public void setMetamodel(Metamodel metamodel) {
		this.metamodel = metamodel;
		for (Literal l : metamodel.getAsLiterals()) {
			addOrg(l);
		}
	}

	public Metamodel getMetamodel() {
		return metamodel;
	}

	public BeliefBase getOrgBB() {
		return getBB(BB_ORG);
	}

	public BeliefBase getOptBB() {
		return getBB(BB_OPT);
	}

	private boolean bbContains(BeliefBase bb, Literal lit) {
		if (lit.negated()) {
			// check if it exists => true if not
			Literal unNegated = lit.clone();
			unNegated.setNegated(false);
			
			Iterator<Literal> it = bb.getRelevant(unNegated);
			while (it.hasNext()) {
				Literal relOpt = it.next();
				if (relOpt.clone().match(unNegated.clone(), new Unifier())) {
					return false;
				}
			}
			return true;			
		} else {
			// check if exists => false if not
			Iterator<Literal> it = bb.getRelevant(lit);
			while (it.hasNext()) {
				Literal relOpt = it.next();
				if (relOpt.clone().match(lit.clone(), new Unifier())) {
					return true;
				}
			}
			return false;
		}
	}

	public boolean optContains(Literal lit) {
		return bbContains(getOptBB(), lit);
	}

	public boolean orgContains(Literal lit) {
		return bbContains(getOrgBB(), lit);
	}

	public boolean belContains(Literal lit) {
		return bbContains(getBB(), lit);
	}

	public boolean goalContains(Literal lit) {
		Iterator<Goal> it = getGoalBase().getRelevant(lit);
		while (it.hasNext()) {
			Goal relOpt = it.next();
			if (relOpt.getLiteral().clone().match(lit.clone(), new Unifier())) {
				return true;
			}
		}
		return false;
	}

	public void addOrg(Literal bel) {
		addBel(bel, new BeliefBaseAnnotation(new Predicate(BB_ORG)), BB_ORG);
		// update mm
		switch (bel.getFunctor()) {
			case "rea":
				Rea rea = new Rea(bel.getTerm(0).getFunctor(), bel.getTerm(1).
						getFunctor());
				metamodel.addRea(rea);
				break;
			case "norm":
				Norm norm = new Norm(
							bel.getTerm(0).getFunctor(),
							bel.getTerm(1).getFunctor(), 
							bel.getTerm(2).getFunctor(),
							(Literal) bel.getTerm(3), 
							(Literal) bel.getTerm(4));
				metamodel.addNorm(norm);
				break;
			case "viol":
				Violation viol = new Violation(
							bel.getTerm(0).getFunctor(),
							bel.getTerm(1).getFunctor(), 
							bel.getTerm(2).getFunctor(),
							(Literal) bel.getTerm(3));
				metamodel.addViolation(viol);
				break;
		}
	}

	public void delOrg(Literal bel) {
		delBel(new StringTermImpl(BB_ORG), bel);
		// update mm
	}

	public void addOpt(Literal bel) {
		addBel(bel, new BeliefBaseAnnotation(new Predicate(BB_OPT)), BB_OPT);
	}

	public void delOpt(Literal bel) {
		delBel(new StringTermImpl(BB_OPT), bel);
	}

	@Override
	public void sleep() {
		if (allowedToSleep) {
			super.sleep();
		}
	}

	public void receiveMessage(Message msg) {
		// handle aorta message
		Predicate pred = (Predicate) msg.getPropCont();
		Literal aMsg = new Literal(true, new PredicatewAnnotation((Predicate) pred.getTerm(0)));
		switch (pred.getFunctor()) {
			case "bel":
				addBel(aMsg, new SourceAnnotation(new Predicate(msg.
						getSender())));

				ArrayList<Deed> ds = new ArrayList<>();
				ArrayList<Guard> gs  = new ArrayList<>();
				Unifier u = new Unifier();

				ds.add(new Deed(Deed.AILAddition, Deed.AILBel, aMsg));
				gs.add(new Guard(new GBelief(GBelief.GTrue)));
				Intention i = new Intention(new Event(Event.Estart), ds, gs, u, AILAgent.refertopercept());
				addNewIntention(i);

				break;
			case "goal":
				addNewIntention(new Intention(new Goal(aMsg, Goal.achieveGoal), new SourceAnnotation(new Predicate(msg.getSender()))));
				break;
			case "org":
				addOrg(aMsg);
				break;
			case "opt":
				addOpt(aMsg);
				break;
			default:
				throw new IllegalArgumentException("Wrong message: "
						+ aMsg);
		}

		tellawake();
	}

	@Override
	public String toString() {
		return super.toString() + "\n" + getInbox() + "\n" + getBB(BB_ORG) + "\n" + getBB(BB_OPT);
	}

	@Override
	public void printagentstate() {
		if (!(getReasoningCycle().getStage() instanceof AortaRCStage)) {
			super.printagentstate();
		}
	}
	
	@Override
	public boolean MCAPLhasOrganizationalBelief(MCAPLFormula phi) {
		Literal lit = new Literal(Literal.LPos, new PredicatewAnnotation((MCAPLPredicate) phi));
		return orgContains(lit);
	}

	@Override
	public boolean MCAPLhasOrganizationalOption(MCAPLFormula phi) {
		Literal lit = new Literal(Literal.LPos, new PredicatewAnnotation((MCAPLPredicate) phi));
		return optContains(lit);
	}

	@Override
	public boolean MCAPLhasCapability(MCAPLFormula phi) {
		Literal cap = new Literal(Literal.LPos, new PredicatewAnnotation((MCAPLPredicate) phi));
		for (Literal l : capabilities) {
			if (cap.clone().match(l, new Unifier())) {
				return true;
			}
		}
		return false;
	}
	
}
