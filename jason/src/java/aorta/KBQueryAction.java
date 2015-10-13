/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import aorta.jason.AortaJasonAgent;
import aorta.jason.TermConverter;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.util.FormulaQualifier;
import jason.JasonException;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Literal;
import jason.asSyntax.Term;

/**
 *
 * @author asj
 */
public abstract class KBQueryAction extends DefaultInternalAction {
	
	private KBType kbType;

	public KBQueryAction(KBType kbType) {
		this.kbType = kbType;
	}
	
    @Override protected void checkArguments(Term[] args) throws JasonException {
        super.checkArguments(args); // check number of arguments
        if (! (args[0] instanceof Literal))
            throw JasonException.createWrongArgument(this,"first argument must be a term");
    }
	
	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {	
		checkArguments(args);

        Literal term = (Literal)args[0];
		alice.tuprolog.Term aortaTerm = TermConverter.convertToTerm(args[0]);
	
		if (!(ts.getAg() instanceof AortaJasonAgent)) {
			throw JasonException.createWrongArgument(this, "Can only be used by AORTA agents");
		}
		
		AortaJasonAgent ag = (AortaJasonAgent) ts.getAg();
		AortaAgent aorta = ag.getAortaAgent();
		MentalState ms = aorta.getState().getMentalState();
		alice.tuprolog.Term qualified = FormulaQualifier.qualifyTerm(aortaTerm, kbType, true);
		
		SolveInfo info = ms.solve(qualified);
		if (info.isSuccess()) {
			qualified = ms.unify(qualified, info);
			aortaTerm = ((Struct) qualified).getArg(0);
			
			return un.unifies(args[0], TermConverter.toLiteral(aortaTerm));
		} else {
			return false;
		}
	}
	
	
}
