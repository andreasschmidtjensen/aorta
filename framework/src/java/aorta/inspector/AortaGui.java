/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.inspector;

import aorta.AortaAgent;
import aorta.organization.AortaArtifact;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author asj
 */
public class AortaGui extends JFrame {
	private JTabbedPane tabs;
	
	public AortaGui() {
		setTitle("AORTA");
        setSize(800,500);
		
		tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
		add(tabs);
	}
	
	public void addAgent(AortaAgent agent) {
		tabs.addTab(agent.getName(), new AgentInspector(agent));
		System.out.println("Adding tab for " + agent.getName());
		if (!isVisible()) {
			setVisible(true);
		}
	}
	
	public void addArtifact(AortaArtifact artifact) {
		tabs.addTab("ARTIFACT", new ArtifactInspector(artifact));
		System.out.println("Adding tab for artifact");
		if (!isVisible()) {
			setVisible(true);
		}
	}
	
}
