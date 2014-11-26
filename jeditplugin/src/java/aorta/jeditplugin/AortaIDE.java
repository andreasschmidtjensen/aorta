/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.jeditplugin;

import jason.mas2j.MAS2JProject;
import jason.runtime.OutputStreamAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EBComponent;
import org.gjt.sp.jedit.EBMessage;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.gui.DockableWindowManager;
import org.gjt.sp.jedit.msg.BufferUpdate;

/**
 *
 * @author Andreas Schmidt Jensen <ascje at dtu.dk>
 */
public class AortaIDE extends JPanel implements EBComponent {

	View view;
	JTextArea textArea;
	OutputStreamAdapter myOut;
	DefaultListModel listModel = new DefaultListModel();
	JList fileList = new JList(listModel);

	public AortaIDE(View view, String position) {
		super(new BorderLayout());

		this.view = view;

		add(BorderLayout.NORTH, createToolBar());

		boolean floating = position.equals(DockableWindowManager.FLOATING);
		if (floating) {
			this.setPreferredSize(new Dimension(500, 250));
		}

		textArea = new JTextArea(5, 10);
		textArea.setEditable(false);
		textArea.setText("");

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.CENTER, new JScrollPane(textArea));
		pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Console", TitledBorder.LEFT, TitledBorder.TOP));
		add(BorderLayout.CENTER, pane);

		JPanel pLst = new JPanel(new BorderLayout());
		pLst.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Reasoning files", TitledBorder.LEFT, TitledBorder.TOP));

		pLst.add(BorderLayout.CENTER, new JScrollPane(fileList));
		// pLst.setMinimumSize(new Dimension(100,50));
		fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fileList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				openReasoningFile(((AortaFile) fileList.getSelectedValue()).file);
			}
		});
		pLst.setPreferredSize(new Dimension(160, 50));
		add(BorderLayout.EAST, pLst);

		myOut = new OutputStreamAdapter(null, textArea);
//		myOut.setAsDefaultOut();
		
		AortaSideKickParser.addPluginInstance(this);
	}

	private JPanel createToolBar() {
		JPanel toolBar = new JPanel();
		toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));

		JButton newRuleFile = new JButton("New AORTA file");
		newRuleFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Buffer mas2j = getProjectBuffer();
					if (mas2j == null) {
						textArea.append("No Jason-project has been loaded!\n");
						return;
					}

					String filename = JOptionPane.showInputDialog("Input name of new reasoning file.", ".aorta");
					if (!filename.matches("[a-zA-Z0-9_\\.-]+\\.aorta")) {
						textArea.append("Invalid filename. Should only contain alphanumerics, _, . and -.\n");
						return;
					}

					File file = new File(mas2j.getDirectory() + filename);
					if (!file.createNewFile()) {
						textArea.append("Could not create new reasoning file at " + filename + ".\n");
						return;
					}

					listModel.addElement(new AortaFile(file, filename));
					openReasoningFile(file);

					textArea.append("Created reasoning file (" + filename + "). Remember to add to one or more agents in the project file (i.e. agentname [aorta=\"" + filename + "\"]).\n");
				} catch (IOException ex) {
					textArea.append("Could not create new reasoning file (" + ex.getMessage() + ").\n");
					ex.printStackTrace();
				}
			}
		});

		JButton showOrgModel = new JButton("Metamodel");
		showOrgModel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Buffer mas2j = getProjectBuffer();
				if (mas2j == null) {
					textArea.append("No Jason-project has been loaded!\n");
					return;
				}

				Pattern pattern = Pattern.compile("infrastructure: AORTA\\s*\\(organization\\(\"(.*)\"\\).*\\)");
				String metamodelName = null;
				String mas2jContents = mas2j.getText(0, mas2j.getLength());

				Matcher matcher = pattern.matcher(mas2jContents);
				if (matcher.find()) {
					metamodelName = matcher.group(1);
				}

				if (metamodelName != null) {
					openMetamodel(mas2j.getDirectory() + metamodelName);
				} else {
					textArea.append("No metamodel has been specified. Must be specified in infrastructure (infrastructure: AORTA(organization(\"metamodel.mm\"))).\n");
				}
			}
		});

		JButton refresh = new JButton("Refresh files");
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshFiles();
			}
		});

		JButton clearText = new JButton("Clear Textarea");
		clearText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});

		toolBar.add(newRuleFile);
		toolBar.add(showOrgModel);
		toolBar.add(refresh);
		toolBar.add(clearText);
		toolBar.add(Box.createGlue());

		JPanel p = new JPanel(new BorderLayout());
		p.add(toolBar, BorderLayout.EAST);
		JLabel title = new JLabel("AORTA");
		/*
		 JLabel jasonLabel = new JLabel("Jason IDE");
		 jasonLabel.setFont(new Font("Times", Font.BOLD | Font.ITALIC, 16));
		 */
		JPanel pAbt = new JPanel();
		pAbt.add(title);
		//pAbt.add(jasonLabel);
		p.add(pAbt, BorderLayout.WEST);

		return p;
	}

	private class AortaFile {

		File file;
		String name;

		public AortaFile(File file, String name) {
			this.file = file;
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

	}

	/**
	 * returns the current MAS2J project
	 */
	private Buffer getProjectBuffer() {
		if (view.getBuffer().getPath().endsWith(MAS2JProject.EXT)) {
			return view.getBuffer();
		}
		Buffer[] bufs = org.gjt.sp.jedit.jEdit.getBuffers();
		for (int i = 0; i < bufs.length; i++) {
			if (bufs[i].getPath().endsWith(MAS2JProject.EXT)) {
				return bufs[i];
			}
		}
		return null;
	}

	void openReasoningFile(File file) {
		try {
			if (!file.exists()) {
				textArea.append("File " + file.getAbsolutePath() + " does not exist!\n");
				return;
			}

			Buffer nb = org.gjt.sp.jedit.jEdit.openFile(view, file.getAbsolutePath());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void openMetamodel(String filename) {
		try {
			File file = new File(filename);
			if (!file.exists()) {
				if (JOptionPane.showConfirmDialog(this, "No metamodel exists at " + filename + ". Create new file?", "Create new metamodel?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (!file.createNewFile()) {
						textArea.append("Could not create new metamodel at " + filename + ".\n");
						return;
					}
				} else {
					return;
				}
			}

			Buffer nb = org.gjt.sp.jedit.jEdit.openFile(view, file.getAbsolutePath());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void refreshFiles() {
		listModel.clear();

		Buffer mas2j = getProjectBuffer();
		if (mas2j == null) {
			textArea.append("No Jason-project has been loaded!\n");
			return;
		}
		File mas2jDir = new File(mas2j.getDirectory());
		for (File file : mas2jDir.listFiles()) {
			if (file.getName().endsWith(".aorta")) {
				listModel.addElement(new AortaFile(file, file.getName()));
			}
		}
		textArea.append("List refreshed. Note that only files in the directory of the project file are shown. Other files may be opened manually in JEdit.\n");
	}

	@Override
	public void handleMessage(EBMessage message) {
	}
}
