package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import java.util.ArrayList;
import java.util.List;

public class ElevatorService {
    private List<Request> requests = new ArrayList<>();
    private List<Elevator> elevators = new ArrayList<>();

    // methods
    public void allocateRequest(Elevator elevator) {
        int elevatorCurrentFloor = elevator.getCurrentFloor();
        int nearestFloor;
        for (Request request : requests) {
            if (request.getCurrentFloor() == elevatorCurrentFloor) {
                elevator.loadDestinationFloor(request.getDestinationFloor());
                elevator.goToFloor(request);
            }
        }
    }

    public void removeRequest(Request request) {
        requests.remove(request);
    }

    public void addRequest(Request request) {
        requests.remove(request);
    }

    // getters
    public List<Request> getRequests() {
        return requests;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }


}
