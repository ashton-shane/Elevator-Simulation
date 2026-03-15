package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PassengerFloorMap {
    private Map<Integer, List<Passenger>> passengerFloorMap;             // map format: { floor : List<Passenger> }
    public static final PassengerFloorMap instance = new PassengerFloorMap();

    // singleton
    private PassengerFloorMap(){
        this.passengerFloorMap = new ConcurrentHashMap<>();
    }

    public static PassengerFloorMap getInstance() {
        return instance;
    }

    // methods
    public void loadFloorMapWithPassengers(){
        for (Request r : RequestManager.getInstance().getRequestsPool()) {
            List<Passenger> waitingPassengers = passengerFloorMap.computeIfAbsent(
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

    // getter
    public Map<Integer, List<Passenger>> getPassengerFloorMap() {
        return this.passengerFloorMap;
    }
}
