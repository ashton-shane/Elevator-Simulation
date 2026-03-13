package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FloorMap {
    // map format: { floor : List<Passenger> }
    Map<Integer, List<Passenger>> buildingFloorMap = new ConcurrentHashMap<>();
    public static final FloorMap instance = new FloorMap();

    private FloorMap(){

        for (Request r : ElevatorService.getRequests()) {
            List<Passenger> waitingPassengers = buildingFloorMap.computeIfAbsent(
                    r.getCurrentFloor(), floor -> new ArrayList<>()
            );
            for (int i = 0; i < r.getNumOfPassengers(); i++) {
                Passenger passenger = new Passenger(
                        r.getCurrentFloor(),
                        r.getDestinationFloor()
                );
                waitingPassengers.add(passenger);
            }
        }
    }

    public static FloorMap getInstance(){
        return instance;
    }
}
