package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

public class SafetyDepositBoxService {
    private static SafetyDepositBoxService uniqueInstance;

    // SINGLETON private constructor
    private SafetyDepositBoxService(){};

    public static SafetyDepositBoxService getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SafetyDepositBoxService();
        }
        return uniqueInstance;
    }
}
