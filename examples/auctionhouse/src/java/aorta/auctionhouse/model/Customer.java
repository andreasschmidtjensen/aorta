/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.auctionhouse.model;

import alice.tuprolog.Int;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import jason.asSyntax.Atom;
import jason.asSyntax.Literal;
import jason.asSyntax.LiteralImpl;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.StringTermImpl;

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

	public Literal toJason() {
		Literal result = new LiteralImpl("registered");
		result.addTerm(new Atom(name));
		result.addTerm(new StringTermImpl(address));
		result.addTerm(new StringTermImpl(account));
		return result;
	}

	public Literal verifiedToJason() {
		Literal result = new LiteralImpl("verified");
		result.addTerm(new Atom(name));
		return result;
	}

	@Override
	public String toString() {
		String s = toProlog().toString();
		return s;
	}

}
