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
import aorta.tracer.FiredTransitionRule;
import aorta.tracer.StateListener;
import aorta.tracer.VisitedState;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
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
import javax.swing.tree.TreePath;
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
		
		public ExecutionModel(String agentName) {
			super(new DefaultMutableTreeNode(agentName));
			
			top = (DefaultMutableTreeNode) getRoot();
		}

		@Override
		public void termAdded(String name, Term term) {
			DefaultMutableTreeNode ruleNode = getCurrentRuleNode(name);
			DefaultMutableTreeNode termNode = new DefaultMutableTreeNode("+" + term);
			insertNodeInto(termNode, ruleNode, ruleNode.getChildCount());
		}

		@Override
		public void termRemoved(String name, Term term) {
			DefaultMutableTreeNode ruleNode = getCurrentRuleNode(name);
			DefaultMutableTreeNode termNode = new DefaultMutableTreeNode("-" + term);
			insertNodeInto(termNode, ruleNode, ruleNode.getChildCount());
		}

		@Override
		public void newState(State state) {
			if (stateNum > 0 && currentRuleNodes.isEmpty()) {
				// state did not change
				stateNum--;
			}
			
			currentStateNode = new StateNode("s_" + stateNum++, state.getMentalState());
			currentRuleNodes = new HashMap<>();
		}
		
		private DefaultMutableTreeNode getCurrentRuleNode(String name) {
			if (currentRuleNodes.isEmpty()) {
				insertNodeInto(currentStateNode, top, top.getChildCount());
			}
			
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
