/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionhouse;

import ail.mas.ActionScheduler;
import ail.mas.DefaultEnvironment;
import ail.semantics.AILAgent;
import ail.syntax.Action;
import ail.syntax.Literal;
import ail.syntax.NumberTerm;
import ail.syntax.NumberTermImpl;
import ail.syntax.Predicate;
import ail.syntax.Unifier;
import ail.util.AILexception;
import ajpf.MCAPLJobber;
import ajpf.util.VerifyMap;
import ajpf.util.VerifySet;
import gov.nasa.jpf.annotation.FilterField;
import auctionhouse.model.Auction;
import auctionhouse.model.Bid;
import auctionhouse.model.Customer;

/**
 * TODO: Update addPercept to allow adding multiple percepts (addPercepts(...) perhaps?)
 * @author Andreas
 */
public class AuctionHouse extends DefaultEnvironment implements MCAPLJobber {

	@FilterField
	private final VerifyMap<Integer, Auction> auctions = new VerifyMap<>();
	
	@FilterField
	private final VerifyMap<String, Customer> customers = new VerifyMap<>();
	
	@FilterField
	private static int auctionIdCounter = 1;
	
	@FilterField
	private final long timeOffset = System.currentTimeMillis();
	
	@FilterField
	private long time;
	
	@FilterField
	private boolean done = true;
	
	private final String name = "auctionhouse";

	public AuctionHouse() {
		ActionScheduler s = new ActionScheduler();
		s.addJobber(this);
		setScheduler(s);
		addPerceptListener(s);
	}

	@Override
	public void addAgent(AILAgent a) {
		super.addAgent(a); 	
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int compareTo(MCAPLJobber j) {
		return j.getName().compareTo(getName());
	}

	@Override
	public void do_job() {
		if (done) {
			getScheduler().notActive(name);
			return;
		}
		
		long newTime = getTime();
		if (time < newTime) {
			time = newTime;

			uptodateAgs.clear();
			notifyListeners();
		}

		boolean allDone = true;
		for (Auction a : auctions.values()) {
			if (a.getEndTime() > 0 && a.getEndTime() <= time) {
				Literal ended = new Literal("auction_done");
				ended.addTerm(new NumberTermImpl(a.getId()));

				String winner = "no_winner";
				int bid = 0;
				if (a.getCurrentBid() != null) {
					winner = a.getCurrentBid().getBidder();
					bid = a.getCurrentBid().getBid();
				}
				a.setEnded(true);
				ended.addTerm(new Predicate(winner));
				ended.addTerm(new NumberTermImpl(bid));

				addPercept(ended);

				addPercept(winner, a.wonToJason());
				
				System.out.println("[" + this + "] Auction " + a.getId() + " for " + a.getName() + " ended - winner: " + winner + ".");
			}
			if (!a.isEnded()) {
				allDone = false;
			}
		}
		
		if (allDone) {
			done = true;
			getScheduler().notActive(name);
		}
	}

	@Override
	public boolean done() {
		return done;
	}
	
	@Override
	public Unifier executeAction(String agName, Action act) throws AILexception {
		Unifier unifier = null;
		
		System.out.println("******************************* [" + agName + "] " + printAction(act) + " ************************************");
		try {
			switch (act.getFunctor()) {
				case "register": {
					String address = ((Predicate) act.getTerm(0)).getFunctor();
					String account = ((Predicate) act.getTerm(1)).getFunctor();
					unifier = register(agName, address, account);
					break;
				}
				case "verify": {
					String agentForVerification = ((Literal) act.getTerm(0)).getFunctor();
					unifier = verify(agName, agentForVerification);
					break;
				}
				case "start_auction": {
					int startPrice, durationInSec;
					startPrice = (int) ((NumberTerm) act.getTerm(1)).solve();
					durationInSec = (int) ((NumberTerm) act.getTerm(2)).solve();

					String auctionName = ((Predicate) act.getTerm(0)).getFunctor();

					unifier = startAuction(auctionName, agName, startPrice, durationInSec);
					break;
				}
				case "bid": {
					int bid, id;
					id = (int) ((NumberTerm) act.getTerm(0)).solve();
					Auction auction = auctions.get(id);
					bid = auction.getCurrentBid() == null ? auction.getStartPrice() : auction.getCurrentBid().getBid() + 5;

					unifier = bid(id, agName, bid);
					
					if (auction.getNumBids() >= 2) {						
						int endTime = (int) ((System.currentTimeMillis() - timeOffset) / 1000 + auction.getDuration());
						auction.setEndTime(endTime);

						getScheduler().isActive(name);
						done = false;
					}
					
					break;
				}
				case "pay": {
					int id;
					id = (int) ((NumberTerm) act.getTerm(0)).solve();

					unifier = pay(id, agName);
					break;
				}
				case "enter_auction": {
					int id;
					id = (int) ((NumberTerm) act.getTerm(0)).solve();
					unifier = addToAuction(id, agName);
					break;
				}
				case "leave_auction": {
					int id;
					id = (int) ((NumberTerm) act.getTerm(0)).solve();

					unifier = removeFromAuction(id, agName);
					break;
				}
				case "add_to_auction": {
					int id;
					id = (int) ((NumberTerm) act.getTerm(0)).solve();

					String agent = ((Literal) act.getTerm(1)).getFunctor();
					unifier = addToAuction(id, agent);
					break;
				}
				case "remove_from_auction": {
					int id;
					id = (int) ((NumberTerm) act.getTerm(0)).solve();

					String agent = ((Literal) act.getTerm(1)).getFunctor();
					unifier = removeFromAuction(id, agent);
					break;
				}
			}
		} catch (AILexception ex) {
			System.err.println("[" + agName + "] " + ex.getMessage());
			throw ex;
		}
		
		if (unifier == null) {
			unifier = super.executeAction(agName, act);
		}
		
		return unifier;
	}

	private Unifier register(String name, String address, String account) throws AILexception {
		if (!customers.containsKey(name)) {
			Customer c = new Customer(name, address, account);
			customers.put(name, c);
			addPercept(c.toJason());
			return new Unifier();
		} else {
			throw new AILexception("Already registered");
		}
	}

	private Unifier verify(String verifier, String name) throws AILexception {
		if (customers.containsKey(name)) {
			Customer c = customers.get(name);
			c.setVerified(true);
			addPercept(verifier, c.verifiedToJason());
			return new Unifier();
		} else {
			throw new AILexception("Not registered");
		}
	}

	private Unifier startAuction(String name, String agName, int startPrice, int duration) throws AILexception {
		if (customers.containsKey(agName)) {
			Auction a = new Auction(generateId(), name, agName, startPrice, duration);
			auctions.put(a.getId(), a);
			addPercept(a.toJason());
			addPercept(agName, a.myAuctionToJason());

			a.addParticipant(agName);
			addPercept(createParticipantPercept(a.getName(), agName));

			Literal doneAuction = new Literal("auction_created");
			doneAuction.addTerm(new Predicate(agName));
			doneAuction.addTerm(new Predicate(a.getName()));
			addPercept(doneAuction);
			return new Unifier();
		} else {
			throw new AILexception("Not registered");
		}
	}

	private Unifier bid(int id, String agName, int bid) throws AILexception {
		if (customers.containsKey(agName) && auctions.containsKey(id) && auctions.get(id).participates(agName)) {
			Auction auction = auctions.get(id);
						
			Bid b = new Bid(id, agName, bid);
			if (auction.bid(b)) {
				removeUnifiesPercept(b.varHighToJason());
				addPercept(b.toJason());
				addPercept(b.highToJason());
				
				Literal doneBid = new Literal("bid_done");
				doneBid.addTerm(new Predicate(agName));
				doneBid.addTerm(new Predicate(auction.getName()));
				addPercept(doneBid);
				return new Unifier();
			} else {
				addPercept(b.errorToJason());
				return new Unifier();
			}
		} else {
			throw new AILexception("bid error: reg:" + customers.containsKey(agName) + ", auction: " + auctions.containsKey(id) + ", participates: " + auctions.get(id).participates(agName));
		}
	}

	private Unifier pay(int id, String agName) throws AILexception {
		if (customers.containsKey(agName) && auctions.containsKey(id)) {
			Auction a = auctions.get(id);
			if (!a.isPaid()) {
				a.setPaid(true);
				addPercept(createPaidPercept(a.getName()));

				return new Unifier();
			}
		}
		throw new AILexception("pay error: " + customers.containsKey(agName) + ", auction: " + auctions.containsKey(id));
	}

	private Unifier addToAuction(int id, String agent) throws AILexception {
		Auction auction = auctions.get(id);
		if (customers.containsKey(agent) && auctions.containsKey(id) && !auction.participates(agent)) {
			auction.addParticipant(agent);

			Literal percept = createParticipantPercept(auction.getName(), agent);
			addPercept(percept);

			return new Unifier();
		} else {
			throw new AILexception("participate error: reg: "+ customers.containsKey(agent) + ", auction: "+ auctions.containsKey(id) +", not participate:"+ !auction.participates(agent));
		}
	}

	@Override
	public void addPercept(Predicate per) {
		switch (per.getFunctor()) {
			case "auction":
				addPercept("bob", per);
				addPercept("carol", per);
				addPercept("sally", per);
				addPerceptSilent("mike", per);
				break;
			case "registered":
				addPercept("bob", per);
				addPercept("carol", per);
				addPerceptSilent("sally", per);
				addPercept("mike", per);
				break;
			case "won":
				addPercept("bob", per);
				addPercept("carol", per);
				addPerceptSilent("sally", per);
				addPerceptSilent("mike", per);
				break;
			case "bid":
				addPerceptSilent("bob", per);
				addPerceptSilent("carol", per);
				addPerceptSilent("sally", per);
				addPercept("mike", per);
				break;
			case "bid_high":
				addPercept("bob", per);
				addPercept("carol", per);
				addPerceptSilent("sally", per);
				addPerceptSilent("mike", per);
				break;
			case "auction_done":
				addPerceptSilent("bob", per);
				addPerceptSilent("carol", per);
				addPercept("sally", per);
				addPerceptSilent("mike", per);
				break;
			default:				
				addPerceptSilent(per);
				break;
		}
	}

	@Override
	public boolean removePercept(Predicate per) {
		switch (per.getFunctor()) {
			case "participant":
				removePerceptSilent("bob", per);
				removePerceptSilent("carol", per);
				removePercept("sally", per);
				removePerceptSilent("mike", per);
				return true;
			default:
				return removePerceptSilent(per);
		}
	}
		
 	public void addPerceptSilent(Predicate per) {
  		if (per != null) {
  			if (! percepts.contains(per)) {
  				percepts.add(per);
  				uptodateAgs.clear();
  			}
  		}
	}	
	
	public void addPerceptSilent(String agName, Predicate per) {
		if (per != null && agName != null) {
			VerifySet<Predicate> agl = agPercepts.get(agName);
			if (agl == null) {
				agl = new VerifySet<>();
				uptodateAgs.remove(agName);
				agl.add(per);
				agPercepts.put( agName, agl);
			} else {
				if (! agl.contains(per)) {
					uptodateAgs.remove(agName);
					agl.add(per);
				}
			}
		}
	}
	
	public boolean removePerceptSilent(Predicate per) {
  		if (per != null) {
  			uptodateAgs.clear();
  			boolean b =  percepts.remove(per);
  			return b;
  		} 
  		return false;
	}
	
	public boolean removePerceptSilent(String agName, Predicate per) {
		if (per != null && agName != null) {
			VerifySet<Predicate> agl = agPercepts.get(agName);
			if (agl != null) {
				uptodateAgs.remove(agName);
				for (Predicate l: agl) {
					if (l.equals(per)) {
						boolean result = agl.remove(l);
						return result;
					}
				}
			}
		}
		
		return false;
	}

	private Unifier removeFromAuction(int id, String agent) throws AILexception {
		Auction auction = auctions.get(id);
		if (auctions.containsKey(id) && auction.participates(agent)) {
			Bid newHighest = auction.removeParticipant(agent);

			Literal percept = createParticipantPercept(auction.getName(), agent);
			removePercept(percept);
			
			// TODO: Remove bids by that agent
			Bid bid = new Bid(id, agent, 0);
			boolean rem = true;
			while (rem) {
				rem = removeUnifiesPercept(bid.toJason());
			}
			
			if (newHighest != null) {
				while (rem) {
					rem = removeUnifiesPercept(bid.highToJason());
				}
				addPercept(newHighest.highToJason());
			}

			return new Unifier();
		} else {
			throw new AILexception("leave error: auction: "+ auctions.containsKey(id) +", participate:"+ auction.participates(agent));
		}
	}
	
	private Literal createParticipantPercept(String item, String ag) {
		Literal percept = new Literal("participates");
		percept.addTerm(new Predicate(ag));
		percept.addTerm(new Predicate(item));
		return percept;
	}

	private Literal createPaidPercept(String item) {
		Literal percept = new Literal("paidFor");
		percept.addTerm(new Predicate(item));
		return percept;
	}
	
	private synchronized int generateId() {
		return auctionIdCounter++;
	}

	private long getTime() {
		return (System.currentTimeMillis() - timeOffset) / 1000;
	}

	@Override
	public String toString() {
		return name + " (" + auctions.size() + " auctions)";
	}

}
