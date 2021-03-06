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
 * The formula Opt(a, phi) - a believes that phi is an organizational option
 * 
 * @author louiseadennis
 * 
 */
public class MCAPLOrgOption extends Proposition {
//	 protected static Logger log = JPF.getLogger("ajpf.psl.MCAPLOrgBelief");
	
	/**
	 * The agent which is required to believe the formula.
	 */
	private transient MCAPLAgent agent;
	/**
	 * The formula the agent believes.
	 */
	private transient MCAPLFormula fmla;
	
	private int hashcode;
		
	/**
	 * Constructor.
	 * 
	 * @param a  The agent which believes the formula.
	 * @param f  The formula that is believed.
	 */
	public MCAPLOrgOption(MCAPLAgent a, MCAPLFormula f) {
		fmla = f;
		agent = a;
		hashcode = f.hashCode() ^ a.hashCode();
	}
	
	/**
	 * Constructor for when we only have the agent name, not the 
	 * agent.
	 * 
	 * @param s The name of the agent.
	 * @param c The controller for the multi-agent system.
	 * @param f The formula to be believed.
	 */
	public MCAPLOrgOption(String s, MCAPLcontroller c, MCAPLFormula f) {
		this(c.getAgent(s), f);
	}
	
	/**
	 * Checks the truth of the formula agent believs fmla
	 * 
	 * @return the truth of the formula.
	 */
	public boolean check() {
	//	System.err.println("Checking " + fmla);
		boolean result = agent.hasOrganizationalOption(fmla);
	//	System.err.println("result is: " + result);
		return (result);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mcapl.psl.Proposition#equals(mcapl.psl.MCAPLProperty)
	 */
	public boolean equals(Object p) {
		if (p instanceof MCAPLOrgOption) {
			return (((MCAPLOrgOption) p).getOpt().equals(fmla) && ((MCAPLOrgOption) p).getAgent().equals(agent));
		}
		
		return false;
	}
	public int hashCode() {
		return hashcode;
	}
	
	/**
	 * Getter method for the MCAPL Formula to be believed.
	 * 
	 * @return the formula that should be believed.
	 */
	public MCAPLFormula getOpt() {
		return fmla;
	}
	
	/**
	 * Getter method for the Agent.
	 * 
	 * @return the agent who should believe the belief.
	 */
	public MCAPLAgent getAgent() {
		return agent;
	}
	
	/**
	 * We don't need to clone the agent - its the same one we want to query.
	 */
	public MCAPLOrgOption clone() {
		return(new MCAPLOrgOption(agent, (MCAPLFormula) fmla.clone()));
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "Opt(" + agent.getAgName() + "," + fmla.toString() + ")";
		return s;
	}
	
	public int quickCompareVal() {
		return 19;
	}

	    
}
