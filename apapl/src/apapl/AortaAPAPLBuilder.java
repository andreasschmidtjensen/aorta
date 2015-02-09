/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apapl;

import alice.tuprolog.InvalidLibraryException;
import alice.tuprolog.InvalidTheoryException;
import aorta.AORTAException;
import aorta.Aorta;
import aorta.AortaAgent;
import aorta.apapl.AortaAPAPLBridge;
import aorta.apapl.AortaAPLModule;
import aorta.gui.AortaGui;
import aorta.kr.language.OrganizationImportException;
import aorta.parser.helper.AortaBuilder;
import aorta.tracer.StateListener;
import aorta.tracer.Tracer;
import apapl.data.Tuple;
import apapl.messaging.Messenger;
import apapl.parser.ParseMASException;
import apapl.parser.ParseModuleException;
import apapl.parser.ParsePrologException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author asj
 */
public class AortaAPAPLBuilder extends APAPLBuilder {

	private final AortaAPAPLParse parser = new AortaAPAPLParse();
	
	private final AortaBuilder builder = new AortaBuilder();
	
	private Aorta aorta;
	private AortaGui gui;
	
	private final Map<String,String> aortaFiles = new HashMap<>();

	public Aorta getAorta() {
		return aorta;
	}

	@Override
	public APLMAS buildMas(File masfile, Messenger msngr, Executor exctr) throws ParseMASException, ParseModuleException, ParsePrologException, LoadEnvironmentException {
		// parse the XML document
		Document doc = null;
		try {
			DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
			doc = documentbuilderfactory.newDocumentBuilder().parse(masfile);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new ParseMASException(masfile, "error parsing");
		}

		// get the masPath
		String masPath = masfile.getParentFile().getAbsolutePath() + File.separatorChar;

		// get the root
		Element root = doc.getDocumentElement();
		if (root.getNodeName().equals("apaplmas") == false) {
			throw new ParseMASException(masfile, "root-element must be apaplmas");
		}

		// parse all childs
		HashMap<String, Environment> envs = new HashMap<>();
		LinkedList<APLModule> mods = new LinkedList<>();
		for (int a = 0; a < root.getChildNodes().getLength(); a++) {

			Node child = root.getChildNodes().item(a);

			// node is an agent-specification
			if (child.getNodeName().equals("agent")) {

				// attributes = environment name and file
				String agentName = child.getAttributes().getNamedItem("name").getNodeValue();
				String aortaFile = child.getAttributes().getNamedItem("aorta").getNodeValue();
				
				aortaFiles.put(agentName, masPath + aortaFile);

			} else if (child.getNodeName().equals("aorta")) {
				try {
					String modelFile = child.getAttributes().getNamedItem("organization").getNodeValue();
					boolean useGui = child.getAttributes().getNamedItem("nogui") == null;
					Node ignoreNode = child.getAttributes().getNamedItem("notrace");
					String ignore = null;
					if (ignoreNode != null) {
						ignore = ignoreNode.getNodeValue();
					}
					Node sleepNode = child.getAttributes().getNamedItem("sleep");
					String sleep = null;
					if (sleepNode != null) {
						sleep = sleepNode.getNodeValue();
					}
										
					aorta = new Aorta(masPath + modelFile);
					msngr = new AortaMessenger(aorta);
					
					if (useGui) {
						gui = AortaGui.get();
					}
					
					if (ignore != null) {
						for (String s : ignore.split(",")) {
							StateListener.IgnoredEvents.ignore(s);
							Tracer.ignore(s);
						}
					}
					
					if (sleep != null && sleep.matches("\\d+")) {
						// TODO
					}
				} catch (AORTAException ex) {
					ex.printStackTrace();
					throw new ParseMASException(masfile, "Could not find AORTA metamodel");
				}
			}

		}

		return super.buildMas(masfile, msngr, exctr);
	}

	@Override
	public Tuple<APLModule, LinkedList<File>> buildModule(String moduleSpec, String agentName, APLMAS mas) throws ParseModuleException, ParsePrologException {
		File file = getModuleSpecificationFile(mas, moduleSpec);

		// Parse the module file
		Tuple<APLModule, LinkedList<File>> t = parser.parseFile(file);

		// set name of the newly created module
		t.l.setName(agentName);

		// Assign mas to the module
		t.l.setMas(mas);
		
		// Add AortaAgent
		AortaAPLModule aortaModule = (AortaAPLModule)t.l;

		try {
			String aortaFile = aortaFiles.get(agentName);		
			AortaAgent aortaAgent = builder.parseAgent(agentName, aortaFile, aorta.getOrganizationLocation(), new AortaAPAPLBridge(aortaModule));
			aortaModule.setAortaAgent(aortaAgent);
			
			aorta.addAgent(aortaAgent);
			
			if (gui != null) {
				gui.addAgent(aortaAgent);
			}
		} catch (AORTAException | IOException | InvalidLibraryException | InvalidTheoryException | OrganizationImportException ex) {
			ex.printStackTrace();
			throw new ParseModuleException(file, "Could not load AORTA: " + ex);
		}

		return t;
	}

	/**
	 * Determines the file that contains given module specification. By convention, the module specification will be loaded from the file
	 * named identically as the module specification followed by <code>.2apl</code> suffix. If the expected file does not yet exist, an
	 * empty one will be created.
	 *
	 * @param mas the multi-agent system this module belongs to
	 * @param moduleSpec the name of the module specification
	 * @return the file containing the module specification
	 */
	private File getModuleSpecificationFile(APLMAS mas, String moduleSpec) {
		// Support for old MAS file syntax
		if (!moduleSpec.toLowerCase().endsWith(".2apl")) {
			moduleSpec = new String(moduleSpec + ".2apl");
		}

		File file = new File(mas.getModuleSpecDir(), moduleSpec);
		try {
			file.createNewFile();
		} catch (IOException e) {
		}

		return file;
	}
}
