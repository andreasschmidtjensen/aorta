package aorta.inspector;

import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Var;
import aorta.AortaAgent;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.reasoning.ActionRule;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;


import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class AgentWebInspector implements Inspector {

    private static AgentWebInspector singleton = null;
    
    private HttpServer httpServer = null;
    private String     httpServerURL = "http://localhost:11518";
    private int        httpServerPort = 11518;
    private int        refreshInterval = 5;
    
    //private Map<String,Boolean>        agHasHistory = new HashMap<String, Boolean>();    
    private Map<String,List<String>> histories = new TreeMap<>();
    private Map<String,Integer>        lastStepSeenByUser = new HashMap<>();
    private Map<String, AortaAgent>     registeredAgents = new HashMap<>();
    
    public static synchronized AgentWebInspector get() {
        if (singleton == null) {
            singleton = new AgentWebInspector();
            singleton.startHttpServer();
            singleton.registerRootBrowserView();
            singleton.registerAgentsBrowserView();
            singleton.registerAgentView("no_ag");
        }
        return singleton;
    }
    
    public static boolean isRunning() {
        return singleton != null;
    }
    
    public static String getURL() {
        if (singleton != null)
            return singleton.httpServerURL;
        else
            return null;
    }
    
    private AgentWebInspector() {
    }
    
    private synchronized String startHttpServer()  {
        if (httpServer == null) {
            try {
                httpServer = HttpServer.create(new InetSocketAddress(httpServerPort), 0);
                httpServer.setExecutor(Executors.newCachedThreadPool());

                httpServer.start();
                httpServerURL = "http://"+InetAddress.getLocalHost().getHostAddress()+":"+httpServerPort;
                System.out.println("AORTA Http Server running on "+httpServerURL);
            } catch (BindException e) {
                httpServerPort++;
                return startHttpServer();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return httpServerURL;
    }

    private void registerRootBrowserView() {        
        if (httpServer == null)
            return;
        try {
            httpServer.createContext("/", new HttpHandler() {                                
                public void handle(HttpExchange exchange) throws IOException {
                    String requestMethod = exchange.getRequestMethod();
                    Headers responseHeaders = exchange.getResponseHeaders();
                    responseHeaders.set("Content-Type", "text/html");
                    exchange.sendResponseHeaders(200, 0);
                    OutputStream responseBody = exchange.getResponseBody();

                    if (requestMethod.equalsIgnoreCase("GET")) {
                        String path = exchange.getRequestURI().getPath();
                        StringWriter so = new StringWriter();

                        if (path.length() < 2) { // it is the root
                            so.append("<html><head><title>AORTA Agent Inspector -- Web View</title></head><body>");
                            so.append("<iframe width=\"20%\" height=\"100%\" align=left src=\"/agents\" border=5 frameborder=0 ></iframe>");
                            so.append("<iframe width=\"78%\" height=\"100%\" align=left src=\"/agent-mind/no_ag\" name=\"am\" border=5 frameborder=0></iframe>");
                            so.append("</body></html>");
                        } else if (path.indexOf("agent-mind") >= 0) {
                            so.append("<meta http-equiv=\"refresh\" content=0>");                                
                        }
                        responseBody.write(so.toString().getBytes());
                    }
                    responseBody.close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAgNameFromPath(String path) {
        int nameStart = path.indexOf("agent-mind")+11;
        if (nameStart < 0) return null;
        int nameEnd   = path.indexOf("/",nameStart+1);
        if (nameEnd >= 0)
            return path.substring(nameStart,nameEnd).trim();
        else
            return path.substring(nameStart).trim();        
    }
    
    private void registerAgentsBrowserView() {        
        if (httpServer == null)
            return;
        try {
            httpServer.createContext("/agents", new HttpHandler() {                                
                public void handle(HttpExchange exchange) throws IOException {
                    String requestMethod = exchange.getRequestMethod();
                    Headers responseHeaders = exchange.getResponseHeaders();
                    responseHeaders.set("Content-Type", "text/html");
                    exchange.sendResponseHeaders(200, 0);
                    OutputStream responseBody = exchange.getResponseBody();

                    if (requestMethod.equalsIgnoreCase("GET")) {
                        responseBody.write(("<html><head><title>AORTA Agents</title><meta http-equiv=\"refresh\" content=\""+refreshInterval+"\" ></head><body>").getBytes());
                        responseBody.write(("<font size=\"+2\"><p style='color: red; font-family: arial;'>Agents</p></font>").getBytes());
                        for (String a: histories.keySet()) {
                            responseBody.write( ("- <a href=\"/agent-mind/"+a+"/latest\" target=\"am\" style=\"font-family: arial; text-decoration: none\">"+a+"</a><br/>").getBytes());                            
                        }
                    }                                
                    responseBody.write("<hr/>by <a href=\"http://www2.compute.dtu.dk/~ascje/AORTA/\" target=\"_blank\">AORTA</a>".getBytes());
                    responseBody.write("</body></html>".getBytes());
                    responseBody.close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** add the agent in the list of available agent for mind inspection */
    public synchronized void registerAgent(AortaAgent ag) {
        String agName = ag.getName();
        if (!agName.equals("no-named")) {
            registeredAgents.put(agName, ag);
            histories.put(agName, new ArrayList<String>());
			registerAgentView(agName);
        }
    }
    
	@Override
    public synchronized void addAgentState(AortaAgent ag) {
        String agName = ag.getName();
        List<String> h = histories.get(agName);
        if (h == null) {
            h = new ArrayList<>();
            histories.put(agName, h);
        }
		String agState = getAgStateAsString(ag);
		if (h.isEmpty() || !h.get(h.size() - 1).equals(agState))  {
			h.add(agState);
		}
    }
    
    String registerAgentView(final String agName) {
        if (httpServer == null)
            return null;
        try {
            String url = "/agent-mind/"+agName;
            httpServer.createContext(url, new HttpHandler() {                                
                public void handle(HttpExchange exchange) throws IOException {
                    String requestMethod = exchange.getRequestMethod();
                    Headers responseHeaders = exchange.getResponseHeaders();
                    exchange.sendResponseHeaders(200, 0);
                    OutputStream responseBody = exchange.getResponseBody();
                    responseHeaders.set("Content-Type", "text/html");

                    if (requestMethod.equalsIgnoreCase("GET")) {
                        try {
                            StringWriter so = new StringWriter();
                            so.append("<html><head><title>"+agName+"</title>");
    
                            // test if the url is for this agent
                            String path = exchange.getRequestURI().getPath();
                            if (!getAgNameFromPath(path).equals(agName)) {
                                so.append("<meta http-equiv=\"refresh\" content=0>");                                
                            } else {
    
                                List<String> h = histories.get(agName);
                                if (h != null && h.size() > 0) {
                                    String agState; 
                                    int i = -1;
                                    exchange.getRemoteAddress();
    
                                    String query = exchange.getRequestURI().getRawQuery(); // what follows ?
                                    String remote = exchange.getRemoteAddress().toString();
                                    
                                    if (path.endsWith("hide")) {
                                        Integer ii = lastStepSeenByUser.get(remote);
                                        if (ii != null)
                                            i = ii;
                                    } else if (path.endsWith("show")) {
                                        Integer ii = lastStepSeenByUser.get(remote);
                                        if (ii != null)
                                            i = ii;
                                    } else if (path.endsWith("clear")) {
                                        agState = h.get(h.size()-1);
                                        h.clear();
                                        h.add(agState);
                                    } else {                                
                                        // see if ends with a number
                                        try {
                                            int pos = path.lastIndexOf("/");
                                            String n = path.substring(pos+1).trim();
                                            i = new Integer(n);
                                        } catch (Exception e) {}
                                    }
                                    if (i == -1) { 
                                        so.append("<meta http-equiv=\"refresh\" content=\""+refreshInterval+"\">");
                                        agState = h.get(h.size()-1);
                                    } else {  
                                        agState = h.get(i-1);
                                    }
                                    try {
                                        lastStepSeenByUser.put(remote, i);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    so.append("</head><body>");                            
                                    if (h.size() > 1) {
                                        //so.append("history: ");                            
                                        so.append("<a href=/agent-mind/"+agName+"/latest>latest state</a> ");
                                        for (i=h.size()-1; i>0; i--) {
                                            so.append("<a href=\"/agent-mind/"+agName+"/"+i+"\" style=\"text-decoration: none\">"+i+"</a> ");
                                        }
                                        so.append("<a href=\"/agent-mind/"+agName+"/clear\">clear history</a> ");
                                        so.append("<hr/>");                            
                                    }
                                    so.append(agState);
                                } else {
                                    so.append("select an agent");
                                }
                            }
                            responseBody.write(so.toString().getBytes());
                            
                            responseBody.write("</body></html>".getBytes());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }                                
                    responseBody.close();
                }
            });
            return httpServerURL+url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    String registerAgCodeBrowserView(String agId, final String agCode) {        
        if (httpServer == null)
            return null;
        try {
            String url="/agent-code/"+agId;
            httpServer.createContext(url, new HttpHandler() {                                
                public void handle(HttpExchange exchange) throws IOException {
                    String requestMethod = exchange.getRequestMethod();
                    Headers responseHeaders = exchange.getResponseHeaders();
                    responseHeaders.set("Content-Type", "text/html");
                    exchange.sendResponseHeaders(200, 0);
                    OutputStream responseBody = exchange.getResponseBody();

                    if (requestMethod.equalsIgnoreCase("GET")) {
                        responseBody.write(agCode.getBytes());
                    }                                
                    responseBody.close();
                }
            });
            return httpServerURL+url;                          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    */

    synchronized String getAgStateAsString(AortaAgent agent) {
        StringBuilder sb = new StringBuilder();
		try {
			QueryEngine qe = new QueryEngine();
			MentalState ms = agent.getState().getMentalState();
			MetaLanguage ml = new MetaLanguage();
			sb.append("<style>"
					+ "body { font: 10pt sans-serif; }"
					+ "b { font-weight: bold; color: #cc0000; } "
					+ "h1 { font-size: 1.5em; color: #990000; }" 
					+ "h3 { font-size: 1.2em; color: #cc0000; margin-bottom: 0.2em; }"
					+ "ul { margin-top: 0; padding-left: 0pt; } "
					+ "li.viol { color: red; font-weight: bold; }"
					+ "</style>");
			sb.append("<h1>").append(agent.getName()).append("</h1>");
			
			sb.append("<p><b>Roles: </b>");
			List<SolveInfo> results = qe.findAll(ms, new Struct("org", ml.rea(new Struct(agent.getName()), new Var("R"))));
			for (int i = 0; i < results.size(); i++) {
				SolveInfo si = results.get(i);
				if (si.isSuccess()) {
					if (i > 0) {
						sb.append(" &mdash; ");
					}
					sb.append(si.getVarValue("R"));
				}
			}
			sb.append("</p>");
            
            sb.append("<div style=\"float: right; margin-right: 200px; width: 300px; font-size: 0.8em; color: #ccc;\">");
            sb.append(agent.getLastTrace().replace("&", "&amp;").replace("<", "&lt;").replace("\n", "<br />"));
            sb.append("</div>");

			sb.append("<h3>Options</h3>");
			sb.append("<ul>");
			results = qe.findAll(ms, new Struct("opt", new Var("X")));
			for (SolveInfo si : results) {
				if (si.isSuccess()) {
					sb.append("<li>")
							.append(si.getVarValue("X"))
							.append("</li>");
				}
			}
			sb.append("</ul>");
			
			sb.append("<h3>Capabilities</h3>");
			sb.append("<ul>");
			results = qe.findAll(ms, new Struct("cap", new Var("X")));
			for (SolveInfo si : results) {
				if (si.isSuccess()) {
					sb.append("<li>")
							.append(si.getVarValue("X"))
							.append("</li>");
				}
			}
			sb.append("</ul>");
			
			sb.append("<h3>Goals</h3>");
			sb.append("<ul>");
			results = qe.findAll(ms, new Struct("goal", new Var("X")));
			for (SolveInfo si : results) {
				if (si.isSuccess()) {
					sb.append("<li>")
							.append(si.getVarValue("X"))
							.append("</li>");
				}
			}
			sb.append("</ul>");
			
			sb.append("<h3>Beliefs</h3>");
			sb.append("<ul>");
			results = qe.findAll(ms, new Struct("bel", new Var("X")));
			for (SolveInfo si : results) {
				if (si.isSuccess()) {
					sb.append("<li>")
							.append(si.getVarValue("X"))
							.append("</li>");
				}
			}
			sb.append("</ul>");
			
			sb.append("<h3>Norms</h3>");
			sb.append("<ul>");
			results = qe.findAll(ms, new Struct("org", ml.norm(new Struct(agent.getName()), new Var("R"), new Var("Deon"), new Var("O"), new Var("D"))));
			for (SolveInfo si : results) {
				if (si.isSuccess()) {
					SolveInfo viol = qe.solve(ms, new Struct("org", ml.violation(new Struct(agent.getName()), si.getVarValue("R"), si.getVarValue("Deon"), si.getVarValue("O"))));
					sb.append("<li")
							.append(viol.isSuccess() ? " class=\"viol\"" : "")
							.append(">")
							.append("[").append(si.getVarValue("R")).append("] ")
							.append(si.getVarValue("O")).append(" &lt; ")
							.append(si.getVarValue("D"))
							.append(".</li>");
				}
			}
            // violated, completed (strike through)
            results = qe.findAll(ms, new Struct("org", ml.norm(new Struct(agent.getName()), new Var("R"), new Var("Deon"), new Var("O"), new Var("D"))));
			for (SolveInfo si : results) {
				if (si.isSuccess()) {
					SolveInfo viol = qe.solve(ms, new Struct("org", ml.violation(new Struct(agent.getName()), si.getVarValue("R"), si.getVarValue("Deon"), si.getVarValue("O"))));
					sb.append("<li")
							.append(viol.isSuccess() ? " class=\"viol\"" : "")
							.append(">")
							.append("[").append(si.getVarValue("R")).append("] ")
							.append(si.getVarValue("O")).append(" &lt; ")
							.append(si.getVarValue("D"))
							.append(".</li>");
				}
			}
			sb.append("</ul>");
			

			sb.append("<h3>MentalState.toString()</h3>");
			sb.append("<pre>").append(agent.getState().getMentalState()).append("</pre>");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return sb.toString();
    }

}
