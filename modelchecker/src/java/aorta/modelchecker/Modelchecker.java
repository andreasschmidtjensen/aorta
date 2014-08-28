/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.modelchecker;

import ail.mas.AIL;
import ajpf.util.AJPFLogger;
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
			System.out.println("Usage: Modelchecker AIL-File [PSL-File]");
			System.exit(1);
		}

		Modelchecker runner;
		if (args.length == 1) {
			runner = new Modelchecker(args[0]);
		} else {
			runner = new Modelchecker(args[0], args[1]);
		}
		runner.run();
	}
	
	private String ailFile;
	private String pslFile;
	private Map<String, String> properties;

	public Modelchecker(String ailFile) {
		this.ailFile = ailFile;
	}

	public Modelchecker(String ailFile, String pslFile) throws Exception {
		this.ailFile = ailFile.replace("\\", "\\\\");
		this.pslFile = pslFile.replace("\\", "\\\\");

		properties = new LinkedHashMap<>();

		BufferedReader in = new BufferedReader(new FileReader(pslFile));
		System.out.println("Properties: ");
		String str;
		while ((str = in.readLine()) != null) {
			if (!str.startsWith("#")) {
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
		} else {
			for (String propertyKey : properties.keySet()) {
				String property = properties.get(propertyKey);
				String jpfFile = createJPFFile(propertyKey);

				System.out.println("Verifying property: " + property);

				String jarLocation = JPFSiteUtils.getSiteCoreDir().getAbsolutePath() + "/build/runJPF.jar";
				System.out.println("Executing java -jar " + jarLocation + " +shell.port=4242 " + jpfFile + " in " + System.getProperty("user.dir"));
				Process process = new ProcessBuilder("java", "-jar", jarLocation, "+shell.port=4242", jpfFile).start();

				InputStream is = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line;

				boolean print = true;
				while ((line = br.readLine()) != null) {
					if (!print && line.equals("====================================================== results")) {
						print = true;
					}

					if (print) {
						System.out.println(line);
					}

					if (print && line.startsWith("====================================================== search started:")) {
						print = false;
					}
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
		contents.append("log.info = ail.mas.DefaultEnvironment,ajpf.product.Product,ajpf.MCAPLAgent,aorta.ail.AortaAILAgent,aorta.ts.impl.Ext,aorta.reasoning.coordination.CoordinationRule\n");
		contents.append("log.finest = ajpf.psl.buchi.BuchiAutomaton,ajpf.product.MCAPLmodel\n");
		contents.append("listener+=,.listener.ExecTracker\n");
		contents.append("et.print_insn=false\n");

		File tmp = new File("modelchecker.jpf");
		try (FileWriter writer = new FileWriter(tmp)) {
			writer.write(contents.toString());
		}

		return tmp.getAbsolutePath();
	}
}
