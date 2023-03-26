package com.neobank.spacemoney.model;

public class Client {

	public String name;

	public Integer age;

	public String rfc;

	public String cellPhoneCountryCode;

	public boolean isBlacklisted;

	public boolean isLegalAge;

	public boolean isAuthorizedCountry;

	public boolean isCandidate;

	public Account account;

	@Override
	public String toString() {
		return "Client [name=" + name + ", age=" + age + ", rfc=" + rfc + ", cellPhoneCountryCode="
				+ cellPhoneCountryCode + ", isBlacklisted=" + isBlacklisted + ", isLegalAge=" + isLegalAge
				+ ", isAuthorizedCountry=" + isAuthorizedCountry + ", isCandidate=" + isCandidate + ", account="
				+ account + "]";
	}

	

}
