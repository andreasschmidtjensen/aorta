package aorta.kr.util;

import java.util.Iterator;
import java.util.LinkedList;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Parser;
import alice.tuprolog.Prolog;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import alice.tuprolog.Var;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.reasoning.fml.ConjunctFormula;
import aorta.reasoning.fml.Formula;
import aorta.reasoning.fml.NegatedFormula;
import aorta.reasoning.fml.ReasoningFormula;
import aorta.reasoning.fml.TrueFormula;

public class Qualifier {

	private static final Term[] qualifiers = new Term[] {
		Term.createTerm("goal(_)"),Term.createTerm("org(_)"),Term.createTerm("opt(_)"),Term.createTerm("bel(_)")
	};
	private static final Term[] reserved = new Term[] {
			qualifiers[0],qualifiers[1],qualifiers[2],qualifiers[3],
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
			Term.createTerm("_ < _") };
	
	public static Theory qualifyTheory(Prolog prolog, KBType type, Theory theory)
			throws InvalidTheoryException {
		Iterator<? extends Term> it = theory.iterator(prolog);
		Struct clauseList = new Struct();
		while (it.hasNext()) {
			Term t = it.next();
			Term q = qualifyTerm(t, type.getType());
			clauseList.append(q);
		}

		return new Theory(clauseList);
	}

	public static Term qualifyGoal(MentalState ms, Formula fml) {
		return qualifyGoal(ms, fml, false);
	}
	
	public static Term qualifyGoal(MentalState ms, Formula fml, boolean qualifyUnboundVars) {
		Parser parser = new Parser(ms.getProlog().getOperatorManager(), qualifyGoalFml(fml, qualifyUnboundVars));
		return parser.nextTerm(false);
	}
	
	public static String qualifyGoalFml(Formula fml, boolean qualifyUnboundVars) {
		String result;
		if (fml instanceof TrueFormula) {
			result = "true";
		} else if (fml instanceof NegatedFormula) {
			Formula subFml = ((NegatedFormula) fml).getFormula();
			String qualified = qualifyGoalFml(subFml, qualifyUnboundVars);
			result = "\\+ (" + qualified + ")";
		} else if (fml instanceof ConjunctFormula) {
			Formula subFml1 = ((ConjunctFormula) fml).getFml1();
			Formula subFml2 = ((ConjunctFormula) fml).getFml2();
			result = qualifyGoalFml(subFml1, qualifyUnboundVars) + "," + qualifyGoalFml(subFml2, qualifyUnboundVars);
		} else if (fml instanceof ReasoningFormula) {
			Term term = ((ReasoningFormula) fml).getFormula();
			result = qualifyGoal(term, ((ReasoningFormula) fml).getType(), qualifyUnboundVars);
		} else {
			throw new IllegalArgumentException("Formula '" + fml
					+ "' is not supported (yet).");
		}
		return result;
	}
	
	private static String qualifyGoal(Term query, String reasoningType, boolean qualifyUnboundVars) {
		String result;
		if (query instanceof Var) {
			if (query.getTerm() == query) {
				// unbound
				if (qualifyUnboundVars) {
					result = qualifyElementStr(reasoningType, query);
				} else {
					result = query.toString();
				}
			} else {
				result = qualifyGoal(query.getTerm(), reasoningType, qualifyUnboundVars);
			}
		} else if (query instanceof alice.tuprolog.Number) {
			result = query.toString();
		} else if (query instanceof Struct) {
			String queryFunctor = ((Struct) query).getName();
			if (isReserved(query)) {
				// qualify arguments:
				Struct queryAsList = toList((Struct) query);
				Struct arguments = queryAsList.listTail();
				Struct qualifiedArguments = (Struct) qualifyTerm(arguments, reasoningType, qualifyUnboundVars);
				Struct rebuiltList = new Struct();
				rebuiltList.append(new Struct(queryFunctor));
				Iterator<? extends Term> it = qualifiedArguments.listIterator();
				while (it.hasNext()) {
					rebuiltList.append(it.next());
				}
				result = fromList(rebuiltList).toString();
			} else if (queryFunctor.equals(",") || queryFunctor.equals(":-")) {
				Term conj1 = ((Struct) query).getArg(0);
				Term conj2 = ((Struct) query).getArg(1);
				result = qualifyGoal(conj1, reasoningType, qualifyUnboundVars) + queryFunctor + qualifyGoal(conj2, reasoningType, qualifyUnboundVars);
			} else if (!query.isList()) { 
				// do qualification on atoms
				result = qualifyElementStr(reasoningType, query);
			} else {
				Struct list = (Struct) query;

				Struct newList = new Struct();
				Iterator<? extends Term> it = list.listIterator();
				while (it.hasNext()) {
					Term t = it.next();
					newList.append(qualifyTerm(t, reasoningType, qualifyUnboundVars));
				}
				result = newList.toString();
			}
		} else {
			throw new IllegalArgumentException("Query '" + query
					+ "' is not supported (yet).");
		}
		return result;
	}
	
	public static Term qualifyFormula(Formula fml) {
		return qualifyFormula(fml, false);
	}
	
	public static Term qualifyFormula(Formula fml, boolean qualifyUnboundVars) {
		Term result;
		if (fml instanceof TrueFormula) {
			result = new Struct("true");
		} else if (fml instanceof NegatedFormula) {
			Formula subFml = ((NegatedFormula) fml).getFormula();
			Term qualified = qualifyFormula(subFml, qualifyUnboundVars);
			result = new Struct("\\+", qualified);
		} else if (fml instanceof ConjunctFormula) {
			Formula subFml1 = ((ConjunctFormula) fml).getFml1();
			Formula subFml2 = ((ConjunctFormula) fml).getFml2();
			result = new Struct(",", qualifyFormula(subFml1, qualifyUnboundVars), qualifyFormula(subFml2, qualifyUnboundVars));
		} else if (fml instanceof ReasoningFormula) {
			Term term = ((ReasoningFormula) fml).getFormula();
			result = qualifyTerm(term, ((ReasoningFormula) fml).getType(), qualifyUnboundVars);
		} else {
			throw new IllegalArgumentException("Formula '" + fml
					+ "' is not supported (yet).");
		}
		return result;
	}

    public static Struct qualifyStruct(Struct query, KBType reasoningType) {
        return (Struct) qualifyTerm((Term) query, reasoningType);
    }
	
    public static Struct qualifyStruct(Struct query, String reasoningType) {
        return (Struct) qualifyTerm((Term) query, reasoningType);
    }
	
    public static Term qualifyTerm(Term query, KBType reasoningType) {
        return qualifyTerm(query, reasoningType.getType());
    }
    
	public static Term qualifyTerm(Term query, String reasoningType) {
		return qualifyTerm(query, reasoningType, false);
	}
	
	public static Term qualifyTerm(Term query, KBType reasoningType, boolean qualifyUnboundVars) {
		return qualifyTerm(query, reasoningType.getType(), qualifyUnboundVars);
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

	private static Struct toList(Struct struct) {
		Struct t = new Struct();
		for (int c = ((Struct) struct).getArity() - 1; c >= 0; c--) {
			t = new Struct(((Struct) struct).getArg(c).getTerm(), t);
		}
		return new Struct(new Struct(struct.getName()), t);
	}

	private static Struct fromList(Struct struct) {
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

	private static boolean isReserved(Term term) {
		for (Term r : reserved) {
			if (term.match(r)) {
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

	private static String qualifyElementStr(String reasoningType, Term query) {
		String result;
		result = reasoningType + "(" + query + ")";
		return result;
	}

	private static Term qualifyElement(String reasoningType, Term query) {
		Term result;
		// do qualification on atoms
		result = new Struct(reasoningType, query);
		return result;
	}

	public static boolean isQualified(Struct term) {
		for (Term r : qualifiers) {
			if (r.match(term)) {
				return true;
			}
		}
		return false;
	}
	
	public static KBType getQualifier(Struct term) {
		for (Term r : qualifiers) {
			if (r.match(term)) {
				return KBType.get(((Struct)r).getName());
			}
		}
		return null;
	}
	public static Term getQualified(Struct term) {
		if (isQualified(term)) {
			if (term.getArg(0) == null) 
				System.out.println("arg0 for " + term + " is " + term.getArg(0));
			return term.getArg(0);
		}
		return null;
	}
	
}
