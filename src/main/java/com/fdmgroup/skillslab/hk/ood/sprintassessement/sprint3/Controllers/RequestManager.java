package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class RequestManager {
    private Queue<Request> requestsPool = new ArrayDeque<>();
    private List<Request> activeRequests = new ArrayList<>();
    private static final RequestManager instance = new RequestManager();

    // singleton
    private RequestManager(){}

    public static RequestManager getInstance() {
        return instance;
    }

    // methods
    public void allocateRequest(Elevator elevator) {
        int elevatorCurrentFloor = elevator.getCurrentFloor();
        for (Request request : this.requestsPool) {
            if (request.getCurrentFloor() == elevatorCurrentFloor) {
                elevator.loadDestinationFloor(request.getDestinationFloor());
                elevator.setCurrentFloor(request);
                this.removeRequest(request);
            }
            else {
                int[] nearestFloor = {elevatorCurrentFloor,0}; // { closest floor, index of request }
                if (request.isGoingUp() == elevator.isGoingUp()) {  // HANDLE NEGATIVES FOR REVERSE DIRECTION
                    if (request.getCurrentFloor() < nearestFloor[0]) {
                        nearestFloor[0] = request.getCurrentFloor();
                        nearestFloor[1] = this.getRequestsPool().indexOf(request);
                    }
                }
            }
        }
    }

    public void poolToActiveRequests() {
        Request requestToMove = requestsPool.peek();
        activeRequests.add(requestToMove);
        requestsPool.remove(requestToMove);
    }

    // getters and setters
    public Queue<Request> getRequestsPool() {
        return this.requestsPool;
    }

    public List<Request> getActiveRequests() {
        return activeRequests;
    }

    public void addRequest(Request request) {
        requestsPool.remove(request);
    }
}
