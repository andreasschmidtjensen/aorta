/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.auctionhouse.model;

import alice.tuprolog.Struct;
import apapl.data.APLFunction;
import apapl.data.APLIdent;

/**
 *
 * @author Andreas
 */
public class Customer {

	private String name;
	private String address;
	private String account;
	private boolean verified;

	public Customer(String name, String address, String account) {
		this.name = name;
		this.address = address;
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Struct toProlog() {
		Struct result = new Struct("registered", new Struct(name), new Struct(address), new Struct(account));
		return result;
	}
	public Struct verifiedToProlog() {
		Struct result = new Struct("verified", new Struct(name));
		return result;
	}

	public APLFunction toAPL() {
		APLFunction result = new APLFunction("registered", new APLIdent(name), new APLIdent(address), new APLIdent(account));
		return result;
	}

	public APLFunction verifiedToAPL() {
		APLFunction result = new APLFunction("verified", new APLIdent(name));
		return result;
	}

	@Override
	public String toString() {
		String s = toProlog().toString();
		return s;
	}

}
