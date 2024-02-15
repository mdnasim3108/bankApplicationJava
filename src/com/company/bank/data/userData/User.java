package com.company.bank.data.userData;

public class User {
	
	private String name;
	private String password;
	private double balance;
	private String role;
	private String bankName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public User(String name,String password,double balance,String role,String bankName) {
		
		this.name=name;
		this.balance=balance;
		this.password=password;
		this.role=role;
		this.bankName=bankName;
	}
	
	
}


