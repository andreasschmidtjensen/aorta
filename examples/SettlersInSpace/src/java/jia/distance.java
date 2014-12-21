package jia;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Term;
import jason.asSyntax.VarTerm;
import java.awt.Point;

public class distance extends DefaultInternalAction  {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        int x = (int) ((NumberTerm) args[0]).solve();
        int y = (int) ((NumberTerm) args[1]).solve();
        int x1 = (int) ((NumberTerm) args[2]).solve();
        int y1 = (int) ((NumberTerm) args[3]).solve();
        
        double distance = new Point(x,y).distance(new Point(x1,y1));
        
        VarTerm d = (VarTerm) args[4];
        un.unifies(d, new NumberTermImpl(distance));
        
        return true;
    }
	
}
