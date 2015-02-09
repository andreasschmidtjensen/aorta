/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.apapl;

import alice.tuprolog.Number;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import apapl.data.APLFunction;
import apapl.data.APLIdent;
import apapl.data.APLList;
import apapl.data.APLListVar;
import apapl.data.APLNum;
import apapl.data.APLVar;
import apapl.data.Literal;
import java.util.List;

/**
 *
 * @author asj
 */
public class TermConverter {

	public static Struct apl2aorta(Literal term) {
		Struct result = (Struct) convertToTerm(term);
		return result;
	}

	public static Literal aorta2apl(Term term) {
		return convertTo2APLTerm(term);
	}

	//<editor-fold defaultstate="collapsed" desc="(2APL -> AORTA) Literal -> Struct">
	public static Term convertToTerm(Literal literal) {
		apapl.data.Term term = literal.getBody();
		if (term instanceof APLNum) {
			return parseNumber((APLNum) term);
		} else if (term instanceof APLFunction) {
			return parseFunction((APLFunction) term, literal.getSign());
		} else if (term instanceof APLIdent) {
			return parseIdent((APLIdent) term, literal.getSign());
		} else if (term instanceof APLList) {
			return parseList((APLList) term);
		} else if (term instanceof APLVar) {
			return parseVar((APLVar) term);
		}
		
		throw new IllegalArgumentException("term of wrong type: " + term + " (class: " + term.getClass() + ")");
	}
	
	public static Term convertToTerm(apapl.data.Term term) {
		if (term instanceof APLNum) {
			return parseNumber((APLNum) term);
		} else if (term instanceof APLFunction) {
			return parseFunction((APLFunction) term, true);
		} else if (term instanceof APLIdent) {
			return parseIdent((APLIdent) term, true);
		} else if (term instanceof APLList) {
			return parseList((APLList) term);
		} else if (term instanceof APLVar) {
			return parseVar((APLVar) term);
		}
		
		throw new IllegalArgumentException("term of wrong type: " + term + " (class: " + term.getClass() + ")");
	}

	private static Term parseVar(APLVar var) {
		if (var.isBounded()) {
			return convertToTerm(var.getSubst());
		} else {
			return new Var(var.getName());
		}
	}

	private static Struct parseFunction(APLFunction function, boolean positive) {
		String functor = function.getName().trim();
		Struct struct = new Struct(functor, parseTerms(function.getParams()));
		if (!positive) {
			struct = new Struct("\\+", struct);
		}
		return struct;
	}

	private static Struct parseIdent(APLIdent ident, boolean positive) {
		Struct struct = new Struct(ident.getName());
		if (!positive) {
			struct = new Struct("\\+", struct);
		}
		return struct;
	}

	private static Struct parseList(APLList list) {
		Struct listStruct = new Struct();
		
		listStruct.append(convertToTerm(list.getHead()));
		parseList(list.getTail(), listStruct);

		return listStruct;
	}

	private static void parseList(APLListVar list, Struct resultList) {
		if (list instanceof APLVar) {
			APLVar var = (APLVar) list;
			resultList.append(convertToTerm(var));
		} else if (list instanceof APLList) {
			APLList l = (APLList) list;
			resultList.append(convertToTerm(l.getHead()));
			parseList(l.getTail(), resultList);
		}
	}

	private static Term[] parseTerms(List<apapl.data.Term> terms) {
		Term[] result = new Term[terms.size()];
		for (int i = 0; i < terms.size(); i++) {
			result[i] = convertToTerm(terms.get(i));
		}
		return result;
	}

	private static alice.tuprolog.Number parseNumber(APLNum number) {
		double d = number.toDouble();
		if ((d == Math.floor(d)) && !Double.isInfinite(d)) {
			return new alice.tuprolog.Int((int) d);
		} else {
			return new alice.tuprolog.Double(d);
		}
	}

	//</editor-fold>
	//<editor-fold defaultstate="collapsed" desc="(AORTA -> 2APL) Term -> Literal">
	private static Literal convertTo2APLTerm(Term term) {
		if (term instanceof Struct) {
			return parseStruct((Struct) term);
		} else if (term instanceof Var) {
			if (((Var) term).isGround()) {
				return convertTo2APLTerm(((Var) term).getTerm());
			} else {
				APLVar var = new APLVar(((Var) term).getName());
				return new Literal(var, true);
			}
		} else if (term instanceof Number) {
			APLNum num = new APLNum(((Number)term).doubleValue());
			return new Literal(num, true);
		}
		throw new IllegalArgumentException("term of wrong type: " + term);
	}

	private static Literal parseStruct(Struct struct) {
		boolean positive = !struct.getName().equals("\\+");
		if (!positive) {
			struct = (Struct) struct.getTerm(0);
		}
		if (struct.isAtomic()) {
			APLIdent ident;
			if (struct.getName().startsWith("\"") && struct.getName().endsWith("\"")) {
				ident = new APLIdent(struct.getName().substring(1, struct.getName().length() - 1));
			} else if (struct.toString().startsWith("'")) {
				ident = new APLIdent(struct.getName());
			} else {
				ident = new APLIdent(struct.getName());
			}
			return new Literal(ident, positive);
		} else if (struct.isList()) {
			apapl.data.Term[] terms = new apapl.data.Term[struct.listSize()];
			for (int i = 0; i < struct.listSize(); i++) {
				terms[i] = convertTo2APLTerm(struct.getTerm(i)).getBody();
			}
			return new Literal(new APLList(terms), true);
		} else {
			apapl.data.Term[] terms = new apapl.data.Term[struct.getArity()];
			for (int i = 0; i < struct.getArity(); i++) {
				terms[i] = convertTo2APLTerm(struct.getTerm(i)).getBody();
			}
			return new Literal(new APLFunction(struct.getName(), terms), positive);
		}
	}
	//</editor-fold>
}
