package aorta.auctionhouse;

import aorta.auctionhouse.model.Auction;
import aorta.auctionhouse.model.Customer;
import apapl.Environment;
import apapl.data.APLFunction;
import apapl.data.APLIdent;
import apapl.data.APLNum;
import apapl.data.Term;
import java.util.HashMap;
import java.util.Map;

public class AuctionHouse extends Environment {

	private final boolean log = false;

	private Map<Integer, Auction> auctions = new HashMap<>();
	private Map<String, Customer> customers = new HashMap<>();
	private static int auctionIdCounter = 1;

	private long timeOffset;
	private long time;

	private Thread thread;

	/**
	 * We do not use this method, but we need it so that the JAR file that we will create can point to this class as the main class. This is
	 * only possible if the class contains main method.
	 *
	 * @param args arguments
	 */
	public static void main(String[] args) {
	}

//	@Override
//	public void initialized() {
	public AuctionHouse() {
		super();
	}

	@Override
	protected void addAgent(String ag) {
		super.addAgent(ag); 
		
		APLFunction badInfo = new APLFunction("badInfo", new APLIdent("bob"));
		addPercept(ag, badInfo);
	}

	public Term register(String agName, APLIdent address, APLIdent account) {
		if (!customers.containsKey(agName)) {
			Customer c = new Customer(agName, address.getName(), account.getName());
			customers.put(agName, c);
			addPercept(c.toAPL());
			return new APLIdent("ok");
		} else {
			return null;
		}
	}

	public Term verify(String agName, APLIdent agent) {
		if (customers.containsKey(agent.getName())) {
			Customer c = customers.get(agent.getName());
			c.setVerified(true);
			addPercept(c.verifiedToAPL());
			return new APLIdent("ok");
		} else {
			return null;
		}
	}

	public Term startAuction(String agName, APLIdent name, APLNum startPrice, APLNum endTime) {
		if (customers.containsKey(agName)) {
			Auction a = new Auction(generateId(), name.getName(), agName, startPrice.toInt(), endTime.toInt());
			auctions.put(a.getId(), a);
			addPercept(a.toAPL());
			addPercept(agName, a.myAuctionToAPL());

			a.addParticipant(agName);
			addPercept(createParticipantPercept(a.getId(), agName));

			return new APLIdent("ok");
		} else {
			return null;
		}
	}

	private APLFunction createParticipantPercept(int id, String ag) {
		APLFunction percept = new APLFunction("participant", new APLNum(id), new APLIdent(ag));
		return percept;
	}
	
	private void addPercept(APLFunction f) {
		log(f.toString());
		throwEvent(f);
	}
	
	private void addPercept(String agent, APLFunction f) {
		log("[" + agent + "] " + f.toString());
		throwEvent(f, agent);
	}

	private long getTime() {
		return (System.currentTimeMillis() - timeOffset) / 1000;
	}

	private synchronized int generateId() {
		return auctionIdCounter++;
	}

	private void log(String str) {
		if (log) {
			System.out.println(str);
		}
	}
}
