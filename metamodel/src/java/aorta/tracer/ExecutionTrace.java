/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.tracer;

import alice.tuprolog.Term;
import aorta.State;
import aorta.kr.MentalState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author asj
 */
public class ExecutionTrace implements StateListener, Iterable<VisitedState> {
		
	private final String agent;
	private final List<VisitedState> states;
	
	private int stateNum = 0;
	private VisitedState currentState;

	public ExecutionTrace(String agent) {
		this.agent = agent;
		
		states = new ArrayList<>();
	}

	private void newState(MentalState state) {
		currentState = new VisitedState(stateNum++, state);
		states.add(currentState);
	}
	
	public void revokeLastState() {
		stateNum--;
		states.remove(currentState);
		if (!states.isEmpty()) {
			currentState = states.get(states.size() - 1);
		}
	}
	
	public String getAgent() {
		return agent;
	}

	@Override
	public String toString() {
		return agent;
	}

	@Override
	public void termAdded(String name, Term term) {
		currentState.termAdded(name, term);
	}

	@Override
	public void termRemoved(String name, Term term) {
		currentState.termRemoved(name, term);
	}

	@Override
	public void newState(State state) {
		newState(state.getMentalState());
	}

	@Override
	public Iterator<VisitedState> iterator() {
		return states.iterator();
	}
	
	public String toTikz() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\\documentclass[class=minimal,border=10pt]{standalone}\n");
		sb.append("\\usepackage{makecell}\n");
		sb.append("\\usepackage{tikz}\n");
		sb.append("\\usetikzlibrary{positioning}\n");
		sb.append("\\begin{document}\n");
		sb.append("\\begin{tikzpicture}[->]\n");
		sb.append("\\tikzstyle{node} = [font=\\normalfont]");
		
		
		sb.append("\\node[node] (agent) {").append(agent).append("};\n");
		
		String prev = "agent";
		
		for (VisitedState state : this) {
			int x = 2;
			for (FiredTransitionRule rule : state) {
				x += rule.getAdded().size() + rule.getRemoved().size();
			}
			
			String curr = "s" + state.getStateNum();
			sb.append("\\node[node, below=").append(2*x).append("ex of ").append(prev).append("] (").append(curr).append(") {$s_{").append(state.getStateNum()).append("}$};\n");
			sb.append("\\draw (").append(prev).append(") -- node[auto=left] {\\makecell[l]{\n");
			for (FiredTransitionRule rule : state) {
				for (Term added : rule.getAdded()) {
					sb.append("\\texttt{+").append(added.toString().replace("\\+","\\textbackslash{+}").replace("_","\\_")).append("}").append("\\\\").append("\n");
				}
				for (Term removed : rule.getRemoved()) {
					sb.append("\\texttt{-").append(removed.toString().replace("\\+","\\textbackslash{+}").replace("_","\\_")).append("}").append("\\\\").append("\n");
				}
			}
			sb.delete(sb.length()-2, sb.length()-2);
			sb.append("}} (").append(curr).append(");\n");
			
			prev = curr;
		}
		
		sb.append("	\\end{tikzpicture}\n");
		sb.append("\\end{document}");
		
		return sb.toString();
	}
	
}
