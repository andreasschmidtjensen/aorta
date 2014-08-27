/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import jason.asSyntax.Literal;
import jason.asSyntax.LiteralImpl;
import jason.asSyntax.NumberTermImpl;
import jason.environment.Environment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asj
 */
public class DayEnvironment extends Environment {
	
	private long start;
	private int dayLength;

	@Override
	public void init(String[] args) {
		super.init(args);
		
		start = System.currentTimeMillis();
		dayLength = Integer.parseInt(args[0]) * 1000;
	}
	
	@Override
	public List<Literal> getPercepts(String agName) {
		Literal date = new LiteralImpl("day");
		date.addTerm(new NumberTermImpl(getDay()));
		
		List<Literal> percepts = super.getPercepts(agName);
		if (percepts == null) {
			percepts = new ArrayList<>();
		}
		percepts.add(date);
		
		return percepts;
	}
	
	protected int getDay() {
		long elapsed = System.currentTimeMillis() - start;
		int day = (int) (elapsed / dayLength);
		return day;
	}

}
