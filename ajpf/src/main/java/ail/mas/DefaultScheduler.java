// ----------------------------------------------------------------------------
// Copyright (C) 2012 Louise A. Dennis, and  Michael Fisher 
//
// This file is part of the Agent Infrastructure Layer (AIL)
// 
// The AIL is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 3 of the License, or (at your option) any later version.
// 
// The AIL is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with the AIL; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// 
// To contact the authors:
// http://www.csc.liv.ac.uk/~lad
//
//----------------------------------------------------------------------------

package ail.mas;

import java.util.Map;
import java.util.List;
import java.util.TreeMap;

import ajpf.util.VerifyList;
import ajpf.MCAPLScheduler;
import ajpf.MCAPLJobber;



/**
 * A straightforward implementation of MCAPLScheduler.  Every active agent has the opportunity to
 * be picked every time the scheduler is called.  NB.  This is likely to generate a lot of 
 * unnecessary states since all interleavings of agent execution will be checked not just those
 * where something has changed since the agent was last executed.
 * 
 * @author louiseadennis
 *
 */
public class DefaultScheduler implements MCAPLScheduler { 
	private Map<String, MCAPLJobber> agnames = new TreeMap<String, MCAPLJobber>();
	private VerifyList<String> activeAgents = new VerifyList<String>();
	private VerifyList<String> inactiveAgents = new VerifyList<String>();
	
	/*
	 * (non-Javadoc)
	 * @see ajpf.MCAPLScheduler#getActiveJobbers()
	 */
	public List<MCAPLJobber> getActiveJobbers() {
		List<MCAPLJobber> ags = new VerifyList<MCAPLJobber>();
		for (String s: activeAgents) {
			ags.add(agnames.get(s));
		}
		return ags;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ajpf.MCAPLScheduler#getActiveJobberNames()
	 */
	public List<String> getActiveJobberNames() {
		List<String> ags = new VerifyList<String>();
		for (String s: activeAgents) {
			ags.add(s);
		}
		return ags;
	}

	/*
	 * (non-Javadoc)
	 * @see ajpf.MCAPLScheduler#notActive(java.lang.String)
	 */
	public void notActive(String agName) {
		activeAgents.remove(agName);
		if (!inactiveAgents.contains(agName)) {
			inactiveAgents.put(agName);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ajpf.MCAPLScheduler#isActive(ajpf.MCAPLJobber)
	 */
	public void isActive(String a) {
		if (!activeAgents.contains(a)) {
			activeAgents.put(a);
		}
		inactiveAgents.remove(a);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ajpf.MCAPLScheduler#addJobber(ajpf.MCAPLJobber)
	 */
	public void addJobber(MCAPLJobber a) {
		agnames.put(a.getName(), a);
		activeAgents.add(a.getName());
	}

}