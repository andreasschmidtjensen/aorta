/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import aorta.kr.language.model.Metamodel;
import aorta.kr.language.parser.MetamodelLexer;
import aorta.kr.language.parser.MetamodelParser;
import example.Example;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @author ascje
 */
public class OrganizationLoader {
	
	public Theory getOrganizationTheory(InputStream organizationIs) throws OrganizationImportException {
		try {
			Theory theory = new Theory(organizationIs);
			Struct list = new Struct();
			Iterator<? extends Term> it = theory.iterator(new Prolog());
			while (it.hasNext()) {
				list.append(it.next());
			}
			return new Theory(list);
		} catch (IOException ex) {
			throw new OrganizationImportException("The theory could not be loaded", ex);
		} catch (InvalidTheoryException ex) {
			throw new OrganizationImportException("The theory was invalid", ex);
		}
	}
	
	public Metamodel loadMetamodel(InputStream is) throws OrganizationImportException {
		try {
			MetamodelParser parser = new MetamodelParser(null);
			MetamodelLexer lexer = new MetamodelLexer(null);

			ANTLRInputStream input = new ANTLRInputStream(is);
			lexer.setInputStream(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			tokens.fill();

			parser.setTokenStream(tokens);
			parser.setTrace(false);

//			parser.removeErrorListeners();
			parser.setErrorHandler(new BailErrorStrategy());

			MetamodelParser.MetamodelContext modelContext = parser.metamodel();
			Metamodel mm = modelContext.mm;
			
			return mm;
		} catch (Exception ex) {
			throw new OrganizationImportException("Could not load metamodel", ex);
		}
	}
	
}
