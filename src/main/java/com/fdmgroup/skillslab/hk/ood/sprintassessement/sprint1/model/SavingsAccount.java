package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model;

public class SavingsAccount extends Account {
    private double interestRate;

    // constructor
    public SavingsAccount(double balance) {
        super(balance);
    }

    // methods
    public void addInterest(double balance) {
        balance += (balance * interestRate / 100);
    }

    @Override
    public double withdraw(double amount){
        if (amount >= balance){                 // do not allow overdrawing
            balance -= amount;
        }
        return amount;
    }

    // getter and setter
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
