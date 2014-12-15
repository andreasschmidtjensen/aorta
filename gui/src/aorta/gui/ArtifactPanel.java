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
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.TermFormatter;
import aorta.organization.AortaArtifact;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author asj
 */
public class ArtifactPanel extends EntityPanel {

	private AortaArtifact artifact;
	private StateViewer stateViewer = StateViewer.get();
	
	private final JList reaList;
	private final JList oblList;
	private final JList violList;
	
	public ArtifactPanel(AortaArtifact artifact) {
		this.artifact = artifact;
		
		reaList = addListPanel("Role enactment");
		oblList = addListPanel("Obligations");
		violList = addListPanel("Violations");
		
		setState(new State());
		start();
	}
		
	@Override
	public void delegateStateViewer() {
		stateViewer.setArtifact(artifact);
		stateViewer.requestFocus();
	}

	@Override
	public String getEntityName() {
		return "ARTIFACT";
	}

	class State extends EntityState {
				
		// TODO: Only update if actually changed
		final MetaLanguage ml = new MetaLanguage();
		final Term REA = new Struct("org", ml.rea(new Var("A"), new Var("R")));
		final Term OPT = Term.createTerm("opt(O)");
		final Term GOAL = Term.createTerm("goal(G)");
		final Term OBL = new Struct("org", ml.obligation(new Var("A"), new Var("R"), new Var("O"), new Var("D")));
		final Term VIOL = new Struct("org", ml.violation(new Var("A"), new Var("R"), new Var("O")));
		
		
		@Override
		public void update() {
			updateRea();
			updateObligations();
			updateViolations();
		}

		private void updateRea() {
			List<String> values = new ArrayList<>();
			List<SolveInfo> solutions = engine.findAll(artifact.getState().getMentalState(), REA);
			for (SolveInfo solution : solutions) {
				if (solution.isSuccess()) {
					try {
						String agent = solution.getVarValue("A").toString();
						String role = solution.getVarValue("R").toString();
						String value = agent + ": " + role;
						values.add(value);
					} catch (NoSolutionException ex) {
						// ignore because of isSuccess()
					}
				}
			}
			DefaultListModel reaModel = new DefaultListModel();
			Collections.sort(values);
			for (String v : values) {
				reaModel.addElement(v);
			}
			violList.setModel(reaModel);
		}
		
		private void updateObligations() {
			List<String> values = new ArrayList<>();
			List<SolveInfo> solutions = engine.findAll(artifact.getState().getMentalState(), OBL);
			for (SolveInfo solution : solutions) {
				if (solution.isSuccess()) {
					try {
						String agent = solution.getVarValue("A").toString();
						String role = solution.getVarValue("R").toString();
						Term objective = solution.getVarValue("O");
						Term deadline = solution.getVarValue("D");
						String value = agent + "[" + role + "] : " + TermFormatter.toString(objective) + " < " + TermFormatter.toString(deadline);
						values.add(anon.matcher(value).replaceAll("_"));
					} catch (NoSolutionException ex) {
						// ignore because of isSuccess()
					}
				}
			}
			DefaultListModel obligationModel = new DefaultListModel();
			Collections.sort(values);
			for (String v : values) {
				obligationModel.addElement(v);
			}
			oblList.setModel(obligationModel);
		}
		
		private void updateViolations() {
			List<String> values = new ArrayList<>();
			List<SolveInfo> solutions = engine.findAll(artifact.getState().getMentalState(), VIOL);
			for (SolveInfo solution : solutions) {
				if (solution.isSuccess()) {
					try {
						String agent = solution.getVarValue("A").toString();
						String role = solution.getVarValue("R").toString();
						Term objective = solution.getVarValue("O");
						String value = agent + "[" + role + "] : " + TermFormatter.toString(objective);
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
