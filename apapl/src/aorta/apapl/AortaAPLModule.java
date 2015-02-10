/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.apapl;

import aorta.AortaAgent;
import aorta.ts.strategy.StrategyFailedException;
import apapl.APLModule;
import apapl.deliberation.Deliberation;
import apapl.program.BeliefUpdates;
import apapl.program.PCrulebase;
import apapl.program.PRrulebase;
import apapl.program.Planbase;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asj
 */
public class AortaAPLModule extends APLModule {

	private static final Logger logger = Logger.getLogger(AortaAPLModule.class.getName());
	
	private AortaAgent aortaAgent;

	public AortaAPLModule() {
		super(new ArrayList<>(),	// Internal Events
				new LinkedList<>(),	// External events
				new HashMap<>(),	// Environments
				new Deliberation(),
				null,				// Messenger
				"",				// Name
				null,				// MAS
				null,				// Parent
				null,				// Stopping Condition
				new AortaBeliefbase(), 
				new AortaGoalbase(), 
				new BeliefUpdates(), 
				new AortaPGrulebase(), 
				new PRrulebase(), 
				new PCrulebase(), 
				new Planbase(), 
				false);
	}

	public void setAortaAgent(AortaAgent aortaAgent) {
		this.aortaAgent = aortaAgent;
		
		getBeliefbase().setAortaAgent(aortaAgent);
		getGoalbase().setAortaAgent(aortaAgent);
		((AortaPGrulebase)getPGrulebase()).setAortaAgent(aortaAgent);
	}

	@Override
	public void step() {
		if (aortaAgent != null) {
			try {
				aortaAgent.newCycle();
				
				if (aortaAgent.hasChanged()) {
					try {
						APLModule parent = (APLModule) this;
						Field delibField = APLModule.class.getDeclaredField("delib");
						delibField.setAccessible(true);
						Deliberation delib = (Deliberation) delibField.get(parent);

						Field changedField = Deliberation.class.getDeclaredField("changed");
						changedField.setAccessible(true);
						changedField.setBoolean(delib, true);
					} catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
						ex.printStackTrace();
					}
				}
			} catch (StrategyFailedException ex) {
				logger.log(Level.SEVERE, null, ex);
			}
		}

		super.step();
	}

	@Override
	public AortaBeliefbase getBeliefbase() {
		return (AortaBeliefbase) super.getBeliefbase();
	}

	@Override
	public AortaGoalbase getGoalbase() {
		return (AortaGoalbase) super.getGoalbase();
	}

}
