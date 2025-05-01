/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cs1bg1.banksystem;

/**
 *
 * @author kelly
 */
public abstract class Account {

    private String acc_num;
    private String username;
    private String fullname;
    private String type;
    private double balance;

    // private String date;

    public Account(String acc_num, String username, String fullname, String type, double balance) {
        this.acc_num = acc_num;
        this.username = username;
        this.fullname = fullname;
        this.type = type;
        this.balance = balance;
    }

    abstract public boolean canDepositAmount(double amount);

    abstract public boolean canWithdrawAmount(double amount);

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    // GETTERS

    public String getAccNum() {
        return acc_num;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }


}
