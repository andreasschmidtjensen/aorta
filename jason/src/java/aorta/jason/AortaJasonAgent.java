/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason;

import aorta.AortaAgent;
import jason.architecture.AgArch;
import jason.asSemantics.Agent;
import jason.asSemantics.Event;
import jason.asSemantics.Intention;
import jason.asSyntax.Literal;
import jason.asSyntax.Plan;
import jason.asSyntax.Trigger;
import java.util.ArrayList;
import java.util.List;

	
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
	public void initAg() {
		super.initAg(); 
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

}
