package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SafetyDepositBoxService {
    private static SafetyDepositBoxService uniqueInstance;
    private static final Logger LOGGER = LogManager.getLogger(SafetyDepositBoxService.class);

    private List<SafetyDepositBox> safetyDepositBoxes = new ArrayList<>();
    private int maxNumberOfSafetyDepositBoxes;
    private AtomicInteger allottedCount = new AtomicInteger(0);

    // ===============  LAZY Singleton =============== //
    private SafetyDepositBoxService(){
        this.maxNumberOfSafetyDepositBoxes = 2;
        for (int i = 0; i < this.maxNumberOfSafetyDepositBoxes; i++) {
            SafetyDepositBox box = new SafetyDepositBox();
            safetyDepositBoxes.add(box);
        }
    };

    public static synchronized SafetyDepositBoxService getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SafetyDepositBoxService();
        }
        return uniqueInstance;
    }


    // =============== GETTERS AND SETTERS =============== //
    public synchronized List<SafetyDepositBox> getSafetyDepositBoxes() {
        return this.safetyDepositBoxes;
    }

    public synchronized int getMaxNumberOfSafetyDepositBoxes() {
        return this.maxNumberOfSafetyDepositBoxes;
    }

    public int getAllottedCount() {
        return this.allottedCount.get();
    }

    public synchronized int getCurrTotalBoxesCreated(){
        return this.getSafetyDepositBoxes().size();
    }

    public synchronized void setMaxNumberOfSafetyDepositBoxes(int number) {
        // set the size regardless
        this.maxNumberOfSafetyDepositBoxes = number;

        // decrease pool if set number is lower than current size
        if (number < this.getSafetyDepositBoxes().size()){
            int boxesToDestroy = this.getCurrTotalBoxesCreated() - number;
            AtomicInteger counter = new AtomicInteger(0);
            while (counter.get() != boxesToDestroy) {
                for (SafetyDepositBox box : this.getSafetyDepositBoxes()) {
                    if (!box.isAllotted()) {
                        this.getSafetyDepositBoxes().remove(box);
                        counter.getAndIncrement();
                    }
                }
            }
        }
    }


    // =============== BOX MOVEMENT LOGIC =============== //
    public synchronized SafetyDepositBox allocateSafetyDepositBox() {
        while (areAllBoxesAllotted()) {
            int currentNumOfBoxes = this.getSafetyDepositBoxes().size();
            if (currentNumOfBoxes < this.maxNumberOfSafetyDepositBoxes) {
                SafetyDepositBox box = new SafetyDepositBox();
                this.getSafetyDepositBoxes().add(box);
                LOGGER.info("A new box has been created. Box is now available");
                notifyAll();
            }
            else {
                try {
                    LOGGER.info("All boxes have been allocated. Please wait.");
                    wait();
                }
                catch (InterruptedException e) {
                    LOGGER.info("Thread was interrupted while waiting for a box.");
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
        }
        return this.getReleasedSafetyDepositBox();
    }

    private synchronized boolean areAllBoxesAllotted() {
        return this.getAllottedCount() == this.getCurrTotalBoxesCreated();
    }

    public synchronized SafetyDepositBox getReleasedSafetyDepositBox(){
        for (SafetyDepositBox box : this.getSafetyDepositBoxes()) {
            if (!box.isAllotted()) {
                box.setAllotted(true);
                allottedCount.incrementAndGet();
                notifyAll();
                return box;
            }
        }
        return null;
    }

    public synchronized int getNumberOfAvailableSafetyBoxes(){
        return this.getCurrTotalBoxesCreated() - this.getAllottedCount();
    }

    public synchronized void releaseSafetyDepositBox(SafetyDepositBox safetyDepositBox){
        for (SafetyDepositBox box : this.getSafetyDepositBoxes()) {
            if (safetyDepositBox == box) {
                box.setAllotted(false);
                allottedCount.getAndDecrement();
                notifyAll();
                LOGGER.info("Box has been released");
            }
        }
    }

    // Only for testing purposes
    public static void resetInstance() {
        uniqueInstance = null;
    }
}
