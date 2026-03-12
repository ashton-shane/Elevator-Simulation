package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElevatorService {
    private List<Request> requests = new ArrayList<>();
    private Map<Integer, ArrayList<Request>> requestsMap = new HashMap<>();
    private List<Elevator> elevators = new ArrayList<>();

    public ElevatorService(List<Request> requests, Configuration config) {
        for (var r : requests) {
            var currentList = requestsMap.getOrDefault(r.getCurrentFloor(), new ArrayList<>());
            currentList.add(r);
            requestsMap.put(r.getCurrentFloor(), currentList);
        }
    }

    public void processRequests() throws InterruptedException {
        var requests = new ArrayList<Request>();
        var currentFloor = 0;
        while (true) {
            if (requests.size() == 0) {
                requests.addAll(getRequests());
            } else {
                Integer smallestDiff = 10;
                Integer targetFloor = 0;
                for (var r : requests) {
                    if (r.getDestinationFloor() - currentFloor < smallestDiff) {
                        targetFloor = r.getDestinationFloor();
                    }
                }
                while (currentFloor < targetFloor) {
                    currentFloor += 1;
                    Thread.sleep(3000);
                }
            }
        }
    }

    private List<Request> getRequests() {
        for (var pair: requestsMap.entrySet()) {
            return pair.getValue();
        }
        return new ArrayList<Request>();
    }
    
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

    public List<Elevator> getElevators() {
        return elevators;
    }
}
