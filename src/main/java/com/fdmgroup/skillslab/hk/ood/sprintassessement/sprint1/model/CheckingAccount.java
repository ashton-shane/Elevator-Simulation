package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model;

public class CheckingAccount extends Account {
    private int nextCheckNumber = 1;

    // constructor
    public CheckingAccount() {
        super();
    }

    // getter and setter
    public int getNextCheckNumber() {
        return nextCheckNumber;
    }

    public void setNextCheckNumber() {
        nextCheckNumber += 1;
    }
}
