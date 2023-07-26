package com.neobank.spacemoney.model;

public class Client {

	public String name;
	
	public Integer age;
	
	public String rfc;
	
	public String cellPhoneCountryCode;
	
	public Account account;

	@Override
	public String toString() {
		return "Client [name=" + name + ", account=" + account + "]";
	}

}
