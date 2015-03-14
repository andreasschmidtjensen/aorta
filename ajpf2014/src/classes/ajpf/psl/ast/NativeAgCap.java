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

import gov.nasa.jpf.vm.ElementInfo;
import gov.nasa.jpf.vm.VM;
import gov.nasa.jpf.vm.Verify;
import java.util.Objects;


/**
 * The formula C(a, phi) - a has the capability phi
 * 
 * @author louiseadennis
 * 
 */
public class NativeAgCap extends Native_Proposition {
	private static final long serialVersionUID = 6L;
	
	/**
	 * The agent which is required to believe the formula.
	 */
	private String agent;
	/**
	 * The formula the agent believes.
	 */
	private Abstract_Formula fmla;
		
	/**
	 * Constructor.
	 * 
	 * @param a  The agent which believes the formula.
	 * @param f  The formula that is believed.
	 */
	public NativeAgCap(String a, Abstract_Formula f) {
		fmla = f;
		agent = a;
	}
	
		
	/*
	 * (non-Javadoc)
	 * @see mcapl.psl.Proposition#equals(mcapl.psl.MCAPLProperty)
	 */
	public boolean equals(Object p) {
		if (p instanceof NativeAgCap) {
			return (((NativeAgCap) p).getCapability().equals(fmla) && ((NativeAgCap) p).getAgent().equals(agent));
		}
		
		return false;
	}

	@Override	
	public int hashCode() {
		if (Verify.isRunningInJPF()) {
			return objref;
		} else {				
			int hash = 7;
			hash = 67 * hash + Objects.hashCode(this.agent);
			hash = 67 * hash + Objects.hashCode(this.fmla);
			return hash;
		}
	}
	/**
	 * Getter method for the MCAPL Formula to be believed.
	 * 
	 * @return the formula that should be believed.
	 */
	public Abstract_Formula getCapability() {
		return fmla;
	}
	
	/**
	 * Getter method for the Agent.
	 * 
	 * @return the agent who should believe the belief.
	 */
	public String getAgent() {
		return agent;
	}
	
	/**
	 * We don't need to clone the agent - its the same one we want to query.
	 */
	public NativeAgCap clone() {
		return(new NativeAgCap(agent, fmla));
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "C(" + agent + "," + fmla + ")";
		return s;
	}
	    
	@Override
	public String getEquivalentJPFClass() {
		return "ajpf.psl.ast.Abstract_AgCap";
	}
	
	
	public int createInJPF(VM vm) {
		int objref = super.createInJPF(vm);
		ElementInfo ei = vm.getElementInfo(objref);
		ei.setReferenceField("fmla", fmla.createInJPF(vm));
		ei.setReferenceField("agent", vm.getHeap().newString(agent, vm.getCurrentThread()).getObjectRef());
		return objref;
	}
	
	public int quickCompareVal() {
		return 20;
	}

}
