/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.generic;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.Aorta;
import aorta.AortaAgent;
import aorta.inspector.AgentWebInspector;
import aorta.inspector.AortaGui;
import aorta.logging.Logger;
import aorta.parser.helper.AortaBuilder;
import aorta.ts.rules.Ext;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

/**
 *
 * @author asj
 */
public class GenericLauncher {
    
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: GenericLauncher configuration.properties");
            System.exit(1);
        }
        Properties props = new Properties();
        props.load(new FileInputStream(new File(args[0])));
//        
//        GenericEnvironment env = null;
//        String envLocation = props.getProperty("env");
//        if (envLocation != null) {
//            Class cls = Class.forName(envLocation);
//            env = (GenericEnvironment) cls.newInstance();
//        }
        
        String[] agents = props.getProperty("agents").split(",");
        String metamodel = props.getProperty("metamodel");
        AortaBuilder builder = new AortaBuilder();			
        
        GenericMAS mas = new GenericMAS();
		Aorta aorta = new Aorta(metamodel);
        
        for (String agentName : agents) {
            String[] caps = props.getProperty(agentName + ".capabilities").split(",");
            String aortaLocation = props.getProperty(agentName + ".aorta");
            List<Struct> capabilities = new ArrayList<>();
            for (String cap : caps) {
                capabilities.add((Struct) Term.createTerm(cap));
            }
            
            GenericAgent agent = new GenericAgent(capabilities);
            AortaAgent aortaAgent = builder.parseAgent(agentName, aortaLocation, metamodel, agent);
            agent.setAortaAgent(aortaAgent);
            mas.addAgent(agent);
            
            AgentWebInspector.get().registerAgent(aortaAgent);
            aortaAgent.addInspector(AgentWebInspector.get());
            
            aorta.addAgent(aortaAgent);
        }
        
        
        while (true) {
            mas.cycle();
            
            Thread.sleep(2000);
        }
    }
    
}
