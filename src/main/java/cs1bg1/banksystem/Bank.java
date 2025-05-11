package cs1bg1.banksystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private String file_path;
    private ArrayList<Account> account_list;

    public Bank(String file_path) throws IOException, FileNotFoundException {
        this.file_path = file_path;

        FileWriter writer = new FileWriter(file_path, true);
        writer.close();

        Scanner scanner = new Scanner(new FileReader(file_path));
        
        account_list = new ArrayList<>();

        while (scanner.hasNextLine()) {

            // Read each line from the file
            String line = scanner.nextLine(); // nextLine() will return: ACC2446,savingstest,John Doe,Savings,9500.00
            Scanner line_scanner = new Scanner(line);
            line_scanner.useDelimiter(",");

            // Read the account details
            String acc_num = line_scanner.next(); // returns: ACC2446
            String username = line_scanner.next(); // returns: savingstest
            String fullname = line_scanner.next(); // returns: John Doe
            String type = line_scanner.next(); 
            double balance = line_scanner.nextDouble();

            if (type.equals("Checking")) {
                account_list.add(new CheckingAccount(acc_num, username, fullname, balance));
            } else if (type.equals("Savings")) {
                account_list.add(new SavingsAccount(acc_num, username, fullname, balance));
            } 

            line_scanner.close();
        }

        scanner.close();
    }

    public String listAllAccountsInTable() {
        String str = "";

        // Looks like this:
        // Account no.  Username    Full Name   Account Type    Balance
        // -----        -----       -----       -----           -----
        // (some account here)

        str += String.format("%-10s %-20s %-30s %-15s %-15s\n", 
            "Account #", "Username", "Full Name", "Account Type", "Balance");

        str += String.format("%-10s %-20s %-30s %-15s %-15s\n",
            "----------", "----------", "----------", "----------", "----------");

        for (Account acc : account_list) {
            str += String.format("%-10s %-20s %-30s %-15s %.2f\n", 
                acc.getAccNum(), 
                acc.getUsername(), 
                acc.getFullname(), 
                acc.getType(), 
                acc.getBalance());
        }

        return str;
    }

    public String generateAccountNumber() {
        String acc_num;
        do {
            int randomNum = (int) (Math.random() * 9000) + 1000;
            acc_num = "ACC" + randomNum;
        } while (isAccNumTaken(acc_num));
        return acc_num;
    }

    public void addAccount(Account acc) {
        account_list.add(acc);
    }

    public boolean isUsernameTaken(String username) {
        for (Account acc : account_list) {
            if (acc.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAccNumTaken(String acc_num) {
        for (Account acc : account_list) {
            if (acc.getAccNum().equals(acc_num)) {
                return true;
            }
        }
        return false;
    }

    public Account getAccountByUsername(String username) {
        for (Account acc : account_list) {
            if (acc.getUsername().equals(username)) {
                return acc;
            }
        }
        return null;
    }

    public void saveToFile() throws IOException {
        FileWriter writer = new FileWriter(file_path);
        for (Account acc : account_list) {
            writer.write(
                String.format("%s,%s,%s,%s,%.2f\n", 
                    acc.getAccNum(), 
                    acc.getUsername(), 
                    acc.getFullname(), 
                    acc.getType(), 
                    acc.getBalance())
            );
        }

        writer.close();
    }
}
