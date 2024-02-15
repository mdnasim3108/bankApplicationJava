package com.company.bank.service;
import java.util.*;

import com.company.bank.data.userData.User;
public class userService {
		public static LinkedList<User> users;
		static {
			users=new LinkedList();
			users.add(new User("nasim","nasim123",10000,"user","IB"));
			users.add(new User("krishna","krishna123",20000,"user","IB"));
			users.add(new User("deva","deva123",30000,"user","IB"));
			users.add(new User("dinesh","dinesh123",10000,"user","IB"));
			users.add(new User("admin","admin123",0,"admin",""));
		}
		
		public User authenticate(String name,String password) {
				boolean flag=checkIfUserExist(name);
				if(flag) {
					User u=findUser(name);
					if(password.equals(u.getPassword())) return u;
					else return null;
				}
				else return null;
		}
		
		public User addUser(User u) {
			boolean flag=checkIfUserExist(u.getName());
			if(flag) return null;
			else users.add(u);
			return u;
		}
		
		public boolean removeUser(String name) {
			boolean flag=checkIfUserExist(name);
			if(flag) {
				User u=findUser(name);
				users.remove(u);
				return true;
			}
			else return false;
		}
		
		public User findUser(String name) {
			for(User u:users) {
				if(name.equals(u.getName())) return u;
			}
			return null;
		}
		
		public boolean checkIfUserExist(String username) {
			for(User us:users) {
				if(username.equals(us.getName())) return true;
			}
			return false;
		}
		public void showUsers() {
			for(User u:users) {
				if(u.getRole().equals("user"))System.out.println(u.getName()+" "+u.getBalance()+" "+u.getRole()+" "+u.getBankName());
			}
		}
}
