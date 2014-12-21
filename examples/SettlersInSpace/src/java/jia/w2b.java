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
public class w2b extends DefaultInternalAction {
	
    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        int wx = (int) ((NumberTerm) args[0]).solve();
        int wy = (int) ((NumberTerm) args[1]).solve();
        
		Position p = new Position(wx, wy, Position.PosType.WALK);
		
        int bx = p.getBX();
        int by = p.getBY();
        
        VarTerm dx = (VarTerm) args[2];
        VarTerm dy = (VarTerm) args[3];
        un.unifies(dx, new NumberTermImpl(bx));
        un.unifies(dy, new NumberTermImpl(by));
        
        return true;
    }
	
}
