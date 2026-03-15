package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.LiftFloorMap;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;

import java.util.ArrayList;
import java.util.List;

public class ElevatorService {
    private List<Elevator> elevators = new ArrayList<>();
    LiftFloorMap liftFloorMap = LiftFloorMap.getInstance();

    // ======= LIFT MOVEMENT METHODS ======= //
    public void moveElevator(Elevator elevator, Request request) {
        try {
            Thread.sleep(3000);
            elevator.setCurrentFloor(request);
            liftFloorMap.getLiftFloorMap().get(request.getCurrentFloor()).remove(elevator);
            liftFloorMap.getLiftFloorMap().get(request.getDestinationFloor()).add(elevator);
            elevator.getDestinationFloors().remove();   // leftpops the dest floor from the queue
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    // ======= GETTERS ======= //
    public List<Elevator> getElevators() {
        return elevators;
    }
}
