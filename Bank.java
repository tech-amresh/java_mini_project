// ===== MEMBER 2: Create Account =====
// Bank.java - keeps the list of all accounts and creates new ones

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {

    // ArrayList to store all accounts
    private ArrayList<Account> accounts = new ArrayList<Account>();

    // account numbers start from 1001
    private int nextAccountNumber = 1001;

    // ---------- FEATURE: Create Account ----------
    public void createAccount(Scanner sc) {
        System.out.println("\n--- Create New Account ---");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Set a 4-digit PIN: ");
        int pin = sc.nextInt();

        System.out.print("Enter opening deposit amount: ");
        double balance = sc.nextDouble();

        // validation using if-else
        if (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty! Account not created.");
        } else if (pin < 1000 || pin > 9999) {
            System.out.println("PIN must be exactly 4 digits! Account not created.");
        } else if (balance < 0) {
            System.out.println("Opening balance cannot be negative! Account not created.");
        } else {
            // create the object and add it to the list
            Account newAccount = new Account(nextAccountNumber, name, pin, balance);
            accounts.add(newAccount);

            System.out.println("Account created successfully!");
            System.out.println("Your Account Number is: " + nextAccountNumber);
            System.out.println("(Remember this number for login)");

            nextAccountNumber++;
        }
    }

    // finds an account by number and pin (used by login in Main.java)
    // returns null if no account matches
    public Account findAccount(int accountNumber, int pin) {
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getAccountNumber() == accountNumber && acc.getPin() == pin) {
                return acc;
            }
        }
        return null;
    }
}
