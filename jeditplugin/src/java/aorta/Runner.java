/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta;

import aorta.jason.infra.AortaLauncher;
import aorta.jeditplugin.Installer;
import java.util.Arrays;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Runner {
	
	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.out.println("Usage: java -jar AORTA.jar command");
			System.out.println("Available commands:");
			System.out.println("> install [location]: Installs AORTA in the Jason IDE.");
			System.out.println("> run [Jason MAS file]: Executes the specified MAS in AORTA+Jason.");
			System.exit(0);
		}
		
		String command = args[0];
		switch (command) {
			case "install":
				Installer installer = new Installer();
				installer.install(args);
				break;
			case "run":
				String[] args2 = Arrays.copyOfRange(args, 1, args.length);
				AortaLauncher.main(args2);
				break;
		}
	}
	
}
