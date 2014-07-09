// ----------------------------------------------------------------------------
// Copyright (C) 2012 Louise A. Dennis, and  Michael Fisher 
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
// License along with AJPF; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// 
// To contact the authors:
// http://www.csc.liv.ac.uk/~lad
//
//----------------------------------------------------------------------------

package ajpf.psl.ast;

import gov.nasa.jpf.jvm.MJIEnv;
import ajpf.MCAPLcontroller;
import ajpf.MCAPLmas;
import ajpf.psl.MCAPLProperty;
import ajpf.psl.MCAPLFormula;
import ajpf.psl.MCAPLOrgOption;

//import ail.syntax.Literal;
//import ail.syntax.Pred;
//import ail.syntax.ast.Abstract_Structure;

/**
 * The formula Opt(a, phi) - a believes that phi is an organizational option
 * 
 * @author louiseadennis
 * 
 */
public class Abstract_OrgOption extends Abstract_Proposition {
	Abstract_Formula fmla;
	String agent;
		
	/**
	 * Constructor.
	 * 
	 * @param a  The agent which believes the formula.
	 * @param f  The formula that is believed.
	 */
	public Abstract_OrgOption(String agName, Abstract_Formula f) {
		fmla =  f;
		agent = agName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "Opt(" + agent + "," + fmla + ")";
		return s;
	}
	
	public MCAPLProperty toMCAPL(MCAPLmas mas, MCAPLcontroller c) {
		return new MCAPLOrgOption(c.getAgent(agent), (MCAPLFormula) fmla.toMCAPL());
	}
	
	public MCAPLProperty toMCAPLNative() {
		return new NativeOrgOption(agent, fmla);
	}
	    
	public int newJPFObject(MJIEnv env) {
		int ref = env.newObject("ajpf.psl.ast.Abstract_OrgOption");
		env.setReferenceField(ref, "fmla", fmla.newJPFObject(env));
		env.setReferenceField(ref, "agent", env.newString(agent));
		return ref;
	}

}
