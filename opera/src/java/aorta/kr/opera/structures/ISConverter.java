/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.opera.structures;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.kr.language.MetaLanguage;
import aorta.kr.language.model.Metamodel;
import aorta.kr.language.model.Obligation;
import aorta.kr.opera.ConversionUtils;
import aorta.kr.opera.OperAImportException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.ictalive.operetta.OM.Arc;
import net.sf.ictalive.operetta.OM.IS;
import net.sf.ictalive.operetta.OM.Landmark;
import net.sf.ictalive.operetta.OM.Objective;
import net.sf.ictalive.operetta.OM.PartialOrder;
import net.sf.ictalive.operetta.OM.Role;
import net.sf.ictalive.operetta.OM.Scene;
import net.sf.ictalive.operetta.OM.SceneToTransitionArc;
import net.sf.ictalive.operetta.OM.Transition;
import net.sf.ictalive.operetta.OM.TransitionToSceneArc;
import static net.sf.ictalive.operetta.OM.TransitionType.AND;
import static net.sf.ictalive.operetta.OM.TransitionType.OR;

/**
 *
 * @author asj
 */
public class ISConverter {
	
	private static MetaLanguage ml = new MetaLanguage();
	
	static class ISOrdering {

		Scene scene;
		Transition transition;
		List<ISOrdering> previousArcs = new ArrayList<>();
		List<ISOrdering> nextArcs = new ArrayList<>();

		public ISOrdering(Scene scene) {
			this.scene = scene;
		}

		public ISOrdering(Transition transition) {
			this.transition = transition;
		}

		@Override
		public String toString() {
			return (scene != null ? scene.getSceneID() : transition.getTransitionID());
		}
	}

	static class LandmarkOrdering {

		Landmark landmark;
		List<LandmarkOrdering> previousLandmarks = new ArrayList<>();
		List<LandmarkOrdering> nextLandmarks = new ArrayList<>();

		public LandmarkOrdering(Landmark landmark) {
			this.landmark = landmark;
		}

		@Override
		public String toString() {
			return landmark.getName();
		}
	}

	public static void convert(IS is, Metamodel mm) throws OperAImportException {
		Map<String, ISOrdering> map = new HashMap<>();

		for (Arc a : is.getArcs()) {
			if (a instanceof SceneToTransitionArc) {
				SceneToTransitionArc arc = (SceneToTransitionArc) a;
				if (!map.containsKey(arc.getFrom().getSceneID())) {
					map.put(arc.getFrom().getSceneID(), new ISOrdering(arc.getFrom()));
				}
				if (!map.containsKey(arc.getTo().getTransitionID())) {
					map.put(arc.getTo().getTransitionID(), new ISOrdering(arc.getTo()));
				}
				map.get(arc.getFrom().getSceneID()).nextArcs.add(map.get(arc.getTo().getTransitionID()));
				map.get(arc.getTo().getTransitionID()).previousArcs.add(map.get(arc.getFrom().getSceneID()));
			} else if (a instanceof TransitionToSceneArc) {
				TransitionToSceneArc arc = (TransitionToSceneArc) a;
				if (!map.containsKey(arc.getFrom().getTransitionID())) {
					map.put(arc.getFrom().getTransitionID(), new ISOrdering(arc.getFrom()));
				}
				if (!map.containsKey(arc.getTo().getSceneID())) {
					map.put(arc.getTo().getSceneID(), new ISOrdering(arc.getTo()));
				}
				map.get(arc.getFrom().getTransitionID()).nextArcs.add(map.get(arc.getTo().getSceneID()));
				map.get(arc.getTo().getSceneID()).previousArcs.add(map.get(arc.getFrom().getTransitionID()));
			}
		}

		Map<Scene, Map<Landmark, LandmarkOrdering>> landmarkOrderings = new HashMap<>();
		for (Scene scene : is.getScenes()) {
			Map<Landmark, LandmarkOrdering> lmap = new HashMap<>();

			for (Landmark l : scene.getInteractionPattern().getLandmarks()) {
				lmap.put(l, new LandmarkOrdering(l));
			}
			
			if (!scene.getInteractionPattern().getLandmarkOrder().isEmpty()) {
				for (PartialOrder po : scene.getInteractionPattern().getLandmarkOrder()) {
					if (!lmap.containsKey(po.getFrom())) {
						lmap.put(po.getFrom(), new LandmarkOrdering(po.getFrom()));
					}
					if (!lmap.containsKey(po.getTo())) {
						lmap.put(po.getTo(), new LandmarkOrdering(po.getTo()));
					}
					lmap.get(po.getFrom()).nextLandmarks.add(lmap.get(po.getTo()));
					lmap.get(po.getTo()).previousLandmarks.add(lmap.get(po.getFrom()));
				}
			}
			landmarkOrderings.put(scene, lmap);
		}

		for (Scene scene : is.getScenes()) {
			ISOrdering ordering = map.get(scene.getSceneID());

			Struct deadline;
			Struct condition;

			for (Landmark landmark : scene.getInteractionPattern().getLandmarks()) {
				Map<Landmark, LandmarkOrdering> lmap = landmarkOrderings.get(scene);
				LandmarkOrdering lordering = lmap.get(landmark);

				if (lordering.nextLandmarks.isEmpty()) {
					deadline = getDeadlineFromNextScene(ordering, landmarkOrderings);
				} else {
					deadline = getDeadlineFromNextLandmarks(lordering);
				}

				if (lordering.previousLandmarks.isEmpty()) {
					condition = getConditionFromPreviousScene(ordering);
				} else {
					condition = getConditionFromPreviousLandmarks(lordering);
				}

				for (Objective o : landmark.getEntails()) {
					Struct objective = ConversionUtils.stateDescriptionToStruct(o.getStateDescription());
					for (Role r : o.getUsedByRole()) {
						String name = r.getName();
						name = name.substring(0, 1).toLowerCase() + name.substring(1);
						if (r.getObjectives().contains(o)) {
							mm.getObligations().add(new Obligation(name, objective, deadline, condition));
						}
					}
				}
			}
		}
	}

	private static Struct getConditionFromPreviousLandmarks(LandmarkOrdering ordering) throws OperAImportException {
		List<Struct> structs = new ArrayList<>();
		for (LandmarkOrdering lo : ordering.previousLandmarks) {
			structs.add(ConversionUtils.stateDescriptionToStruct(lo.landmark.getStateDescription()));
		}

		return ConversionUtils.combine(",", structs);
	}

	private static Struct getDeadlineFromNextLandmarks(LandmarkOrdering ordering) throws OperAImportException {
		List<Struct> structs = new ArrayList<>();
		for (LandmarkOrdering lo : ordering.nextLandmarks) {
			structs.add(ConversionUtils.stateDescriptionToStruct(lo.landmark.getStateDescription()));
		}

		return ConversionUtils.combine(";", structs);
	}

	private static Struct getConditionFromPreviousScene(ISOrdering ordering) throws OperAImportException {
		Struct result;
		if (ordering.previousArcs.isEmpty()) {
			result = (Struct) Term.TRUE;
		} else {
			// A scene always has at most one object before (a transition)
			ISOrdering transitionBefore = ordering.previousArcs.get(0);
			Transition transition = transitionBefore.transition;

			String op;
			switch (transition.getTransitionType()) {
				case AND:
					op = ",";
					break;
				case OR:
					op = ";";
					break;
				//TODO case XOR:
				default:
					throw new RuntimeException(transition.getTransitionType() + " not supported.");
			}

			Struct outerFirst = null;
			Struct outerSecond = null;
			for (int i = 0; i < transitionBefore.previousArcs.size(); i++) {
				ISOrdering sceneBefore = transitionBefore.previousArcs.get(i);
				Scene scene = sceneBefore.scene;

				if (scene.getResults().isEmpty()) {
					outerFirst = (Struct) Term.TRUE;
				} else {
					// The condition of the first objective of a scene is the results of the previous
					List<Struct> structs = new ArrayList<>();
					for (Landmark l : scene.getResults()) {
						structs.add(ConversionUtils.stateDescriptionToStruct(l.getStateDescription()));
					}
					Struct combined = ConversionUtils.combine(op, structs);

					if (outerFirst == null) {
						outerFirst = combined;
					} else {
						outerSecond = combined;
						outerFirst = new Struct(op, outerFirst, outerSecond);
					}
				}
			}

			result = outerFirst;
		}

		return result;
	}

	private static Struct getDeadlineFromNextScene(ISOrdering ordering, Map<Scene, Map<Landmark, LandmarkOrdering>> landmarkOrderings) throws OperAImportException {
		Struct result;
		if (ordering.nextArcs.isEmpty()) {
			result = (Struct) Term.FALSE;
		} else {
			// A scene always has at most one object after (a transition)
			ISOrdering transitionAfter = ordering.nextArcs.get(0);
			// Transition type does not matter, so next line is not used
			// Transition transition = transitionAfter.transition; 

			Struct outerFirst = null;
			Struct outerSecond = null;
			for (int i = 0; i < transitionAfter.nextArcs.size(); i++) {
				ISOrdering sceneAfter = transitionAfter.nextArcs.get(i);
				Scene scene = sceneAfter.scene;

				Map<Landmark, LandmarkOrdering> lmap = landmarkOrderings.get(scene);
				List<Landmark> firstLandmarks = new ArrayList<>();
				for (LandmarkOrdering lo : lmap.values()) {
					if (lo.previousLandmarks.isEmpty()) {
						firstLandmarks.add(lo.landmark);
					}
				}

				if (firstLandmarks.isEmpty()) {
					outerFirst = (Struct) Term.FALSE;
				} else {
					List<Struct> structs = new ArrayList<>();
					for (Landmark l : firstLandmarks) {
						structs.add(ConversionUtils.stateDescriptionToStruct(l.getStateDescription()));
					}
					Struct combined = ConversionUtils.combine(";", structs);

					if (outerFirst == null) {
						outerFirst = combined;
					} else {
						outerSecond = combined;
						outerFirst = new Struct(";", outerFirst, outerSecond);
					}
				}
			}

			result = outerFirst;
		}

		return result;
	}

}
