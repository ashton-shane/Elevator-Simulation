package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.LiftFloorMap;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ElevatorService {
    private static ElevatorService instance = new ElevatorService();
    private List<Elevator> elevators = new ArrayList<>();
    LiftFloorMap liftFloorMap = LiftFloorMap.getInstance();

    // ======= SINGLETON ======= //
    private ElevatorService() {}

    public static ElevatorService getInstance() {
        return instance;
    }
    // ======= LIFT MOVEMENT METHODS ======= //
    public synchronized void moveElevator(Elevator elevator, Request request) {
        try {
            // simulate movement
            Thread.sleep(3000);

            Map<Integer, List<Elevator>> map = liftFloorMap.getLiftFloorMap();

            // remove lift from current floor (ensure list exists so we don't NPE)
            int fromFloor = elevator.getCurrentFloor();
            List<Elevator> currentLiftFloor = map.computeIfAbsent(
                    fromFloor,
                    floor -> Collections.synchronizedList(new ArrayList<>())
            );
            currentLiftFloor.remove(elevator);

            // ensure list exists for destination floor, then add elevator
            List<Elevator> newLiftFloor = map.computeIfAbsent(
                    request.getDestinationFloor(),
                    floor -> Collections.synchronizedList(new ArrayList<>())
            );
            newLiftFloor.add(elevator);
            elevator.setCurrentFloor(request.getDestinationFloor());

            // remove the dest floor from the queue
            elevator.getDestinationFloors().remove();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    // ======= GETTERS ======= //
    public List<Elevator> getElevators() {
        return elevators;
    }
}
