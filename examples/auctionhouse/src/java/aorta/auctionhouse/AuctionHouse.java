/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.auctionhouse;

import aorta.auctionhouse.model.Auction;
import aorta.auctionhouse.model.Bid;
import aorta.auctionhouse.model.Customer;
import jason.NoValueException;
import jason.asSyntax.Atom;
import jason.asSyntax.Literal;
import jason.asSyntax.LiteralImpl;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.StringTerm;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Andreas
 */
public class AuctionHouse extends Environment {

	private Map<Integer, Auction> auctions = new HashMap<>();
	private Map<String, Customer> customers = new HashMap<>();
	private static int auctionIdCounter = 1;

	private long timeOffset;
	private long time;

	private Thread thread;

	@Override
	public void init(String[] args) {
		timeOffset = System.currentTimeMillis();

		Literal badInfo = new LiteralImpl("badInfo");
		badInfo.addTerm(new Atom("bob"));
		addPercept(badInfo);
		
		thread = new Thread() {
			@Override
			public void run() {
				while (!isInterrupted()) {
					long newTime = getTime();
					if (time < newTime) {
						Literal percept = new LiteralImpl("time");
						percept.addTerm(new NumberTermImpl(time));
						removePercept(percept);

						time = newTime;

						percept = new LiteralImpl("time");
						percept.addTerm(new NumberTermImpl(time));
						addPercept(percept);
					}

					for (Auction a : auctions.values()) {
						if (a.getEndTime() <= time) {
							Literal ended = new LiteralImpl("auction_done");
							ended.addTerm(new NumberTermImpl(a.getId()));

							String winner = "no_winner";
							int bid = 0;
							if (a.getCurrentBid() != null) {
								winner = a.getCurrentBid().getBidder();
								bid = a.getCurrentBid().getBid();
							}
							a.setEnded(true);
							ended.addTerm(new Atom(winner));
							ended.addTerm(new NumberTermImpl(bid));

							addPercept(ended);

							addPercept(winner, a.wonToJason());
						}
					}

					try {
						sleep(1000);
					} catch (InterruptedException ex) {
						break;
					}
				}
			}
		};
		thread.start();
	}

	@Override
	public void stop() {
		thread.interrupt();
	}

	@Override
	public synchronized boolean executeAction(String agName, Structure act) {
		switch (act.getFunctor()) {
			case "register": {
				String address = ((StringTerm) act.getTerm(0)).getString();
				String account = ((StringTerm) act.getTerm(1)).getString();
				return register(agName, address, account);
			}
			case "verify": {
				String name = ((LiteralImpl) act.getTerm(0)).getFunctor();
				return verify(name);
			}
			case "start_auction": {
				int startPrice, durationInSec;
				try {
					startPrice = (int) ((NumberTerm) act.getTerm(1)).solve();
					durationInSec = (int) ((NumberTerm) act.getTerm(2)).solve();
				} catch (NoValueException ex) {
					throw new RuntimeException(ex);
				}

				String name = act.getTerm(0).toString();
				int endTime = (int) ((System.currentTimeMillis() - timeOffset) / 1000 + durationInSec);
				return startAuction(name, agName, startPrice, endTime);
			}
			case "bid": {
				int bid, id;
				try {
					id = (int) ((NumberTerm) act.getTerm(0)).solve();
					bid = (int) ((NumberTerm) act.getTerm(1)).solve();
				} catch (NoValueException ex) {
					throw new RuntimeException(ex);
				}

				return bid(id, agName, bid);
			}
			case "pay": {
				int id;
				try {
					id = (int) ((NumberTerm) act.getTerm(0)).solve();
				} catch (NoValueException ex) {
					throw new RuntimeException(ex);
				}

				return pay(id, agName);
			}
			case "deliver": {
				int id;
				try {
					id = (int) ((NumberTerm) act.getTerm(0)).solve();
				} catch (NoValueException ex) {
					throw new RuntimeException(ex);
				}

				return deliver(id, agName);
			}
			case "enter_auction": {
				int id;
				try {
					id = (int) ((NumberTerm) act.getTerm(0)).solve();
				} catch (NoValueException ex) {
					throw new RuntimeException(ex);
				}
				return addToAuction(id, agName);
			}
			case "leave_auction": {
				int id;
				try {
					id = (int) ((NumberTerm) act.getTerm(0)).solve();
				} catch (NoValueException ex) {
					throw new RuntimeException(ex);
				}

				return removeFromAuction(id, agName);
			}
			case "remove_auction": {
				try {
					int id = (int) ((NumberTerm) act.getTerm(0)).solve();

					return removeAuction(id);
				} catch (NoValueException ex) {
					throw new RuntimeException(ex);
				}
			}
			case "add_to_auction": {
				int id;
				try {
					id = (int) ((NumberTerm) act.getTerm(0)).solve();
				} catch (NoValueException ex) {
					throw new RuntimeException(ex);
				}

				String agent = ((LiteralImpl) act.getTerm(1)).getFunctor();
				return addToAuction(id, agent);
			}
			case "remove_from_auction": {
				int id;
				try {
					id = (int) ((NumberTerm) act.getTerm(0)).solve();
				} catch (NoValueException ex) {
					throw new RuntimeException(ex);
				}

				String agent = ((LiteralImpl) act.getTerm(1)).getFunctor();
				return removeFromAuction(id, agent);
			}
		}
		return super.executeAction(agName, act);
	}

	private boolean register(String name, String address, String account) {
		if (!customers.containsKey(name)) {
			Customer c = new Customer(name, address, account);
			customers.put(name, c);
			addPercept(c.toJason());
			addPercept(c.toJason2());
			return true;
		} else {
			return false;
		}
	}

	private boolean verify(String name) {
		if (customers.containsKey(name)) {
			Customer c = customers.get(name);
			c.setVerified(true);
			addPercept(c.verifiedToJason());
			return true;
		} else {
			return false;
		}
	}

	private boolean startAuction(String name, String agName, int startPrice, int endTime) {
		if (customers.containsKey(agName)) {
			Auction a = new Auction(generateId(), name, agName, startPrice, endTime);
			auctions.put(a.getId(), a);
			addPercept(a.toJason());
			addPercept(agName, a.myAuctionToJason());

			a.addParticipant(agName);
			addPercept(createParticipantPercept(a.getId(), agName));

			return true;
		} else {
			return false;
		}
	}

	private boolean bid(int id, String agName, int bid) {
		if (customers.containsKey(agName) && auctions.containsKey(id) && auctions.get(id).participates(agName)) {
			Auction auction = auctions.get(id);
			Bid prev = auction.getCurrentBid();
			Bid b = new Bid(id, agName, bid);
			if (auction.bid(b)) {
				if (prev != null) {
					removePercept(prev.highToJason());
				}
				addPercept(b.toJason());
				addPercept(b.highToJason());
				return true;
			} else {
				addPercept(b.errorToJason());
				return true;
			}
		} else {
			return false;
		}
	}

	private boolean pay(int id, String agName) {
		if (customers.containsKey(agName) && auctions.containsKey(id)) {
			Auction a = auctions.get(id);
			Bid currentBid = a.getCurrentBid();
			if (a.getEndTime() <= time && !a.isPaid() && currentBid != null && currentBid.getBidder().equals(agName)) {
				a.setPaid(true);
				addPercept(createPaidPercept(id));

				return true;
			}
		}

		return false;
	}

	private boolean deliver(int id, String agName) {
		if (customers.containsKey(agName) && auctions.containsKey(id)) {
			Auction a = auctions.get(id);
			Bid currentBid = a.getCurrentBid();
			if (a.getSeller().equals(agName) && currentBid != null) {
				a.setDelivered(true);
				addPercept(createDeliveredPercept(id));

				return true;
			}
		}

		return false;
	}

	private boolean removeAuction(int id) {
		if (auctions.get(id) != null) {
			Auction a = auctions.remove(id);
			for (String ag : a.getParticipants()) {
				Literal percept = createParticipantPercept(id, ag);
				removePercept(percept);
			}
			if (a.isPaid()) {
				removePercept(createPaidPercept(id));
			}
			if (a.isDelivered()) {
				removePercept(createDeliveredPercept(id));
			}
			removePercept(a.toJason());
			removePercept(a.getSeller(), a.myAuctionToJason());

			return true;
		} else {
			return false;
		}
	}

	private boolean addToAuction(int id, String agent) {
		if (customers.containsKey(agent) && auctions.containsKey(id) && !auctions.get(id).participates(agent)) {
			Auction auction = auctions.get(id);
			auction.addParticipant(agent);

			Literal percept = createParticipantPercept(id, agent);
			addPercept(percept);
			
			addPercept(createParticipantPercept2(agent, auction.getName()));

			return true;
		} else {
			return false;
		}
	}

	private boolean removeFromAuction(int id, String agent) {
		if (auctions.containsKey(id) && auctions.get(id).participates(agent)) {
			Bid newHighest = auctions.get(id).removeParticipant(agent);

			// TODO: Remove bids by that agent
			Literal percept = createParticipantPercept(id, agent);
			removePercept(percept);
			
			Bid bid = new Bid(id, agent, 0);
			removePerceptsByUnif(bid.toJason());
			
			if (newHighest != null) {
				removePerceptsByUnif(bid.highToJason());
				addPercept(newHighest.highToJason());
			}

			return true;
		} else {
			return false;
		}
	}

	private Literal createParticipantPercept(int id, String ag) {
		Literal percept = new LiteralImpl("participant");
		percept.addTerm(new NumberTermImpl(id));
		percept.addTerm(new Atom(ag));
		return percept;
	}
	private Literal createParticipantPercept2(String ag, String name) {
		Literal percept = new LiteralImpl("participates");
		percept.addTerm(new Atom(ag));
		percept.addTerm(new Atom(name));
		return percept;
	}

	private Literal createPaidPercept(int id) {
		Literal percept = new LiteralImpl("paid");
		percept.addTerm(new NumberTermImpl(id));
		return percept;
	}

	private Literal createDeliveredPercept(int id) {
		Literal percept = new LiteralImpl("delivered");
		percept.addTerm(new NumberTermImpl(id));
		return percept;
	}

	private long getTime() {
		return (System.currentTimeMillis() - timeOffset) / 1000;
	}

	private synchronized int generateId() {
		return auctionIdCounter++;
	}

}
