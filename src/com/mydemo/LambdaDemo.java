package com.mydemo;

@FunctionalInterface
interface AccountCheck{
	boolean isAverageBalanceMaintained(BankAccount acc, String str);
}


// Data bean
class BankAccount{
	
	public BankAccount(account_type type, int account_number, int balance) {
		super();
		this.type = type;
		this.account_number = account_number;
		this.balance = balance;
	}
	enum account_type{
		SAVINGS, CURRENT		
	};
	account_type type;
	int account_number;
	int balance;

	public void setBalance(int balance) {
		this.balance = balance;
	}	
}

class Teller{
	void showAccountBalanceStatus(AccountCheck check, BankAccount ac){
		System.out.println("Average balance is" + (!check.isAverageBalanceMaintained(ac, "About to print the balance stats...")?" NOT":"") + " maintained.\n");
	}
}

public class LambdaDemo {
	
	public static void main(String[] args) {
		BankAccount account = new BankAccount(BankAccount.account_type.SAVINGS, 1234, 500);

		// 1) Valid (if used with return statement.)
		AccountCheck check = (BankAccount ac, String str) -> {
			System.out.println(str);
			System.out.println("This is printed from a lambda expression. Useful syntax when the body of functional method involves multiple statements");
			return ac.balance>5000;
		};
		
		Teller teller = new Teller();
		teller.showAccountBalanceStatus(check,account);
		
		System.out.println("Now changing the account balance...\n");
		account.setBalance(5100);

		teller.showAccountBalanceStatus(check,account);

		// 2) Valid (if method body contains only a single statement)
		AccountCheck check2 = (BankAccount ac, String str)->ac.balance>5000;
		
		// 3) Valid (variation of above (#2) - here we can omit the type of parameters in the lambda expression)
		AccountCheck check3 = (ac, str)->ac.balance>5000;
	}

}
