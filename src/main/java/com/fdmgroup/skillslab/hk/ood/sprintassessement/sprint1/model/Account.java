package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model;

public abstract class Account {
    private final long ACCOUNT_ID;
    protected double balance;
    private long nextAccountId = 1_000;

    // constructor
    public Account() {
        this.ACCOUNT_ID = nextAccountId;
        this.balance = 0;
        nextAccountId += 5;
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
