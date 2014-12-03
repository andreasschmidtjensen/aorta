/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public abstract class EntityPanel extends JPanel {
	
	private Thread updateThread;		
	private JPanel statePanel;
	private EntityState state;
	
	public EntityPanel() {
		setPreferredSize(new Dimension(300,740));

		setLayout(new BorderLayout());
		
		statePanel = new JPanel();
		add(statePanel, BorderLayout.CENTER);
		statePanel.setLayout(new BoxLayout(statePanel, BoxLayout.Y_AXIS));
		
		JButton openStateViewer = new JButton("Show internal state");
		openStateViewer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delegateStateViewer();
			}
		});
		add(openStateViewer, BorderLayout.SOUTH);
		
		updateThread = new Thread() {
			@Override
			public void run() {
				while (!interrupted()) {
					try {
						state.update();
						sleep(2000);
					} catch (InterruptedException ex) {
						break;
					}
				}
				System.out.println("stopped gui loop for " + getEntityName());
			}
		}; 
	}

	protected JPanel addListPanel(String titleText, ListModel model) {
		JLabel title = new JLabel(titleText);
		JList items = new JList(model);
		JScrollPane scroll = new JScrollPane(items);
		JPanel container = new JPanel(new BorderLayout());
		container.add(title, BorderLayout.NORTH);
		container.add(scroll, BorderLayout.CENTER);
		statePanel.add(container);
		return container;
	}
	
	protected void setState(EntityState state) {
		this.state = state;
	}
	
	public void start() {
		updateThread.start();
	}

	public void stop() {
		updateThread.interrupt();
	}
	
	public abstract void delegateStateViewer();
	public abstract String getEntityName();
	
}
