package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import java.util.ArrayList;
import java.util.List;

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

    public int getNumberOfSafetyDepositBoxes() {
        return totalNumberOfSafetyDepositBoxes;
    }

    public void setNumberOfSafetyDepositBoxes(int numberOfSafetyDepositBoxes) {
        this.totalNumberOfSafetyDepositBoxes = numberOfSafetyDepositBoxes;
    }

    // Testing purposes
    public List<SafetyDepositBox> getSafetyDepositBoxes() {
        return this.safetyDepositBoxes;
    }
}
