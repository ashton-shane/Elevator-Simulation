package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SafetyDepositBox {
    private boolean isAllotted;
    private final double id;
    private static double nextId = 1;

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

    public synchronized boolean isAllotted() {
        return this.isAllotted;
    }

    public synchronized void setAllotted(boolean allotted) {
        this.isAllotted = allotted;
    }

    public static double getNextId() {
        return nextId;
    }
}
