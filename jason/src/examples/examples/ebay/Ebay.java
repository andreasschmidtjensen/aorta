/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.ebay;

import examples.DayEnvironment;
import jason.asSyntax.Atom;
import jason.asSyntax.Literal;
import jason.asSyntax.LiteralImpl;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asj
 */
public class Ebay extends DayEnvironment {

	private Map<String, LiteralImpl> items = new HashMap<>();
	private Map<String, List<Literal>> percepts = new HashMap<>();

	@Override
	public void init(String[] args) {
		super.init(args);
		
		items.put("bike", newItem("alice", "bike", 300));
	}
		
	@Override
	public boolean executeAction(String agName, Structure act) {
		switch (act.getFunctor()) {
			case "buy": { 
				Term goods = act.getTerm(0);
				Term amount = act.getTerm(1);
				Term seller = act.getTerm(2);
				
				Literal buys = new LiteralImpl("buys");
				buys.addTerm(goods);
				buys.addTerm(amount);
				buys.addTerm(new Atom(agName));
				addPercept(seller.toString(), buys);				
				
				return true;
			}
			case "sell": {
				Term goods = act.getTerm(0);
				Term buyer = act.getTerm(1);
				
				Literal bought = new LiteralImpl("bought");
				bought.addTerm(goods);
				bought.addTerm(items.get(goods.toString()).getTerm(2));
				bought.addTerm(new Atom(agName));
				bought.addTerm(new NumberTermImpl(getDay()));
				
				Literal sold = new LiteralImpl("sold");
				sold.addTerm(goods);
				sold.addTerm(buyer);
				sold.addTerm(new NumberTermImpl(getDay()));
				
				addToPerceptMap(buyer.toString(), bought);
				addToPerceptMap(agName, sold);
				
				return true;
			}
			case "deliver": {
				Term goods = act.getTerm(0);
				Term buyer = act.getTerm(1);
				
				Literal delivered = new LiteralImpl("delivered");
				delivered.addTerm(goods);
				delivered.addTerm(buyer);
				delivered.addTerm(new NumberTermImpl(getDay()));
				
				addToPerceptMap(buyer.toString(), delivered);
				
				return true;
			}
		}
		
		return super.executeAction(agName, act);
	}
	
	private void addToPerceptMap(String ag, Literal percept) {
		if (!percepts.containsKey(ag)) {
			percepts.put(ag, new ArrayList<Literal>());
		}
		percepts.get(ag).add(percept);
	}

	@Override
	public List<Literal> getPercepts(String agName) {
		List<Literal> agPercepts = super.getPercepts(agName);
		for (LiteralImpl item : items.values()) {
			agPercepts.add(item);
		}
		if (percepts.containsKey(agName)) {
			agPercepts.addAll(percepts.get(agName));
		}
		
		return agPercepts;
	}

	private LiteralImpl newItem(String seller, String goods, int price) {
		LiteralImpl item = new LiteralImpl("item");
		item.addTerm(new Atom(seller));
		item.addTerm(new Atom(goods));
		item.addTerm(new NumberTermImpl(price));
		
		return item;
	}
	
}
