/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.opera;

import alice.tuprolog.Int;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import java.util.ArrayList;
import java.util.List;
import net.sf.ictalive.operetta.OM.Atom;
import net.sf.ictalive.operetta.OM.Conjunction;
import net.sf.ictalive.operetta.OM.Constant;
import net.sf.ictalive.operetta.OM.Disjunction;
import net.sf.ictalive.operetta.OM.Function;
import net.sf.ictalive.operetta.OM.Implication;
import net.sf.ictalive.operetta.OM.Landmark;
import net.sf.ictalive.operetta.OM.Negation;
import net.sf.ictalive.operetta.OM.Objective;
import net.sf.ictalive.operetta.OM.PartialStateDescription;
import net.sf.ictalive.operetta.OM.Player;
import net.sf.ictalive.operetta.OM.Variable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * @author asj
 */
public class ConversionUtils {
	
	public static Struct stateDescriptionToStruct(PartialStateDescription psd) throws OperAImportException {
		Struct result;
		if (psd instanceof Atom) {
			Atom a = (Atom)psd;
			Term[] terms = new Term[a.getArguments().size()];
			for (int i = 0; i < a.getArguments().size(); i++) {
				net.sf.ictalive.operetta.OM.Term operaTerm = a.getArguments().get(i);
				terms[i] = operaTermToTerm(operaTerm);
			}
			result = new Struct(a.getPredicate(), terms);
		} else if (psd instanceof Negation) {
			result = new Struct("\\+", stateDescriptionToStruct(((Negation)psd).getStateFormula()));
		} else if (psd instanceof Conjunction) {
			Conjunction c = (Conjunction) psd;
			result = new Struct(",", stateDescriptionToStruct(c.getLeftStateFormula()), stateDescriptionToStruct(c.getRightStateFormula()));
		} else if (psd instanceof Disjunction) {
			Disjunction d = (Disjunction) psd;
			result = new Struct(";", stateDescriptionToStruct(d.getLeftStateFormula()), stateDescriptionToStruct(d.getRightStateFormula()));			
		} else if (psd instanceof Implication) {
			Implication i = (Implication) psd;
			result = new Struct(";", new Struct("\\+", stateDescriptionToStruct(i.getAntecedentStateFormula())), stateDescriptionToStruct(i.getConsequentStateFormula()));			
		} else {
			throw new OperAImportException("PartialStateDescription " + psd + " not supported!");
		}
		
		return result;
	}

	public static Term operaTermToTerm(net.sf.ictalive.operetta.OM.Term operaTerm) throws OperAImportException {
		Term result;
		if (operaTerm instanceof Constant) {			
			Constant c = (Constant) operaTerm;
			String constantName = c.getName();
			if (constantName.matches("\\d+")) {
				result = new Int(Integer.parseInt(constantName));
			} else if (constantName.matches("\\d+(.\\d+)?")) {
				result = new alice.tuprolog.Double(Double.parseDouble(constantName));
			} else {
				result = new Struct(c.getName());
			}
		} else if (operaTerm instanceof Variable) {
			Variable v = (Variable) operaTerm;
			String vName = v.getName().substring(0, 1).toUpperCase() + v.getName().substring(1);
			result = new Var(vName);			
		} else if (operaTerm instanceof Function) {
			Function f = (Function) operaTerm;
			Term[] terms = new Term[f.getArguments().size()];
			for (int i = 0; i < f.getArguments().size(); i++) {
				net.sf.ictalive.operetta.OM.Term arg = f.getArguments().get(i);
				terms[i] = operaTermToTerm(arg);
			}
			result = new Struct(f.getName(), terms);
			
		} else {
			throw new OperAImportException("Term " + operaTerm + " not supported!");			
		}
		return result;
	}
	
	public static List<Term> convertObjectivesList(List<Objective> objectives) throws OperAImportException {
		List<Term> fmls = new ArrayList<>();
		for (Objective o : objectives) {
			fmls.add(stateDescriptionToStruct(o.getStateDescription()));
		}
		return fmls;
	}
	
	public static Struct combine(String operator, List<Struct> structs) {
		Struct first = null;
		Struct second;
		for (Struct s : structs) {
			if (first == null) {
				first = s;
			} else {
				second = s;
				first = new Struct(operator, first, second);
			}
		}
		return first;
	}
	
	public static Term[] getEListAsTermArray(EList<? extends EObject> list) {
		Term[] result = new Term[list.size()];
		for (int i = 0; i < list.size(); i++) {

			EObject obj = list.get(i);
			String representation = obj.toString();
			if (obj instanceof Objective) {
				representation = ((Objective) obj).getName();
			} else if (obj instanceof Player) {
				representation = ((Player) obj).getRole().getName();
			} else if (obj instanceof Landmark) {
				representation = ((Landmark) obj).getName();
			}
			result[i] = new Struct(representation);
		}
		return result;
	}

}
