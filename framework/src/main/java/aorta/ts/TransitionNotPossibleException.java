/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts;

import aorta.AORTAException;

/**
 *
 * @author asj
 */
public class TransitionNotPossibleException extends AORTAException {

    public TransitionNotPossibleException() {
    }

    public TransitionNotPossibleException(String arg0) {
        super(arg0);
    }

    public TransitionNotPossibleException(Throwable arg0) {
        super(arg0);
    }

    public TransitionNotPossibleException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
    
}
