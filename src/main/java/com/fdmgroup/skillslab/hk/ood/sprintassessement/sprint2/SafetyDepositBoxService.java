package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.lang.Math.abs;

public class SafetyDepositBoxService {
    private static SafetyDepositBoxService uniqueInstance;
    private static final Logger LOGGER = LogManager.getLogger(SafetyDepositBoxService.class);

    private List<SafetyDepositBox> safetyDepositBoxes = new ArrayList<>();
    private int totalNumberOfSafetyDepositBoxes;

    // LAZY Singleton Design Pattern that only allows one SafetyDepositBoxService instance to be created
    private SafetyDepositBoxService(){
        this.totalNumberOfSafetyDepositBoxes = 2;
        for (int i = 0; i < this.totalNumberOfSafetyDepositBoxes; i++) {
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

    // getters and setters
    public synchronized int getNumberOfSafetyDepositBoxes() {
        return totalNumberOfSafetyDepositBoxes;
    }

    public synchronized void setTotalNumberOfSafetyDepositBoxes(int number) {
        this.totalNumberOfSafetyDepositBoxes = number;
        int boxesToDestroy = this.getSafetyDepositBoxes().size() - number;

        // decrease pool: check allotted status first to ensure it's not in use.
        int counter = 0;
        while (counter != boxesToDestroy) {
            for (SafetyDepositBox box : this.safetyDepositBoxes) {
                if (!box.isAllotted()) {
                    safetyDepositBoxes.remove(box);
                    counter += 1;
                }
            }
        }
    }

    public synchronized List<SafetyDepositBox> getSafetyDepositBoxes() {
        return this.safetyDepositBoxes;
    }

    public synchronized SafetyDepositBox allocateSafetyDepositBox() {
        // if no available boxes
        while (areAllBoxesAllotted()) {
            int currentNumOfBoxes = this.getSafetyDepositBoxes().size();
            if (currentNumOfBoxes < this.totalNumberOfSafetyDepositBoxes) {
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

    private boolean areAllBoxesAllotted() {
        for (SafetyDepositBox box : this.safetyDepositBoxes) {
            if (!box.isAllotted()) {
                return false;
            }
        }
        return true;
        // atomicInteger
    }

    public synchronized SafetyDepositBox getReleasedSafetyDepositBox(){
        for (SafetyDepositBox box : this.safetyDepositBoxes) {
            if (!box.isAllotted()) {
                box.setAllotted(true);
                notifyAll();
                return box;
            }
        }
        return null;
    }

    public synchronized int getNumberOfAvailableSafetyBoxes(){
        int availableBoxes = 0;
        for (SafetyDepositBox box : this.safetyDepositBoxes) {
            if (!box.isAllotted()) {
                availableBoxes += 1;
            }
        }
        return availableBoxes;
    }

    public synchronized void releaseSafetyDepositBox(SafetyDepositBox safetyDepositBox){
        for (SafetyDepositBox box : this.safetyDepositBoxes) {
            if (safetyDepositBox == box) {
                box.setAllotted(false);
                notifyAll();
                LOGGER.info("Box has been released");
            }
        }
    }
}
