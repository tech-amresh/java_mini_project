// ===== MEMBER 3: Deposit + Withdraw =====
// Customer.java - the logged-in customer who does deposit and withdraw

import java.util.Scanner;

public class Customer {

    // the account this customer is using
    private Account account;

    // constructor
    public Customer(Account account) {
        this.account = account;
    }

    // getter
    public Account getAccount() {
        return account;
    }

    // ---------- FEATURE: Deposit ----------
    public void deposit(Scanner sc) {
        System.out.println("\n--- Deposit Money ---");
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount! Amount must be greater than 0.");
        } else {
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            System.out.println("Rs. " + amount + " deposited successfully.");
            System.out.println("New balance: Rs. " + newBalance);
        }
    }

    // ---------- FEATURE: Withdraw ----------
    public void withdraw(Scanner sc) {
        System.out.println("\n--- Withdraw Money ---");
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount! Amount must be greater than 0.");
        } else if (amount > account.getBalance()) {
            System.out.println("Insufficient balance!");
        } else {
            double newBalance = account.getBalance() - amount;
            account.setBalance(newBalance);
            System.out.println("Rs. " + amount + " withdrawn successfully.");
            System.out.println("New balance: Rs. " + newBalance);
        }
    }
}
