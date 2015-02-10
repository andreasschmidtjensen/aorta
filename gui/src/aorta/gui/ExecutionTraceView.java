/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.gui;

import alice.tuprolog.Term;
import aorta.AortaAgent;
import aorta.State;
import aorta.kr.MentalState;
import aorta.tracer.ExecutionTrace;
import aorta.tracer.StateListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author asj
 */
public class ExecutionTraceView extends JFrame {
	
	private static final ExecutionTraceView viewer = new ExecutionTraceView();
	
	private final JTree tree;
	final JTextArea mentalStateView = new JTextArea();
	
	private final Map<String, ExecutionModel> traces = new HashMap<>();
	
	public static ExecutionTraceView get() {
		return viewer;
	}
	
	private ExecutionTraceView() {
		super("Execution Tracer");
		
		setSize(800,500);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout());
		
		tree = new JTree();
		JScrollPane treeView = new JScrollPane(tree);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				 DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

				if (node == null) {
					return;
				}
				
				if (node instanceof StateNode) {
					mentalStateView.setText(((StateNode)node).getMentalState());
				}

			}
		});
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
		split.setLeftComponent(new JScrollPane(treeView));
		split.setRightComponent(new JScrollPane(mentalStateView));
		split.setResizeWeight(0.5);
		panel.add(split, BorderLayout.CENTER);
		
		JButton export = new JButton("Tikz");
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExecutionModel model = ((ExecutionModel)tree.getModel());
				try (FileWriter wr = new FileWriter(model.trace.getAgent() + ".tex")) {
					wr.write(model.trace.toTikz());
					System.out.println("Written to " + model.trace.getAgent() + ".tex");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(export);
		
		panel.add(buttons, BorderLayout.SOUTH);
	}
	
	public void addExecutionTrace(AortaAgent agent) {
		ExecutionModel model = new ExecutionModel(agent.getName());
		agent.getState().addStateListener(model);
		traces.put(agent.getName(), model);
	}
	
	public void setAgent(String agent) {
		if (!isVisible()) {
			setVisible(true);
		}
		tree.setModel(traces.get(agent));
	}
	
	private class ExecutionModel extends DefaultTreeModel implements StateListener {

		private final DefaultMutableTreeNode top;
		
		private DefaultMutableTreeNode currentStateNode;
		private Map<String, DefaultMutableTreeNode> currentRuleNodes;
		
		private int stateNum = 0;
		
		private ExecutionTrace trace;	
		
		public ExecutionModel(String agentName) {
			super(new DefaultMutableTreeNode(agentName));
			
			top = (DefaultMutableTreeNode) getRoot();
			trace = new ExecutionTrace(agentName);
		}

		@Override
		public void termAdded(String name, Term term) {
			DefaultMutableTreeNode ruleNode = getCurrentRuleNode(name);
			DefaultMutableTreeNode termNode = new DefaultMutableTreeNode("+" + term);
			insertNodeInto(termNode, ruleNode, ruleNode.getChildCount());
			
			trace.termAdded(name, term);
		}

		@Override
		public void termRemoved(String name, Term term) {
			DefaultMutableTreeNode ruleNode = getCurrentRuleNode(name);
			DefaultMutableTreeNode termNode = new DefaultMutableTreeNode("-" + term);
			insertNodeInto(termNode, ruleNode, ruleNode.getChildCount());
			
			trace.termRemoved(name, term);
		}

		@Override
		public void newState(State state) {
			if (stateNum > 0 && currentRuleNodes.isEmpty()) {
				// state did not change
				stateNum--;
				trace.revokeLastState();
				removeNodeFromParent(currentStateNode);
			}
			
			currentStateNode = new StateNode("s_" + stateNum++, state.getMentalState());
			currentRuleNodes = new HashMap<>();
			
			trace.newState(state);
			insertNodeInto(currentStateNode, top, top.getChildCount());
		}
		
		private DefaultMutableTreeNode getCurrentRuleNode(String name) {
			if (!currentRuleNodes.containsKey(name)) {
				DefaultMutableTreeNode ruleNode = new DefaultMutableTreeNode(name);
				currentRuleNodes.put(name, ruleNode);
				insertNodeInto(ruleNode, currentStateNode, currentStateNode.getChildCount());
			}
			return currentRuleNodes.get(name);
		}
	
	}
	
	private class StateNode extends DefaultMutableTreeNode {

		private String mentalState;
		
		public StateNode(String name, MentalState ms) {
			super(name);
			
			mentalState = ms.toString();
		}

		public String getMentalState() {
			return mentalState;
		}		
		
	}
	
}
