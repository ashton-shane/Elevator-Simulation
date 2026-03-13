package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.models.Configuration;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.models.Request;

public class ElevatorService {
    private Map<Integer, ArrayList<Request>> requestsThatAreGoingUpMap = new HashMap<>();
    private Map<Integer, ArrayList<Request>> requestsThatAreGoingDownMap = new HashMap<>();
    private Configuration config;

    // in the constructor, the requests are split into 2 maps, one for requests that
    // are going up and the other for down
    public ElevatorService(List<Request> requests, Configuration config) {
        for (var r : requests) {
            var requestsThatAreGoingUp = this.requestsThatAreGoingUpMap.getOrDefault(r.currentFloor(),
                    new ArrayList<>());
            var requestsThatAreGoingDown = this.requestsThatAreGoingDownMap.getOrDefault(r.currentFloor(),
                    new ArrayList<>());
            if (r.destinationFloor() - r.currentFloor() > 0) {
                requestsThatAreGoingUp.add(r);
                this.requestsThatAreGoingUpMap.put(r.currentFloor(), requestsThatAreGoingUp);
            } else {
                requestsThatAreGoingDown.add(r);
                this.requestsThatAreGoingDownMap.put(r.currentFloor(), requestsThatAreGoingDown);
            }
        }
        this.config = config;
    }

    public void processRequests() throws InterruptedException {
        var pendingRequests = new ArrayList<Request>();
        var processingRequests = new ArrayList<Request>();
        var currentFloor = 0;
        var totalCompletedRequests = 0;
        while (true) {
            if (pendingRequests.size() == 0) {
                // if there are no pending requests, ask for requests
                if (!hasRequests()) { break; }
                pendingRequests.addAll(getRequests());
            } else {
                // if there are pendings requests,
                // head to floor indicated by pending request
                var pickUpFloor = pendingRequests.getFirst().currentFloor();
                while (currentFloor != pickUpFloor) {
                    Thread.sleep(config.simulationRate() * 3);
                    currentFloor += (pickUpFloor - currentFloor) / Math.abs(pickUpFloor - currentFloor);
                    System.out.println(Thread.currentThread().getName() + " is at floor " + currentFloor);
                }
                // pick up passengers indicated by requests on the floor
                // mark requests as processing
                for (var r : pendingRequests) {
                    if (r.currentFloor() == currentFloor) {
                        processingRequests.add(r);
                    }
                }
                pendingRequests.removeAll(processingRequests);
                Thread.sleep(config.simulationRate() * 5);
                // check processing requests, find the nearest floor to drop off passengers
                while (processingRequests.size() != 0) {
                    var targetFloor = getTargetFloor(processingRequests, currentFloor);
                    while (currentFloor != targetFloor) {
                        Thread.sleep(config.simulationRate() * 3);
                        currentFloor += (targetFloor - currentFloor) / Math.abs(targetFloor - currentFloor);
                        System.out.println(Thread.currentThread().getName() + " is at floor " + currentFloor);
                    }
                    var requestsOnTargetFloor = new ArrayList<>();
                    for (var req : processingRequests) {
                        if (req.destinationFloor() == currentFloor) {
                            requestsOnTargetFloor.add(req);
                        }
                    }
                    Thread.sleep(config.simulationRate() * 5);
                    totalCompletedRequests += requestsOnTargetFloor.size();
                    System.out.println(Thread.currentThread().getName() + " has completed " + totalCompletedRequests
                            + " requests in total.");
                    processingRequests.removeAll(requestsOnTargetFloor);
                }
            }
        }
    }

    // this method scans all floors for requests
    // if there are requests, it looks for requests that are going up
    // if there are no requests that are going up, add requests that are going down
    private List<Request> getRequests() {
        var requestList = new ArrayList<Request>();
        synchronized (requestsThatAreGoingUpMap) {
            for (var requests : requestsThatAreGoingUpMap.entrySet()) {
                if (requests.getValue().size() != 0) {
                    requestList.addAll(requests.getValue());
                    requests.getValue().clear();
                    return requestList;
                }
            }
        }
        synchronized (requestsThatAreGoingDownMap) {
            for (var requests : requestsThatAreGoingDownMap.entrySet()) {
                if (requests.getValue().size() != 0) {
                    requestList.addAll(requests.getValue());
                    requests.getValue().clear();
                    return requestList;
                }
            }
        }
        return requestList;
    }

    private Integer getTargetFloor(List<Request> requests, Integer currentFloor) {
        var smallestDiff = 10;
        var targetFloor = currentFloor;
        for (var req : requests) {
            var diff = Math.abs(req.destinationFloor() - currentFloor);
            if (diff < smallestDiff) {
                smallestDiff = diff;
                targetFloor = req.destinationFloor();
            }
        }
        return targetFloor;
    }

    private Boolean hasRequests() {
        synchronized (requestsThatAreGoingUpMap) {
            for (var pair : requestsThatAreGoingUpMap.entrySet()) {
                if (pair.getValue().size() != 0) {
                    return true;
                }
            }
        }
        synchronized (requestsThatAreGoingDownMap) {
            for (var pair : requestsThatAreGoingDownMap.entrySet()) {
                if (pair.getValue().size() != 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
