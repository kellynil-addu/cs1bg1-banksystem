package cs1bg1.banksystem;

public class CheckingAccount extends Account {

    public CheckingAccount(String acc_num, String username, String fullname, double balance) {
        super(acc_num, username, fullname, "Checking", balance);
    }

}
