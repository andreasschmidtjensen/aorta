package aorta.kr.language;

import alice.tuprolog.Prolog;
import alice.tuprolog.Term;
import aorta.kr.util.TermVisitor;

public abstract class OrganizationalLanguage implements Language {
	
    public abstract Term getRole(Term role);

    public abstract Term getConflict(Term role1, Term role2);

    public abstract Term getObjective(Term objective);

    public abstract Term getResponsibility();

    public abstract Term getNorm(Term norm, Term role);

    public abstract Term getDependency(Term role1, Term role2, Term obj);

    public abstract Term getOrder(Term obj1, Term obj2);

    public abstract Term getEnactment(Term agent, Term role);

    @Override
    public boolean isValid(Prolog prolog) {
        boolean result = true;
        
        TermVisitor tv = new TermVisitor(prolog);
//        result &= tv.hasVariable(getRole(new Var("R")), new Var("R"));
        // TODO: validate that they have the expected variables
        
        return result;
    }
}
