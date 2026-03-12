package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private int currentFloor;
    private List<Integer> destinationFloors = new ArrayList<>();
    private int numOfPassengers;
    private ElevatorService elevatorService;

    public Elevator(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    // methods
    public void requestFloor(){
        if (this.numOfPassengers == 0) {
            elevatorService.allocateRequest(this);
        }
    }

    public void loadDestinationFloor(int destinationFloor) {
        destinationFloors.add(destinationFloor);
        // sort
    }

    public void goToFloor(int floor){
        this.setCurrentFloor(floor);
    }


    // getters
    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public List<Integer> getDestinationFloors() {
        return destinationFloors;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

}
