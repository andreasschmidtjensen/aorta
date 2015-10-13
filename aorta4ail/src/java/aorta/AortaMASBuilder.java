/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta;

import ail.mas.MAS;
import ail.mas.MASBuilder;
import ail.semantics.AILAgent;
import ail.syntax.Literal;
import ail.syntax.Predicate;
import ail.syntax.annotation.SourceAnnotation;
import ail.util.AILConfig;
import ajpf.MCAPLLanguageAgent;
import ajpf.MCAPLcontroller;
import ajpf.util.AJPFException;
import ajpf.util.AJPFLogger;
import aorta.parser.AortaLexer;
import aorta.parser.AortaParser;
import aorta.syntax.ast.Abstract_AortaAgent;
import aorta.syntax.ast.metamodel.Abstract_Metamodel;
import aorta.syntax.metamodel.Metamodel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mcaplantlr.runtime.ANTLRFileStream;
import mcaplantlr.runtime.CommonTokenStream;
import mcaplantlr.runtime.RecognitionException;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class AortaMASBuilder implements MASBuilder {
	
	MAS mas;
	Abstract_AortaAgent abs_agent;
	Abstract_Metamodel abs_model;
	
	@Override
	public MAS getMAS(String filename) {
		try {
			AILConfig config = new AILConfig(filename);

			String masBuilder = config.getProperty("mas.builder");
			String masFile = config.getProperty("mas.file");
			String abs_filename = null;
			try {
				abs_filename = MCAPLcontroller.getFilename(masFile);
			} catch (AJPFException e) {
				AJPFLogger.severe("ail.mas.AIL", e.getMessage());
			}
			
			buildMas(masBuilder, abs_filename);
			addAorta(config);
			
			return mas;
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			throw new RuntimeException(ex);
		}
	}

	private void buildMas(String masBuilder, String abs_filename) {
		try {
			MASBuilder masbuilder = (MASBuilder) (Class.forName(masBuilder)).newInstance();
			mas = masbuilder.getMAS(abs_filename);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			AJPFLogger.severe("ail.mas.AIL", "Could not load wrapped MAS: " + e.getMessage());
		}
	}

	private void addAorta(AILConfig config) {
		String modelFile = config.getProperty("aorta.model");
		String aortaFile = config.getProperty("aorta.rules");
		
		List<AILAgent> agents = new ArrayList<>();
		for (MCAPLLanguageAgent agent : mas.getMCAPLAgents()) {
			agents.add((AILAgent) agent);
		}
		
		List<Literal> agentLits = new ArrayList<>();
		for (AILAgent agent : agents) {
			mas.delAg(agent.getAgName());
			
			buildAgent(aortaFile);
			
			buildModel(modelFile);
			abs_agent.setModel(abs_model);
			
			AILAgent aortaAgent = abs_agent.toMCAPL(agent);
			
			mas.addAg(aortaAgent);
			
			
			Literal agLit = new Literal("agent");
			agLit.addTerm(new Predicate(agent.getAgName()));			
			agentLits.add(agLit);
		}
		
		for (AILAgent agent : agents) {
			for (Literal agLit : agentLits) {
				agent.addBel(agLit, new SourceAnnotation(new Predicate("self")));
			}
			Literal meLit = new Literal("me");
			meLit.addTerm(new Predicate(agent.getAgName()));			
			agent.addBel(meLit, new SourceAnnotation(new Predicate("self")));
		}
	}

	private void buildModel(String modelFile) {
		try {
			AortaLexer lexer = new AortaLexer(new ANTLRFileStream(modelFile));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			AortaParser parser = new AortaParser(tokens);
    		abs_model = parser.model();
     	} catch (IOException | RecognitionException e) {
     		throw new RuntimeException(e);
    	}
	}
	
	private void buildAgent(String aortaFile) {
		try {
			AortaLexer lexer = new AortaLexer(new ANTLRFileStream(aortaFile));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			AortaParser parser = new AortaParser(tokens);
    		abs_agent = parser.aortaagent();
     	} catch (IOException | RecognitionException e) {
     		throw new RuntimeException(e);
    	}
	}

	public Metamodel getModel() {
		return abs_model.toMCAPL();
	}
	
	
}
