package jia;

import eisbw.BWAPIBridge;
import eisbw.BWApiUtility;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.ListTerm;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Term;
import jason.asSyntax.VarTerm;
import java.awt.Point;
import java.util.List;
import jnibwapi.JNIBWAPI;
import jnibwapi.Position;

public class closest extends DefaultInternalAction  {
    private final JNIBWAPI game;
    private final BWApiUtility util;
    
    public closest() {
        super();
        this.game = BWAPIBridge.getGame();
        this.util = new BWApiUtility(game);
    }
    
    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        int posWX = (int) ((NumberTerm) args[0]).solve();
		int posWY = (int) ((NumberTerm) args[1]).solve();
        List<Term> unitIds = ((ListTerm) args[2]).getAsList();
        
        if (unitIds.isEmpty()) {
            return false;
        }
        
        int closestId = (int) ((NumberTerm)unitIds.get(0)).solve();
		Point point = new Point(posWX, posWY);
		Position unitPos = game.getUnit(closestId).getPosition();
		Point unitPoint = new Point(unitPos.getWX(), unitPos.getWY());
        double closestDistance = point.distanceSq(unitPoint);
        
        for (Term term : unitIds) {
            int id = (int) ((NumberTerm)term).solve();
			unitPos = game.getUnit(id).getPosition();
			unitPoint = new Point(unitPos.getWX(), unitPos.getWY());
            double distance = point.distanceSq(unitPoint);
            
            if (distance < closestDistance) {
                closestDistance = distance;
                closestId = id;
            }
        }
        
        VarTerm x = (VarTerm) args[3];
        un.unifies(x, new NumberTermImpl(closestId));
        
        return true;
    }
}
