/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.gui;

import aorta.AortaAgent;
import aorta.organization.AortaArtifact;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author asj
 */
public class AortaGui extends JFrame {

	private JPanel checkboxes;
	private JPanel agentInspectors;
	private Map<String, EntityPanel> inspectors = new HashMap<>();
	private final static AortaGui gui = new AortaGui();

	public static AortaGui get() {
		return gui;
	}

	private AortaGui() {
		setTitle("AORTA: Adding Organizational Reasoning to Agents");
		setSize(1100, 800);

		setLayout(new BorderLayout());

		add(createAgentList(), BorderLayout.WEST);

		agentInspectors = new JPanel();
		agentInspectors.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		agentInspectors.setLayout(new FlowLayout(FlowLayout.LEFT));

		JScrollPane scroller = new JScrollPane(agentInspectors);
		add(scroller, BorderLayout.CENTER);
		
		setLocationByPlatform(true);
	}

	private JPanel createAgentList() {
		JPanel panel = new JPanel(new BorderLayout());
		final JLabel title = new JLabel("Select agents");
		panel.add(title, BorderLayout.NORTH);
		checkboxes = new JPanel();
		checkboxes.setLayout(new BoxLayout(checkboxes, BoxLayout.Y_AXIS));
		panel.add(checkboxes, BorderLayout.CENTER);

		return panel;
	}

	public void addAgent(final AortaAgent agent) {		
		if (!isVisible()) {
			setVisible(true);
		}
		
		final String name = agent.getName();
		JCheckBox checkbox = new JCheckBox(name);
		checkbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					AgentPanel agPanel = new AgentPanel(agent);
					inspectors.put(name, agPanel);
					agentInspectors.add(agPanel);
				} else {
					if (inspectors.containsKey(name)) {
						EntityPanel entityPanel = inspectors.remove(name);
						agentInspectors.remove(entityPanel);
					}
				}

				agentInspectors.revalidate();
				agentInspectors.repaint();
			}
		});
		checkboxes.add(checkbox);
		checkboxes.revalidate();
		checkboxes.repaint();
		
		ExecutionTraceView.get().addExecutionTrace(agent);
	}

	public void addArtifact(final AortaArtifact art) {
		if (!isVisible()) {
			setVisible(true);
		}
		
		final String name = "ARTIFACT";
		JCheckBox checkbox = new JCheckBox(name);
		checkbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ArtifactPanel inspector = new ArtifactPanel(art);
					inspectors.put(name, inspector);
					agentInspectors.add(inspector);
				} else {
					if (inspectors.containsKey(name)) {
						EntityPanel inspector = inspectors.remove(name);
						agentInspectors.remove(inspector);
					}
				}

				agentInspectors.revalidate();
			}
		});
		checkboxes.add(checkbox);
		checkboxes.revalidate();
	}
}
