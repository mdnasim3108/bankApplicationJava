package com.company.bank.service;
import java.util.*;

import com.company.bank.bankInterface;
import com.company.bank.bankStatement.bankSatement;
import com.company.bank.data.configData.config;
import com.company.bank.data.userData.User;


public class service implements bankInterface {
	
		ArrayList<bankSatement> statements=new ArrayList();
		LinkedList<helpSupport> compliants=new LinkedList();
		
		public void showBalance(User u) {
			System.out.println("available balance: "+u.getBalance());
		}
		
		public void withDraw(User u,config c,int amount) {
			if(amount%100!=0) {
				System.out.println("The amount should be multiples of 100");
				return;
			}
			if(amount>u.getBalance()) {
	    		System.out.println("Insufficient balance!");
	    		return;
	    	}
	    	if(u.getBalance()-amount<1000 || ((u.getBalance() - amount) - ((c.withdrawCharges / 100) * (u.getBalance() - amount)))<1000) {
	    		System.out.println("minimum balance could not be maintained");
	    		return;
	    	}
	        double balance = (u.getBalance() - amount) - ((c.withdrawCharges / 100) * (u.getBalance() - amount));
	        u.setBalance(balance);
	        addBankStatement(u, "withDraw", amount);
	        showBalance(u);
		}
		
		public void deposit(User u,config c,int amount) {
				if(amount%100!=0) {
					System.out.println("The amount should be multiples of 100");
					return;
				}
				double charges=(c.depositCharges/100)*amount;
				double balance=(u.getBalance()+amount)-charges;
				u.setBalance(balance);
				addBankStatement(u, "deposit", amount);
				showBalance(u);
		}
		
		public void addBankStatement(User u,String type,double amount) {
				
				statements.add(new bankSatement(u.getName(), type, amount, u.getBalance(), new Date(),u.getBankName()));
		}
		
		public void showStatement(User u) {
				System.out.println("user "+u.getName());
				System.out.println("       -----statement----     ");
				System.out.println("type "+"amount "+"Date "+"balance ");
				for(bankSatement stat:statements) {
					if(stat.username.equals(u.getName()))
					System.out.println(stat.type+" "+stat.amount+" "+stat.transactionDate+" "+stat.userBalance);
					
				}
		}
		
		public void onlineTransfer(User sender,User reciever,double amount) {
			    if(sender.getBalance()-amount<1000 || amount>sender.getBalance()) {
			    		System.out.println("insufficient balance");
			    		return;
			    }
				sender.setBalance(sender.getBalance()-amount);
				addBankStatement(sender,"onlineTransferDebit",amount);
				reciever.setBalance(reciever.getBalance()+amount);
				addBankStatement(reciever,"online transfer credit",amount);
				System.out.println("amount transfered sucessfully");
		}
		
		public void addCompliant(User u,String compliant) {
				
				compliants.add(new helpSupport(compliant,u.getName(),new Date(),"admin"));
		}
		
		public helpSupport getCompliant(User u) {
			for(helpSupport hs:compliants) {
				if(u.getName().equals(hs.getUserName()) && !hs.getResolved()) {
					return hs;
				}
			}
			return null;
		}
		
		public void resolveCompliant(User u) {
			  helpSupport helpSupportOfUser=getCompliant(u);
			  if(helpSupportOfUser!=null) {
				  helpSupportOfUser.setResolved(true);
				  helpSupportOfUser.setDateResolved(new Date());
			  }
			  else {
				  System.out.println("user not found");
			  }
		}
		
		public void showCompliants() {
				System.out.println("userName "+"complaint "+"RaiseDate "+"resolved "+"admin "+"dateResolved ");
				for(helpSupport hs:compliants) {
					System.out.println(hs.getUserName()+" "+hs.getComplaint()+" "+hs.getDate()+" "+hs.getResolved()+" "+"admin "+" "+hs.getDateResolved()+" " );
				}
		}
			
}
