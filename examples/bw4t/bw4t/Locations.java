/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bw4t;

import jason.asSyntax.Term;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author ascje
 */
public final class Locations {
	
	public static HashMap<String, Term> locations = new HashMap<>();
	
	public synchronized static void set(String agName, Term location) {
		locations.put(agName, location);
	}
	
	public synchronized static List<String> getAgsAtLocation(Term location) {
		List<String> result = new ArrayList<>();
		for (Entry<String, Term> loc : locations.entrySet()) {
			if (loc.getValue().equals(location)) {
				result.add(loc.getKey());
			}
		}
		return result;
	}
	
}
