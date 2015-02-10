/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.apapl;

import alice.tuprolog.Struct;
import aorta.AortaAgent;
import apapl.Logger;
import apapl.data.Goal;
import apapl.data.GoalCompare;
import apapl.data.Literal;
import apapl.program.Goalbase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author asj
 */
public class AortaGoalbase extends Goalbase {

	private AortaAgent aortaAgent;

	private final List<Struct> initialGoals = new ArrayList<>();

	public void setAortaAgent(AortaAgent aortaAgent) {
		this.aortaAgent = aortaAgent;

		// Initial goals from the apl file will be added 
		// before the AortaAgent has been setup, so we need 
		// to add them when it has been setup
		for (Struct s : initialGoals) {
			aortaAgent.getState().getExternalAgent().addGoal(s);
		}
	}
	
	public static Struct combine(List<Struct> structs) {
		Struct first = null;
		Struct second;
		for (Struct s : structs) {
			if (first == null) {
				first = s;
			} else {
				second = s;
				first = new Struct(",", first, second);
			}
		}
		return first;
	}
	
	private void addToAorta(Goal goal) {		
		List<Struct> list = new ArrayList<>();		
		for (Literal lit : goal.getList()) {
			list.add(TermConverter.apl2aorta(lit));
		}
		addToAorta(combine(list));
	}

	private void addToAorta(Struct struct) {
		if (aortaAgent != null) {
			aortaAgent.getState().getExternalAgent().addGoal(struct);
		} else {
			initialGoals.add(struct);
		}
	}

	private void removeFromAorta(Goal goal) {
		List<Struct> list = new ArrayList<>();		
		for (Literal lit : goal.getList()) {
			list.add(TermConverter.apl2aorta(lit));
		}
		aortaAgent.getState().getExternalAgent().removeGoal(combine(list));
	}

	public void assertFromAorta(Literal literal) {
		LinkedList<Literal> list = new LinkedList<>();
		list.add(literal);

		super.assertGoal(new Goal(list));
	}

	public void removedByAorta(Literal literal) {
		LinkedList<Literal> list = new LinkedList<>();
		list.add(literal);

		super.dropGoal(new Goal(list));
	}

	@Override
	public void assertGoal(Goal goal) {
		for (Goal g : getGoalbase()) {
			if (g.equals(goal)) {
				return;
			}
		}
		
		super.assertGoal(goal);

		addToAorta(goal);
	}

	@Override
	public void assertGoalHead(Goal goal) {		
		for (Goal g : getGoalbase()) {
			if (g.equals(goal)) {
				return;
			}
		}

		super.assertGoalHead(goal);
		
		addToAorta(goal);
	}

	@Override
	public void dropGoal(Goal goal) {
		super.dropGoal(goal);

		ArrayList<Goal> toRemove = new ArrayList<>();
		ArrayList<Literal> toDropList = new ArrayList<>();
		for (Literal l : goal) {
			toDropList.add(l.clone());
		}
		Collections.sort(toDropList, GoalCompare.INSTANCE);
		String toDrop = toDropList.toString();

		for (Goal g : getGoalbase()) {
			ArrayList<Literal> goalList = new ArrayList<>();
			for (Literal l : g) {
				goalList.add(l.clone());
			}
			Collections.sort(goalList, GoalCompare.INSTANCE);
			if (goalList.toString().equals(toDrop)) {
				toRemove.add(g);
			}
		}

		for (Goal g : toRemove) {
			removeFromAorta(goal);
		}
	}

	@Override
	public void dropSubGoals(Goal goal) {
		super.dropSubGoals(goal);

		ArrayList<Goal> toRemove = new ArrayList<>();
		for (Goal g : getGoalbase()) {
			if (isSubGoalFor(g, goal)) {
				toRemove.add(g);
			}
		}
		for (Goal g : toRemove) {
			removeFromAorta(goal);
		}
	}

	@Override
	public void dropSuperGoals(Goal goal) {
		super.dropSuperGoals(goal);

		ArrayList<Goal> toRemove = new ArrayList<>();
		for (Goal g : getGoalbase()) {
			if (isSubGoalFor(goal, g)) {
				toRemove.add(g);
			}
		}
		for (Goal g : toRemove) {
			removeFromAorta(goal);
		}
	}

	/**
	 * Check wheter a is a subgoal of b or not.
	 *
	 * @param a the possible subgoal
	 * @param b the possible supergoal
	 * @return true if a is a subgoal of b, false otherwise
	 */
	private boolean isSubGoalFor(Goal a, Goal b) {
		// TODO log this too

		ArrayList<Goal> toRemove = new ArrayList<>();
		ArrayList<Literal> dropGoal = new ArrayList<>();
		for (Literal l : a) {
			dropGoal.add(l.clone());
		}
		Collections.sort(dropGoal, GoalCompare.INSTANCE);

		ArrayList<Literal> goalList = new ArrayList<>();
		for (Literal l : b) {
			goalList.add(l.clone());
		}
		Collections.sort(goalList, GoalCompare.INSTANCE);

		int i = 0;
		int j = 0;
		while (i < goalList.size()) {
			if (j >= dropGoal.size()) {
				return true;
			} else if (dropGoal.get(j).equals(goalList.get(i))) {
				i++;
				j++;
			} else {
				i++;
			}
		}
		return (j == dropGoal.size());
	}

	@Override
	public AortaGoalbase clone() {
		AortaGoalbase gb = new AortaGoalbase();
		gb.setAortaAgent(aortaAgent);
		
		for (Goal g : getGoalbase()) {
			gb.assertGoal(g);
		}
		gb.setLogger(new Logger());
		return gb;
	}

}
