package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

public class Request {
    private int currentFloor;
    private int destinationFloor;
    private int numOfPassengers;

    public Request(int currentFloor, int destinationFloor, int numOfPassengers) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.numOfPassengers = numOfPassengers;
    }

    // getters
    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }
}
