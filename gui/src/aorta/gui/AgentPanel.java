/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.gui;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.AortaAgent;
import aorta.kr.MentalState;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.TermFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author asj
 */
public class AgentPanel extends EntityPanel {

	private final AortaAgent agent;
	
	private final JList violList;
	private final JList normList;
	private final JList goalList;
	private final JList optionList;
	private final JList roleList;
	
	public AgentPanel(final AortaAgent agent) {
		this.agent = agent;
		
		JLabel title = new JLabel(agent.getName());
		statePanel.add(title);
		
		roleList = addListPanel("Roles");
		optionList = addListPanel("Options");
		goalList = addListPanel("Goals");
		normList = addListPanel("Norms");
		violList = addListPanel("Violations");
		
		State state = new State();		
		setState(state);
		agent.getState().addStateListener(state);
	}

	@Override
	public void delegateTraceViewer() {
		ExecutionTraceView etv = ExecutionTraceView.get();
		etv.setAgent(agent.getName());
		etv.requestFocus();
	}

	@Override
	public String getEntityName() {
		return agent.getName();
	}

	class State extends EntityState {				
		
		// TODO: Only update if actually changed
		final MetaLanguage ml = new MetaLanguage();
		final Term REA = new Struct("org", ml.rea(new Struct(agent.getName()), new Var("R")));
		final Term OPT = Term.createTerm("opt(O)");
		final Term GOAL = Term.createTerm("goal(G)");
		final Term NORM = new Struct("org", ml.norm(new Struct(agent.getName()), new Var("R"), new Var("Deon"), new Var("O"), new Var("D")));
		final Term VIOL = new Struct("org", ml.violation(new Struct(agent.getName()), new Var("R"), new Var("Deon"), new Var("O")));
		
		@Override
		public void update() {
			MentalState ms = agent.getState().getMentalState();
			update(ms, roleList, REA, "R");
			update(ms, goalList, GOAL, "G");
			update(ms, optionList, OPT, "O");
			
			updateNorms();
			updateViolations();
		}

		private void updateNorms() {
			List<String> values = new ArrayList<>();
			List<SolveInfo> solutions = agent.getState().getMentalState().findAll(NORM);
			for (SolveInfo solution : solutions) {
				if (solution.isSuccess()) {
					try {
						String role = solution.getVarValue("R").toString();
						Term objective = solution.getVarValue("O");
						String deon = solution.getVarValue("Deon").toString();
						Term deadline = solution.getVarValue("D");
						String value = role + " [" + deon + "] : " + TermFormatter.toString(objective) + " < " + TermFormatter.toString(deadline);
						values.add(anon.matcher(value).replaceAll("_"));
					} catch (NoSolutionException ex) {
						// ignore because of isSuccess()
					}
				}
			}
			DefaultListModel normModel = new DefaultListModel();
			
			Collections.sort(values);
			for (String v : values) {
				normModel.addElement(v);
			}
			
			normList.setModel(normModel);
		}
		
		private void updateViolations() {
			List<String> values = new ArrayList<>();
			List<SolveInfo> solutions = agent.getState().getMentalState().findAll(VIOL);
			for (SolveInfo solution : solutions) {
				if (solution.isSuccess()) {
					try {
						String role = solution.getVarValue("R").toString();
						Term objective = solution.getVarValue("O");
						String deon = solution.getVarValue("Deon").toString();
						String value = role + " [" + deon + "] : " + TermFormatter.toString(objective);
						values.add(anon.matcher(value).replaceAll("_"));
					} catch (NoSolutionException ex) {
						// ignore because of isSuccess()
					}
				}
			}
			DefaultListModel violationModel = new DefaultListModel();
		
			Collections.sort(values);
			for (String v : values) {
				violationModel.addElement(v);
			}
			
			violList.setModel(violationModel);
		}

	}
	
}
