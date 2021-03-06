// ----------------------------------------------------------------------------
// Copyright (C) 2008-2012 Louise A. Dennis, Berndt Farwer, Michael Fisher and 
// Rafael H. Bordini.
// 
// This file is part of Agent JPF (AJPF)
//
// AJPF is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 3 of the License, or (at your option) any later version.
// 
// AJPF is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with AJPF if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// 
// To contact the authors:
// http://www.csc.liv.ac.uk/~lad
//----------------------------------------------------------------------------

package ajpf.psl;

import ajpf.MCAPLAgent;
import ajpf.MCAPLcontroller;

/**
 * The formula I(a, phi) - a has intention phi.
 * 
 * @author louiseadennis
 * 
 */
public class MCAPLAgIntention extends Proposition {
	
	/**
	 * The agent which is required to have the goal.
	 */
	private transient MCAPLAgent agent;
	/**
	 * The formula that is the agent's goal.
	 */
	private transient MCAPLFormula goal;
	
	private int hashcode;
	
	/**
	 * Constructor.
	 * 
	 * @param a  The agent which has the goal.
	 * @param f  The goal.
	 */
	public MCAPLAgIntention(MCAPLAgent a, MCAPLFormula f) {
		goal = f;
		agent = a;
		hashcode = goal.hashCode() ^ agent.hashCode();
	}
	
	/**
	 * Constructor for when we only have the agent name, not the 
	 * agent.
	 * 
	 * @param s The name of the agent.
	 * @param c The controller for the multi-agent system.
	 * @param f The goal.
	 */
	public MCAPLAgIntention(String s, MCAPLcontroller c, MCAPLFormula f) {
		this(c.getAgent(s), f);
	}
	
	/**
	 * Checks the truth of the formula agent has intention fmla
	 * 
	 * @return the truth of the formula.
	 */
	public boolean check() {
		return (agent.hasIntention(goal));
	}
	
	/*
	 * (non-Javadoc)
	 * @see mcapl.psl.Proposition#equals(mcapl.psl.MCAPLProperty)
	 */
	public boolean equals(Object p) {
		if (p instanceof MCAPLAgIntention) {
			return (((MCAPLAgIntention) p).getGoal().equals(goal) && ((MCAPLAgIntention) p).getAgent().equals(agent));
		}
		
		return false;
	}
	
	public int hashCode() {
		return hashcode;
	}
	
	/**
	 * Getter method for the Goal.
	 * 
	 * @return the goal.
	 */
	public MCAPLFormula getGoal() {
		return goal;
	}
	
	/**
	 * Getter method for the Agent.
	 * 
	 * @return the agent who should have the goal.
	 */
	public MCAPLAgent getAgent() {
		return agent;
	}
	
	/**
	 * We don't need to clone the agent - its the same one we want to query.
	 */
	public MCAPLAgIntention clone() {
		return(new MCAPLAgIntention(agent, (MCAPLFormula) goal.clone()));
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "I(" + agent.getAgName() + "," + goal.toString() + ")";
		return s;
	}
	
	public int quickCompareVal() {
		return 8;
	}

	    
}
