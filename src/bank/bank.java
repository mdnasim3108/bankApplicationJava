package bank;
import java.util.*;

public class bank {
    static String name = "krishnan";
    static String password="admin";
    static double balance = 10000;
    static double minBalance=1000;
    static void showBalance() {
    	System.out.println("remaining balance " + balance);
    }

    static void withdraw(int amount) {
    	if(amount>balance) {
    		System.out.println("Insufficient balance!");
    		return;
    	}
    	if(balance-amount<1000 || ((balance - amount) - ((0.14 / 100) * (balance - amount)))<1000) {
    		System.out.println("minimum balance could not be maintained");
    		return;
    	}
        balance = (balance - amount) - ((0.14 / 100) * (balance - amount));
        showBalance();
    }

    static void deposit(int amount) {
        balance = (balance + amount) - ((0.012 / 100) * (balance + amount));
        showBalance();
    }

    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        int count=0,flag=0;
        String user_name,password;
        do {
        	if(flag==1) System.out.println("invalid credentials,try again");
        	System.out.println("Enter username:");
        	user_name=s.next();
        	System.out.println("Enter password:");
        	password=s.next();
        	flag=1;
        }while(!user_name.equals(bank.name) && !password.equals(bank.password) );
        	
        System.out.println("welcome krishna,select options");
        do {
            System.out.println("1.) show balance");
            System.out.println("2.) withdraw");
            System.out.println("3.) deposit");

             
            switch (s.nextInt()) {
                case 1:
                	showBalance();
                    break;
                case 2:
                    System.out.println("enter the amount:");
                    withdraw(s.nextInt());
                    break;
                case 3:
                    System.out.println("enter the amount:");
                    deposit(s.nextInt());
                    break;
                default:
                    System.out.println("invalid option");
            }
            
            System.out.println("do you wish to continue? y/n");
            count++;
       
        } while (s.next().charAt(0)=='y' && count<=3  );
        s.close();
        }
}
