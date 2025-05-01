package cs1bg1.banksystem;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {
    // DateTimeFormatter is just a class that helps instruct .format() how to format the date
    private static DateTimeFormatter datetime_format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void createRecordFile(Account acc) throws IOException {
        String str = "";
        str += String.format("Transaction history for account number %s\n", acc.getAccNum());
        str += String.format("%-20s %-20s %-15s %-15s\n", "Date", "Transaction Type", "Amount", "New Balance");
        str += String.format("%-20s %-20s %-15s %-15s\n", "--------------------", "--------------------", "---------------", "---------------");

        String filename = String.format("history-%s.txt", acc.getAccNum());

        FileWriter writer = new FileWriter(filename);
        
        writer.write(str);
        writer.close();
    }

    public static void addRecord(Account acc, LocalDateTime date, String transactionType, double amount) throws IOException {
        String output = String.format("%-20s %-20s %-15s %-15s\n", 
            date.format(datetime_format), 
            transactionType, 
            amount >= 0 ? String.format("+%.2f", amount) : String.format("%.2f", amount), 
            String.format("%.2f", acc.getBalance()));

        FileWriter writer = new FileWriter(String.format("history-%s.txt", acc.getAccNum()), true);
        writer.write(output);
        writer.close();
    }
}
