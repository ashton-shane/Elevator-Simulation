package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class SafetyDepositBoxService {
    private static SafetyDepositBoxService uniqueInstance;

    private List<SafetyDepositBox> safetyDepositBoxes = new ArrayList<>();
    private int totalNumberOfSafetyDepositBoxes;

    // Singleton Design Pattern that only allows one SafetyDepositBoxService instance to be created
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
    public int getNumberOfSafetyDepositBoxes() {
        return totalNumberOfSafetyDepositBoxes;
    }

    public void setTotalNumberOfSafetyDepositBoxes(int number) {
        this.totalNumberOfSafetyDepositBoxes = number;
        int boxesToCreate = this.totalNumberOfSafetyDepositBoxes - this.getSafetyDepositBoxes().size();

        // decrease pool: check allotted status first to ensure it's not in use.
        if (boxesToCreate < 0) {
            int boxesToRemove = abs(boxesToCreate);
            int counter = 0;
            while (counter != boxesToRemove) {
                for (SafetyDepositBox box : this.safetyDepositBoxes) {
                    if (!box.isAllotted()) {
                        safetyDepositBoxes.remove(box);
                        counter += 1;
                    }
                }
            }
        }
    }

    public List<SafetyDepositBox> getSafetyDepositBoxes() {
        return this.safetyDepositBoxes;
    }

    public SafetyDepositBox allocateSafetyDepositBox(){
        // check if any available boxes
        boolean allAllotted = true;
        for (SafetyDepositBox box : this.safetyDepositBoxes) {
            if (!box.isAllotted()) {
                allAllotted = false;
                break;
            }
        }

        // if no available boxes
        if (allAllotted) {
            int currentNumOfBoxes = this.getSafetyDepositBoxes().size();
            if (currentNumOfBoxes < this.totalNumberOfSafetyDepositBoxes) {
                SafetyDepositBox box = new SafetyDepositBox();
                this.safetyDepositBoxes.add(box);
            }
        }

        return this.getReleasedSafetyDepositBox();
    }

    public SafetyDepositBox getReleasedSafetyDepositBox(){
        for (SafetyDepositBox box : this.safetyDepositBoxes) {
            if (!box.isAllotted()) {
                box.setAllotted(true);
                return box;
            }
        } // implement optional?
        return null;
    }

    public int getNumberOfAvailableSafetyBoxes(){
        int availableBoxes = 0;
        for (SafetyDepositBox box : this.safetyDepositBoxes) {
            if (!box.isAllotted()) {
                availableBoxes += 1;
            }
        }
        return availableBoxes;
    }

    public void releaseSafetyDepositBox(SafetyDepositBox safetyDepositBox){
        for (SafetyDepositBox box : this.safetyDepositBoxes) {
            if (safetyDepositBox == box) {
                box.setAllotted(false);
            }
        }
    }
}
