/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.rules;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.AgentState;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.ts.TransitionNotPossibleException;
import aorta.ts.TransitionRule;
import java.util.Iterator;
import aorta.logging.Logger;

/**
 *
 * @author asj
 */
public class NormCompliance implements TransitionRule {

	private static final Logger logger = Logger.getLogger(NormCompliance.class.getName());
	
	@Override
	public AgentState execute(QueryEngine engine, AgentState state) throws TransitionNotPossibleException {
		final MentalState ms = state.getMentalState();
		
		Struct norms = new Struct();// ms.getNorms();
        Iterator<? extends Term> it = norms.listIterator();
        while (it.hasNext()) {
            Term norm = it.next();
			SolveInfo normInfo = engine.solve(ms, norm);
			logger.fine(" > " + norm + "=" + normInfo.isSuccess());
            if (!normInfo.isSuccess()) {
                // all norms must still be satisfied by the new configuration
				logger.fine(" > Norm " + norm + " is violated");
                
				throw new TransitionNotPossibleException();
            }
        }
		
		return state;
	}
	
}
