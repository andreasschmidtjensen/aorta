/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.modelchecker;

import ail.mas.AIL;
import ajpf.ModelLoader;
import ajpf.product.MCAPLmodel;
import ajpf.util.AJPFLogger;
import gov.nasa.jpf.Config;
import gov.nasa.jpf.util.JPFSiteUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author asj
 */
public class Modelchecker {

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.out.println("Usage: Modelchecker AIL-File [PSL-File] [-save] [p=Property] [m=Model-file]");
			System.exit(1);
		}

		Modelchecker runner;
		if (args.length == 1) {
			runner = new Modelchecker(args[0]);
		} else {
			runner = new Modelchecker(args);
		}
		runner.run();
	}

	private String ailFile;
	private String pslFile;
	private String propertyKey;
	private MCAPLmodel model;
	private String modelFile;
	private Map<String, String> properties;
	private boolean save;
	
	private final String memory = "-Xmx1000m";

	public Modelchecker(String ailFile) {
		this.ailFile = ailFile;
	}
	
//TODO: Specify propostiions to be saved when -save is provided
	public Modelchecker(String[] args) throws Exception {
		ailFile = args[0].replace("\\", "\\\\");
		pslFile = args[1].replace("\\", "\\\\");

		if (args.length > 2) {
			for (int i = 2; i < args.length; i++) {
				String arg = args[i];
				if (arg.startsWith("p=")) {
					propertyKey = arg.substring(2);
				} else if (arg.startsWith("m=")) {
					modelFile = arg.substring(2);
				} else if (arg.equals("-save")) {
					save = true;
				}
			}
		}
		
		if (!save && modelFile != null) {
			model = ModelLoader.loadModelFromFile(modelFile);
			model.setConfig(new Config(new String[0]));
		}

		properties = new LinkedHashMap<>();

		BufferedReader in = new BufferedReader(new FileReader(pslFile));
		System.out.println("Properties: ");
		String str;
		while ((str = in.readLine()) != null) {
			if (!str.startsWith("#") && !str.trim().isEmpty()) {
				String[] pair = str.split(":");
				properties.put(pair[0].trim(), pair[1].trim());
				System.out.println(" > " + str);
			}
		}
	}

	public void run() throws Exception {
		if (pslFile == null) {
			AJPFLogger.setLevel("ail.semantics.AILAgent", Level.WARNING);
			AIL.runAIL(ailFile);
		} else if (save) {
			saveModel();
		} else if (propertyKey != null) {
			if (properties.containsKey(propertyKey)) {

				File outDir = new File("out/");
				if (!outDir.exists()) {
					outDir.mkdir();
				}

				verifyProperty(propertyKey);
			} else {
				throw new RuntimeException("Property '" + propertyKey + "' not found!");
			}
		} else {
			File outDir = new File("out/");
			if (!outDir.exists()) {
				outDir.mkdir();
			}
			for (String propertyKey : properties.keySet()) {
				verifyProperty(propertyKey);
			}
		}
	}

	private void verifyProperty(String propertyKey) throws IOException, ClassNotFoundException {
		long ms = System.currentTimeMillis();
		if (model != null) {
			checkInExistingModel(propertyKey, model);
		} else {
			runJPF(propertyKey);
		}
		System.out.println("*** Time elapsed for " + propertyKey + ": " + (System.currentTimeMillis() - ms) + "ms");
	}

	private void checkInExistingModel(String propertyKey, MCAPLmodel model) {
		String property = properties.get(propertyKey);

		System.out.print("Verifying " + property + ": ");
		
		ExistingModelChecker mc = new ExistingModelChecker(model, property);
		mc.start();

		if (mc.getAcceptingPath().isEmpty()) {
			System.out.println("no errors detected!");
		} else {
			System.out.println("accepting path found: " + mc.getAcceptingPath());
		}

	}

	private void saveModel() throws IOException {
		String prop = properties.get(propertyKey);
		if (propertyKey == null) {
			throw new RuntimeException("Property not specified, use p=Propertykey to specify.");
		}
		if (prop == null) {
			throw new RuntimeException("Property '" + propertyKey + "' not found!");
		}
		String jpfFile = createJPFFile(propertyKey);

		System.out.println("Saving model to " + modelFile);

		String jarLocation = JPFSiteUtils.getSiteCoreDir().getAbsolutePath() + "/build/runJPF.jar";
		System.out.println("Executing java " + memory + " -jar " + jarLocation + " +shell.port=4242 " + jpfFile + " in " + System.getProperty("user.dir"));
		Process process = new ProcessBuilder("java", memory, "-jar", jarLocation, "+shell.port=4242", jpfFile).start();

		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;

		boolean print = true;
		while ((line = br.readLine()) != null) {
			if (print) {
				System.out.println(line);
			}
		}
	}
	
	private void runJPF(String propertyKey) throws IOException {
		String prop = properties.get(propertyKey);
		String jpfFile = createJPFFile(propertyKey);

		File outputFile = new File("out/" + propertyKey + ".log");
		try (FileWriter writer = new FileWriter(outputFile)) {
			System.out.println("Verifying property: " + prop);

			String jarLocation = JPFSiteUtils.getSiteCoreDir().getAbsolutePath() + "/build/runJPF.jar";
			System.out.println("Executing java " + memory + " -jar " + jarLocation + " +shell.port=4242 " + jpfFile + " in " + System.getProperty("user.dir"));
			writer.write("To reproduce:\n ");
			writer.write(System.getProperty("user.dir") + "> java " + memory + " -jar " + jarLocation + " +shell.port=4242 " + jpfFile + "\n");
			Process process = new ProcessBuilder("java", memory, "-jar", jarLocation, "+shell.port=4242", jpfFile).start();

			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

			boolean print = true;
			while ((line = br.readLine()) != null) {
				print = !line.trim().startsWith("# exception: ");
				if (print) {
					writer.write(line + "\n");
					writer.flush();
					System.out.println(line);
				}
			}
		}
	}

	private String createJPFFile(String propertyKey) throws IOException {
		StringBuilder contents = new StringBuilder();
		contents.append("@using = mcapl\n");
		contents.append("@using = aortamc\n");
		contents.append("target = ail.util.AJPF_w_AIL\n");
		contents.append("target.args = ").append(ailFile).append(",").append(pslFile).append(",").append(propertyKey).append("\n");
		contents.append("log.info=ajpf.MCAPLAgent\n");
		contents.append("log.fine=ail.mas.DefaultEnvironment,ajpf.product.Product,aorta.ail.AortaAILAgent\n");
		contents.append("log.finest=ajpf.psl.buchi.BuchiAutomaton,ajpf.product.MCAPLmodel\n");
		contents.append("listener+=,listener.ExecTracker\n");
		contents.append("et.print_insn=false\n");
		if (save) {
			contents.append("ajpf.model_only=true\n");
			contents.append("ajpf.model.file=").append(modelFile).append("\n");
		}

		File tmp = new File("modelchecker.jpf");
		try (FileWriter writer = new FileWriter(tmp)) {
			writer.write(contents.toString());
		}

		return tmp.getAbsolutePath();
	}

}
