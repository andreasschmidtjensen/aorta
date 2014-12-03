/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.gui;

import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public abstract class EntityState {

	protected final QueryEngine engine = new QueryEngine();
	protected final Pattern anon = Pattern.compile("_\\d+");
	
	public abstract void update();

	protected void update(MentalState ms, DefaultListModel model, Term struct, String var) {
		List<String> values = new ArrayList<>();
		List<SolveInfo> solutions = engine.findAll(ms, struct);
		for (SolveInfo solution : solutions) {
			if (solution.isSuccess()) {
				try {
					String value = solution.getVarValue(var).toString();
					values.add(anon.matcher(value).replaceAll("_"));
				} catch (NoSolutionException ex) {
					// ignore because of isSuccess()
				}
			}
		}
		Collections.sort(values);
		model.clear();
		for (String v : values) {
			model.addElement(v);
		}
	}
}
