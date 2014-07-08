/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language.generic;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;
import aorta.kr.language.OrganizationalLanguage;

/**
 *
 * @author asj
 */
public class GenericOrganizationalLanguage extends OrganizationalLanguage {

    @Override
    public Term getRole(Term role) {
        return new Struct("role", role, new Var("Os"));
    }

    @Override
    public Term getConflict(Term role1, Term role2) {
        return new Struct("restriction", role1, role2);
    }

    @Override
    public Term getObjective(Term objective) {
		return new Struct("objective", objective);
    }

    @Override
    public Term getResponsibility() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Term getNorm(Term norm, Term role) {
        return new Struct("norm", norm, role);
    }

    @Override
    public Term getDependency(Term role1, Term role2, Term obj) {
        return new Struct("dependency", role1, role2, obj);
    }

    @Override
    public Term getOrder(Term obj1, Term obj2) {
        return new Struct("order", obj1, obj2);
    }

    @Override
    public Term getEnactment(Term agent, Term role) {
        return new Struct("rea", agent, role);
    }

    @Override
    public boolean inLanguage(Term term) {
        return term.match(getRole(new Var()))
                || term.match(getObjective(new Var()))
                || term.match(getConflict(new Var(), new Var()))
                //                    || term.match(getResponsibility())
                || term.match(getNorm(new Var(), new Var()))
                || term.match(getDependency(new Var(), new Var(), new Var()))
				|| term.match(getOrder(new Var(), new Var()))
                || term.match(getEnactment(new Var(), new Var()));
    }
}
