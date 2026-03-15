package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;

public class Main {

    static void main(String[] args) {
        ElevatorService elevatorService = new ElevatorService();
        var loader = new ConfigLoader();
        loader.loadConfigFile("ElevatorConfig.txt", elevatorService);
        // loader usage:
        // loader.getConfig(); -> returns Configuration object
        // loader.getRequests(); -> returns List<Request>
    }
}
