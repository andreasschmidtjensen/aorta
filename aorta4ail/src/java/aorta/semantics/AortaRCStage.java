/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.semantics;

import ail.semantics.OSRule;
import ail.semantics.RCStage;
import gov.nasa.jpf.annotation.FilterField;
import gwendolen.semantics.GwendolenRCStage;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author asj
 */
public class AortaRCStage implements RCStage {
	
	@FilterField
	private int stage;
	@FilterField
	private String name;
	@FilterField
	private LinkedList<OSRule> rules = new LinkedList<OSRule>();

	public AortaRCStage(int stage, String name) {
		this.stage = stage;
		this.name = name;
	}
	
	private int getStageNum() {
		return stage;
	}
	
	@Override
	public String getStageName() {
		return name;
	}
	
	public int compareTo(AortaRCStage other) {
		if (other.getStageNum() == stage) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public Iterator<OSRule> getStageRules() {
		return rules.iterator();
	}

	@Override
	public void setRule(OSRule rule) {
		rules.add(rule);
	}
	
}
