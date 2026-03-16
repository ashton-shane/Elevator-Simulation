package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Configuration;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;

public class MyRunnable implements Runnable {
    private final Elevator elevator;
    private final ElevatorService elevatorService;
    private final RequestManager requestManager = RequestManager.getInstance();

    public MyRunnable(Elevator elevator, ElevatorService elevatorService) {
        this.elevator = elevator;
        this.elevatorService = elevatorService;
    }

    @Override
    public void run(){
        long endTime = System.currentTimeMillis() + (Configuration.getSimulationPeriod());
        while (System.currentTimeMillis() < endTime) {
            elevator.requestFloor();
            elevatorService.moveElevator();
            requestManager.requestSameFloorAlloc(elevator);
        }
    }
}
