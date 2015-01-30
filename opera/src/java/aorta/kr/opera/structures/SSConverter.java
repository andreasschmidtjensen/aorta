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
			
			String name = role.getName();
			name = name.substring(0, 1).toLowerCase() + name.substring(1);

			mm.getRoles().add(new aorta.kr.language.model.Role(name, roleObjectives));
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

			for (Objective o : dep.getObjectOfDependency()) {
				Struct objective = ConversionUtils.stateDescriptionToStruct(o.getStateDescription());
				
				String n1 = r1.getName();
				n1 = n1.substring(0, 1).toLowerCase() + n1.substring(1);
				String n2 = r2.getName();
				n2 = n2.substring(0, 1).toLowerCase() + n2.substring(1);
				
				mm.getDependencies().add(new aorta.kr.language.model.Dependency(n1, n2, ml.qualify(objective)));
			}
		}
	}
	
}
