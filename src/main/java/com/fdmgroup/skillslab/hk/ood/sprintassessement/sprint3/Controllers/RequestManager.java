package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestManager {
    private static List<Request> requests = new ArrayList<>();

    // methods
    public static void allocateRequest(Elevator elevator) {
        int elevatorCurrentFloor = elevator.getCurrentFloor();
        for (Request request : requests) {
            if (request.getCurrentFloor() == elevatorCurrentFloor) {
                elevator.loadDestinationFloor(request.getDestinationFloor());
                elevator.goToFloor(request);
                this.removeRequest(request);
            }
            else {
                int[] nearestFloor = {elevatorCurrentFloor,0}; // { closest floor, index of request }
                if (request.isGoingUp() == elevator.isGoingUp()) {  // HANDLE NEGATIVES FOR REVERSE DIRECTION
                    if (request.getCurrentFloor() < nearestFloor[0]) {
                        nearestFloor[0] = request.getCurrentFloor();
                        nearestFloor[1] = requests.indexOf(request);
                    }
                }
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
    public static List<Request> getRequests() {
        return requests;
    }
}
