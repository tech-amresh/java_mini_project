// ===== MEMBER 1: Login + Main Menu =====
// Main.java - program starts here

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.println("=====================================");
        System.out.println("   WELCOME TO BANKING MANAGEMENT SYSTEM");
        System.out.println("=====================================");

        boolean running = true;

        // ---------- MAIN MENU ----------
        while (running) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear the leftover Enter key

            switch (choice) {
                case 1:
                    bank.createAccount(sc);   // Member 2's code
                    break;
                case 2:
                    login(sc, bank);
                    break;
                case 3:
                    System.out.println("Thank you for using our bank. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1 to 3.");
            }
        }

        sc.close();
    }

    // ---------- FEATURE: Login ----------
    public static void login(Scanner sc, Bank bank) {
        System.out.println("\n--- Login ---");
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();
        sc.nextLine();

        Account account = bank.findAccount(accNo, pin);

        if (account == null) {
            System.out.println("Login failed! Wrong account number or PIN.");
        } else {
            System.out.println("Login successful! Welcome, " + account.getHolderName());
            accountMenu(sc, account);
        }
    }

    // ---------- MENU AFTER LOGIN ----------
    public static void accountMenu(Scanner sc, Account account) {
        Customer customer = new Customer(account);
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n========== ACCOUNT MENU ==========");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Details");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    customer.deposit(sc);        // Member 3's code
                    break;
                case 2:
                    customer.withdraw(sc);       // Member 3's code
                    break;
                case 3:
                    account.checkBalance();      // Member 4's code
                    break;
                case 4:
                    account.viewDetails();       // Member 4's code
                    break;
                case 5:
                    System.out.println("Logged out successfully.");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1 to 5.");
            }
        }
    }
}
