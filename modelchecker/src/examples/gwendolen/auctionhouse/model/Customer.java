/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gwendolen.auctionhouse.model;

import ail.syntax.Literal;
import ail.syntax.Predicate;
import alice.tuprolog.Struct;
import java.util.Objects;

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
		Literal result = new Literal("registered");
		result.addTerm(new Predicate(name));
		result.addTerm(new Predicate(address));
		result.addTerm(new Predicate(account));
		return result;
	}

	public Literal verifiedToJason() {
		Literal result = new Literal("verified");
		result.addTerm(new Predicate(name));
		return result;
	}

	@Override
	public String toString() {
		String s = toProlog().toString();
		return s;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 17 * hash + Objects.hashCode(this.name);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Customer other = (Customer) obj;
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
		return true;
	}

}
