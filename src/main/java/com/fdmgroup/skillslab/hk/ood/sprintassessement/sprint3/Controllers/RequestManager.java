package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.PassengerFloorMap;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class RequestManager {
    private Queue<Request> requestsPool = new ArrayDeque<>();
    private List<Request> requestsPendingAssignment = new ArrayList<>();
    private static final RequestManager instance = new RequestManager();

    // singleton
    private RequestManager(){}

    public static RequestManager getInstance() {
        return instance;
    }

    // methods
    public void requestSameFloorAlloc(Elevator elevator) {
        int elevatorCurrentFloor = elevator.getCurrentFloor();

        // allocating requests on the same floor
        for (Request request : this.getRequestsPendingAssignment()) {
            if (request.getCurrentFloor() == elevatorCurrentFloor) {
                elevator.loadDestinationFloor(request.getDestinationFloor());
                elevator.setCurrentFloor(request);
                this.removeFromRequestsPendingAssignment(request);
                PassengerFloorMap.getInstance().loadFloorMapWithPassengers(request);
            }
        }
    }

    public void emptyLiftFloorAlloc(Elevator elevator) {
        int elevatorCurrentFloor = elevator.getCurrentFloor();
        Request nearestFloorRequest = this.getRequestsPendingAssignment().getFirst();
        int floorDiff = nearestFloorRequest.getCurrentFloor();
        ArrayList<Integer> destinationFloors = new ArrayList<>();

        // find nearest floor (TODO: need to fix this logic)
        for (Request r : this.getRequestsPendingAssignment()) {
            int currDiff = Math.abs(r.getCurrentFloor() - elevatorCurrentFloor);
            if (currDiff < floorDiff) {
                floorDiff = currDiff;
                nearestFloorRequest = r;
                destinationFloors.add(r.getDestinationFloor());
            }
        }

        // load destination floors
        for (int floor : destinationFloors) {
            elevator.loadDestinationFloor(floor);
        }

        // move elevator to the floor
        elevator.setCurrentFloor(nearestFloorRequest);
    }

    public void poolToActiveRequests() {
        Request requestToMove = requestsPool.peek();
        getRequestsPendingAssignment().add(requestToMove);
        removeFromRequestPool(requestToMove);
    }

    // getters and setters
    public Queue<Request> getRequestsPool() {
        return this.requestsPool;
    }

    public void removeFromRequestPool(Request request) {
        this.requestsPool.remove(request);
    }

    public List<Request> getRequestsPendingAssignment() {
        return this.requestsPendingAssignment;
    }

    public void removeFromRequestsPendingAssignment(Request request){
        this.requestsPendingAssignment.remove(request);
    }

}
