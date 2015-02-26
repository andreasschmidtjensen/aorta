/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package auctionhouse.model;

import ail.syntax.Literal;
import ail.syntax.NumberTermImpl;
import ail.syntax.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Andreas
 */
public class Auction {
	
	private int id;
	private String name;
	private String seller;
	private int startPrice;
	private int endTime;
	private int duration;
	private Bid currentBid;
	private final List<Bid> allBids = new ArrayList<>();
	private final Set<String> participants;
	private boolean paid;
	private boolean delivered;
	private boolean ended;
	

	public Auction(int id, String name, String seller, int startPrice,int duration) {
		this.id = id;
		this.name = name;
		this.seller = seller;
		this.startPrice = startPrice;
		this.duration = duration;
		
		participants = new HashSet<>();
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getDuration() {
		return duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public int getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public boolean bid(Bid bid) {
		if (isEnded()) {
			return false;
		}
		
		if (currentBid == null || (bid.getBid() > 0 && bid.getBid() > currentBid.getBid())) {
			currentBid = bid;
			allBids.add(currentBid);
			return true;
		} else {
			return false;
		}
	}

	public int getNumBids() {
		return allBids.size();
	}
	
	public void addParticipant(String agent) {
		participants.add(agent);
	}
	
	/** 
	 * returns new highest bid
	 * @param agent
	 * @return 
	 */
	public Bid removeParticipant(String agent) {
		participants.remove(agent);
		
		Bid newBid = null;
		for (int i = allBids.size() - 1; i >= 0; i--) {
			Bid bid = allBids.get(i);
			if (!bid.getBidder().equals(agent)) {
				newBid = bid;
				break;
			}
		}
		
		if (newBid != null) {
			currentBid = newBid;
		} else {
			currentBid = null;
		}
		return currentBid;
	}

	public boolean participates(String agent) {
		return participants.contains(agent);
	}
	
	public Set<String> getParticipants() {
		return participants;
	}
	
	public Bid getCurrentBid() {
		return currentBid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public boolean isDelivered() {
		return delivered;
	}
	
	public Literal toJason() {
		Literal result = new Literal("auction");
		result.addTerm(new NumberTermImpl(id));
		result.addTerm(new Predicate(name));
		result.addTerm(new Predicate(seller));
		result.addTerm(new NumberTermImpl(startPrice));
		result.addTerm(new NumberTermImpl(endTime));
		return result;
	}
		
	public Literal doneToJason() {
		Literal result = new Literal("auction_done");
		result.addTerm(new NumberTermImpl(id));
		if (currentBid != null) {
			result.addTerm(new Predicate(currentBid.getBidder()));
			result.addTerm(new NumberTermImpl(currentBid.getBid()));
		} else {
			result.addTerm(new Predicate("no_winner"));
			result.addTerm(new NumberTermImpl(0));
		}
		return result;
	}
		
	public Literal myAuctionToJason() {
		Literal result = new Literal("my_auction");
		result.addTerm(new NumberTermImpl(id));
		return result;
	}
		
	public Literal wonToJason() {
		Literal result = new Literal("won");
		if (currentBid != null) {
			result.addTerm(new Predicate(currentBid.getBidder()));
		} else {
			result.addTerm(new Predicate("no_winner"));
		}
		result.addTerm(new Predicate(name));
		return result;
	}
	
	@Override
	public String toString() {
		String s = toJason().toString();
		s += " >" + currentBid + "\n";
		return s;
	}
	
}
