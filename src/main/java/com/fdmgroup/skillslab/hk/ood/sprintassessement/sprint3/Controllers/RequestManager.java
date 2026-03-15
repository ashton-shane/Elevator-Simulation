package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestManager {
    private List<Request> requests = new ArrayList<>();
    private static final RequestManager instance = new RequestManager();

    // singleton
    private RequestManager(){}

    public static RequestManager getInstance() {
        return instance;
    }

    // methods
    public void allocateRequest(Elevator elevator) {
        int elevatorCurrentFloor = elevator.getCurrentFloor();
        for (Request request : this.requests) {
            if (request.getCurrentFloor() == elevatorCurrentFloor) {
                try {
                    elevator.loadDestinationFloor(request.getDestinationFloor());
                    elevator.goToFloor(request);
                    this.removeRequest(request);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                int[] nearestFloor = {elevatorCurrentFloor,0}; // { closest floor, index of request }
                if (request.isGoingUp() == elevator.isGoingUp()) {  // HANDLE NEGATIVES FOR REVERSE DIRECTION
                    if (request.getCurrentFloor() < nearestFloor[0]) {
                        nearestFloor[0] = request.getCurrentFloor();
                        nearestFloor[1] = this.getRequests().indexOf(request);
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
    public List<Request> getRequests() {
        return this.requests;
    }
}
