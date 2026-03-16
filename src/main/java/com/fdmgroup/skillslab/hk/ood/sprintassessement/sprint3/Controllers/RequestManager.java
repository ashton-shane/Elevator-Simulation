package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.PassengerFloorMap;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class RequestManager {
    private Queue<Request> requestsPool = new ArrayDeque<>();
    private List<Request> reqPendingAssignment = new ArrayList<>();
    private static final RequestManager instance = new RequestManager();

    // ======= SINGLETON ======= //
    private RequestManager(){}

    public static RequestManager getInstance() {
        return instance;
    }

    // ======= REQUEST ALLOCATION METHODS ======= //
    public synchronized void requestSameFloorAlloc(Elevator elevator) {
        int elevatorCurrentFloor = elevator.getCurrentFloor();

        for (Request request : this.getReqPendingAssignment()) {
            if (request.getCurrentFloor() == elevatorCurrentFloor) {
                elevator.loadDestinationFloor(request.getDestinationFloor());
                elevator.setCurrentFloor(request.getCurrentFloor());
                this.removeFromRequestsPendingAssignment(request);
                PassengerFloorMap.getInstance().loadFloorMapWithPassengers(request);
            }
        }
    }

    public synchronized void emptyLiftFloorAlloc(Elevator elevator) {
        if (reqPendingAssignment.isEmpty()) {
            return;
        }

        int elevatorCurrentFloor = elevator.getCurrentFloor();

        // Find the minimum floor difference
        int nearestFloor = this.getReqPendingAssignment().get(0).getCurrentFloor();
        int minFloorDiff = elevatorCurrentFloor - nearestFloor;
        boolean isGoingUp = ((elevatorCurrentFloor - nearestFloor) > 0);

        for (Request r : this.getReqPendingAssignment()) {
            boolean reqDirection = ((elevatorCurrentFloor - nearestFloor) > 0);
            int currFloorDiff = elevatorCurrentFloor - r.getCurrentFloor();

            // reassign if we find a nearer floor
            if (abs(currFloorDiff) < abs(minFloorDiff)) {
                nearestFloor = r.getCurrentFloor();
                minFloorDiff = currFloorDiff;
            }

            // For subsequent req w/ same floor difference, prefer floors going up
            if (abs(currFloorDiff) == minFloorDiff && isGoingUp) {
                nearestFloor = r.getCurrentFloor();
            }

        }

        // Collect all requests on that exact nearest floor going in the same direction
        ArrayList<Integer> destinationFloors = new ArrayList<>();
        for (Request r : this.getReqPendingAssignment()) {
            boolean reqDirection = ((elevatorCurrentFloor - nearestFloor) > 0);
            if (r.getCurrentFloor() == nearestFloor && reqDirection == isGoingUp) {
                destinationFloors.add(r.getDestinationFloor());
                PassengerFloorMap.getInstance().loadFloorMapWithPassengers(r);
            }
        }

        // Set direction
        elevator.setGoingUp(isGoingUp);

        // Load all destination floors and move elevator
        for (int floor : destinationFloors) {
            elevator.loadDestinationFloor(floor);
        }
        elevator.setCurrentFloor(nearestFloor);
    }

    public synchronized void moveToPendingRequests() {
        Request requestToMove = requestsPool.peek();
        if (requestToMove == null) {
            return;
        }
        getReqPendingAssignment().add(requestToMove);
        removeFromRequestPool(requestToMove);
    }

    public synchronized Request getNextRequestForElevator(Elevator elevator) {
        int currentFloor = elevator.getCurrentFloor();
        if (elevator.getDestinationFloors().isEmpty()) return null;
        int nextDest = elevator.getDestinationFloors().peek();

        for (Request r : reqPendingAssignment) {
            if (r.getCurrentFloor() == currentFloor &&
                    r.getDestinationFloor() == nextDest) {
                return r;
            }
        }
        return null;
    }

    // ======= GETTERS AND SETTERS ======= //
    public Queue<Request> getRequestsPool() {
        return this.requestsPool;
    }

    public synchronized void removeFromRequestPool(Request request) {
        this.requestsPool.remove(request);
    }

    public List<Request> getReqPendingAssignment() {
        return this.reqPendingAssignment;
    }

    public synchronized void removeFromRequestsPendingAssignment(Request request){
        this.reqPendingAssignment.remove(request);
    }

}
