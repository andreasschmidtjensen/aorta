/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason;

import jason.asSyntax.Plan;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public interface PlanListener {
	public void planAdded(Plan plan, boolean first);
	public void planRemoved(Plan plan, boolean last);
}
