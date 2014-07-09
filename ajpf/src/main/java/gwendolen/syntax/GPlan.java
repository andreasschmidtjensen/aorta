// ----------------------------------------------------------------------------
// Copyright (C) 2012 Louise A. Dennis, and  Michael Fisher 
//
// This file is part of Gwendolen
// 
// Gwendolen is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 3 of the License, or (at your option) any later version.
// 
// Gwendolen is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with Gwendolen; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// 
// To contact the authors:
// http://www.csc.liv.ac.uk/~lad
//
//----------------------------------------------------------------------------

package gwendolen.syntax;

import java.util.ArrayList;

import ail.syntax.Event;
import ail.syntax.GBelief;
import ail.syntax.Plan;
import ail.syntax.Deed;
import ail.syntax.Guard;

/**
 * Class for Gwendolen Plans providing, in particular, a Gwendolen specific plan
 * constructor.
 * @author louiseadennis
 *
 */
public class GPlan extends Plan { 
	
	/**
	 * Construct a plan from an event, a guard and a stack of deeds.
	 * @param e
	 * @param g
	 * @param ds
	 */
	public GPlan(Event e, Guard g, ArrayList<Deed> ds) {
		setTrigger(e);
		ArrayList<Guard> gs = new ArrayList<Guard>();
		for (int i = 1; i < ds.size(); i++) {
			gs.add(new Guard(new GBelief(GBelief.GTrue)));
		}
		gs.add(g);
		setContext(gs);
		
		setBody(ds);
        
 		ArrayList<Deed> prefix = new ArrayList<Deed>();
		prefix.add(new Deed(Deed.Dnpy));
		setPrefix(prefix);
		
	}

}
