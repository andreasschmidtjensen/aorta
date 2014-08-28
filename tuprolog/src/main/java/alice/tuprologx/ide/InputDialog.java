package alice.tuprologx.ide;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayInputStream;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import alice.tuprolog.UserContextInputStream;
import alice.tuprolog.event.ReadEvent;
import alice.tuprolog.event.ReadListener;


public class InputDialog extends JDialog implements Runnable{

	private static final long serialVersionUID = 1L;
	private JTextArea testo;
	private UserContextInputStream stream;
	private Thread t;
	
	public InputDialog(UserContextInputStream str)
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		initComponent();
		stream = str;
		stream.setReadListener(new ReadListener(){
			@Override
			public void readCalled(ReadEvent event) {
				
				t = new Thread(new InputDialog(stream));
				setVisible(true);
				t.start();
			}
		});
	}
	
	public void initComponent()
	{
		setTitle("Input Console");
		setSize(new Dimension(200,100));
		testo = new JTextArea();
		add(testo);		
		testo.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{ 

					stream.putInput(new ByteArrayInputStream(testo.getText().toString().getBytes()));
					setVisible(false);	
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyTyped(KeyEvent arg0) {}
		});	
	}

	@Override
	public void run() {
		initComponent();
	}
}
