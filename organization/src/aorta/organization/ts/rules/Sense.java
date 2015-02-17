/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta.organization.ts.rules;

import alice.tuprolog.Struct;
import aorta.kr.KBType;
import aorta.kr.util.FormulaQualifier;
import aorta.organization.ArtifactState;
import aorta.tracer.Tracer;
import aorta.ts.TransitionRule;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class Sense extends TransitionRule<ArtifactState> {

	@Override
	protected ArtifactState execute(ArtifactState state) {
		if (state.getEnvironment() != null) {
			List<Struct> envState = state.getEnvironment().getEnvState();
			List<Struct> qualified = new ArrayList<>();
			for (Struct s : envState) {
				qualified.add(FormulaQualifier.qualifyStruct(s, KBType.BELIEF));
			}
			List<Struct>[] result = state.getMentalState().mergeKBs(KBType.BELIEF, qualified);
			List<Struct> added = result[0];
			List<Struct> removed = result[1];
			
			if (added.size() > 0 || removed.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (Struct s : added) {
					sb.append("+").append(s).append("; ");
				}
				for (Struct s : removed) {
					sb.append("-").append(s).append("; ");
				}
				Tracer.trace(state.getIdentifier(), getName(), sb.toString());
			}			
		}
		return state;
	}

	@Override
	public String getName() {
		return "Sense";
	}

}
