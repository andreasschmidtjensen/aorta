/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.opera.structures;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.kr.language.MetaLanguage;
import aorta.kr.language.model.Metamodel;
import aorta.kr.opera.ConversionUtils;
import aorta.kr.opera.OperAImportException;
import java.util.ArrayList;
import java.util.List;
import net.sf.ictalive.operetta.OM.Dependency;
import net.sf.ictalive.operetta.OM.HierarchyDependency;
import net.sf.ictalive.operetta.OM.MarketDependency;
import net.sf.ictalive.operetta.OM.NetworkDependency;
import net.sf.ictalive.operetta.OM.Objective;
import net.sf.ictalive.operetta.OM.Role;
import net.sf.ictalive.operetta.OM.SS;

/**
 *
 * @author asj
 */
public class SSConverter {
	
	private static MetaLanguage ml = new MetaLanguage();
	
	public static void convert(SS ss, Metamodel mm) throws OperAImportException {
		for (Role role : ss.getRoles()) {
			List<Term> roleObjectives = ConversionUtils.convertObjectivesList(role.getObjectives());

			mm.getRoles().add(new aorta.kr.language.model.Role(role.getName(), roleObjectives));
		}

		for (Objective obj : ss.getObjectives()) {
			Term objective = ConversionUtils.stateDescriptionToStruct(obj.getStateDescription());			
			List<Term> subObjectives = ConversionUtils.convertObjectivesList(obj.getSubObjectives());
			
			mm.getObjectives().add(new aorta.kr.language.model.Objective(objective, subObjectives));
		}

		for (Dependency dep : ss.getDependencies()) {
			Role r1;
			Role r2;
			if (dep instanceof MarketDependency) {
				r1 = ((MarketDependency) dep).getDependant();
				r2 = ((MarketDependency) dep).getDependee();
			} else if (dep instanceof NetworkDependency) {
				r1 = ((NetworkDependency) dep).getDependant1();
				r2 = ((NetworkDependency) dep).getDependant2();
			} else if (dep instanceof HierarchyDependency) {
				r1 = ((HierarchyDependency) dep).getDependant();
				r2 = ((HierarchyDependency) dep).getDependee();
			} else {
				throw new OperAImportException("Invalid dependency specified: " + dep);
			}

			List<Struct> dependencies = new ArrayList<>();
			for (Objective o : dep.getObjectOfDependency()) {
				dependencies.add(ConversionUtils.stateDescriptionToStruct(o.getStateDescription()));
				
				mm.getDependencies().add(new aorta.kr.language.model.Dependency(r1.getName(), r2.getName(), ConversionUtils.stateDescriptionToStruct(o.getStateDescription())));
			}
			Struct obj = ConversionUtils.combine(",", dependencies);
			
//			mm.getDependencies().add(new aorta.kr.language.model.Dependency(r1.getName(), r2.getName(), obj));
		}
	}
	
}
