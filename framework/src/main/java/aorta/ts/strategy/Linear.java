/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ts.strategy;

import aorta.AgentState;
import aorta.kr.QueryEngine;
import aorta.tracer.Tracer;
import aorta.ts.Transition;
import aorta.ts.impl.Act;
import aorta.ts.impl.ActN;
import aorta.ts.impl.Chk;
import aorta.ts.impl.Coord;
import aorta.ts.impl.Ext;
import aorta.ts.impl.Opt;
import java.util.logging.Logger;

/**
 *
 * @author asj
 */
public class Linear implements Strategy {

	private static final Logger logger = Logger.getLogger(Linear.class.getName());
	
	private Chk chk = new Chk();
	private Ext ext = new Ext();
	private Opt opt = new Opt();
	private Act act = new Act();
	private ActN actN = new ActN();
	private Coord coord = new Coord();
	private QueryEngine engine = new QueryEngine();

	@Override
	public AgentState execute(AgentState s0) throws StrategyFailedException {
		AgentState s1 = execute(chk, s0); 
		AgentState s2 = execute(ext, s1);
		AgentState s3 = execute(opt, s2);
		AgentState s4;
		if (s3 == s2) {
			logger.fine("No options to consider.");
			s4 = s3;
		} else {
			s4 = act.execute(engine, s3);
			if (s4 == null) {
				s4 = execute(actN, s3);
			}
		}
		AgentState s5 = execute(coord, s4);
		if (s5 != s0) {
			Tracer.trace(s0.getAgent().getName(), "--------------------\n");
		}
		return s5;
	}
	
	private AgentState execute(Transition transition, AgentState currentState) {
		AgentState newState = transition.execute(engine, currentState);
		if (newState != null) {
			newState.prepareForTransition();
			return newState;
		} else {
			return currentState;
		}
	}
}
