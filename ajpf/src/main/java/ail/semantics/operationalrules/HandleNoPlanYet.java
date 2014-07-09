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

import java.util.Iterator;

import ail.semantics.AILAgent;
import ail.syntax.Intention;
import ail.syntax.Unifier;
import ail.syntax.Deed;

/**
 * Do nothing if the current intention hasn't yet been planned.
 * 
 * @author lad
 *
 */
public class HandleNoPlanYet extends DoNothing {
	private final static String name = "Handle No Plan Yet";
	
	/*
	 * (non-Javadoc)
	 * @see ail.semantics.operationalrules.OSRule#getName()
	 */
	public String getName() {
		return name;
	}

	
	/*
	 * (non-Javadoc)
	 * @see ail.semantics.operationalrules.DoNothing#checkPreconditions(ail.semantics.AILAgent)
	 */
	public boolean checkPreconditions(AILAgent a) {
		Intention i = a.getIntention();

		if (!i.deeds().isEmpty()) {
			Unifier thetahd = i.hdU();
			Iterator<Unifier> ui = a.believes(i.hdG(), thetahd);		
			
			if (ui.hasNext()) {
				Deed topdeed = i.hdD();
				int deedtype = topdeed.getCategory();
				
				if (deedtype == Deed.Dnpy) {
					return true;
				}					
			
			}
		}
		
		return false;
	}
}