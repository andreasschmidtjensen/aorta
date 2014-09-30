/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.generic;

import alice.tuprolog.Term;
import aorta.logging.Logger;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author asj
 */
public class GenericMAS {
    
    public static final Logger logger = Logger.getLogger(GenericMAS.class.getName());
    
    private Map<String, GenericAgent> agents = new HashMap<>();
    private int cycle = 0;
    
    public void addAgent(GenericAgent agent) {
        agents.put(agent.getName(), agent);
        agent.setMAS(this);
    }
    
    public void cycle() {
        logger.info("CYCLE " + cycle++);
        for (GenericAgent ag : agents.values()) {
            ag.reason();
        }
    }

    void send(String sender, String recipient, Term message) {
        GenericAgent ag = agents.get(recipient);
        if (ag != null) {
            ag.receive(sender, message);
        } else {
            logger.warning("No recipient (" + recipient + ") for " + message + " sent by " + sender);
        }
    }
    
}
