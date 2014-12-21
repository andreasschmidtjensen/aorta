/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.util;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class TermQualifier {
	
	public static final Term[] QUALIFIERS = new Term[] {
		Term.createTerm("goal(_)"),Term.createTerm("org(_)"),Term.createTerm("opt(_)"),Term.createTerm("bel(_)")
	};
	public static final Term[] RESERVED_TERMS = new Term[] {
			QUALIFIERS[0],QUALIFIERS[1],QUALIFIERS[2],QUALIFIERS[3],
			Term.createTerm("true"),
			Term.createTerm("fail"),
			Term.createTerm("_ \\= _"),
			Term.createTerm("\\+(_)"),
			Term.createTerm("member(_,_)"),
			Term.createTerm("append(_,_,_)"),
			Term.createTerm("findall(_,_,_)"),
			Term.createTerm("intersection(_,_,_)"),
			Term.createTerm("union(_,_,_)"),
			Term.createTerm("_ is _"),
			Term.createTerm("is(_,_)"),
			Term.createTerm("_ + _"),
			Term.createTerm("'+'(_,_)"),
			Term.createTerm("_ - _"),
			Term.createTerm("_ * _"),
			Term.createTerm("_ / _"),
			Term.createTerm("_ > _"),
			Term.createTerm("_ < _"),
			Term.createTerm("_ = _"),
			Term.createTerm("length(_,_)"),
			Term.createTerm("sort(_,_)"),
			Term.createTerm("write(_)")};
	
    public static Struct qualifyStruct(Struct query, String reasoningType) {
        return (Struct) qualifyTerm((Term) query, reasoningType);
    }
	
	public static Term qualifyTerm(Term query, String reasoningType) {
		return qualifyTerm(query, reasoningType, false);
	}
	
	public static Term qualifyTerm(Term query, String reasoningType, boolean qualifyUnboundVars) {
		if (alreadyQualified(query)) {
			return query;
		}
		Term result;
		if (query instanceof Var) {
			if (query.getTerm() == query) {
				// unbound
				if (qualifyUnboundVars) {
					result = qualifyElement(reasoningType, query);
				} else {
					result = query;
				}
			} else {
				result = qualifyTerm(query.getTerm(), reasoningType,qualifyUnboundVars);
			}
		} else if (query instanceof alice.tuprolog.Number) {
			result = query;
		} else if (query instanceof Struct) {
			String queryFunctor = ((Struct) query).getName();
			if (isReserved(query)) {
				// qualify arguments:
				Struct queryAsList = toList((Struct) query);
				Struct arguments = queryAsList.listTail();
				Struct qualifiedArguments = (Struct) qualifyStruct(arguments,
						reasoningType);
				Struct rebuiltList = new Struct();
				rebuiltList.append(new Struct(queryFunctor));
				Iterator<? extends Term> it = qualifiedArguments.listIterator();
				while (it.hasNext()) {
					rebuiltList.append(it.next());
				}
				result = fromList(rebuiltList);
			} else if (queryFunctor.equals(",") || queryFunctor.equals(":-")) {
				Term conj1 = ((Struct) query).getArg(0);
				Term conj2 = ((Struct) query).getArg(1);
				result = new Struct(queryFunctor,
						qualifyTerm(conj1, reasoningType, qualifyUnboundVars), qualifyTerm(conj2,
								reasoningType, qualifyUnboundVars));
			} else if (!query.isList()) { 
				result = qualifyElement(reasoningType, query);
			} else {
				Struct list = (Struct) query;

				Struct newList = new Struct();
				Iterator<? extends Term> it = list.listIterator();
				while (it.hasNext()) {
					Term t = it.next();
					newList.append(qualifyTerm(t, reasoningType, qualifyUnboundVars));
				}
				result = newList;
			}
		} else if (query == null) {
			return null;
		} else {
			throw new IllegalArgumentException("Query '" + query
					+ "' is not supported (yet).");
		}
		return result;
	}
	
	public static boolean isQualified(Struct term) {
		for (Term r : QUALIFIERS) {
			if (r.match(term)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean alreadyQualified(Term term) {
		if (term instanceof Struct) {
			Struct s = (Struct) term;
			return isQualified(s);
		}
		return false;
	}

	private static Term qualifyElement(String reasoningType, Term query) {
		Term result;
		// do qualification on atoms
		result = new Struct(reasoningType, query);
		return result;
	}

	public static Struct toList(Struct struct) {
		Struct t = new Struct();
		for (int c = ((Struct) struct).getArity() - 1; c >= 0; c--) {
			t = new Struct(((Struct) struct).getArg(c).getTerm(), t);
		}
		return new Struct(new Struct(struct.getName()), t);
	}

	public static Struct fromList(Struct struct) {
		Term ft = struct.getArg(0).getTerm();
		Struct at = (Struct) struct.getArg(1).getTerm();
		LinkedList<Term> al = new LinkedList<>();
		while (!at.isEmptyList()) {
			if (!at.isList()) {
				return null;
			}
			al.addLast(at.getTerm(0));
			at = (Struct) at.getTerm(1);
		}
		return new Struct(((Struct) ft).getName(), al.toArray(new Term[0]));
	}

	public static boolean isReserved(Term term) {
		for (Term r : RESERVED_TERMS) {
			if (term.match(r)) {
				return true;
			}
		}
		return false;
	}

}
