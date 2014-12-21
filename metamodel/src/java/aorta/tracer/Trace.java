/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.tracer;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Trace {
	
	private String event;
	private StringBuilder description;

	public Trace(String event) {
		this.event = event;
		description = new StringBuilder();
	}

	public Trace(String event, String description) {
		this.event = event;
		this.description = new StringBuilder().append(description);
	}

	public void append(String description) {
		this.description.append(description);
	}
	
	public String getDescription() {
		return description.toString();
	}

	public String getEvent() {
		return event;
	}

	@Override
	public String toString() {
		return "(" + event + ") " + description;
	}

}
