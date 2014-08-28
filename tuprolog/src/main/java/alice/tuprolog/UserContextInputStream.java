package alice.tuprolog;

import java.io.IOException;
import java.io.InputStream;
import java.util.EventListener;

import alice.tuprolog.event.ReadEvent;
import alice.tuprolog.event.ReadListener;


public class UserContextInputStream extends InputStream implements Runnable{
	
	private boolean avalaible;
	private boolean start;
	private int i;
	private String context;	
	private InputStream result;
	private EventListener listener; 
	
	public UserContextInputStream(String cont)
	{
		this.context = cont;
		this.start = true;
	}

	public synchronized InputStream getInput()
	{
		while (avalaible == false){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		avalaible = false;
		notifyAll();
		return this.result;
	}
	
	public synchronized void putInput(InputStream input)
	{
		while (avalaible == true){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(this.result != input)
		{
			
		}
		this.result = input;
		avalaible = true;
		notifyAll();
	}
	
	public void setCounter(){
		start = true;
		result = null;
	}

	public int read() throws IOException
	{
		if(context.compareTo("console") == 0)
		{
			
			try {
				while((i = System.in.read()) != -1)
				{
					return i;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		else if (context.compareTo("graphic") == 0)
		{
			if(start)
			{
				fireReadCalled();
				getInput();
				start = false;
			}
				
			 do {
		            try {
		            	i = result.read();
		    			
		    			if(i == -1)
		    			{
		    				fireReadCalled();
		    				getInput();
		    				i = result.read();
		    			}
					} catch (IOException e) {
						e.printStackTrace();
					}
		        } while (i < 0x20 && i >= -1);	
		}	
		return i;					
	}
	
	private void fireReadCalled()
	{
		ReadEvent event = new ReadEvent(this);
		((ReadListener) listener).readCalled(event);
	}
	
	public void setReadListener(ReadListener r)
	{
		this.listener = r;
	}
	
	@Override
	public void run() {}
}
