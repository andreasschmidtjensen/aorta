/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.gui;

import alice.tuprolog.MalformedGoalException;
import aorta.AortaAgent;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.util.TermFormatter;
import aorta.organization.AortaArtifact;
import aorta.parser.AORTALexer;
import aorta.parser.AORTAParser;
import aorta.reasoning.fml.Formula;
import aorta.tracer.Tracer;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class StateViewer extends JFrame {

	public static final String SOLVE = "Solve";
	public static final String SOLVE_AGAIN = "Solve again";
	private AortaAgent agent;
	private AortaArtifact artifact;
	private String formula;
	private MentalState currentMs;
	private JTextArea solveResult;
	private JTextField solveFml;
	private JButton solveButton;
	
	public Thread updateThread;
	
	final JTextArea mentalStateArea = new JTextArea();
	final JTextArea traceArea = new JTextArea();
	
	private final Object lock = new Object();
	
	private static final StateViewer viewer = new StateViewer();
	
	public static StateViewer get() {
		return viewer;
	}
	
	public StateViewer() throws HeadlessException {
		super("Agent Internal State");
		setSize(800,500);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout());

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
		split.setLeftComponent(new JScrollPane(mentalStateArea));
		split.setRightComponent(new JScrollPane(traceArea));
		split.setResizeWeight(0.5);
		panel.add(split, BorderLayout.CENTER);

		JPanel fmlTester = new JPanel(new BorderLayout());
		solveResult = new JTextArea(3, 0);
		solveResult.setEditable(false);

		solveFml = new JTextField();
		solveButton = new JButton(SOLVE);

		solveFml.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (solveButton.getText().equals(SOLVE_AGAIN)) {
					solveButton.setText(SOLVE);
				}
				if (!solveButton.isEnabled()) {
					solveButton.setEnabled(true);
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					solveButton.requestFocus();
					solve();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		solveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solve();
			}
		});

		fmlTester.add(solveResult, BorderLayout.NORTH);
		fmlTester.add(solveFml, BorderLayout.CENTER);
		fmlTester.add(solveButton, BorderLayout.EAST);
		add(fmlTester, BorderLayout.SOUTH);
		
		updateThread = new Thread() {
			@Override
			public void run() {
				while (!interrupted()) {
					try {
						synchronized(lock) {
							String agentText;
							String traceText;
							if (agent != null) {
								agentText = agent.toString();
								traceText = Tracer.printTrace(agent.getName());
							} else {
								agentText = artifact.toString();
								traceText = Tracer.printTrace("ARTIFACT");
							}
							if (traceText != null) {
								traceArea.setText(traceText);
							}
							if (agentText != null) {
								mentalStateArea.setText(agentText);
							}
						}
						sleep(500);
					} catch (InterruptedException ex) {
						break;
					}
				}
				System.out.println("stopped gui loop for state viewer");
			}
		};
	}
	
	private void start() {
		setVisible(true);
		updateThread.start();
	}
	
	public void stop() {
		updateThread.interrupt();
	}
	
	public void setAgent(AortaAgent agent) {
		synchronized (lock) {
			this.agent = agent;
			artifact = null;
			formula = null;
			currentMs = null;
			
			if (!updateThread.isAlive()) {
				start();
			}
		}
	}
	
	public void setArtifact(AortaArtifact artifact) {
		synchronized (lock) {
			this.artifact = artifact;
			agent = null;
			formula = null;
			currentMs = null;
			
			if (!updateThread.isAlive()) {
				start();
			}
		}
	}
	
	private void solve() {
		new Thread() {
			@Override
			public void run() {
				synchronized (lock) {
					QueryEngine engine = new QueryEngine();
					if (solveFml.getText().equals(formula)) {
						solveResult.append("\n" + engine.solveNext(currentMs).toString());
					} else {
						if (agent != null) {
							currentMs = agent.getState().getMentalState();
						} else {
							currentMs = artifact.getState().getMentalState();
						}
						formula = solveFml.getText();

						AORTAParser parser = new AORTAParser(null);
						AORTALexer lexer = new AORTALexer(null);

						ANTLRInputStream inputStream = new ANTLRInputStream(formula);
						lexer.setInputStream(inputStream);
						CommonTokenStream tokens = new CommonTokenStream(lexer);
						tokens.fill();

						parser.setTokenStream(tokens);
						parser.setTrace(false);
						Formula fml = parser.formulas().fml;

						if (fml == null) {
							try {
								solveResult.setText("Could not parse " + formula + " trying as text:\n" + engine.findAll(currentMs, formula));

								solveButton.setText(SOLVE_AGAIN);
							} catch (MalformedGoalException ex) {
								solveResult.setText("Could not parse " + formula + ": " + ex);
							}
						} else {
							try {
								solveResult.setText("Solving " + TermFormatter.toString(fml.getAsTerm()) + ":\n" + engine.findAll(currentMs, fml).toString());
							} catch (Exception ex) {
								try {
									solveResult.setText("Could not parse " + formula + " trying as text:\n" + engine.findAll(currentMs, formula));

									solveButton.setText(SOLVE_AGAIN);
								} catch (MalformedGoalException ex1) {
									solveResult.setText("Could not parse " + formula + ": " + ex1);
								}
							}

							solveButton.setText(SOLVE_AGAIN);
						}
					}

					if (!engine.hasMoreSolutions(currentMs)) {
						solveButton.setEnabled(false);
					}
				}
			}
		}.start();
	}
	
}
