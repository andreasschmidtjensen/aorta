/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.tracer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class Tracer {

	private Map<String, List<Trace>> traces;
	private Map<String, Trace> queue;
	private static final Tracer tracer = new Tracer();
	
	private Set<String> ignoredEvents = new HashSet<>();

	private Tracer() {
		traces = new HashMap<>();
		queue = new HashMap<>();
	}
	
	private void ignoreEvent(String event) {
		ignoredEvents.add(event);
	}
	
	private synchronized void beginNewTrace(String agent, String event) {
		queue.put(agent, new Trace(event));
	}

	private synchronized void addToQueue(String agent, String description) {
		queue.get(agent).append(description);
	}
	
	private synchronized void clearAgentQueue(String agent) {
		queue.remove(agent);
	}
	
	private synchronized Trace commitQueue(String agent) {
		Trace value = null;
		if (!traces.containsKey(agent)) {
			traces.put(agent, new ArrayList<Trace>());
		}
		
		Trace trace = queue.get(agent);
		if (trace != null) {
			value = trace;
			if (!ignoredEvents.contains(trace.getEvent())) {
				traces.get(agent).add(trace);
				clearAgentQueue(agent);				
			}
		}
		
		return value;
	}	
		
	private synchronized void addTrace(String agent, Trace trace) {
		if (!traces.containsKey(agent)) {
			traces.put(agent, new ArrayList<Trace>());
		}
		
		if (!ignoredEvents.contains(trace.getEvent())) {
			traces.get(agent).add(trace);
		}
	}	
	
	private synchronized String toString(String agent) {
		List<Trace> tr = traces.get(agent);
		if (tr != null) {
			StringBuilder sb = new StringBuilder();

			sb.append(agent).append(":\n");
			for (Trace trace : tr) {
				sb.append(trace).append("\n");
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
	
	public static void beginTrace(String agent, String event) {
		tracer.beginNewTrace(agent, event);
	}
	
	public static void queue(String agent, String description) {
		tracer.addToQueue(agent, description);
	}
	
	public static void clearQueue(String agent) {
		tracer.clearAgentQueue(agent);
	}
	
	public static Trace commitTrace(String agent) {
		return tracer.commitQueue(agent);
	}
	
	public static void trace(String agent, String event, String description) {
		tracer.addTrace(agent, new Trace(event, description));
	}
	
	public static void ignore(String event) {
		tracer.ignoreEvent(event);
	}

}
