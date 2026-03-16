package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    static void main(String[] args) {
        ElevatorService elevatorService = new ElevatorService();
        var loader = new ConfigLoader();
        loader.loadConfigFile("ElevatorConfig.txt", elevatorService);
        // loader usage:
        // loader.getConfig(); -> returns Configuration object
        // loader.getRequests(); -> returns List<Request>

        // Requests are released every 5 seconds
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            RequestManager.getInstance().moveToPendingRequests();
        }, 0, 5, TimeUnit.SECONDS);
    }
}
