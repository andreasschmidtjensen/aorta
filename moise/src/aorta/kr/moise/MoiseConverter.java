/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.moise;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import aorta.kr.language.model.Dependency;
import aorta.kr.language.model.Metamodel;
import aorta.kr.language.model.Norm;
import aorta.kr.language.model.Objective;
import aorta.kr.language.model.Role;
import aorta.kr.language.model.Rule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import moise.os.OS;
import moise.os.fs.FS;
import moise.os.fs.Goal;
import moise.os.fs.Mission;
import moise.os.fs.Plan;
import moise.os.fs.Scheme;
import moise.os.ns.NS;
import moise.os.ss.Group;
import moise.os.ss.Link;
import moise.os.ss.SS;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class MoiseConverter {
	
	private Map<Mission, List<String>> missionRoles;
	
	public Theory getAortaTheory(String uri) throws MoiseImportException {
		try {
			return getAortaMetamodel(uri).createTheory();
		} catch (InvalidTheoryException ex) {
			throw new MoiseImportException(ex);
		}
	}
	
	public Metamodel getAortaMetamodel(String uri) throws MoiseImportException {
		OS model = MoiseLoader.getModel(uri);
		
		return convertToMetamodel(model);
	}

	private Metamodel convertToMetamodel(OS model) throws MoiseImportException {
		Metamodel mm = new Metamodel();
		SS ss = model.getSS();
		FS fs = model.getFS();
		
		missionRoles = new HashMap<>();
		
		for (moise.os.ss.Role role : ss.getRolesDef()) {
			if (!role.isAbstract()) {
				String name = role.getId();
				List<Term> objectives = new ArrayList<>();
				
				for (moise.os.ns.Norm norm : role.getDeonticRelations()) {
					Mission mission = norm.getMission();
					
					if (!missionRoles.containsKey(mission)) {
						missionRoles.put(mission, new ArrayList<>());
					}
					missionRoles.get(mission).add(name);
					
					List<Term> normObjectives = new ArrayList<>();
					
					for (Goal goal : mission.getGoals()) {
						Struct objective = convertGoalToObjective(goal);
						normObjectives.add(objective);
					}
					
					if (norm.getType() == NS.OpTypes.obligation) {
						if (normObjectives.size() > 1) {
							// only add as norm if more than one objective -> otherwise the objective will be added as a norm later (and might contradict this)
							
							Term o = ConversionUtils.combine(",", normObjectives);
							Term d = Term.FALSE;
							if (norm.getTimeConstraint() != null) {
								d = new Struct(norm.getTimeConstraint().getTC());
							}
							Norm n = new Norm(name, Norm.OBLIGATION, o, d, Term.TRUE);
							mm.getNorms().add(n);
						}
					}
					
					objectives.addAll(normObjectives);
				}
				
				mm.getRoles().add(new Role(name, objectives));
			}
		}
		
		for (Scheme scheme : fs.getSchemes()) {
			for (Goal goal : scheme.getGoals()) {
				List<Term> subObjectives = new ArrayList<>();
				Struct objective = convertGoalToObjective(goal);
								
				// operator in condition is disjunct if "choice", conjunct otherwise
				String op = ",";
				if (goal.hasPlan()) {
					op = goal.getPlan().getOp() == Plan.PlanOpType.choice ? ";" : ",";
					for (Goal subGoal : goal.getPlan().getSubGoals()) {
						subObjectives.add(convertGoalToObjective(subGoal));
					}
				}
				
				// OBJECTIVE
				if (mm.getObjective(objective) != null) {
					Objective obj = mm.getObjective(objective);
					obj.getSubObjectives().removeAll(subObjectives);
					obj.getSubObjectives().addAll(subObjectives);
				} else {
					mm.getObjectives().add(new Objective(objective, subObjectives));
				}
				
				// NORM
				Term deadlineTTF = Term.FALSE;
				if (!goal.getTTF().isEmpty()) {
					deadlineTTF = new Struct(goal.getTTF());
				}
				Term deadlineObj = null;
				
				if (goal.getInPlan() != null) {
					Plan plan = goal.getInPlan();
					deadlineObj = convertGoalToObjective(plan.getTargetGoal());
				}
				
				Term deadline = deadlineTTF;
				if (deadlineObj != null) {
					if (deadlineTTF == Term.FALSE) {
						deadline = deadlineObj;
					} else {
						deadline = new Struct(",", deadline, deadlineObj);
					}
				}
				
				List<Term> preCond = new ArrayList<>();
				for (Goal g : goal.getPreConditionGoals()) {
					preCond.add(convertGoalToObjective(g));
				}
				
				Term condition = Term.TRUE;
				if (!preCond.isEmpty()) {
					condition = ConversionUtils.combine(op, preCond);
				}
				
				for (Mission mission : scheme.getMissions()) {
					if (mission.getGoals().contains(goal) && missionRoles.containsKey(mission)) {
						for (String role : missionRoles.get(mission)) {
							Norm n = new Norm(role, Norm.OBLIGATION, objective, deadline,
										condition);
							mm.getNorms().add(n);
						}
					}
				}
				
				// no missions: create RULE
				boolean inMission = false;
				for (Mission m : fs.getAllMissions()) {
					if (m.getGoals().contains(goal)) {
						inMission = true;
						break;
					}
				}
				if (!inMission && goal.hasPlan()) {
					op = goal.getPlan().getOp() == Plan.PlanOpType.choice ? ";" : ",";
					List<Term> sub = new ArrayList<>();
					for (Goal subGoal : goal.getPlan().getSubGoals()) {
						sub.add(convertGoalToObjective(subGoal));
					}
					mm.getRules().add(new Rule(convertGoalToObjective(goal), ConversionUtils.combine(op, sub)));
				}
			}
		}
		for (Group group : ss.getRootGrSpec().getAllSubGroupsTree()) {
			for (Link link : group.getLinks()) {
				if (link.getTypeStr().equals("authority")) {
					List<moise.os.ss.Role> dependants = getNonAbstractRoles(link.getTarget());
					List<moise.os.ss.Role> dependees = getNonAbstractRoles(link.getSource());

					for (moise.os.ss.Role dependant : dependants) {
						for (moise.os.ss.Role dependee : dependees) {
							for (Term objective : mm.getRole(dependant.getId()).getObjectives()) {
								mm.getDependencies()
										.add(new Dependency(dependant.getId(), dependee.getId(), objective));
							}							
						}
					}
				}
			}
		}
		
		
		return mm;
	}

	private Struct convertGoalToObjective(Goal goal) {
		Struct objective;
		String id = goal.getId();
		boolean negated = id.startsWith("neg_");
		if (negated) {
			id = id.substring(4);
		}
		
		if (goal.hasArguments()) {
			int i = 0;
			Term[] args = new Term[goal.getArguments().size()];
			for (Object o : goal.getArguments().values()) {
				args[i++] = Term.createTerm(o.toString());
			}
			objective = new Struct(id, args);
		} else {
			objective = new Struct(id);
		}
		if (negated) {
			return new Struct("\\+", objective);
		} else {
			return objective;
		}
	}

	private List<moise.os.ss.Role> getNonAbstractRoles(moise.os.ss.Role role) {
		List<moise.os.ss.Role> roles = new ArrayList<>();
		if (!role.isAbstract()) {
			roles.add(role);
		}
		for (moise.os.ss.Role subRole : role.getSubRoles()) {
			roles.addAll(getNonAbstractRoles(subRole));
		}
		
		return roles;
	}
}
