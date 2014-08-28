/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.parser.helper;

import alice.tuprolog.InvalidLibraryException;
import alice.tuprolog.InvalidTheoryException;
import aorta.AORTAException;
import aorta.Aorta;
import aorta.AortaAgent;
import aorta.AortaBridge;
import aorta.kr.language.OrganizationImportException;
import aorta.parser.AORTALexer;
import aorta.parser.AORTAParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import aorta.logging.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @author ascje
 */
public class AortaBuilder {

	private static final Logger logger = Logger.getLogger(AortaBuilder.class.getName());
	
	private AORTALexer lexer;
	private AORTAParser parser;

	public AortaBuilder() {
		this.parser = new AORTAParser(null);
		this.lexer = new AORTALexer(null);
	}

	public AortaAgent parseAgent(String agentName, String file, Aorta aorta, AortaBridge bridge) throws InvalidTheoryException, IOException, InvalidLibraryException, OrganizationImportException, AORTAException {
		Reader r = null;
		if (new File(file).exists()) {
			r = new InputStreamReader(new FileInputStream(file), "UTF-8");			
		} else {
			InputStream resource = getClass().getResourceAsStream("/" + file);
			if (resource != null) {
				r = new InputStreamReader(resource);
			}
		}
		if (r == null) {
			logger.log(Level.WARNING, "No AORTA file exists at location: " + file);
			return null;
		}

		ANTLRInputStream input = new ANTLRInputStream(r);
		lexer.setInputStream(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.fill();
		
		parser.setTokenStream(tokens);
		parser.setTrace(false);

		AORTAParser.AortaAgentContext aortaAgentContext = parser.aortaAgent(agentName);
		aortaAgentContext.agent.setBridge(bridge);
		aortaAgentContext.agent.setOrganizationPath(aorta.getOrganizationLocation());
		
		return aortaAgentContext.agent.build(file);
	}
}
