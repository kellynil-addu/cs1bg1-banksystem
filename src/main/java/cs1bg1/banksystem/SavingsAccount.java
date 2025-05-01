package cs1bg1.banksystem;

public class SavingsAccount extends Account {

    // Savings accounts usually restrict the deposit amount per transaction.

    final private double MAX_DEPOSIT = 7500;
    final private double MIN_DEPOSIT = 100;

    public SavingsAccount(String acc_num, String username, String fullname, double balance) {
        super(acc_num, username, fullname, "Savings", balance);
    }

    @Override
    public boolean canDepositAmount(double amount) {
        if (amount < MIN_DEPOSIT) {
            System.out.println("Deposit amount must be at least the minimum limit: " + String.format("%.2f", MIN_DEPOSIT));
            return false;
        }

        if (amount > MAX_DEPOSIT) {
            System.out.println("Deposit amount must not exceed maximum limit: " + String.format("%.2f", MAX_DEPOSIT));
            return false;
        }

        return true;
    }

    @Override
    public boolean canWithdrawAmount(double amount) {
        if (amount <= 0) {
            System.out.println("Withdraw amount is invalid.");
            return false;
        }

        if (amount > getBalance()) {
            System.out.println("Withdraw amount must not exceed current balance: " + String.format("%.2f", getBalance()));
            return false;
        }

        return true; 
    }    
}
