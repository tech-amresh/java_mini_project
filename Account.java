// ===== MEMBER 4: Check Balance + View Details =====
// Account.java - stores the data of one bank account

public class Account {

    // variables (data of the account)
    private int accountNumber;
    private String holderName;
    private int pin;
    private double balance;

    // constructor - runs when we create a new Account object
    public Account(int accountNumber, String holderName, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.pin = pin;
        this.balance = balance;
    }

    // getters (to read the private variables from other files)
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    // setter (used by deposit and withdraw in Customer.java)
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // ---------- FEATURE: Check Balance ----------
    public void checkBalance() {
        System.out.println("\n--- Check Balance ---");
        System.out.println("Your current balance is: Rs. " + balance);
    }

    // ---------- FEATURE: View Details ----------
    public void viewDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Holder Name    : " + holderName);
        System.out.println("Balance        : Rs. " + balance);
    }
}
