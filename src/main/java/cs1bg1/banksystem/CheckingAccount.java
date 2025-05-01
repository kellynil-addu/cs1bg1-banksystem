package cs1bg1.banksystem;

public class CheckingAccount extends Account {

    // Checking accounts usually allow unlimited deposits and withdrawals.

    final public double OVERDRAFT_LIMIT = 4000;
    final public double MIN_DEPOSIT = 100;

    public CheckingAccount(String acc_num, String username, String fullname, double balance) {
        super(acc_num, username, fullname, "Checking", balance);
    }

    @Override
    public boolean canDepositAmount(double amount) {
        if (amount < MIN_DEPOSIT) {
            System.out.println("Deposit amount must be at least the minimum limit: " + String.format("%.2f", MIN_DEPOSIT));
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

        if (amount > getBalance() + OVERDRAFT_LIMIT) {
            System.out.println("Withdraw amount must not exceed the account's overdraft limit: " + String.format("%.2f", (0 - OVERDRAFT_LIMIT)));
            return false;
        }
        
        return true;
    }
}
