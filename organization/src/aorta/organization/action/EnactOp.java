/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta.organization.action;

import alice.tuprolog.Struct;
import aorta.kr.KBType;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.FormulaQualifier;
import aorta.organization.ArtifactState;
import aorta.organization.ts.rules.OperationExecution;

/**
 *
 * @author Andreas
 */
public class EnactOp extends Operation {
	
	private final String role;

	public EnactOp(String agent, String role) {
		super(agent);
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	@Override
	public boolean execute(ArtifactState state) {
		Struct rea = new MetaLanguage().rea(new Struct(agent), new Struct(role));
		Struct qualified = FormulaQualifier.qualifyStruct(rea, KBType.ORGANIZATION);
		
		QueryEngine qe = new QueryEngine();
		OperationExecution tr = new OperationExecution();
		tr.remove(state, qe, qualified);
		
		return true;
	}

	@Override
	public String toString() {
		return "enact(" + agent + ", " + role + ")";
	}
	
}
