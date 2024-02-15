package com.company.bank.bankStatement;
import java.util.*;
public class bankSatement {
	public String username;
    public String type;
    public double amount;
    public double userBalance;
    public Date transactionDate;
    public String bankName;
	public bankSatement(String username, String type, double amount, double userBalance, Date transactionDate,
			String bankName) {
		this.username = username;
		this.type = type;
		this.amount = amount;
		this.userBalance = userBalance;
		this.transactionDate = transactionDate;
		this.bankName = bankName;
	}
}
