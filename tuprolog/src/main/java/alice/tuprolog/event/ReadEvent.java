package alice.tuprolog.event;

import java.util.EventObject;
import alice.tuprolog.*;

public class ReadEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private alice.tuprolog.UserContextInputStream stream;
	
	public ReadEvent(UserContextInputStream str) {
		super(str);
		this.stream = str;
	}

	public UserContextInputStream getStream()
	{
		return this.stream;
	}

}
