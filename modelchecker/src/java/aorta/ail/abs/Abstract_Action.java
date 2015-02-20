/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail.abs;

import aorta.ail.abs.action.Abstract_CommitAction;
import aorta.ail.abs.action.Abstract_DeactAction;
import aorta.ail.abs.action.Abstract_DropAction;
import aorta.ail.abs.action.Abstract_EnactAction;
import aorta.ail.abs.action.Abstract_SendAction;
import aorta.reasoning.action.Action;
import aorta.reasoning.action.CommitAction;
import aorta.reasoning.action.DeactAction;
import aorta.reasoning.action.DropAction;
import aorta.reasoning.action.EnactAction;
import aorta.reasoning.action.SendAction;
import gov.nasa.jpf.vm.MJIEnv;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public abstract class Abstract_Action {

	public static Abstract_Action convert(Action action) {
		if (action instanceof CommitAction) {
			return new Abstract_CommitAction(((CommitAction)action).getObjective().toString());
		} else if (action instanceof DropAction) {
			return new Abstract_DropAction(((DropAction)action).getObjective().toString());			
		} else if (action instanceof EnactAction) {
			return new Abstract_EnactAction(((EnactAction)action).getRole().toString());			
		} else if (action instanceof DeactAction) {
			return new Abstract_DeactAction(((DeactAction)action).getRole().toString());			
		} else if (action instanceof SendAction) {
			return new Abstract_SendAction(((SendAction)action).getRecipient().toString(), Abstract_Formula.convert(((SendAction)action).getMessage()));
		} else {
			throw new RuntimeException("Wrong kind of action: " + action.getClass().getName());
		}
	}
	public abstract Action toAORTA();	
	public abstract int newJPFObject(MJIEnv env);	
}
