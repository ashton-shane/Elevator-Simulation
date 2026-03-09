package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

public class SafetyDepositBox {
    private boolean isAllotted;
    private double id;
    private double nextId = 0;

    // constructor
    public SafetyDepositBox(){
        this.isAllotted = false;
        this.id = nextId;
        nextId += 1;
    }
    // getters and setters
    public double getId() {
        return this.id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public boolean isAllotted() {
        return this.isAllotted;
    }

    public void setAllotted(boolean allotted) {
        this.isAllotted = allotted;
    }



}
