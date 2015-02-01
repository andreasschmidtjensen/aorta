/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.tracer;

import alice.tuprolog.Term;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asj
 */
public class FiredTransitionRule {
	
	private final String name;
	private final List<Term> added;
	private final List<Term> removed;

	public FiredTransitionRule(String name) {
		this(name, new ArrayList<Term>(), new ArrayList<Term>());
	}

	public FiredTransitionRule(String name, List<Term> added, List<Term> removed) {
		this.name = name;
		this.added = added;
		this.removed = removed;
	}
	
	public List<Term> getAdded() {
		return added;
	}

	public List<Term> getRemoved() {
		return removed;
	}
	
	public void termAdded(Term term) {
		added.add(term);
	}
	
	public void termRemoved(Term term) {
		removed.add(term);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
