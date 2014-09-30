/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.tracer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Tracer {

	private Map<String, List<String>> queue;
	private Map<String, List<String>> traces;
	private static final Tracer tracer = new Tracer();

	private Tracer() {
		traces = new HashMap<>();
		queue = new HashMap<>();
	}

	private synchronized void addToQueue(String agent, String event) {
		if (!queue.containsKey(agent)) {
			queue.put(agent, new ArrayList<String>());
		}
		queue.get(agent).add(event);
	}
	
	private synchronized void clearAgentQueue(String agent) {
		queue.remove(agent);
	}
	
	private synchronized void commitQueue(String agent) {
		if (!traces.containsKey(agent)) {
			traces.put(agent, new ArrayList<String>());
		}
		if (queue.get(agent) != null) {
			traces.get(agent).addAll(queue.get(agent));
			clearAgentQueue(agent);
		}
	}	
	
	
	private synchronized void addTrace(String agent, String event) {
		if (!traces.containsKey(agent)) {
			traces.put(agent, new ArrayList<String>());
		}
		traces.get(agent).add(event);
	}	
	
	public static void queue(String agent, String event) {
		tracer.addToQueue(agent, event);
	}
	
	public static void clearQueue(String agent) {
		tracer.clearAgentQueue(agent);
	}
	
	public static void trace(String agent, String event) {
		tracer.addTrace(agent, event);
	}

	public static void trace(String agent) {
		tracer.commitQueue(agent);
	}

	private synchronized String toString(String agent) {
		List<String> events = traces.get(agent);
		if (events != null) {
			StringBuilder sb = new StringBuilder();

			sb.append(agent).append(":\n");
			for (String event : events) {
				sb.append("  ").append(event);
			}
			return sb.toString();
		} else {
			return null;
		}
	}

	@Override
	public synchronized String toString() {
		StringBuilder sb = new StringBuilder();

		for (String agent : traces.keySet()) {
			String trace = printTrace(agent);
			if (trace != null) {
				sb.append(trace).append("\n");
			}
		}

		return sb.toString();
	}

	public static String printTrace() {
		return tracer.toString();
	}

	public static String printTrace(String agent) {
		return tracer.toString(agent);
	}
    
    public static void clearTrace(String agent) {
        if (tracer.traces.containsKey(agent)) {
            tracer.traces.get(agent).clear();
        }
    }
}
