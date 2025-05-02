package cs1bg1.banksystem;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Example1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        // To make an Account object, use:
        Account account = new SavingsAccount("ACC4444", "john_doe", "John Doe", 5000.0);

        // In this Account object, you can do withdraws/deposits:
        account.deposit(1000.0);
        account.withdraw(2000.0);

        // You can check if the deposit/withdraw is feasible before doing the actual process:
        if (account.canDepositAmount(999999.99)) { // If .canDepositAmount() is false, it will display a message with the reason why (in this case, the deposit is too large)
            account.deposit(999999.99);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("The deposit did not go through.");
        }

        // Afterwards, you can get the balance and other details:
        System.out.println("Account number: " + account.getAccNum());
        System.out.println("Username: " + account.getUsername());
        System.out.println("Current balance: " + account.getBalance());
    }
}
