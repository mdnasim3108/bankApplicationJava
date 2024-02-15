package com.company.bank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.company.bank.data.configData.config;
import com.company.bank.data.userData.User;
import com.company.bank.service.service;
import com.company.bank.service.userService;

public class bankExecutor {
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner s=new Scanner(System.in);
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		config conf=new config();
		
		service bankService=new service();
		userService us=new userService();
		User currUser=null;
		String username=null,password,bankName;
		boolean flag,isLoggedIn=false;
		int userCount;
		double balance;
		
			do {
				userCount=0;
			//prompt user for Log in unless the credentials are correct
			while(!isLoggedIn) {
				
			System.out.println("1.)Login");
			System.out.println("2.)help and support");
			switch(s.nextLine()) {
			case "1":
				
					System.out.println("enter username:");
					username=s.nextLine();
					System.out.println("enter password");
					password=s.nextLine();
					currUser=us.authenticate(username, password);
					if(currUser!=null) isLoggedIn=true;
					else System.out.println("Invalid credentials!Try again");
					break;
				
			case "2":
					System.out.println("enter username:");
					username=s.nextLine();
					if(!us.checkIfUserExist(username)) System.out.println("user does not exist");
					else if(bankService.getCompliant(us.findUser(username))!=null && !bankService.getCompliant(us.findUser(username)).getResolved()) {
						
						System.out.println("Your previous compliant is not resolved yet,Please wait");
						
					}
					else{
						System.out.println("enter compliant:");
						bankService.addCompliant(us.findUser(username),s.nextLine() );
						System.out.println("compliant raised sucessfully");
					}
					
					break;
			default:
					System.out.println("invalid option");
			}
			
			}
			
		
		//admin Menu
		System.out.println("welcome back "+currUser.getName());
		if(currUser.getRole().equals("admin")) {
			do {
				
				System.out.println("1.)add User");
				System.out.println("2.)remove User");
				System.out.println("3.)show users");
				System.out.println("4.)resolve compliants");
				System.out.println("5.)show compliants");
			switch(s.nextLine()) {
			case "1":
				System.out.println("Enter username:");
				username=s.next();
				System.out.println("Enter password:");
				password=s.next();
				System.out.println("Enter bank name:");
				bankName=s.next();
				System.out.println("Enter balance:");
				balance=s.nextDouble();
				User u=us.addUser(new User(username,password,balance,"user",bankName));
				if(u!=null)System.out.println("user added sucessfully");
				else System.out.println("user already exists!!");
				break;
			case "2":
				System.out.println("Enter username:");
				username=s.next();
				flag=us.removeUser(username);
				if(flag) System.out.println("user removed sucessfully");
				else System.out.println("user not found");
				break;
			case "3":
				us.showUsers();
				break;
			case "4":
				System.out.println("enter user name");
				username=s.next();
				if(!us.checkIfUserExist(username)) System.out.println("user does not exist");
				else {
					bankService.resolveCompliant(us.findUser(username));
					System.out.println("compliant resovled sucessfully");
				}
				break;
			case "5":
				bankService.showCompliants();
				break;
		default:
				System.out.println("invalid option");
			}
			System.out.println("do you wish to continue? y/n");
		}while(s.nextLine().charAt(0)=='y');
	}
		
		//user menu
		else {
			do {
			System.out.println("1.) show balance");
			System.out.println("2.) withdraw");
			System.out.println("3.) deposit");
			System.out.println("4.) View bank statement");
			System.out.println("5.) Online transfer");
          switch(s.nextInt()) {
          	case 1:
          		bankService.showBalance(currUser);
          		
          		break;
          	case 2:
          		System.out.println("enter amount:");
          		bankService.withDraw(currUser, conf, s.nextInt());
          		
          		break;
          	case 3:
          		System.out.println("enter amount:");
          		bankService.deposit(currUser, conf, s.nextInt());
          		
          		break;
          	case 4:
        	  	bankService.showStatement(currUser);
        	  	
        	  	break;
          	case 5:
          		System.out.println("enter reciever name");
          		String rec=s.next();
          		if(us.checkIfUserExist(rec)) {
          			System.out.println("enter amount:");
          			bankService.onlineTransfer(currUser, us.findUser(rec), s.nextDouble());
          		}
          		else {
          			System.out.println("user does not exist!");
          		}
          		
          		break;
          default:
          		System.out.println("Invalid option");
          }
          userCount++;
          if(userCount<3)
          System.out.println("do you wish to continue? y/n");
		}while(userCount<3 && s.nextLine().charAt(0)=='y'  );
		}
		System.out.println("session completed.");
		System.out.println("1.)Go to Home");
		System.out.println("2.)terminate process");
		isLoggedIn=false;
		}while(s.nextLine().equals("1"));
			s.close();
		}
}
	
	

