/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package auctionhouse.model;

import ail.syntax.Literal;
import ail.syntax.NumberTermImpl;
import ail.syntax.Predicate;
import ail.syntax.VarTerm;

/**
 *
 * @author Andreas
 */
public class Bid {
	
	private int auctionId;
	private String bidder;
	private int bid;

	public Bid(int auctionId, String bidder, int bid) {
		this.auctionId = auctionId;
		this.bidder = bidder;
		this.bid = bid;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public String getBidder() {
		return bidder;
	}

	public void setBidder(String bidder) {
		this.bidder = bidder;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}
	
	public Literal toJason() {
		Literal result = new Literal("bid");
		result.addTerm(new NumberTermImpl(auctionId));
		result.addTerm(new Predicate(bidder));
		result.addTerm(bid == 0 ? new VarTerm("Bid") : new NumberTermImpl(bid));
		return result;
	}
	
	public Literal highToJason() {
		Literal result = new Literal("bid_high");
		result.addTerm(new NumberTermImpl(auctionId));
		result.addTerm(new Predicate(bidder));
		result.addTerm(bid == 0 ? new VarTerm("Bid") : new NumberTermImpl(bid));
		return result;
	}
	
	public Literal varHighToJason() {
		Literal result = new Literal("bid_high");
		result.addTerm(new NumberTermImpl(auctionId));
		result.addTerm(new VarTerm("Bidder"));
		result.addTerm(new VarTerm("Bid"));
		return result;
	}
	
	public Literal errorToJason() {
		Literal result = new Literal("bid_error");
		result.addTerm(new NumberTermImpl(auctionId));
		result.addTerm(new Predicate(bidder));
		return result;
	}
	
	@Override
	public String toString() {
		return toJason().toString();
	}
		
}
