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
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.TermFormatter;
import java.awt.BorderLayout;
import aorta.logging.Logger;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

/**
 *
 * @author asj
 */
public class AgentPanel extends EntityPanel {

	private static final Logger logger = Logger.getLogger(AgentPanel.class.getName());

	private AortaAgent agent;
	private StateViewer stateViewer = StateViewer.get();
	
	private final DefaultListModel roleModel = new DefaultListModel();
	private final DefaultListModel goalModel = new DefaultListModel();
	private final DefaultListModel optionModel = new DefaultListModel();
	private final DefaultListModel obligationModel = new DefaultListModel();
	private final DefaultListModel violationModel = new DefaultListModel();

	public AgentPanel(final AortaAgent agent) {
		this.agent = agent;
		
		addListPanel("Roles", roleModel);
		addListPanel("Options", optionModel);
		addListPanel("Goals", goalModel);
		addListPanel("Obligations", obligationModel);
		addListPanel("Violations", violationModel);
		
		setState(new State());
		start();
	}

	@Override
	public void delegateStateViewer() {
		stateViewer.setAgent(agent);
		stateViewer.requestFocus();
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
		final Term OBL = new Struct("org", ml.obligation(new Struct(agent.getName()), new Var("R"), new Var("O"), new Var("D")));
		final Term VIOL = new Struct("org", ml.violation(new Struct(agent.getName()), new Var("R"), new Var("O")));
		
		
		@Override
		public void update() {
			MentalState ms = agent.getState().getMentalState();
			update(ms, roleModel, REA, "R");
			update(ms, goalModel, GOAL, "G");
			update(ms, optionModel, OPT, "O");
			
			updateObligations();
			updateViolations();
		}

		private void updateObligations() {
			obligationModel.clear();
			
			List<String> values = new ArrayList<>();
			List<SolveInfo> solutions = engine.findAll(agent.getState().getMentalState(), OBL);
			for (SolveInfo solution : solutions) {
				if (solution.isSuccess()) {
					try {
						String role = solution.getVarValue("R").toString();
						Term objective = solution.getVarValue("O");
						Term deadline = solution.getVarValue("D");
						String value = role + " : " + TermFormatter.toString(objective) + " < " + TermFormatter.toString(deadline);
						values.add(anon.matcher(value).replaceAll("_"));
					} catch (NoSolutionException ex) {
						// ignore because of isSuccess()
					}
				}
			}
			Collections.sort(values);
			for (String v : values) {
				obligationModel.addElement(v);
			}
		}
		
		private void updateViolations() {
			violationModel.clear();
			
			List<String> values = new ArrayList<>();
			List<SolveInfo> solutions = engine.findAll(agent.getState().getMentalState(), VIOL);
			for (SolveInfo solution : solutions) {
				if (solution.isSuccess()) {
					try {
						String role = solution.getVarValue("R").toString();
						Term objective = solution.getVarValue("O");
						String value = role + " : " + TermFormatter.toString(objective);
						values.add(anon.matcher(value).replaceAll("_"));
					} catch (NoSolutionException ex) {
						// ignore because of isSuccess()
					}
				}
			}
			Collections.sort(values);
			for (String v : values) {
				violationModel.addElement(v);
			}
		}
		
	}
	
}
