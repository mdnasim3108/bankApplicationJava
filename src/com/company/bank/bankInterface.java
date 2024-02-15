package com.company.bank;

import com.company.bank.data.configData.config;
import com.company.bank.data.userData.User;

public interface bankInterface {
	public void showBalance(User u);
	public void withDraw(User u,config c,int amount);
	public void deposit(User u,config c,int amount);
	public void addBankStatement(User u,String type,double amount);
}
