/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason;

import aorta.AortaAgent;
import dm.ProgramLoader;
import dm.gen.ModelGenerator;
import dm.gen.RuleProgram;
import dm.semantics.DecisionModel;
import dm.semantics.QDTModel;
import dm.syntax.Term;
import jason.architecture.AgArch;
import jason.asSemantics.Agent;
import jason.asSemantics.Event;
import jason.asSemantics.Intention;
import jason.asSyntax.Literal;
import jason.asSyntax.Trigger;
import jason.runtime.Settings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import org.antlr.runtime.RecognitionException;

	
/**
 *
 * @author asj
 */
public class AortaJasonAgent extends Agent {

	private AortaAgent aortaAgent;
	
	private List<Literal> initialGoals;
	private AortaPlanLibrary aortaPL;

	public AortaJasonAgent() {
		super();
		initialGoals = new ArrayList<>();
		
		aortaPL = new AortaPlanLibrary();
		setPL(aortaPL);
	}

	@Override
	public void addInitialGoal(Literal g) {
		super.addInitialGoal(g);
		initialGoals.add(g);
	}
	
	public void setAortaAgent(AortaAgent aortaAgent) {
		this.aortaAgent = aortaAgent;
		
		AortaGoalListener agl = new AortaGoalListener(aortaAgent);
		for (Literal initialGoal : initialGoals) {
			agl.goalStarted(new Event(new Trigger(Trigger.TEOperator.add, Trigger.TEType.achieve, initialGoal), Intention.EmptyInt));
		}
		
		aortaPL.addPlanListener(new AortaPlanListener(aortaAgent));

		// listen for goal completion to bridge to AORTA
		ts.addGoalListener(agl);
	}
	
	public AortaAgent getAortaAgent() {
		return aortaAgent;
	}
	
	@Override
	public Agent clone(AgArch arch) {
		AortaJasonAgent clone = (AortaJasonAgent) super.clone(arch);
		clone.aortaAgent = aortaAgent;
		return clone;
	}

	// Decision-making using QDT 
	private RuleProgram rp;
	private Set<Term> controllable;
	
	@Override
	public void initAg() {
		super.initAg();
		Settings settings = getTS().getSettings();
		String dmFile = settings.getUserParameter("dm");
		String controllables = settings.getUserParameter("c");

		if (dmFile != null && controllables != null) {
			try {
				rp = ProgramLoader.load(dmFile);
				controllable = ProgramLoader.parseTerms(controllables);
			} catch (IOException | RecognitionException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	@Override
	public Event selectEvent(Queue<Event> events) {
		if (rp != null) {
			ModelGenerator gen = new ModelGenerator(rp);

			for (Iterator<Event> it = events.iterator(); it.hasNext();) {
				Event e = it.next();
				if (!e.getTrigger().isAchvGoal()) {
					continue;
				}
				jason.asSyntax.Literal literal = (jason.asSyntax.Literal) e.getTrigger().getLiteral().clone();
				literal.clearAnnots();

				dm.syntax.Literal pos = new dm.syntax.Literal(literal.toString());
				dm.syntax.Literal neg = new dm.syntax.Literal(literal.toString(), false);
				Set<dm.syntax.Literal> influences = new HashSet<>();
				influences.add(pos);
				influences.add(neg);
				
				try {
					QDTModel qdt = gen.generate(influences);
					Set<dm.syntax.Literal> beliefs = new HashSet<>();
					for (jason.asSyntax.Literal bel : getBB()) {
						bel = (jason.asSyntax.Literal) bel.clone();
						bel.clearAnnots();

						if (bel.negated()) {
							beliefs.add(new dm.syntax.Literal(bel.toString().substring(1), false));
						} else {
							beliefs.add(new dm.syntax.Literal(bel.toString()));
						}
					}

					DecisionModel dm = new DecisionModel(qdt, influences, controllable, beliefs);
					if (!dm.isEmpty()) {
						Set<dm.syntax.Literal> decision = dm.getDecision();
						if (decision.contains(pos)) {
							ts.getLogger().info("Deciding " + pos);
							it.remove();
							return e;
						} else if (decision.contains(neg)) {
							ts.getLogger().info("Dropping " + pos);
							it.remove();
						}
					}
				} catch (Exception ex) {
					ts.getLogger().log(Level.WARNING, "Could not apply some rules: ", ex.getMessage());
				}
			}
			// TODO: return null?
		}
		
		return super.selectEvent(events);
	}

}
