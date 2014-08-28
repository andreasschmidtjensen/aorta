package aorta.jeditplugin;

import jason.mas2j.MAS2JProject;

import javax.swing.SwingUtilities;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EBMessage;
import org.gjt.sp.jedit.EBPlugin;
import org.gjt.sp.jedit.gui.DockableWindowManager;
import org.gjt.sp.jedit.msg.BufferUpdate;

public class AortaPlugin extends EBPlugin {
    public static final String NAME            = "aorta";
    public static final String MENU            = "aorta.menu";
    public static final String PROPERTY_PREFIX = "plugin.aorta.";
    public static final String OPTION_PREFIX   = "options.aorta.";

    static {
        try {
            // set some properties
            org.gjt.sp.jedit.jEdit.setProperty("sidekick.parser.aorta.label", "Aorta");
            org.gjt.sp.jedit.jEdit.setProperty("mode.aorta.sidekick.parser", "aorta");
            org.gjt.sp.jedit.jEdit.setProperty("sidekick.parser.aorta_metamodel.label", "Aorta Metamodel");
            org.gjt.sp.jedit.jEdit.setProperty("mode.aorta_metamodel.sidekick.parser", "aorta_metamodel");
        } catch (Exception e) {
        }
    }

	@Override
    public void handleMessage(EBMessage msg) {
        if (org.gjt.sp.jedit.jEdit.getViews().length > 0) {
            final DockableWindowManager d = org.gjt.sp.jedit.jEdit.getViews()[0].getDockableWindowManager();
            if (d.getDockableWindow(NAME) == null) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        d.addDockableWindow(NAME);
                    }
                });
            }
        }

        if (msg != null && msg instanceof BufferUpdate) {
            final BufferUpdate bu = (BufferUpdate) msg;
            if ((bu.getWhat() == BufferUpdate.LOADED || bu.getWhat() == BufferUpdate.CREATED)) {
                if (bu.getBuffer().getPath().endsWith(MAS2JProject.EXT)) {

                    if (org.gjt.sp.jedit.jEdit.getViews().length > 0) {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                // close all other files
                                Buffer[] bufs = org.gjt.sp.jedit.jEdit.getBuffers();
                                for (int i = 0; i < bufs.length; i++) {
                                    if (!bufs[i].equals(bu.getBuffer())) {
                                        org.gjt.sp.jedit.jEdit.closeBuffer(org.gjt.sp.jedit.jEdit.getViews()[0], bufs[i]);
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    public void start() {
        handleMessage(null);
    }
}
