/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language;

import alice.tuprolog.Term;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author asj
 */
public class MetaLanguageTest {
	
	@Test
	public void testInML() {
		MetaLanguage ml = new MetaLanguage();
		Assert.assertTrue(ml.inML(ml.condition(Term.TRUE, Term.TRUE, Term.TRUE, Term.TRUE)));
		Assert.assertTrue(ml.inML(ml.dependency(Term.TRUE, Term.TRUE, Term.TRUE)));
		Assert.assertTrue(ml.inML(ml.message(Term.TRUE, Term.TRUE)));
		Assert.assertTrue(ml.inML(ml.objective(Term.TRUE, Term.TRUE)));
		Assert.assertTrue(ml.inML(ml.obligation(Term.TRUE, Term.TRUE, Term.TRUE, Term.TRUE)));
		Assert.assertTrue(ml.inML(ml.rea(Term.TRUE, Term.TRUE)));
		Assert.assertTrue(ml.inML(ml.role(Term.TRUE, Term.TRUE)));
		Assert.assertTrue(ml.inML(ml.send(Term.TRUE, Term.TRUE, Term.TRUE)));
		Assert.assertTrue(ml.inML(ml.sent(Term.TRUE, Term.TRUE)));
		Assert.assertTrue(ml.inML(ml.violation(Term.TRUE, Term.TRUE, Term.TRUE)));
		
		Assert.assertFalse(ml.inML(Term.createTerm("test(a,b,c)")));
		Assert.assertFalse(ml.inML(Term.createTerm("rea(a,b,c)")));
		Assert.assertFalse(ml.inML(Term.createTerm("violation(a,b,c)")));
	}
	
}
