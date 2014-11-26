/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import alice.tuprolog.Int;
import alice.tuprolog.Struct;
import aorta.organization.EnvironmentSensor;
import jason.asSyntax.Literal;
import jason.asSyntax.LiteralImpl;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Structure;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asj
 */
public class Library extends DayEnvironment implements EnvironmentSensor {
	
	List<Struct> actions = new ArrayList<>();

	@Override
	public boolean executeAction(String agName, Structure act) {
		switch (act.getFunctor()) {
			case "borrow":
				Literal borrowing = new LiteralImpl("borrowing");
				borrowing.addTerm(act.getTerm(0));
				borrowing.addTerm(new NumberTermImpl(getDay() + 2));
				addPercept(borrowing);

				actions.add(new Struct("borrowed", new Struct(act.getTerm(0).toString())));
				actions.add(new Struct("returnDate", new Struct(act.getTerm(0).toString()), new Int(getDay() + 2)));
				
				return true;
			case "return":
				Literal returned = new LiteralImpl("returned");
				returned.addTerm(act.getTerm(0));
				addPercept(returned);

				actions.add(new Struct("returned", new Struct(act.getTerm(0).toString())));
				
				return true;
		}
		
		return super.executeAction(agName, act);
	}

	public List<Struct> getEnvState() {
		List<Struct> structs = new ArrayList<>();
		structs.add(new Struct("day", new Int(getDay())));
		structs.addAll(actions);
		return structs;
	}

}
