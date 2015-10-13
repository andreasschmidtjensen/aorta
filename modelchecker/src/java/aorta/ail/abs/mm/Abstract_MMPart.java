/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs.mm;

import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public abstract class Abstract_MMPart {
	
	public abstract Object toAorta();
	public abstract int newJPFObject(MJIEnv env);
	
}
