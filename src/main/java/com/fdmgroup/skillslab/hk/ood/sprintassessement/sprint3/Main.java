package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import java.util.ArrayList;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.services.ConfigLoader;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.services.ElevatorService;

public class Main {

    static void main(String[] args) {
        var loader = new ConfigLoader();
        loader.loadConfigFile("ElevatorConfig.txt");
        var requests = loader.getRequests();
        var config = loader.getConfig();
        var elevatorService = new ElevatorService(requests, config.get());
        var numberOfElevators = 5;
        var elevators = new ArrayList<Thread>();
        for (int i = 1; i <= numberOfElevators; i++) {
            var thread = new Thread(() -> {
                try {
                    elevatorService.processRequests();
                } catch (InterruptedException _) {
                }
            });
            thread.setName("elevator-" + i);
            elevators.add(thread);
        }
        for (var elevator : elevators) {
            elevator.start();
        }
    }
}
