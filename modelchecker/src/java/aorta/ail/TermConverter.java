/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.ail;

import ail.syntax.ListTermImpl;
import ail.syntax.Literal;
import ail.syntax.NumberTermImpl;
import ail.syntax.Predicate;
import ail.syntax.PredicatewAnnotation;
import ail.syntax.Rule;
import ail.syntax.StringTermImpl;
import ail.syntax.VarTerm;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author asj
 */
public class TermConverter {

	public static Struct fromLiteral(Literal literal) {
		Struct result = (Struct) convertToTerm(literal);
		return result;
	}

	public static Literal toLiteral(Term term) {
		return (Literal) convertToAILTerm(term);
	}

	//<editor-fold defaultstate="collapsed" desc="(AIL -> AORTA) Literal -> Struct">
	public static Term convertToTerm(ail.syntax.Term term) {
		if (term instanceof Rule) {
			Rule r = (Rule) term;
			return new Struct(":-", convertToTerm(r.getHead().toTerm()), convertToTerm(r.getBody().toTerm()));
		} else if (term instanceof VarTerm) {
			if (((VarTerm)term).isGround()) {
				return convertToTerm(((VarTerm)term).getValue());
			} else {
				return parseVar((VarTerm) term);
			}
		} else if (term instanceof Literal) {
			return parseLiteral((Literal) term);
		} else if (term instanceof Predicate) {
			return parsePredicate((Predicate) term);
		} else if (term instanceof NumberTermImpl) {
			return parseNumber((NumberTermImpl) term);
		} else if (term instanceof StringTermImpl) {
			return parseString((StringTermImpl) term);
		}
		throw new IllegalArgumentException("term of wrong type: " + term + " (class: " + term.getClass() + ")");
	}

	private static Term parseVar(VarTerm vt) {
		if (vt.hasValue()) {
			return convertToTerm(vt.getValue());
		}
		return new Var(vt.getFunctor());
	}

	private static Struct parseLiteral(Literal literal) {
		if (literal.negated()) {
			return new Struct("\\+", parsePredicate(literal));
		} else {
			return parsePredicate(literal);
		}
	}

	private static Struct parsePredicate(Predicate structure) {
		String functor = structure.getFunctor().trim();
		if (functorMap.containsKey(functor)) {
			functor = functorMap.get(functor);
		}

		if (structure instanceof ListTermImpl) {
			ListIterator<ail.syntax.Term> it = ((ListTermImpl) structure).listIterator();
			Struct listStruct = new Struct();
			while (it.hasNext()) {
				listStruct.append(convertToTerm(it.next()));
			}
			return listStruct;
		} else {
			ail.syntax.Term[] terms = structure.getTermsArray();
			return new Struct(functor, parseTerms(terms));
		}
	}

	private static Term[] parseTerms(ail.syntax.Term[] termsArray) {
		Term[] result = new Term[termsArray.length];
		for (int i = 0; i < termsArray.length; i++) {
			result[i] = convertToTerm(termsArray[i]);
		}
		return result;
	}

	private static alice.tuprolog.Number parseNumber(NumberTermImpl nt) {
		double d = nt.solve();
		if ((d == Math.floor(d)) && !Double.isInfinite(d)) {
			return new alice.tuprolog.Int((int) d);
		} else {
			return new alice.tuprolog.Double(d);
		}
	}

	private static Struct parseString(StringTermImpl st) {
		return new Struct("\"" + st.getString() + "\"");
	}
	private static final Map<String, String> functorMap = new HashMap<>();

	static {
	}

	//</editor-fold>
	//<editor-fold defaultstate="collapsed" desc="(AORTA -> AIL) Term -> Literal">
	private static ail.syntax.Term convertToAILTerm(Term term) {
		if (term instanceof Struct) {
			return parseStruct((Struct) term);
		} else if (term instanceof Var) {
			Var vTerm = (Var) term;
			if (vTerm.isGround()) {
				return convertToAILTerm(vTerm.getTerm());
			} else {
				VarTerm var = new VarTerm(vTerm.getName());
				return var;
			}
		}
		throw new IllegalArgumentException("term of wrong type: " + term);
	}

	private static ail.syntax.Term convertToASTerm(Term term) {
		if (term instanceof alice.tuprolog.Number) {
			NumberTermImpl number = new NumberTermImpl(((alice.tuprolog.Number) term).doubleValue());
			return number;
		} else {
			return convertToAILTerm(term);
		}
	}

	private static ail.syntax.Term parseStruct(Struct struct) {
		boolean positive = !struct.getName().equals("\\+");
		if (struct.isAtomic()) {
			if (struct.getName().startsWith("\"") && struct.getName().endsWith("\"")) {
				return new StringTermImpl(struct.getName().substring(1, struct.getName().length() - 1));
			} else {
				return new Literal(positive, struct.getName());
			}
		} else if (struct.isList()) {
			ListTermImpl list = new ListTermImpl();
			Iterator<? extends Term> it = struct.listIterator();
			while (it.hasNext()) {
				list.cons(convertToASTerm(it.next()));
			}
			return list;
		} else {
			PredicatewAnnotation structure = new PredicatewAnnotation(struct.getName());
			for (int i = 0; i < struct.getArity(); i++) {
				structure.addTerm(convertToASTerm(struct.getArg(i)));
			}
			return new Literal(positive, structure);
		}
	}
	//</editor-fold>
}
