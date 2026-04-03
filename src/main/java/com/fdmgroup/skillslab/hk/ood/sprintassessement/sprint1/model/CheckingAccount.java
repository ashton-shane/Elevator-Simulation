package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model;

public class CheckingAccount extends Account {
    private int nextCheckNumber = 0;

    // constructor
    public CheckingAccount() {
        super();
    }

    // getter and setter
    public int getNextCheckNumber() {
        nextCheckNumber += 1;
        return nextCheckNumber;
    }
}
