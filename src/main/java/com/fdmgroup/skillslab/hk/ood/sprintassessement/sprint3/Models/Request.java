package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

public class Request {
    private int currentFloor;
    private int destinationFloor;
    private int numOfPassengers;
    private boolean goingUp = true;

    public Request(int currentFloor, int destinationFloor, int numOfPassengers) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.numOfPassengers = numOfPassengers;

        if (destinationFloor < currentFloor){
            this.goingUp = false;
        }
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

    public boolean isGoingUp() {
        return goingUp;
    }

}
