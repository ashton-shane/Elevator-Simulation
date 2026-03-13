package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.services.ConfigLoader;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.services.ElevatorService;

public class Main {

    static void main(String[] args) {
        var loader = new ConfigLoader();
        loader.loadConfigFile("ElevatorConfig.txt");
        var requests = loader.getRequests();
        var config = loader.getConfig();
        var elevatorService = new ElevatorService(requests, config.get());
        var thread0 = new Thread(() -> {
            try {
                elevatorService.processRequests();
            } catch (InterruptedException _) {
            }
        });
        var thread1 = new Thread(() -> {
            try {
                elevatorService.processRequests();
            } catch (InterruptedException _) {
            }
        });
        thread0.start();
        thread1.start();
    }
}
