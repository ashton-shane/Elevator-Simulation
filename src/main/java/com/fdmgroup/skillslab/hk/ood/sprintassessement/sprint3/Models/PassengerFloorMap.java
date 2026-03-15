package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PassengerFloorMap {
    private static Map<Integer, List<Passenger>> buildingFloorMap;             // map format: { floor : List<Passenger> }
    public static final PassengerFloorMap instance = new PassengerFloorMap();

    public PassengerFloorMap(){
        buildingFloorMap = new ConcurrentHashMap<>();
    }

    public static Map<Integer, List<Passenger>> getBuildingFloorMap() {
        return buildingFloorMap;
    }

    public void loadFloorMapWithPassengers(){
        for (Request r : RequestManager.getInstance().getRequests()) {
            List<Passenger> waitingPassengers = buildingFloorMap.computeIfAbsent(
                    r.getCurrentFloor(), floor -> new ArrayList<>()
            );
            for (int i = 0; i < r.getNumOfPassengers(); i++) {
                Passenger passenger = new Passenger(
                        r.getCurrentFloor(),
                        r.getDestinationFloor(),
                        r.isGoingUp()
                );
                waitingPassengers.add(passenger);
            }
        }
    }
}
