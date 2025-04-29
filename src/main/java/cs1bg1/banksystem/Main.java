package cs1bg1.banksystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        
        Bank bank = new Bank("accounts.txt");

        // Ask for staffId and date
        Scanner input = new Scanner(System.in);
        // System.out.print("Enter Staff ID: ");
        // String staffId = input.nextLine();
        // System.out.print("Enter Date (YYYY-MM-DD): ");
        // String date = input.nextLine();

            // FIXME: Can use current date/time instead of asking for it:

        // Show the menu until the user chooses to exit
        
        int choice;

        do {
            choice = Interactions.showMainMenu();

            switch (choice) {
                case 1:
                    Interactions.showAccountMenu(bank);
                    break;
                case 2:
                    Interactions.showDepositMenu(bank);
                    break;
                case 3:
                    Interactions.showWithdrawMenu(bank);
                    break;
                case 4:
                    Interactions.showBalanceMenu(bank);
                    break;
                case 5:
                    Interactions.showAllAccounts(bank);
                    break;
                case 6:
                    Interactions.goodbye();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        } while (choice != 6);
    }
}
