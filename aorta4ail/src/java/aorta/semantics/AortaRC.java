/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics;

import ail.semantics.AILAgent;
import ail.semantics.RCStage;
import ail.semantics.ReasoningCycle;
import aorta.semantics.operationalrules.ae.ActExec;
import aorta.semantics.operationalrules.nc.*;
import aorta.semantics.operationalrules.og.*;
import gov.nasa.jpf.annotation.FilterField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asj
 */
public class AortaRC implements ReasoningCycle {
	
	private RCStage currentstage;
	
	private final List<AortaRCStage> nc = new ArrayList<>();
	private final List<AortaRCStage> og = new ArrayList<>();
			
	private final AortaRCStage nc1 = new AortaRCStage(1, "NC1");
	private final AortaRCStage nc2 = new AortaRCStage(2, "NC2");
	private final AortaRCStage nc3 = new AortaRCStage(3, "NC3");
	private final AortaRCStage nc4 = new AortaRCStage(4, "NC4");
	private final AortaRCStage nc5 = new AortaRCStage(5, "NC5");
	private final AortaRCStage nc6 = new AortaRCStage(6, "NC6");
	
	private final AortaRCStage og1 = new AortaRCStage(10, "OG1");
	private final AortaRCStage og2 = new AortaRCStage(11, "OG2");
	private final AortaRCStage og3 = new AortaRCStage(12, "OG3");
	private final AortaRCStage og4 = new AortaRCStage(13, "OG4");
	private final AortaRCStage og5 = new AortaRCStage(14, "OG5");
	private final AortaRCStage og6 = new AortaRCStage(15, "OG6");
	private final AortaRCStage og7 = new AortaRCStage(16, "OG7");
	
	private final AortaRCStage ae = new AortaRCStage(20, "AE");
	
	/**
	 * Used to detect when to delegate cycle(AILAgent) to agentCycle.
	 */
	private final RCStage initialRCStage;

	private final ReasoningCycle agentCycle;
	private boolean aortaDone;

	public AortaRC(ReasoningCycle agentCycle) {
		this.agentCycle = agentCycle;
		initialRCStage = agentCycle.getStage();
		
		nc.add(nc1);
		nc.add(nc2);
		nc.add(nc3);
		nc.add(nc4);
		nc.add(nc5);
		nc.add(nc6);
		
		nc1.setRule(new OblAct());
		nc2.setRule(new OblSat());
		nc3.setRule(new OblViol());
		nc4.setRule(new ProAct());
		nc5.setRule(new ProExp());
		nc6.setRule(new ProViol());
		
		og.add(og1);
		og.add(og2);
		og.add(og3);
		og.add(og4);
		og.add(og5);
		og.add(og6);
		og.add(og7);
		
		og1.setRule(new Enact());
		og2.setRule(new Deact());
		og3.setRule(new ObjectiveOpt());
		og4.setRule(new NormOpt());
		og5.setRule(new ViolationOpt());
		og6.setRule(new Delegate());
		og7.setRule(new Inform());
		
		ae.setRule(new ActExec());
		
		currentstage = nc1;
	}
		
	/**
	 * Flag indicating whether this is a point where the properties of the 
	 * multi-agent system should be checked.
	 */
	@FilterField
	private boolean stopandcheck = false;
	private boolean interrupted = false;
	
	@Override
	public void setStopandCheck(boolean stopandcheck) {
		this.stopandcheck = stopandcheck;
	}

	@Override
	public boolean stopandcheck() {
		return stopandcheck;
	}

	@Override
	public void cycle(AILAgent a) {
		AortaAgent ag = (AortaAgent)a;
		if (currentstage == initialRCStage && !aortaDone) {
			ag.setAllowedToSleep(true);
			currentstage = nc1;
		} else if (currentstage instanceof AortaRCStage && nc.contains((AortaRCStage) currentstage)) {
			int index = nc.indexOf(currentstage);
			if (index < nc.size() - 1) {
				currentstage = nc.get(index + 1);
			} else {
				currentstage = og1;
			}
		} else if (currentstage instanceof AortaRCStage && og.contains((AortaRCStage) currentstage)) {			
			int index = og.indexOf(currentstage);
			if (index < og.size() - 1) {
				currentstage = og.get(index + 1);
			} else {
				stopandcheck = true;
				currentstage = ae;
			}
		} else if (currentstage == ae) {			
			stopandcheck = true;
			currentstage = agentCycle.getStage();
			aortaDone = true;
		} else {
			agentCycle.cycle(ag);
			
			currentstage = agentCycle.getStage();			
			setStopandCheck(agentCycle.stopandcheck());
			interrupted = !agentCycle.not_interrupted();
			
			aortaDone = false;
		}
	}

	@Override
	public RCStage getStage() {
		return currentstage;
	}
	
	@Override
	public void setCurrentStage(RCStage rcs) {
		currentstage = (AortaRCStage) rcs;
	}

	@Override
	public boolean not_interrupted() {
		return !interrupted;
	}
	
}
