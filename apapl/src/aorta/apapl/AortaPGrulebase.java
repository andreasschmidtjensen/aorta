/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.apapl;

import alice.tuprolog.Struct;
import aorta.AortaAgent;
import apapl.data.Goal;
import apapl.data.Literal;
import apapl.data.Query;
import apapl.program.PGrule;
import apapl.program.PGrulebase;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asj
 */
public class AortaPGrulebase extends PGrulebase {

	private AortaAgent aortaAgent;

	private final List<Struct> initial = new ArrayList<>();

	public void setAortaAgent(AortaAgent aortaAgent) {
		this.aortaAgent = aortaAgent;

		// Initial rules from the apl file will be added 
		// before the AortaAgent has been setup, so we need 
		// to add them when it has been setup
		for (Struct s : initial) {
			aortaAgent.getState().getExternalAgent().addCapability(s);
		}
	}

	private void addToAorta(Literal literal) {
		Struct struct = TermConverter.apl2aorta(literal);
		addToAorta(struct);
	}

	private void addToAorta(Struct struct) {
		if (aortaAgent != null) {
			aortaAgent.getState().getExternalAgent().addCapability(struct);
		} else {
			initial.add(struct);
		}
	}

	@Override
	public void addRule(PGrule e) {
		super.addRule(e);

		Query goalQuery = e.getHead();
		List<Literal> literals = goalQuery.toLiterals();
		for (Literal lit : literals) {
			addToAorta(lit);
		}
	}

	@Override
	public AortaPGrulebase clone() {
		AortaPGrulebase b = new AortaPGrulebase();
		b.setAortaAgent(aortaAgent);
		b.setRules(getRules());
		return b;
	}
}
