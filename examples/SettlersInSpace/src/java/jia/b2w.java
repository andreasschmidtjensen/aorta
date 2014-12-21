/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jia;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Term;
import jason.asSyntax.VarTerm;
import jnibwapi.Position;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class b2w extends DefaultInternalAction {
	
    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        int bx = (int) ((NumberTerm) args[0]).solve();
        int by = (int) ((NumberTerm) args[1]).solve();
        
		Position p = new Position(bx, by, Position.PosType.BUILD);
		
        int wx = p.getWX();
        int wy = p.getWY();
        
        VarTerm dx = (VarTerm) args[2];
        VarTerm dy = (VarTerm) args[3];
        un.unifies(dx, new NumberTermImpl(wx));
        un.unifies(dy, new NumberTermImpl(wy));
        
        return true;
    }
	
}
