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
	
	public Struct toProlog() {
		Struct result = new Struct("bid", new Int(auctionId), new Struct(bidder), new Int(bid));
		return result;
	}
	
	public Struct errorToProlog() {
		Struct result = new Struct("bid_error", new Int(auctionId), new Struct(bidder));
		return result;
	}
	
	public Literal toJason() {
		Literal result = new LiteralImpl("bid");
		result.addTerm(new NumberTermImpl(auctionId));
		result.addTerm(new Atom(bidder));
		result.addTerm(new NumberTermImpl(bid));
		return result;
	}
	
	public Literal errorToJason() {
		Literal result = new LiteralImpl("bid_error");
		result.addTerm(new NumberTermImpl(auctionId));
		result.addTerm(new Atom(bidder));
		return result;
	}
	
	@Override
	public String toString() {
		return toProlog().toString();
	}
		
}
