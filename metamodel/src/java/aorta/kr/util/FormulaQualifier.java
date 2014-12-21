package aorta.kr.util;

import java.util.Iterator;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Parser;
import alice.tuprolog.Prolog;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import alice.tuprolog.Var;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.reasoning.fml.CapabilityFormula;
import aorta.reasoning.fml.ConjunctFormula;
import aorta.reasoning.fml.Formula;
import aorta.reasoning.fml.NegatedFormula;
import aorta.reasoning.fml.ReasoningFormula;
import aorta.reasoning.fml.TrueFormula;

public class FormulaQualifier extends TermQualifier {
	
	public static Theory qualifyTheory(Prolog prolog, KBType type, Theory theory)
			throws InvalidTheoryException {
		Iterator<? extends Term> it = theory.iterator(prolog);
		Struct clauseList = new Struct();
		while (it.hasNext()) {
			Term t = it.next();
			Term q = TermQualifier.qualifyTerm(t, type.getType());
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
		} else if (fml instanceof CapabilityFormula) {
			result = ((CapabilityFormula) fml).toString();
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
			if (TermQualifier.isReserved(query)) {
				// qualify arguments:
				Struct queryAsList = TermQualifier.toList((Struct) query);
				Struct arguments = queryAsList.listTail();
				Struct qualifiedArguments = (Struct) TermQualifier.qualifyTerm(arguments, reasoningType, qualifyUnboundVars);
				Struct rebuiltList = new Struct();
				rebuiltList.append(new Struct(queryFunctor));
				Iterator<? extends Term> it = qualifiedArguments.listIterator();
				while (it.hasNext()) {
					rebuiltList.append(it.next());
				}
				result = TermQualifier.fromList(rebuiltList).toString();
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
					newList.append(TermQualifier.qualifyTerm(t, reasoningType, qualifyUnboundVars));
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
			result = TermQualifier.qualifyTerm(term, ((ReasoningFormula) fml).getType(), qualifyUnboundVars);
		} else {
			throw new IllegalArgumentException("Formula '" + fml
					+ "' is not supported (yet).");
		}
		return result;
	}

    public static Struct qualifyStruct(Struct query, KBType reasoningType) {
        return (Struct) qualifyTerm((Term) query, reasoningType);
    }
	
    public static Term qualifyTerm(Term query, KBType reasoningType) {
        return TermQualifier.qualifyTerm(query, reasoningType.getType());
    }
    
	public static Term qualifyTerm(Term query, KBType reasoningType, boolean qualifyUnboundVars) {
		return TermQualifier.qualifyTerm(query, reasoningType.getType(), qualifyUnboundVars);
	}
	

	private static String qualifyElementStr(String reasoningType, Term query) {
		String result;
		result = reasoningType + "(" + query + ")";
		return result;
	}

	public static KBType getQualifier(Struct term) {
		for (Term r : TermQualifier.QUALIFIERS) {
			if (r.match(term)) {
				return KBType.get(((Struct)r).getName());
			}
		}
		return null;
	}
	public static Term getQualified(Struct term) {
		if (TermQualifier.isQualified(term)) {
			if (term.getArg(0) == null) 
				System.out.println("arg0 for " + term + " is " + term.getArg(0));
			return term.getArg(0);
		}
		return null;
	}
	
}
