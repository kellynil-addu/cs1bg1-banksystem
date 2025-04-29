package cs1bg1.banksystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {

    // DateTimeFormatter is just a class that helps instruct .format() how to format the date
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

    public static void showWithdrawReceipt(Account acc, double amount, LocalDateTime time, String staff_id) {
        String str = "";

        str += String.format("=============== WITHDRAWAL RECEIPT ===============\n");
        str += String.format("Date & Time   : %s\n", time.format(dtf));
        str += "\n";
        str += String.format("Account Number: %s\n", acc.getAccNum());
        str += String.format("Account Type  : %s\n", acc.getType());
        str += String.format("Customer name : %s\n", acc.getFullname());
        str += String.format("Username      : %s\n", acc.getUsername());
        str += "\n";
        str += String.format("Withdrawn     : %.2f\n", amount);
        str += String.format("New Balance   : %.2f\n", acc.getBalance());
        str += String.format("Staff ID      : %s\n", staff_id);
        str += String.format("==================================================\n");

        System.out.println(str);
    }

    public static void showDepositReceipt(Account acc, double amount, LocalDateTime time, String staff_id) {
        String str = "";

        str += String.format("================ DEPOSIT RECEIPT =================\n");
        str += String.format("Date & Time   : %s\n", time.format(dtf));
        str += "\n";
        str += String.format("Account Number: %s\n", acc.getAccNum());
        str += String.format("Account Type  : %s\n", acc.getType());
        str += String.format("Customer name : %s\n", acc.getFullname());
        str += String.format("Username      : %s\n", acc.getUsername());
        str += "\n";
        str += String.format("Deposited     : %.2f\n", amount);
        str += String.format("New Balance   : %.2f\n", acc.getBalance());
        str += String.format("Staff ID      : %s\n", staff_id);
        str += String.format("==================================================\n");

        System.out.println(str);
    }
}
