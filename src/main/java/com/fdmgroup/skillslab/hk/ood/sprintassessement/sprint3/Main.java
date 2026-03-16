package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

public class Main {

    static void main(String[] args) {
        var loader = new ConfigLoader();
        loader.loadConfigFile("ElevatorConfig.txt");
        var requests = loader.getRequests();
        var config = loader.getConfig();
        var elevatorService = new ElevatorService(requests, config.get());
    }
}
