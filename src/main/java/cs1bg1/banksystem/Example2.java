/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cs1bg1.banksystem;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author kelly
 */
public class Example2 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Let's say we have multiple accounts
        Account account1 = new SavingsAccount("ACC7711", "john_doe", "John Doe", 5000.0);
        Account account2 = new CheckingAccount("ACC3232", "jane_doe", "Jane Doe", 3000.0);

        // We create a Bank object to manage these accounts
        Bank bank = new Bank("example_bank.txt"); // the "example_bank.txt" will be explained later
        bank.addAccount(account1);
        bank.addAccount(account2);

        // Now we can list all accounts in the bank
        String accountList = bank.listAllAccountsInTable();
        System.out.println(accountList);

        // We can also check if a username is taken
        System.out.println("Is 'john_doe' taken? " + bank.isUsernameTaken("john_doe"));

        // How do we access a particular account from the bank?
        // For example: we're looking for an account with the username 'jane_doe'
        Account found_account = bank.getAccountByUsername("jane_doe");
        System.out.println("jane_doe's account number is: " + found_account.getAccNum());

        // We can also tell this bank to generate a new account number that does not conflict with existing ones
        // In other words, it will generate any number other than ACC7711 or ACC3232
        System.out.println("A brand new random acc number -> " + bank.generateAccountNumber());

        // Now, in the future if we want to access this bank again, we must save it:
        bank.saveToFile();
        // This will save as "example_bank.txt"
        // And we can load it again in the future
        // Bank bank = new Bank("example_bank.txt");
    }
}
