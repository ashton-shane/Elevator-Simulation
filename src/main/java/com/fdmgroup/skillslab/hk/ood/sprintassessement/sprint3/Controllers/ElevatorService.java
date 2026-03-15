package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;

import java.util.ArrayList;
import java.util.List;

public class ElevatorService {
    private List<Elevator> elevators = new ArrayList<>();

    // lift movement
    public void moveElevator(Elevator elevator, Request request) throws InterruptedException {
        elevator.goToFloor(request);
    }

    public void checkOtherElevator(Request request){
        int destinationFloor = request.getDestinationFloor();

    }

    // getters
    public List<Elevator> getElevators() {
        return elevators;
    }
}
