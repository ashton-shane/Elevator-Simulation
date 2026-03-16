package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LiftFloorMap {
    private static final LiftFloorMap instance = new LiftFloorMap();
    private final Map<Integer, List<Elevator>> liftFloorMap;

    // singleton
    private LiftFloorMap() {
        this.liftFloorMap = new ConcurrentHashMap<>();
    }

    public static LiftFloorMap getInstance() {
        return instance;
    }

    // load lifts
    public void loadFloorMapWithLifts(){
        for (Elevator elevator : ElevatorService.getInstance().getElevators()) {
            Elevator elevator = new Elevator();
            this.getLiftFloorMap()
                    .computeIfAbsent(elevator.getCurrentFloor(), floor -> new ArrayList<>())
                    .add(elevator);
        }
    }

    // getters
    public Map<Integer, List<Elevator>> getLiftFloorMap() {
        return this.liftFloorMap;
    }
}
