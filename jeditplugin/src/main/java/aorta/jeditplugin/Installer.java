/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jeditplugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Installer {
	
	public void install(String[] args) {
		String inputJasonLocation = null;
		if (args.length >= 2) {
			inputJasonLocation = args[1];
		}
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("****************************************");
		System.out.println("Installing AORTA for Jason");
				
		File jarFile = new File(Installer.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		
		String homeDir = System.getProperty("user.home");
		File jasonSettings = new File(homeDir + "/.jason/user.properties");
		
		if (inputJasonLocation == null) {
			System.out.print("Specify location of Jason: ");
			inputJasonLocation = scanner.nextLine();
		} else {
			System.out.println("Jason location set to: " + inputJasonLocation);
		}
		
		File jasonLocation = new File(inputJasonLocation);
		File jasonLibs = new File(jasonLocation.getAbsolutePath() + "/lib");
		File jasonJedit = new File(jasonLocation.getAbsolutePath() + "/bin/jedit");
		
		if (!jarFile.exists() || !jarFile.getName().endsWith(".jar")) {
			System.err.println("Error: Installer should be run from the provided JAR-file.");
			System.exit(1);
		}
		if (!jasonLocation.exists()) {
			System.err.println("Error: Could not find Jason in the provided location.");
			System.exit(1);
		}
		if (!jasonSettings.exists()) {
			System.err.println("Error: Could not find Jason settings (looked at: " + jasonSettings + "). Please run Jason once before continuing.");
			System.exit(1);
		}
		if (!jasonLibs.exists()) {
			System.err.println("Error: Specified Jason location did not include a library-folder (looked at: " + jasonLibs + "). Are you sure it is the right one?");
			System.exit(1);	
		}
		if (!jasonJedit.exists()) {
			System.err.println("Error: Specified Jason location did not include a jedit-folder (looked at: " + jasonJedit + "). Are you sure it is the right one?");
			System.exit(1);	
		}
		
		try {
			System.out.println("Copying AORTA into Jason...");
			FileUtils.copyFileToDirectory(jarFile, new File(jasonLibs.getAbsolutePath()));
			
			System.out.println("Adding AORTA infrastructure to Jason...");
			updateJasonSettings(jasonSettings);
		} catch (IOException ex) {
			System.err.println("Could install AORTA into Jason: ");
			ex.printStackTrace(System.err);
			System.exit(1);
		}
		try {
			System.out.println("Copying AORTA plugin into JEdit...");
			FileUtils.copyFileToDirectory(jarFile, new File(jasonJedit.getAbsolutePath() + "/jars"));
			
			copyAortaProps(jasonJedit);
		} catch (IOException ex) {
			System.err.println("Could not copy JAR to JEdit: ");
			ex.printStackTrace(System.err);
			System.exit(1);
		}
		try {
			System.out.println("Adding syntax highlight for .aorta-files in JEdit...");
			copyEditMode(jasonJedit);			
			updateCatalog(jasonJedit);		
		} catch (IOException ex) {
			System.err.println("Could not add syntax highlighting: ");
			ex.printStackTrace(System.err);
			System.exit(1);
		}
		
	}

	private void copyEditMode(File jasonJedit) throws IOException {
		InputStream aortaIs = Installer.class.getResourceAsStream("/modes/aorta.xml");
		File aortaXml = new File(jasonJedit.getAbsolutePath() + "/modes/aorta.xml");
		
		InputStream mmIs = Installer.class.getResourceAsStream("/modes/aorta_metamodel.xml");
		File mmXml = new File(jasonJedit.getAbsolutePath() + "/modes/aorta_metamodel.xml");
		
		copy(aortaIs, aortaXml);
		copy(mmIs, mmXml);
	}
	
	private void copyAortaProps(File jasonJedit) throws IOException {
		InputStream is = Installer.class.getResourceAsStream("/jedit-aorta.props");
		File location = new File(jasonJedit + "/properties/jedit-aorta.props");
		
		copy(is, location);
	}
	
	private void copy(InputStream is, File location) throws IOException {
		try (FileWriter wr = new FileWriter(location)) {
			BufferedReader rdr = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = rdr.readLine()) != null) {
				wr.write(line + "\n");
			}
		}
	}

	private void updateCatalog(File jasonJedit) throws IOException {
		String catalog = FileUtils.readFileToString(new File(jasonJedit.getAbsolutePath() + "/modes/catalog"), Charset.forName("UTF-8"));
		String aortaMode = "<MODE NAME=\"aorta\" FILE=\"aorta.xml\" FILE_NAME_GLOB=\"*.aorta\" />\n<MODE NAME=\"aorta_metamodel\" FILE=\"aorta_metamodel.xml\" FILE_NAME_GLOB=\"*.mm\" />";
		if (!catalog.contains(aortaMode)) {
			catalog = catalog.replace("</MODES>", aortaMode + "\n</MODES>");				
			try (FileWriter wr = new FileWriter(jasonJedit.getAbsolutePath() + "/modes/catalog")) {
				wr.write(catalog);
			}
		} else {
			System.out.println("JEdit catalog already contains AORTA, not adding.");
		}
	}
	
	private void updateJasonSettings(File jasonSettings) throws IOException {
		String settings = FileUtils.readFileToString(jasonSettings, Charset.forName("UTF-8"));
		String infrastructure = "infrastructure.AORTA=aorta.jason.infra.AortaFactory";
		if (!settings.contains(infrastructure)) {
			settings += "\n" + infrastructure;				
			try (FileWriter wr = new FileWriter(jasonSettings)) {
				wr.write(settings);
			}
		} else {
			System.out.println("Infrastructure already set up.");
		}
	}
	
	private void updateJEditSettings(File jeditSettings) throws IOException {
		String settings = FileUtils.readFileToString(jeditSettings, Charset.forName("UTF-8"));
		String parser = "mode.aorta.sidekick.parser=aorta\nsidekick.parser.aorta.label=aorta\nmode.aorta_metamodel.sidekick.parser=aorta_metamodel\nsidekick.parser.aorta_metamodel.label=aorta metamodel";
		if (!settings.contains(parser)) {
			settings += "\n" + parser;				
			try (FileWriter wr = new FileWriter(jeditSettings)) {
				wr.write(settings);
			}
			
		} else {
			System.out.println("Parser information already set up.");
		}
	}

}
