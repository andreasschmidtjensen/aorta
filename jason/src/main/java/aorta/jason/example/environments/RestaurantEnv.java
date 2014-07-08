/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason.example.environments;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class RestaurantEnv extends Environment {
	
	public static final String GUEST = "carol";
	public static final String WAITER = "alice";
	public static final String COOK = "bob";
	
	
	@Override
	public void init(String[] args) {
		addPercept(Literal.parseLiteral("guest_in_restaurant"));
	}
	
	@Override
	public boolean executeAction(String agName, Structure act) {
		String functor = act.getFunctor();
		switch (functor) {
			case "seat":
				addPercept(GUEST, Literal.parseLiteral("seated"));
				break;
			case "order_drink":
				break;
			case "order_food":
				break;
			case "prepare_drink":
				break;
			case "prepare_food":
				break;
			case "serve_food":
				break;
			case "serve_drink":
				break;
			case "pay":
				break;
			default:
				return false;
		}
		
		return true;
	}
		
}
