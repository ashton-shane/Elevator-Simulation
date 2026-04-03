package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SafetyDepositBox {
    private boolean isAllotted;
    private final int id;
    private static int nextId = 1;

    // constructor
    public SafetyDepositBox(){
        this.isAllotted = false;
        this.id = nextId;
        nextId += 1;
    }

    // getters and setters
    public int getId() {
        return this.id;
    }

    public synchronized boolean isAllotted() {
        return this.isAllotted;
    }

    public synchronized void setAllotted(boolean allotted) {
        this.isAllotted = allotted;
    }

    public static int getNextId() {
        return nextId;
    }

    // solely for testing purposes
    public static void setNextId(int n) {
        nextId = n;
    }
}
