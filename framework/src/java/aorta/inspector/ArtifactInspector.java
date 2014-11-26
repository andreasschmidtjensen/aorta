/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.inspector;

import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Var;
import aorta.AortaAgent;
import aorta.kr.MentalState;
import aorta.kr.QueryEngine;
import aorta.kr.language.MetaLanguage;
import aorta.kr.util.TermFormatter;
import aorta.parser.AORTALexer;
import aorta.parser.AORTAParser;
import aorta.reasoning.fml.Formula;
import aorta.tracer.Tracer;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import aorta.logging.Logger;
import aorta.reasoning.ActionRule;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @author asj
 */
public class AgentInspector extends JPanel {

	private static final Logger logger = Logger.getLogger(AgentInspector.class.getName());
	public static final String SOLVE = "Solve";
	public static final String SOLVE_AGAIN = "Solve again";
	private AortaAgent agent;
	private String formula;
	private MentalState currentMs;
	private JTextArea solveResult;
	private JTextField solveFml;
	private JButton solveButton;

	public AgentInspector(final AortaAgent agent) {
		this.agent = agent;

		setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel(new GridLayout(1, 2));
		add(mainPanel, BorderLayout.CENTER);

		final JTextArea mentalStateArea = new JTextArea();
		mainPanel.add(new JScrollPane(mentalStateArea));

		final JTextArea traceArea = new JTextArea();
		mainPanel.add(new JScrollPane(traceArea));

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

		new Thread() {
			@Override
			public void run() {
				while (!interrupted()) {
					try {
						String agentText = agent.toString();
						if (agentText != null) {
							mentalStateArea.setText(agentText);
						}
						String traceText = Tracer.printTrace(agent.getName());
						if (traceText != null) {
							traceArea.setText(traceText);
						}
						sleep(500);
					} catch (InterruptedException ex) {
						break;
					}
				}
				logger.finest("stopped gui loop for " + AgentInspector.this.agent.getName());
			}
		}.start();
	}

	private void solve() {
		new Thread() {
			@Override
			public void run() {
				QueryEngine engine = new QueryEngine();
				if (solveFml.getText().equals(formula)) {
					solveResult.append("\n" + engine.solveNext(currentMs).toString());
				} else {
					currentMs = agent.getState().getMentalState();
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
		}.start();
	}
}
