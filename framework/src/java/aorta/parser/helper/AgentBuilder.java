/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.parser.helper;

import alice.tuprolog.InvalidLibraryException;
import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Theory;
import aorta.AORTAException;
import aorta.AortaAgent;
import aorta.AortaBridge;
import aorta.kr.KBType;
import aorta.kr.MentalState;
import aorta.kr.PrologLoader;
import aorta.kr.language.OrganizationImportException;
import aorta.kr.language.OrganizationLoader;
import aorta.kr.language.model.Metamodel;
import aorta.parser.AORTALexer;
import aorta.parser.AORTAParser;
import aorta.ts.strategy.Linear;
import aorta.ts.strategy.Strategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import aorta.logging.Logger;
import aorta.reasoning.ReasoningRule;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @author asj
 */
public class AgentBuilder {
	
	private static final Logger logger = Logger.getLogger(AgentBuilder.class.getName());
	
	private String name;
	private Initialization init;
	private List<ReasoningRule> rules;
	private AortaBridge bridge;

	public AgentBuilder(String name, Initialization init, List<ReasoningRule> rules) {
		this.name = name;
		this.init = init;
		this.rules = rules;
	}
	
	public void setOrganizationPath(String location) {
		init.orgPath = location;
	}

	public AortaAgent build(String file) throws InvalidTheoryException, IOException, InvalidLibraryException, OrganizationImportException, AORTAException {
		PrologLoader loader = new PrologLoader();
		if (init.orgPath.length() > 0) {
			Metamodel model = Metamodel.load(init.orgPath);
			if (model == null) {
				logger.log(Level.WARNING, "No metamodel exists at location: " + init.orgPath);				
			} else {
				Theory th = model.createTheory();
				loader.addTheory(th, KBType.ORGANIZATION);
			}
		} else {
			logger.warning("No organization defined.");
		}
		
		MentalState ms = new MentalState(loader.load());

		String stratClassName = init.strategy;
		if (!stratClassName.contains(".")) {
			// add package name
			stratClassName = "aorta.ts.strategy." + stratClassName;
		}

		Strategy strategy = new Linear();
		
		AortaAgent agent = new AortaAgent(name, ms, rules, strategy);
		agent.getState().setBridge(bridge);
		return agent;
	}

	public void setBridge(AortaBridge bridge) {
		this.bridge = bridge;
	}

	private AORTAParser newParser(InputStream is) throws IOException {
		AORTAParser parser = new AORTAParser(null);
		AORTALexer lexer = new AORTALexer(null);
		
		ANTLRInputStream inputStream = new ANTLRInputStream(is);
		lexer.setInputStream(inputStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.fill();

		parser.setTokenStream(tokens);
		parser.setTrace(false);
		
		return parser;
	}
}
