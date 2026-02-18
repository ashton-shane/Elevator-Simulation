package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model;

public abstract class Account {
    private long ACCOUNT_ID = 1_000;
    private double balance;
    private long nextAccountId;

    // constructor
    public Account(int balance){
        this.balance = balance;
        this.nextAccountId = ACCOUNT_ID;
        ACCOUNT_ID += 5;
    }

    // methods
    public void deposit(double amount){
        balance += amount;
    }

    public double withdraw(double amount){
        balance -= amount;
        return amount;
    }

    public void correctBalance(double newBalance){
        balance = newBalance;
    }

    // getters and setters
    public long getNextAccountId() {
        return nextAccountId;
    }

    public long getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
