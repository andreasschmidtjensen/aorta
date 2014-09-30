/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason;

import jason.JasonException;
import jason.asSyntax.Plan;
import jason.asSyntax.PlanLibrary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class AortaPlanLibrary extends PlanLibrary {
	
	private List<PlanListener> listeners = new ArrayList<>();
	private Map<Plan, Boolean> initialPlans = new HashMap<>();

	public void addPlanListener(PlanListener pl) {
		listeners.add(pl);
		for (Entry<Plan, Boolean> entry : initialPlans.entrySet()) {
			informAdded(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	public void add(Plan p, boolean before) throws JasonException {
		boolean first = getCandidatePlans(p.getTrigger()) == null;
		super.add(p, before);
		informAdded(p, first);
	}

	@Override
	public Plan remove(String pLabel) {
		boolean exists = get(pLabel) != null;
		
		Plan plan = super.remove(pLabel);
		
		boolean last = exists && get(pLabel) == null;
		
		informRemoved(plan, last);
		return plan;
	}
	
	public AortaPlanLibrary clone() {
        AortaPlanLibrary pl = new AortaPlanLibrary();
        try {
            for (Plan p: this) { 
                pl.add((Plan)p.clone(), false);
            }
        } catch (JasonException e) {
            e.printStackTrace();
        }
		pl.listeners.addAll(listeners);
        return pl;
    }

	private void informAdded(Plan plan, boolean first) {
		if (listeners.isEmpty()) {
			initialPlans.put(plan, first);
		} else {
			for (PlanListener pl : listeners) {
				pl.planAdded(plan, first);
			}
		}
	}
	
	private void informRemoved(Plan plan, boolean last) {
		for (PlanListener pl : listeners) {
			pl.planRemoved(plan, last);
		}
	}
		
}
