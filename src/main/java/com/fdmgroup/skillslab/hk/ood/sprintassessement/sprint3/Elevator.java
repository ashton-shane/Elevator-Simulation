package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private int currentFloor;
    private List<Integer> destinationFloors = new ArrayList<>();
    private int numOfPassengers;
    private boolean isGoingUp;
    private List<Passenger> passengers = new ArrayList<>();
    private ElevatorService elevatorService;

    public Elevator(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
        this.numOfPassengers = 0;
        this.isGoingUp = true;
    }

    // floor methods
    public void requestFloor(){
        if (this.numOfPassengers == 0) {
            elevatorService.allocateRequest(this);
        }
    }

    public void loadDestinationFloor(int destinationFloor) {
        destinationFloors.add(destinationFloor);
        // sort
    }

    public void goToFloor(Request request){
        this.setCurrentFloor(request.getCurrentFloor());
        // load or unload logic :(
    }

    // passenger loading methods
    public void loadPassengers(int n){
        this.numOfPassengers += n;
    }

    public void unloadPassengers(int n){
        this.numOfPassengers -= n;
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

    public boolean isGoingUp() {
        return isGoingUp;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

}
