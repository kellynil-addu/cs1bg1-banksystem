package cs1bg1.banksystem;

import java.io.IOException;
import java.time.LocalDateTime;

public class Example3 {
    public static void main(String[] args) throws IOException {
        Account account = new SavingsAccount("ACC4444", "john_doe", "John Doe", 5000.0);

        // TransactionLogger and ReceiptWriter are helper classes that do what their names suggest

        // This creates a brand new record file for this account
        // You only have to do this once every time a new account is opened
        TransactionLogger.createRecordFile(account);

        // Add records to this account
        account.withdraw(400.0);
        TransactionLogger.addRecord(account, LocalDateTime.now(), "purchased stuff", -400.0);
        account.withdraw(200.0);
        TransactionLogger.addRecord(account, LocalDateTime.now(), "purchased more stuff", -200.0);
        // LocalDateTime.now() means that the transaction was done just now

        // All output is in "ACC4444.txt"



        
        // To print receipts on the other hand, you use the ReceiptWriter class
        account.withdraw(200.0);
        ReceiptWriter.showWithdrawReceipt(account, 200.0, LocalDateTime.now(), "STAFF1234");
        account.deposit(200.0);
        ReceiptWriter.showDepositReceipt(account, 200.0, LocalDateTime.now(), "STAFF1234");

    }
}
