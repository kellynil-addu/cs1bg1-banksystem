package cs1bg1.banksystem;

public class SavingsAccount extends Account {

    public SavingsAccount(String acc_num, String username, String fullname, double balance) {
        super(acc_num, username, fullname, "Savings", balance);
    }

}
