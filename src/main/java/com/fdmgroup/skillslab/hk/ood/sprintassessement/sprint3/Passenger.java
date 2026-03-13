package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

public class Passenger {
    private int destinationFloor;
    private boolean isGoingUp;
    private int currentFloor;

    public Passenger(int currentFloor, int destinationFloor, boolean isGoingUp) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.isGoingUp = isGoingUp;
    }

    // getters and setters
    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public boolean isGoingUp() {
        return isGoingUp;
    }

    public void setGoingUp(boolean goingUp) {
        isGoingUp = goingUp;
    }

}
