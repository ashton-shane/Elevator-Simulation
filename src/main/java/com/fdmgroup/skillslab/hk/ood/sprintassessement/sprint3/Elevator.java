package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private int currentFloor;
    private List<Integer> destinationFloors = new ArrayList<>();
    private int numOfPassengers;

    // methods
    public void requestFloor(){
        // To change return type to Request
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

}
