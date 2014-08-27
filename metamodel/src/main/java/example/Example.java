/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import alice.tuprolog.Prolog;
import aorta.kr.language.model.Metamodel;
import aorta.kr.language.parser.MetamodelLexer;
import aorta.kr.language.parser.MetamodelParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @author asj
 */
public class Example {
	
	public static void main(String[] args) throws Exception {
		MetamodelParser parser = new MetamodelParser(null);
		MetamodelLexer lexer = new MetamodelLexer(null);

		ANTLRInputStream input = new ANTLRInputStream(Example.class.getResourceAsStream("ebay.mm"));
		lexer.setInputStream(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.fill();

		parser.setTokenStream(tokens);
		parser.setTrace(false);

		MetamodelParser.MetamodelContext modelContext = parser.metamodel();
		Metamodel mm = modelContext.mm;
		
		System.out.println(mm);
		System.out.println("---");
		// TODO: is
		
		Prolog prolog = new Prolog();
		prolog.addTheory(mm.createTheory());
		System.out.println(prolog.getTheory().toString().replace("\n\n", "\n"));
	}
	
}
