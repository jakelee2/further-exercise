package others;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Author: Jake Lee
 * Date: May 17th, 2017
 */
/*
 1. The model of Account is "Account class".  
  (1) Each account have its own name.
  (2) Account may or may not have amount or sub-account. 
  (3) In case of Main-account, the account will have sub-accounts but won't have amount.
  (4) In case of Sub-account, the account will have amount but won't have sub-accounts.
  (5) BTW basically any account can have both amount and sub-account, if need

 2. Total amount of arbitrary account was implemented in the following function: 
	public static double calculateTotalValue(Account rootAccount)
 
 3. Print out the account chart one level per line was implemented in the following function: 
	public static void printChartLevels(Account rootAccount)  
	
 4. I added one more function "public Account getAccountByName(String accName)" and "private Account searchAccByName(String accName)"
 	We can find the any Account object belong to a specific user (top main account) by name 
 
 5. The input will be like below.  "Jake/" will have one level deeper nested accounts.  
    "Bonds-3-1" and "Bonds-3-2" will be located at 4th level: 

Bob/
   Bank/
      Checking - $1,000
      Savings - $2,000
   401k - $10,000

Amy/
   Bank/
      CD - $3,000
   Brokerage/
      Bonds - $1,000
      Mutual Funds - $3,000
   401k - $20,000

Jake/
   Bank1/
      Checking-1 - $1,000
      Savings-1 - $6,000
   Bank2/
      Checking-2 - $2,000
      Savings-2 - $9,000
   Brokerage1/
      Bonds-1 - $10,000
      Mutual Funds-1 - $25,000
   Brokerage2/
      Bonds-2 - $15,000
      Mutual Funds-2 - $30,000
   Brokerage3/
      Bonds-3/                <== In case of Bonds-3, it will have one level deeper nested accounts (Bonds-3-1 & Bonds-3-2 at 4th level)
         Bonds-3-1 - $1110.0
         Bonds-3-2 - $2220.0
      Bonds-4 - $5550.0
   401k-1 - $50,000   
   401k-2 - $70,000

 6. Once you run this program, you will see the results below:

Bob's total value: $13000.0
Amy's total value: $27000.0
Jake's total value: $226880.0
Checking-1's total value: $1000.0
Savings-1's total value: $6000.0
Bank2's total value: $11000.0
Brokerage1's total value: $35000.0
Brokerage2's total value: $45000.0
Brokerage3's total value: $8880.0
Bonds-3's total value: $3330.0
Bonds-3-1's total value: $1110.0
Bonds-3-2's total value: $2220.0
Bonds-4's total value: $5550.0

Bob  

Bank  401K  

Checking  Savings  


Amy  

Bank  Brokerage  401K  

CD  Bonds  Mutual Funds  


Jake  

Bank1  Bank2  Brokerage1  Brokerage2  Brokerage3  401K-1  401K-2  

Checking-1  Savings-1  Checking-2  Savings-2  Bonds-1  Mutual Funds-1  Bonds-2  Mutual Funds-2  Bonds-3  Bonds-4  

Bonds-3-1  Bonds-3-2  
	
 */


class Account {
	private String name;
	private Double amount;
	private List<Account> subAccounts;
	
	// Main-account constructor without amount, like Bob, Amy, Bank or Brokage with subAccounts list
	public Account(String name){
		this.name = name;
		this.amount = null;
		this.subAccounts  = new ArrayList<Account>();		
	}
	
	// Sub-account constructor like Checking, CD, Bonds, or Mutual Funds with amount
	public Account(String name, double amount){
		this.name = name;
		this.amount = amount;
		this.subAccounts  = null;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		if(amount == null)	// in case of null, we return 0
			return (double) 0;
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<Account> getSubAccounts() {
		return subAccounts;
	}

	public void setSubAccounts(List<Account> subAccounts) {
		this.subAccounts = subAccounts;
	}

	public void addSubAccount(Account account) {
		if(this.subAccounts == null){
			this.subAccounts = new ArrayList<Account>();	
		}
		this.subAccounts.add(account);
	}

	public Account getAccountByName(String accName){
		if(this.name.equalsIgnoreCase(accName))
			return this;
		return searchAccByName(accName);
	}
	
	private Account searchAccByName(String accName){
		
		Queue<Account> queue = new LinkedList<Account>();
	    for(Account account: this.subAccounts)
		    queue.add(account);	    	
	    
	    while (queue.size() > 0) {
	    	Account node = queue.remove();
	    	
	    	if(node.getName().equalsIgnoreCase(accName))
    			return node;
	    	
	    	if(node.getSubAccounts() != null && node.getSubAccounts().size() > 0){
		        for(Account account: node.getSubAccounts()){
		        	queue.add(account);
		        }
	    	}
	    }
	    return null;
	}

}

public class NestedAccounts{
	
	public static void main(String args[]){
		
		Account ao1 = new Account("Bob");
		ao1.addSubAccount(new Account("Bank"));
		ao1.addSubAccount(new Account("401K", 10000));
		ao1.getAccountByName("Bank").addSubAccount(new Account("Checking", 1000));
		ao1.getAccountByName("Bank").addSubAccount(new Account("Savings", 2000));
		
		Account ao2 = new Account("Amy");
		ao2.addSubAccount(new Account("Bank"));
		ao2.addSubAccount(new Account("Brokerage"));
		ao2.addSubAccount(new Account("401K", 20000));		
		ao2.getAccountByName("Bank").addSubAccount(new Account("CD", 3000));		
		ao2.getAccountByName("Brokerage").addSubAccount(new Account("Bonds", 1000));
		ao2.getAccountByName("Brokerage").addSubAccount(new Account("Mutual Funds", 3000));
		
		Account ao3 = new Account("Jake");
		ao3.addSubAccount(new Account("Bank1"));
		ao3.addSubAccount(new Account("Bank2"));
		ao3.addSubAccount(new Account("Brokerage1"));
		ao3.addSubAccount(new Account("Brokerage2"));
		ao3.addSubAccount(new Account("Brokerage3"));
		ao3.addSubAccount(new Account("401K-1", 50000));
		ao3.addSubAccount(new Account("401K-2", 70000));
		ao3.getAccountByName("Bank1").addSubAccount(new Account("Checking-1", 1000));
		ao3.getAccountByName("Bank1").addSubAccount(new Account("Savings-1", 6000));
		ao3.getAccountByName("Bank2").addSubAccount(new Account("Checking-2", 2000));
		ao3.getAccountByName("Bank2").addSubAccount(new Account("Savings-2", 9000));
		ao3.getAccountByName("Brokerage1").addSubAccount(new Account("Bonds-1", 10000));
		ao3.getAccountByName("Brokerage1").addSubAccount(new Account("Mutual Funds-1", 25000));
		ao3.getAccountByName("Brokerage2").addSubAccount(new Account("Bonds-2", 15000));
		ao3.getAccountByName("Brokerage2").addSubAccount(new Account("Mutual Funds-2", 30000));
		ao3.getAccountByName("Brokerage3").addSubAccount(new Account("Bonds-3"));
		ao3.getAccountByName("Brokerage3").addSubAccount(new Account("Bonds-4", 5550));
		ao3.getAccountByName("Bonds-3").addSubAccount(new Account("Bonds-3-1", 1110));
		ao3.getAccountByName("Bonds-3").addSubAccount(new Account("Bonds-3-2", 2220));

		
		System.out.println(ao1.getName() + "'s total value: $" + calculateTotalValue(ao1));
		System.out.println(ao2.getName() + "'s total value: $" + calculateTotalValue(ao2));
		System.out.println(ao3.getName() + "'s total value: $" + calculateTotalValue(ao3));
		System.out.println(ao3.getAccountByName("Checking-1").getName() + "'s total value: $" + calculateTotalValue(ao3.getAccountByName("Checking-1"))); //Checking-1's total value is $1000.0
		System.out.println(ao3.getAccountByName("Savings-1").getName() + "'s total value: $" + calculateTotalValue(ao3.getAccountByName("Savings-1")));   //Savings-1's total value is $6000.0 
		System.out.println(ao3.getAccountByName("Bank2").getName() + "'s total value: $" + calculateTotalValue(ao3.getAccountByName("Bank2"))); 		  //Bank2's total value is $11000.0
		System.out.println(ao3.getAccountByName("Brokerage1").getName() + "'s total value: $" + calculateTotalValue(ao3.getAccountByName("Brokerage1"))); //Brokerage1's total value is $35000.0 (10000+25000)
		System.out.println(ao3.getAccountByName("Brokerage2").getName() + "'s total value: $" + calculateTotalValue(ao3.getAccountByName("Brokerage2"))); //Brokerage2's total value is $45000.0 (15000+30000)
		System.out.println(ao3.getAccountByName("Brokerage3").getName() + "'s total value: $" + calculateTotalValue(ao3.getAccountByName("Brokerage3"))); //Brokerage2's total value is $45000.0 (15000+30000)
		System.out.println(ao3.getAccountByName("Bonds-3").getName() + "'s total value: $" + calculateTotalValue(ao3.getAccountByName("Bonds-3"))); //Brokerage2's total value is $45000.0 (15000+30000)
		System.out.println(ao3.getAccountByName("Bonds-3-1").getName() + "'s total value: $" + calculateTotalValue(ao3.getAccountByName("Bonds-3-1"))); //Brokerage2's total value is $45000.0 (15000+30000)
		System.out.println(ao3.getAccountByName("Bonds-3-2").getName() + "'s total value: $" + calculateTotalValue(ao3.getAccountByName("Bonds-3-2"))); //Brokerage2's total value is $45000.0 (15000+30000)
		System.out.println(ao3.getAccountByName("Bonds-4").getName() + "'s total value: $" + calculateTotalValue(ao3.getAccountByName("Bonds-4"))); //Brokerage2's total value is $45000.0 (15000+30000)
        System.out.println();
		
		printChartLevels(ao1);
        System.out.println();

		printChartLevels(ao2);
        System.out.println();

		printChartLevels(ao3);
	}
	
	public static double calculateTotalValue(Account rootAccount){
		
		double totalValue = 0;		
		Queue<Account> queue = new LinkedList<Account>();

		queue.add(rootAccount);	    	
	    
	    while (queue.size() > 0) {
	    	Account node = queue.remove();
	    	
	    	if(node.getAmount() != null)
	    		totalValue += node.getAmount();
	    	
	    	if(node.getSubAccounts() != null && node.getSubAccounts().size() > 0){
		        for(Account account: node.getSubAccounts()){
		        	queue.add(account);
		        }
	    	}
	    }		
		return totalValue;
	}
	
	public static void printChartLevels(Account rootAccount){
		
		Queue<Account> queue = new LinkedList<Account>();
	    Queue<Integer> levels = new LinkedList<Integer>();
	    queue.add(rootAccount);	    	
	    levels.add(0);

	    int level = 0, lastLevel = 0;

	    while(queue.size() > 0) {
	    	Account node = queue.remove();
	        level = levels.remove();
	
	        if(lastLevel != level){
	            System.out.println();
	            System.out.println();
	            lastLevel = level;
	        }
	
	        System.out.print(node.getName() + "  ");
	
	    	if(node.getSubAccounts() != null && node.getSubAccounts().size() > 0){
		        for(Account account: node.getSubAccounts()){
		        	queue.add(account);
		            levels.add(level+1);
		        }
	    	}
	    }
        System.out.println();
        System.out.println();
		
	}
}

