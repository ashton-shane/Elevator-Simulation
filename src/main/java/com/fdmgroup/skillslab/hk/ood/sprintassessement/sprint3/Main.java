package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.LiftFloorMap;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.PassengerFloorMap;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.ThreadManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    static void main(String[] args) {
        var loader = new ConfigLoader();
        loader.loadConfigFile("ElevatorConfig.txt");
        // loader usage:
        // loader.getConfig(); -> returns Configuration object
        // loader.getRequests(); -> returns List<Request>

        // load FloorMap and PaxMap
        LiftFloorMap.getInstance().loadFloorMapWithLifts();

        // Requests are released every 5 seconds
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            RequestManager.getInstance().moveToPendingRequests();
        }, 0, 5, TimeUnit.SECONDS);

        // Create and Start Threads
        ThreadManager threadManager = new ThreadManager();
        threadManager.createThreads(3);
        threadManager.startThreads();
    }
}
