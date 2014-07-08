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
import aorta.kr.language.OrganizationType;
import aorta.parser.AORTALexer;
import aorta.parser.AORTAParser;
import aorta.reasoning.ActionRule;
import aorta.reasoning.OptionRule;
import aorta.reasoning.Rule;
import aorta.reasoning.coordination.CoordinationRule;
import aorta.ts.strategy.Linear;
import aorta.ts.strategy.Strategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
	private List<OptionRule> optionRules;
	private List<ActionRule> actionRules;
	private List<CoordinationRule> coordinationRules;
	private AortaBridge bridge;

	public AgentBuilder(String name, Initialization init, List<OptionRule> optionRules, List<ActionRule> actionRules, List<CoordinationRule> coordinationRules) {
		this.name = name;
		this.init = init;
		this.optionRules = optionRules;
		this.actionRules = actionRules;
		this.coordinationRules = coordinationRules;
	}
	
	public void setOrganization(OrganizationType type, String location) {
		init.orgType = type;
		init.orgPath = location;
	}

	public AortaAgent build(String file) throws InvalidTheoryException, IOException, InvalidLibraryException, OrganizationImportException, AORTAException {
		PrologLoader loader = new PrologLoader();
		if (init.orgPath.length() > 0) {
			InputStream is;
			if (new File(init.orgPath).exists()) {
				is = new FileInputStream(init.orgPath);
			} else {
				is = getClass().getResourceAsStream("/" + init.orgPath);
			}
			if (is == null) {
				logger.log(Level.WARNING, "No organization file exists at location: " + init.orgPath);
			} else {
				Theory th = init.orgType.getLoader().getOrganizationTheory(is);
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

		Strategy strategy = null;
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			Class<? extends Strategy> strategyClass = null;
			try {
				logger.fine("Strategy: " + stratClassName);
				strategyClass = cl.loadClass(stratClassName).asSubclass(Strategy.class);
			} catch (ClassNotFoundException ex) {
				throw new AORTAException("Could not build agent", ex);
			}
			
			try {
				Constructor<? extends Strategy> lexerCtor = strategyClass.getConstructor();
				strategy = lexerCtor.newInstance();
			} catch (IllegalAccessException | InstantiationException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
				throw new AORTAException("Could not build agent", ex);
			}
		} catch (Exception ex) {
			logger.log(Level.WARNING, "Could not use specified strategy (" + stratClassName + "); falling back to Linear", ex);
			strategy = new Linear();
		}

		
		if (optionRules == null) {
			optionRules = getStandardOptionRules();
		}
		if (coordinationRules == null) {
			coordinationRules = getStandardCoordinationRules();
		}
		
		AortaAgent agent = new AortaAgent(name, ms, optionRules, actionRules, coordinationRules, strategy);
		agent.getState().setBridge(bridge);
		return agent;
	}

	public void setBridge(AortaBridge bridge) {
		this.bridge = bridge;
	}

	private List<CoordinationRule> getStandardCoordinationRules() throws IOException {
		InputStream is = Rule.class.getResourceAsStream("coordination.aorta");
		AORTAParser parser = newParser(is);
		
		return parser.coordinationRules().rules;
	}

	private List<OptionRule> getStandardOptionRules() throws IOException {		
		InputStream is = Rule.class.getResourceAsStream("options.aorta");
		AORTAParser parser = newParser(is);
		
		return parser.optionRules().rules;
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
