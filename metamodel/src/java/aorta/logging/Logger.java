/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.logging;

import gov.nasa.jpf.annotation.FilterField;
import gov.nasa.jpf.vm.Verify;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;

/**
 *
 * @author asj
 */
public abstract class Logger {

	public static Logger getLogger(String name) {
		if (Verify.isRunningInJPF()) {
			return new ModelcheckingLogger(name);
		} else {
			return new JavaLogger(name);
		}
	}

	static class ModelcheckingLogger extends Logger {
		
		@FilterField
		private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");		
		@FilterField
		private String logName;		
		@FilterField
		private Level logLevel = Level.FINE;

		private ModelcheckingLogger(String logName) {
			this.logName = logName;
		}
		
		@Override
		public void setLevel(Level level) {
			this.logLevel = level;
		}

		@Override
		public Level getLevel() {
			return logLevel;
		}

		@Override
		public java.util.logging.Logger getParent() {
			return null;
		}

		@Override
		public Handler[] getHandlers() {
			return new Handler[0];
		}

		@Override
		public void log(Level level, String string) {
			if (level.intValue() >= logLevel.intValue()) {
				print(level, string);
			}
		}

		@Override
		public void log(Level level, String string, Throwable e) {
			if (level.intValue() >= logLevel.intValue()) {
				print(level, string);
				e.printStackTrace(System.out);
			}
		}

		@Override
		public void log(Level level, String string, Object... os) {
			if (level.intValue() >= logLevel.intValue()) {
				print(level, String.format(string, os));
			}
		}

		@Override
		public void removeHandler(Handler handler) {
		}

		@Override
		public void addHandler(Handler h) {
		}

		@Override
		public void fine(String string) {
			if (Level.FINE.intValue() >= logLevel.intValue()) {
				print(Level.FINE, string);
			}
		}

		@Override
		public void finer(String string) {
			if (Level.FINER.intValue() >= logLevel.intValue()) {
				print(Level.FINER, string);
			}
		}

		@Override
		public void finest(String string) {
			if (Level.FINEST.intValue() >= logLevel.intValue()) {
				print(Level.FINEST, string);
			}
		}

		@Override
		public void info(String string) {
			if (Level.INFO.intValue() >= logLevel.intValue()) {
				print(Level.INFO, string);
			}
		}

		@Override
		public void warning(String string) {
			if (Level.WARNING.intValue() >= logLevel.intValue()) {
				print(Level.WARNING, string);
			}
		}

		@Override
		public void severe(String string) {
			if (Level.SEVERE.intValue() >= logLevel.intValue()) {
				print(Level.SEVERE, string);
			}
		}

		@Override
		public boolean isLoggable(Level level) {
			return level.intValue() >= logLevel.intValue();
		}

		private void print(Level level, String string) {
			System.out.println("[" + level + "] " + sdf.format(new Date()) + " <" + logName + "> " + string);
		}
	}
	
	static class JavaLogger extends Logger {
		
		private java.util.logging.Logger javaLogger;
		
		public JavaLogger(String name) {
			javaLogger = java.util.logging.Logger.getLogger(name);
		}

		@Override
		public void setLevel(Level level) {
			javaLogger.setLevel(level);
		}

		@Override
		public Level getLevel() {
			return javaLogger.getLevel();
		}

		@Override
		public java.util.logging.Logger getParent() {
			return javaLogger.getParent();
		}

		@Override
		public Handler[] getHandlers() {
			return javaLogger.getHandlers();
		}

		@Override
		public void log(Level level, String string) {
			javaLogger.log(level, string);
		}

		@Override
		public void log(Level level, String string, Throwable e) {
			javaLogger.log(level, string, e);
		}

		@Override
		public void log(Level level, String string, Object... os) {
			javaLogger.log(level, string, os);
		}

		@Override
		public void removeHandler(Handler handler) {
			javaLogger.removeHandler(handler);
		}

		@Override
		public void addHandler(Handler h) {
			javaLogger.addHandler(h);
		}

		@Override
		public void fine(String string) {
			javaLogger.fine(string);
		}

		@Override
		public void finer(String string) {
			javaLogger.finer(string);
		}

		@Override
		public void finest(String string) {
			javaLogger.finest(string);
		}

		@Override
		public void info(String string) {
			javaLogger.info(string);
		}

		@Override
		public void warning(String string) {
			javaLogger.warning(string);
		}

		@Override
		public void severe(String string) {
			javaLogger.severe(string);
		}

		@Override
		public boolean isLoggable(Level level) {
			return javaLogger.isLoggable(level);
		}
	}
	
		public abstract void setLevel(Level level);
		public abstract Level getLevel();
		public abstract java.util.logging.Logger getParent();
		public abstract Handler[] getHandlers();
		public abstract void log(Level level, String string);
		public abstract void log(Level level, String string, Throwable e);
		public abstract void log(Level level, String string, Object... os);
		public abstract void removeHandler(Handler handler);
		public abstract void addHandler(Handler h);
		public abstract void fine(String string);
		public abstract void finer(String string);
		public abstract void finest(String string);
		public abstract void info(String string);
		public abstract void warning(String string);
		public abstract void severe(String string);
		public abstract boolean isLoggable(Level level);
}
