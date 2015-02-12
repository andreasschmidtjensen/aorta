package aorta.auctionhouse;

import aorta.auctionhouse.model.Auction;
import aorta.auctionhouse.model.Bid;
import aorta.auctionhouse.model.Customer;
import apapl.Environment;
import apapl.ExternalActionFailedException;
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

	public Term register(String agName, APLIdent address, APLIdent account) throws ExternalActionFailedException {
		if (!customers.containsKey(agName)) {
			Customer c = new Customer(agName, address.getName(), account.getName());
			customers.put(agName, c);
			addPercept(c.toAPL());
			return ok();
		} else {
			throw new ExternalActionFailedException("not a customer");
		}
	}

	public Term verify(String agName, APLIdent agent) throws ExternalActionFailedException {
		if (customers.containsKey(agent.getName())) {
			Customer c = customers.get(agent.getName());
			c.setVerified(true);
			addPercept(c.verifiedToAPL());
			return ok();
		} else {
			throw new ExternalActionFailedException("not a customer");
		}
	}

	public Term startAuction(String agName, APLIdent name, APLNum startPrice, APLNum endTime) throws ExternalActionFailedException {
		if (customers.containsKey(agName)) {
			Auction a = new Auction(generateId(), name.getName(), agName, startPrice.toInt(), endTime.toInt());
			auctions.put(a.getId(), a);
			addPercept(a.toAPL());
			addPercept(agName, a.myAuctionToAPL());

			a.addParticipant(agName);
			addPercept(createParticipantPercept(a.getId(), agName));
			
			Thread t = new Thread() {
				@Override
				public void run() {
					try {
						sleep(endTime.toInt()*1000);
					} catch (InterruptedException ex) {
						return;
					}

					String winner = "no_winner";
					int bid = 0;
					if (a.getCurrentBid() != null) {
						winner = a.getCurrentBid().getBidder();
						bid = a.getCurrentBid().getBid();
					}
					a.setEnded(true);
										
					APLFunction ended = new APLFunction("auction_done", new APLNum(a.getId()), new APLIdent(winner), new APLNum(bid));

					addPercept(ended);

					if (!"no_winner".equals(winner)) {
						addPercept(winner, a.wonToAPL());
					}
									
					System.out.println("Auction ended: " + ended);
				}
			};
			t.start();

			return ok();
		} else {
			throw new ExternalActionFailedException("not a customer");
		}
	}

	public Term bid(String agName, APLNum idA, APLNum bidA) throws ExternalActionFailedException {
		int id = idA.toInt();
		int bid = bidA.toInt();
		if (customers.containsKey(agName) && auctions.containsKey(id) && auctions.get(id).participates(agName)) {
			Auction auction = auctions.get(id);
			Bid prev = auction.getCurrentBid();
			Bid b = new Bid(id, agName, bid);
			if (auction.bid(b)) {
				addPercept(b.toAPL());
				addPercept(b.highToAPL());
				return ok();
			} else {
				addPercept(b.errorToAPL());
				throw new ExternalActionFailedException("not a participant in auction");
			}
		} else {
			throw new ExternalActionFailedException("not a participant in auction");
		}
	}

	public Term pay(String agName, APLNum idA) throws ExternalActionFailedException {
		int id = idA.toInt();
		if (customers.containsKey(agName) && auctions.containsKey(id)) {
			Auction a = auctions.get(id);
			Bid currentBid = a.getCurrentBid();
			if (a.getEndTime() <= time && !a.isPaid() && currentBid != null && currentBid.getBidder().equals(agName)) {
				a.setPaid(true);
				addPercept(createPaidPercept(id));

				return ok();
			}
		}

		throw new ExternalActionFailedException("not the winner in auction");
	}

	public Term deliver(String agName, APLNum idA) throws ExternalActionFailedException {
		int id = idA.toInt();
		if (customers.containsKey(agName) && auctions.containsKey(id)) {
			Auction a = auctions.get(id);
			Bid currentBid = a.getCurrentBid();
			if (a.getSeller().equals(agName) && currentBid != null) {
				a.setDelivered(true);
				addPercept(createDeliveredPercept(id));

				return ok();
			}
		}

		throw new ExternalActionFailedException("not participant in auction");
	}

	public Term enterAuction(String agName, APLNum idA) throws ExternalActionFailedException {
		int id = idA.toInt();
		if (customers.containsKey(agName) && auctions.containsKey(id) && !auctions.get(id).participates(agName)) {
			auctions.get(id).addParticipant(agName);

			APLFunction percept = createParticipantPercept(id, agName);
			addPercept(percept);

			return ok();
		} else {
			throw new ExternalActionFailedException("Could not enter");
		}
	}
	
	public Term removeFromAuction(String agName, APLNum idA, APLIdent agentA) throws ExternalActionFailedException {
		int id = idA.toInt();
		String agent = agentA.getName();
		
		if (auctions.containsKey(id) && auctions.get(id).participates(agent)) {
			Bid newHighest = auctions.get(id).removeParticipant(agent);

			// TODO: Remove bids by that agent
			APLFunction percept = createParticipantPercept(id, agent);
			removePercept(percept);
			
			Bid bid = new Bid(id, agent, 0);
			removePercept(bid.toAPL());
			
			if (newHighest != null) {
				removePercept(bid.highToAPL());
				addPercept(newHighest.highToAPL());
			}

			return ok();
		} else {
			throw new ExternalActionFailedException("Not participating in auction");
		}
	}

	private static APLIdent ok() {
		return new APLIdent("ok");
	}
	
	private APLFunction createPaidPercept(int id) {
		APLFunction percept = new APLFunction("paid", new APLNum(id));
		return percept;
	}

	private APLFunction createDeliveredPercept(int id) {
		APLFunction percept = new APLFunction("delivered", new APLNum(id));
		return percept;
	}
	private APLFunction createParticipantPercept(int id, String ag) {
		APLFunction percept = new APLFunction("participant", new APLNum(id), new APLIdent(ag));
		return percept;
	}
	
	private void addPercept(APLFunction f) {
		log(f.toString());
		throwEvent(f);
	}
	
	private void removePercept(APLFunction f) {
		f = new APLFunction("neg", f);
		throwEvent(f);
	}
	private void removePercept(String agent, APLFunction f) {
		f = new APLFunction("neg", f);
		throwEvent(f, agent);
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
