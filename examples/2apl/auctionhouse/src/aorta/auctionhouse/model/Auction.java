/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta.auctionhouse.model;

import alice.tuprolog.Int;
import alice.tuprolog.Struct;
import apapl.data.APLFunction;
import apapl.data.APLIdent;
import apapl.data.APLNum;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
	private Bid currentBid;
	private List<Bid> allBids = new ArrayList<>();
	private Set<String> participants;
	private boolean paid;
	private boolean delivered;
	private boolean ended;
	

	public Auction(int id, String name, String seller, int startPrice, int endTime) {
		this.id = id;
		this.name = name;
		this.seller = seller;
		this.startPrice = startPrice;
		this.endTime = endTime;
		
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
		
		Iterator<Bid> it = allBids.iterator();
		while (it.hasNext()) {
			Bid bid = it.next();
			if (bid.getBidder().equals(agent)) {
				it.remove();
			}
		}
		
		if (!allBids.isEmpty()) {
			currentBid = allBids.get(allBids.size() - 1);
			return currentBid;
		} else {
			return null;
		}
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
	
	public Struct toProlog() {
		Struct result = new Struct("auction", new Int(id), new Struct(name), new Struct(seller), new Int(startPrice), new Int(endTime));
		return result;
	}
	
	public APLFunction toAPL() {
		APLFunction result = 
				new APLFunction("auction", 
						new APLNum(id), 
						new APLIdent(name), 
						new APLIdent(seller), 
						new APLNum(startPrice), 
						new APLNum(endTime));
		return result;
	}
		
	public APLFunction myAuctionToAPL() {
		APLFunction result = new APLFunction("my_auction", new APLNum(id));
		return result;
	}
		
	public APLFunction wonToAPL() {
		APLFunction result = new APLFunction("won", new APLNum(id));
		return result;
	}
	
	@Override
	public String toString() {
		String s = toProlog().toString();
		s += " >" + currentBid + "\n";
		return s;
	}
	
}
