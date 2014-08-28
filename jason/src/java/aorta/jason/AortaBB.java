/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason;

import alice.tuprolog.Struct;
import aorta.AortaAgent;
import jason.asSyntax.Atom;
import jason.asSyntax.Literal;
import jason.asSyntax.PredicateIndicator;
import jason.asSyntax.Term;
import jason.bb.DefaultBeliefBase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author ascje
 */
public class AortaBB extends DefaultBeliefBase {

	private static final Logger logger = Logger.getLogger(AortaBB.class.getName());
	
	public static final Term SILENT = new Atom("aorta_silent");
	
	private AortaAgent aortaAgent;
	
	private List<Struct> initialBels = new ArrayList<>();

	public void setAortaAgent(AortaAgent aortaAgent) {
		this.aortaAgent = aortaAgent;
		
		// Initial beliefs from the asl file will be added 
		// before the AortaAgent has been setup, so we need 
		// to add them when it has been setup
		for (Struct s : initialBels) {
			aortaAgent.getState().getExternalAgent().addBelief(s);
		}
	}

	@Override
	public boolean add(Literal l) {
		return add(l, false);
	}

	@Override
	public boolean add(int index, Literal l) {
		return add(l, index != 0);
	}

	@Override
	protected boolean add(Literal l, boolean addInEnd) {
		if (super.add(l, addInEnd)) {
			Struct struct = TermConverter.fromLiteral(l);
			if (aortaAgent == null) {
				initialBels.add(struct);
			} else if (!l.hasAnnot(SILENT)) {
				aortaAgent.getState().getExternalAgent().addBelief(struct);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean abolish(PredicateIndicator pi) {
		Iterator<Literal> it = getCandidateBeliefs(pi);
		if (it != null) {
			while (it.hasNext()) {
				removeFromAorta(it.next());
			}
		}
		return super.abolish(pi);
	}
	
	
	@Override
	public boolean remove(Literal l) {
		if (super.remove(l)) {
			removeFromAorta(l);
			return true;
		}
		return false;
	}
	
	@Override
	public Iterator<Literal> getPercepts() {
		final Iterator<Literal> i = super.getPercepts();
		return new Iterator<Literal>() {
			Literal current = null;
			@Override
			public boolean hasNext() {
				return i.hasNext();
			}

			@Override
			public Literal next() {
				current = i.next();
				return current;
			}

			@Override
			public void remove() {
				i.remove();
				if (current == null) {
                    logger.warning("Trying to remove a perception, but the the next() from the iterator is not called before!");
                }
				
				removeFromAorta(current);
			}
		};
	}

	private void removeFromAorta(Literal l) {
		if (aortaAgent != null) {
			aortaAgent.getState().getExternalAgent().removeBelief(TermConverter.fromLiteral(l));
		}
	}
	
}
