/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jia;

import eisbw.BWAPIBridge;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Term;
import jason.asSyntax.VarTerm;
import jnibwapi.JNIBWAPI;
import jnibwapi.Position;
import jnibwapi.types.UnitType;
import jnibwapi.types.UnitType.UnitTypes;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class findBunkerLocation extends DefaultInternalAction {

	private Position[] bunkerPositions = new Position[] {
		new Position(32, 80, Position.PosType.BUILD),
		new Position(35, 80, Position.PosType.BUILD),
		new Position(40, 83, Position.PosType.BUILD),
		new Position(40, 85, Position.PosType.BUILD)
	};
	
    private final JNIBWAPI game;
    
    public findBunkerLocation() {
        super();
        this.game = BWAPIBridge.getGame();
    }

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
		UnitType bunker = UnitTypes.Terran_Bunker;
		
		Position bunkerPosition = null;
		for (Position p : bunkerPositions) {
			boolean buildable = game.canBuildHere(p, bunker, false);
			if (buildable) {
				bunkerPosition = p;
				break;
			}
		}
		
		if (bunkerPosition != null) {
			VarTerm x = (VarTerm) args[0];
			VarTerm y = (VarTerm) args[1];

			un.unifies(x, new NumberTermImpl(bunkerPosition.getBX()));
			un.unifies(y, new NumberTermImpl(bunkerPosition.getBY()));
			return true;
		} else {
			return false;
		}
    }

}
