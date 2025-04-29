package cs1bg1.banksystem;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Interactions {

    private static Scanner scanner = new Scanner(System.in);

    public static int showMainMenu() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║         WELCOME TO THE BANK SYSTEM         ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║  1. Account                                ║");
        System.out.println("║  2. Deposit Money                          ║");
        System.out.println("║  3. Withdraw Money                         ║");
        System.out.println("║  4. Check Balance                          ║");
        System.out.println("║  5. View All Accounts                      ║");
        System.out.println("║  6. Exit                                   ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public static void showAccountMenu(Bank bank) throws IOException {
        // Check if a username exists
        System.out.print("Enter username to check: ");
        String username = scanner.nextLine();

        if (bank.checkIfExists(username) == true) {
            System.out.println("Account already exists.");
            return;
        }

        // Continue with account creation
        System.out.println();
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║         ACCOUNT REGISTRATION PANEL         ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║ Please enter the details to create account ║");
        System.out.println("╚════════════════════════════════════════════╝");

        // FIXME: Full name should automatically be proper cased
        
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        int type = askForType("Select account type:");
        String fullname = askForFullname("Enter your full name:");
        double init_deposit = askForMinimumAmount("Enter initial deposit:", 1000.0);

    
        Account account;
        String acc_num = bank.generateAccountNumber();
        if (type == 1) {
            account = new SavingsAccount(acc_num, username, fullname, init_deposit);
        } else {
            account = new CheckingAccount(acc_num, username, fullname, init_deposit);
        }
        
        LocalDateTime current_time = LocalDateTime.now();

        bank.addAccount(account);
        bank.saveToFile();

        TransactionLogger.createRecordFile(account);
        TransactionLogger.addRecord(account, current_time, "Initial deposit", init_deposit);

        ReceiptWriter.showDepositReceipt(account, init_deposit, current_time, acc_num);

        System.out.println("✔ Account created successfully.");
        System.out.println("➤ Your new Account Number is: " + account.getAccNum());
        
        Interactions.pressEnterToContinue();
    }

    public static void showDepositMenu(Bank bank) throws IOException {
        // Check if account exists
        System.out.print("Enter username of the account: ");
        String username = scanner.nextLine();
        
        if (bank.checkIfExists(username) == false) {
            System.out.println("Account does not exist.");
            return;
        }

        Account account = bank.getAccountByUsername(username);

        // Ask amount
        System.out.print("Enter amount to deposit: ");
        double amt = scanner.nextDouble(); 
        scanner.nextLine();
        
        if (amt <= 0) {
            System.out.println("Amount must exceed minimum value.");
            return;
        }

        // Call deposit method
        account.deposit(amt);
        bank.saveToFile();
        LocalDateTime current_time = LocalDateTime.now();
        TransactionLogger.addRecord(account, current_time, "Deposit", amt);
        ReceiptWriter.showDepositReceipt(account, amt, current_time, account.getAccNum());
        
        Interactions.pressEnterToContinue();
    }

    public static void showWithdrawMenu(Bank bank) throws IOException {
        // Check if account exists
        System.out.print("Enter username of your account: ");
        String username = scanner.nextLine();
        
        if (bank.checkIfExists(username) == false) {
            System.out.println("Account does not exist. Please register first.");
            return;
        }

        Account account = bank.getAccountByUsername(username);

        if (account.getBalance() <= 0) {
            System.out.println("This account does not have funds to withdraw from.");
            return;
        }

        // Ask amount
        System.out.print("Enter amount to withdraw: ");
        double amt = scanner.nextDouble();
        scanner.nextLine();

        if (amt <= 0) {
            System.out.println("Amount must be a non-negative number.");
            return;
        }

        if (amt > account.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }

        // Call withdraw method
        account.withdraw(amt);
        bank.saveToFile();
        LocalDateTime current_time = LocalDateTime.now();
        TransactionLogger.addRecord(account, current_time, "Withdraw", -amt);
        ReceiptWriter.showWithdrawReceipt(account, amt, current_time, account.getAccNum());
        
        Interactions.pressEnterToContinue();
    }

    public static void showBalanceMenu(Bank bank) {
        // Check if account exists
        System.out.print("Enter username of your account: ");
        String username = scanner.nextLine();
        
        if (bank.checkIfExists(username) == false) {
            System.out.println("Account does not exist. Please register first.");
            return;
        }

        Account acc = bank.getAccountByUsername(username);

        System.out.println();
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║              ACCOUNT SUMMARY               ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("Account No   : " + acc.getAccNum());
        System.out.println("Username     : " + acc.getUsername());
        System.out.println("Full Name    : " + acc.getFullname());
        System.out.println("Account Type : " + acc.getType());
        System.out.println("Balance      : " + String.format("%.2f", acc.getBalance()));

        Interactions.pressEnterToContinue();
    }

    public static void showAllAccounts(Bank bank) throws IOException {
        String table = bank.listAllAccountsInTable();
        System.out.println(table);
        
        FileWriter writer = new FileWriter("account-list.txt");
        writer.write(table);
        writer.close();
        System.out.println("Account list saved to account-list.txt");
        
        Interactions.pressEnterToContinue();
    }

    public static void goodbye() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║    Thank you for using the Bank System!    ║");
        System.out.println("╚════════════════════════════════════════════╝");
    }

    // HELPER FUNCTIONS

    private static void pressEnterToContinue() {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private static int askForType(String msg) {
        int option;
        do {
            System.out.print(msg);
            option = scanner.nextInt();
            scanner.nextLine();
            if (option != 1 && option != 2) {
                System.out.println("Invalid option.");
            }
        } while (option != 1 && option != 2);
        return option;
    }

    private static String askForFullname(String mgs) {
        String fullname;
        do {
            System.out.print(mgs);
            fullname = scanner.nextLine();
            if (fullname.isEmpty()) {
                System.out.println("Full name cannot be empty. Please try again.");
            }
        } while (fullname.isEmpty());
        return fullname;
    }

    private static double askForMinimumAmount(String msg, double min) {
        double amount;
        do {
            System.out.print(msg);
            amount = scanner.nextDouble();
            scanner.nextLine();
            if (amount < min) {
                System.out.println("Amount must exceed the minimum: " + min);
            }
        } while (amount < min);
        return amount;
    }
}
