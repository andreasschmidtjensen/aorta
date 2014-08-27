/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import jason.asSyntax.Literal;
import jason.asSyntax.LiteralImpl;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Structure;

/**
 *
 * @author asj
 */
public class Library extends DayEnvironment {
	
	@Override
	public boolean executeAction(String agName, Structure act) {
		switch (act.getFunctor()) {
			case "borrow":
				Literal borrowing = new LiteralImpl("borrowing");
				borrowing.addTerm(act.getTerm(0));
				borrowing.addTerm(new NumberTermImpl(getDay() + 2));
				addPercept(borrowing);
				
				return true;
			case "return":
				Literal returned = new LiteralImpl("returned");
				returned.addTerm(act.getTerm(0));
				addPercept(returned);
				
				return true;
		}
		
		return super.executeAction(agName, act);
	}

}
