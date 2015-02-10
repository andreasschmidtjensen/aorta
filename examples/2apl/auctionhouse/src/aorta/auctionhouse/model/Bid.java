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
import apapl.data.APLVar;

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
	
	public APLFunction toAPL() {
		APLFunction result = 
				new APLFunction("bid", 
						new APLNum(auctionId), 
						new APLIdent(bidder), 
						bid == 0 ? new APLVar("Bid") : new APLNum(bid));
		return result;
	}
	
	public APLFunction highToAPL() {
		APLFunction result = 
				new APLFunction("bid_high", 
						new APLNum(auctionId), 
						new APLIdent(bidder), 
						bid == 0 ? new APLVar("Bid") : new APLNum(bid));
		return result;
	}
	
	public APLFunction errorToAPL() {
		APLFunction result = new APLFunction("bid_error", new APLNum(auctionId), new APLIdent(bidder));
		return result;
	}
	
	@Override
	public String toString() {
		return toProlog().toString();
	}
		
}
