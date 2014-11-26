/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.scbw;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.organization.EnvironmentSensor;
import eis.exceptions.NoEnvironmentException;
import eis.exceptions.PerceiveException;
import eis.iilang.Percept;
import eisbw.BWAPIBridge;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asj
 */
public class SensingBWAPI extends BWAPIBridge implements EnvironmentSensor {

	@Override
	public List<Struct> getEnvState() {
		List<Struct> structs = new ArrayList<>();
//		JNIBWAPI api = getGame();
		for (String entity : getEntities()) {
			try {
				List<Percept> percepts = getAllPerceptsFromEntity(entity);
				structs.addAll(convert(percepts));
			} catch (PerceiveException | NoEnvironmentException ex) {
				ex.printStackTrace();
			}
		}
		return structs;
	}

	private List<Struct> convert(List<Percept> percepts) {
		List<Struct> structs = new ArrayList<>();
		for (Percept p : percepts) {
			structs.add(convert(p));
		}
		return structs;
	}

	private Struct convert(Percept p) {
		// TODO: Conversion cannot be done so simple, e.g. unit("Terran Bunker", 1) is converted to unit(Terran Bunker, 1), i.e. w/o quotes.
		return (Struct) Term.createTerm(p.toProlog());
	}
	
}
