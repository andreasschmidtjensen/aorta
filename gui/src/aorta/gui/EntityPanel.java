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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public abstract class EntityPanel extends JPanel {
			
	protected JPanel statePanel;
	private EntityState state;
	
	public EntityPanel() {
		setPreferredSize(new Dimension(300,740));

		setLayout(new BorderLayout());
		
		statePanel = new JPanel();
		add(statePanel, BorderLayout.CENTER);
		statePanel.setLayout(new BoxLayout(statePanel, BoxLayout.Y_AXIS));
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		
		JButton openExecutionTrace = new JButton("Show trace");
		openExecutionTrace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delegateTraceViewer();
			}
		});
		buttons.add(openExecutionTrace);
		add(buttons, BorderLayout.SOUTH);
	}

	protected JList addListPanel(String titleText) {
		JLabel title = new JLabel(titleText);
		JList items = new JList(new DefaultListModel());
		JScrollPane scroll = new JScrollPane(items);
		JPanel container = new JPanel(new BorderLayout());
		container.add(title, BorderLayout.NORTH);
		container.add(scroll, BorderLayout.CENTER);
		statePanel.add(container);
		return items;
	}
	
	protected void setState(EntityState state) {
		this.state = state;
	}
	
	public abstract void delegateTraceViewer();
	public abstract String getEntityName();
	
}
