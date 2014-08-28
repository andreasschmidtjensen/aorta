/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta;

import aorta.jeditplugin.Installer;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Runner {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: java -jar AORTA.jar command");
			System.out.println("Available commands:");
			System.out.println("> install [location]: Installs AORTA in the Jason IDE.");
			System.exit(0);
		}
		
		String command = args[0];
		switch (command) {
			case "install":
				Installer installer = new Installer();
				installer.install(args);
				break;
		}
	}
	
}
