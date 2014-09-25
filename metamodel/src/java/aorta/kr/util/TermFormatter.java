package aorta.kr.util;

import java.util.Iterator;

import alice.tuprolog.Prolog;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TermFormatter {

	public static String toString(Prolog prolog, Theory theory) {
		Iterator<? extends Term> it = theory.iterator(prolog);
		String result = "";
		while (it.hasNext()) {
			result += it.next() + "\n";
		}
		return result;
	}

	public static String sortedToString(Prolog prolog, Theory theory) {
		if (theory == null) {
			return null;
		}
		Iterator<? extends Term> it = theory.iterator(prolog);
		List<String> lines = new ArrayList<>();
		String result = "";
		while (it.hasNext()) {
			lines.add(it.next().toString());
		}
		Collections.sort(lines);
		for (String line : lines) {
			result += line + "\n";
		}
		return result;
	}
	
	public static String toString(Term term) {
		if (term instanceof Struct) {
			Struct struct = (Struct) term;
			if (struct.getName().equals(",")
					|| struct.getName().equals(";")
					|| struct.getName().equals(":-")) {
				Term conj1 = struct.getArg(0);
				Term conj2 = struct.getArg(1);
				if (struct.getName().equals(":-") && struct.getArg(1).match(new Struct("true"))) {
					return toString(conj1);
				} else {
					return toString(conj1) + struct.getName() + toString(conj2);
				}
			} else {
				String result = struct.getName();
				if (struct.getArity() > 0) {
					result += "(";
					for (int i = 0; i < struct.getArity(); i++) {
						result += toString(struct.getArg(i));
						if (i < struct.getArity() - 1) {
							result += ",";
						}
					}
					result += ")";
				}
				return result;
			}
		} else {
			return term.toString();
		}
	}

}
