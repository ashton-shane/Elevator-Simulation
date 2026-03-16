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
            // If elevator is empty, allocate nearest floor request
            elevator.requestFloor(); // internally calls emptyLiftFloorAlloc

            // As long as we have destinations, keep servicing them
            while (!elevator.getDestinationFloors().isEmpty()) {
                try {
                    // Get the Request that matches current floor and next dest
                    Request request = requestManager.getNextRequestForElevator(elevator);
                    if (request == null) break; // nothing to service for this dest

                    // Loading and unloading of pax
                    elevator.loadPassengers(request);
                    elevatorService.moveElevator(elevator, request);
                    requestManager.removeFromRequestsPendingAssignment(request);
                    elevator.unloadPassengers(request);

                    // At the destination floor, allocate same‑direction requests
                    requestManager.requestSameFloorAlloc(elevator);
                }
                catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
