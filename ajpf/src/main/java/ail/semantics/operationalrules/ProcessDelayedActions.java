// ----------------------------------------------------------------------------
// Copyright (C) 2008-2012 Louise A. Dennis, Berndt Farwer, Michael Fisher and 
// Rafael H. Bordini.
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
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// 
// To contact the authors:
// http://www.csc.liv.ac.uk/~lad
//----------------------------------------------------------------------------

package ail.semantics.operationalrules;

import ail.util.AILexception;
import ail.semantics.AILAgent;
import ail.semantics.OSRule;
import ail.syntax.Action;

/**
 * Handle an action.  Takes the top actions from the agent's Action
 * stack and executes it.  If the execution fails the action is still
 * removed from the stack.  Will need to be sub-classed for more sophisticated
 * error handling.
 * 
 * @author lad
 *
 */
public class ProcessDelayedActions implements OSRule {
	private static final String name = "Process Delayed Actions";

	/*
	 * (non-Javadoc)
	 * @see ail.semantics.operationalrules.OSRule#getName()
	 */
	public String getName() {
		return name;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see ail.semantics.operationalrules.HandleTopDeed#checkPreconditions(ail.semantics.AILAgent)
	 */
	public boolean checkPreconditions(AILAgent a) {
		if (! (a.getActions().isEmpty())) {
			return true;
		}

		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ail.semantics.OSRule#apply(ail.semantics.AILAgent)
	 */
	public void apply(AILAgent a) {
		Action act = a.getActions().get(a.getActions().size() - 1);

		try {
			a.getEnv().executeAction(a.getAgName(), act);			
		// NB.  Need to add agent list.
		} catch (AILexception ex) {
			
		}
		
	}
}