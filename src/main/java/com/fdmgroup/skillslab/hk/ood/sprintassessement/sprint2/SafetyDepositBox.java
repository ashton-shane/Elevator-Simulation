package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SafetyDepositBox {
    private boolean isAllotted;
    private final double id;
    private static double nextId = 1;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();


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

    public boolean isAllotted() {
        rwLock.readLock().lock();
        try {
            return this.isAllotted;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public void setAllotted(boolean allotted) {
        rwLock.writeLock().lock();
        try {
            this.isAllotted = allotted;
        }
        finally {
            rwLock.writeLock().unlock();
        }
    }

    public static double getNextId() {
        return nextId;
    }
}
