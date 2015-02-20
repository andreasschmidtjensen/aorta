package gwendolen.auctionhouse;

import ail.syntax.Message;

/**
 *
 * @author asj
 */
public class AHBroadcast extends AuctionHouse {
	
	public static final byte tellILF = 1;
	public static final byte achieveILF = 3;

	@Override
	public void addMessage(String agName, Message msg) {
		if (agName.equals("all")) {
			for (String ag : agentmap.keySet()) {
				if (!msg.getSender().equals(ag)) {
					Message m = new Message(msg.getIlForce(), msg.getSender(), ag, msg.getPropCont());
					super.addMessage(ag, m);
				}
			}
		} else {
			super.addMessage(agName, msg);
		}		
	}
	
	@Override
	public String ilfString(int ilf) {
	   if (ilf == tellILF) {
		   return "tell:";
	   } else if (ilf == achieveILF) {
		   return "achieve:";
	   }
	   return ilf + ":";
   }
}