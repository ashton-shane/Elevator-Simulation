package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LiftFloorMap {
    private static Map<Integer, List<Elevator>> liftFloorMap;             // map format: { floor : List<Elevator> }

    // constructor
    public LiftFloorMap(){
        liftFloorMap = new ConcurrentHashMap<>();
    }

    // load lifts
    public void loadFloorMapWithLifts(){
        for (int i = 0; i < 3; i++) {
            Elevator elevator = new Elevator();
            liftFloorMap
                    .computeIfAbsent(elevator.getCurrentFloor(), floor -> new ArrayList<>())
                    .add(elevator);
        }
    }

    // getters
    public static Map<Integer, List<Elevator>> getLiftFloorMap() {
        return liftFloorMap;
    }
}
