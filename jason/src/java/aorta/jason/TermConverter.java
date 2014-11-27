/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jason;

import alice.tuprolog.Number;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import jason.asSyntax.Atom;
import jason.asSyntax.ListTermImpl;
import jason.asSyntax.Literal;
import jason.asSyntax.LiteralImpl;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Rule;
import jason.asSyntax.StringTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.VarTerm;
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
		return (Literal) convertToJasonTerm(term);
	}

	//<editor-fold defaultstate="collapsed" desc="(Jason -> AORTA) Literal -> Struct">
	public static Term convertToTerm(jason.asSyntax.Term term) {
		if (term instanceof Rule) {
			Rule r = (Rule) term;
			return new Struct(":-", convertToTerm(r.headClone()), convertToTerm(r.getBody()));
		} else if (term instanceof VarTerm) {
			return parseVar((VarTerm) term);
		} else if (term instanceof LiteralImpl) {
			return parseLiteralImpl((LiteralImpl) term);
		} else if (term instanceof Structure) {
			return parseStructure((Structure) term);
		} else if (term instanceof Atom) {
			return parseAtom((Atom) term);
		} else if (term instanceof NumberTermImpl) {
			return parseNumber((NumberTermImpl) term);
		} else if (term instanceof StringTermImpl) {
			return parseString((StringTermImpl) term);
		}
		throw new IllegalArgumentException("term of wrong type: " + term + " (class: " + term.getClass() + ")");
	}

	private static Var parseVar(VarTerm vt) {
		return new Var(vt.getFunctor());
	}

	private static Struct parseLiteralImpl(LiteralImpl literalImpl) {
		if (literalImpl.negated()) {
			return new Struct("\\+", parseStructure(literalImpl));
		} else {
			return parseStructure(literalImpl);
		}
	}

	private static Struct parseAtom(Atom atom) {
		return new Struct(atom.getFunctor());
	}

	private static Struct parseStructure(Structure structure) {
		String functor = structure.getFunctor().trim();
		if (functorMap.containsKey(functor)) {
			functor = functorMap.get(functor);
		}
		if (functor.equals("&")) {
			functor = ",";
		}

		if (structure instanceof ListTermImpl) {
			ListIterator<jason.asSyntax.Term> it = ((ListTermImpl) structure).listIterator();
			Struct listStruct = new Struct();
			while (it.hasNext()) {
				listStruct.append(convertToTerm(it.next()));
			}
			return listStruct;
		} else {
			jason.asSyntax.Term[] terms = structure.getTermsArray();
			return new Struct(functor, parseTerms(terms));
		}
	}

	private static Term[] parseTerms(jason.asSyntax.Term[] termsArray) {
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
		return new Struct(st.getString());
	}
	private static final Map<String, String> functorMap = new HashMap<>();

	static {
		functorMap.put("~", "\\+");
		functorMap.put("&", ",");
		functorMap.put("\\==", "\\=");
		functorMap.put(".member", "member");
		functorMap.put(".findall", "findall");
	}

	//</editor-fold>
	//<editor-fold defaultstate="collapsed" desc="(AORTA -> Jason) Term -> Literal">
	private static jason.asSyntax.Term convertToJasonTerm(Term term) {
		if (term instanceof Struct) {
			return parseStruct((Struct) term);
		} else if (term instanceof Var) {
			if (((Var) term).isGround()) {
				return convertToJasonTerm(((Var) term).getTerm());
			} else {
				VarTerm var = new VarTerm(((Var) term).getName());
				return var;
			}
		} else if (term instanceof Number) {
			return new NumberTermImpl(((Number)term).doubleValue());
		}
		throw new IllegalArgumentException("term of wrong type: " + term);
	}

	private static jason.asSyntax.Term convertToASTerm(Term term) {
		if (term instanceof alice.tuprolog.Number) {
			NumberTermImpl number = new NumberTermImpl(((alice.tuprolog.Number) term).doubleValue());
			return number;
		} else {
			return convertToJasonTerm(term);
		}
	}

	private static jason.asSyntax.Term parseStruct(Struct struct) {
		boolean positive = !struct.getName().equals("\\+");
		if (struct.isAtomic()) {
			if (struct.getName().startsWith("\"") && struct.getName().endsWith("\"")) {
				return new StringTermImpl(struct.getName().substring(1, struct.getName().length() - 1));
			} else {
				return new LiteralImpl(positive, new Atom(struct.getName()));
			}
		} else if (struct.isList()) {
			ListTermImpl list = new ListTermImpl();
			Iterator<? extends Term> it = struct.listIterator();
			while (it.hasNext()) {
				list.append(convertToASTerm(it.next()));
			}
			return list;
		} else {
			Structure structure = new Structure(struct.getName());
			for (int i = 0; i < struct.getArity(); i++) {
				structure.addTerm(convertToASTerm(struct.getArg(i)));
			}
			return new LiteralImpl(positive, structure);
		}
	}
	//</editor-fold>
}
