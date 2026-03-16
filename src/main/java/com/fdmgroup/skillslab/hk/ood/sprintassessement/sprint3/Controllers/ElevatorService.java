package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.LiftFloorMap;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;

import java.util.ArrayList;
import java.util.List;

public class ElevatorService {
    private static ElevatorService instance = new ElevatorService();
    private List<Elevator> elevators = new ArrayList<>();
    LiftFloorMap liftFloorMap = LiftFloorMap.getInstance();

    // ======= SINGLETON ======= //
    private ElevatorService() {}

    public static ElevatorService getInstance() {
        return instance;
    }
    // ======= LIFT MOVEMENT METHODS ======= //
    public synchronized void moveElevator(Elevator elevator, Request request) {
        try {
            // simulate movement
            Thread.sleep(3000);

            // move lift on the liftmap
            liftFloorMap.getLiftFloorMap().get(request.getCurrentFloor()).remove(elevator);
            liftFloorMap.getLiftFloorMap().get(request.getDestinationFloor()).add(elevator);
            elevator.setCurrentFloor(request.getCurrentFloor());

            // remove the dest floor from the queue
            elevator.getDestinationFloors().remove();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    // ======= GETTERS ======= //
    public List<Elevator> getElevators() {
        return elevators;
    }
}
