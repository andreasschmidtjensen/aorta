/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aorta.auctionhouse.model;

import alice.tuprolog.Int;
import alice.tuprolog.Struct;
import jason.asSyntax.Atom;
import jason.asSyntax.Literal;
import jason.asSyntax.LiteralImpl;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.StringTermImpl;
import java.util.HashSet;
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
	private Set<String> participants;
	private boolean paid;
	private boolean delivered;

	public Auction(int id, String name, String seller, int startPrice, int endTime) {
		this.id = id;
		this.name = name;
		this.seller = seller;
		this.startPrice = startPrice;
		this.endTime = endTime;
		
		participants = new HashSet<>();
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
		if (currentBid == null || (bid.getBid() > 0 && bid.getBid() > currentBid.getBid())) {
			currentBid = bid;
			return true;
		} else {
			return false;
		}
	}

	public void addParticipant(String agent) {
		participants.add(agent);
	}
	
	public void removeParticipant(String agent) {
		participants.remove(agent);
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
	
	public Literal toJason() {
		Literal result = new LiteralImpl("auction");
		result.addTerm(new NumberTermImpl(id));
		result.addTerm(new StringTermImpl(name));
		result.addTerm(new Atom(seller));
		result.addTerm(new NumberTermImpl(startPrice));
		result.addTerm(new NumberTermImpl(endTime));
		return result;
	}
		
	public Literal myAuctionToJason() {
		Literal result = new LiteralImpl("my_auction");
		result.addTerm(new NumberTermImpl(id));
		return result;
	}
		
	public Literal wonToJason() {
		Literal result = new LiteralImpl("won");
		result.addTerm(new NumberTermImpl(id));
		return result;
	}
	
	@Override
	public String toString() {
		String s = toProlog().toString();
		s += " >" + currentBid + "\n";
		return s;
	}
	
}
