package aorta.kr.util;

import java.util.Iterator;

import alice.tuprolog.Prolog;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import java.util.List;

public class TermVisitor {

    private Prolog prolog;

    public TermVisitor(Prolog prolog) {
        this.prolog = prolog;
    }

    public void unify(Term term, List<Var> bindings) {
        if (term instanceof Struct) {
            Struct struct = (Struct) term;
            if (!struct.isGround()) {
                if (struct.isCompound()) {
                    for (int i = 0; i < struct.getArity(); i++) {
                        Term elem = struct.getArg(i);
                        unify(elem, bindings);
                    }
                } else if (struct.isList()) {
                    Iterator<? extends Term> it = struct.listIterator();
                    while (it.hasNext()) {
                        Term elem = it.next();
                        unify(elem, bindings);
                    }
                }
            }
        } else if (term instanceof Var) {
            Var var = (Var) term;
            if (!var.isGround() && !var.isAnonymous()) {
				Term substitution = null;
				for (Var v : bindings) {
					if (v.getOriginalName().equals(var.getOriginalName())) {
						substitution = v.getTerm();
						break;
					}
				}
				
				if (substitution != null) {
					var.unify(prolog, substitution);
				}
            }
        }
    }

    public boolean hasVariable(Term term, Var var) {
        boolean result = false;
        if (term instanceof Struct) {
            Struct struct = (Struct) term;
            if (!struct.isGround()) {
                if (struct.isCompound()) {
                    for (int i = 0; i < struct.getArity(); i++) {
                        Term elem = struct.getArg(i);
                        if (hasVariable(elem, var)) {
                            result = true;
                            break; 
                        }
                    }
                } else if (struct.isList()) {
                    Iterator<? extends Term> it = struct.listIterator();
                    while (it.hasNext()) {
                        Term elem = it.next();
                        if (hasVariable(elem, var)) {
                            result = true;
                            break;
                        }
                    }
                }
            }
        } else if (term instanceof Var) {
            result = term.match(var);
        }

        return result;
    }
}
