package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;

import java.util.*;

public class Elevator {
    private int currentFloor;
    private Queue<Integer> destinationFloors = new ArrayDeque<>();
    private int numOfPassengers;
    private boolean isGoingUp;
    private List<Passenger> passengers = new ArrayList<>();
    private List<Request> currentRequests = new ArrayList<>();

    public Elevator() {
        this.numOfPassengers = 0;
        this.isGoingUp = true;
    }

    // floor methods
    public void requestFloor(){
        if (this.numOfPassengers == 0) {
            RequestManager.allocateRequest(this);
        }
    }

    public void loadDestinationFloor(int destinationFloor) {
        destinationFloors.add(destinationFloor);
        // sort
    }

    public void goToFloor(Request request) throws InterruptedException {
            Thread.sleep(3000);
            this.setCurrentFloor(request.getCurrentFloor());
    }

    // passenger loading methods
    public void loadPassengers(Request r) throws InterruptedException {
        Thread.sleep(5000);
        // Add pax count
        this.numOfPassengers += r.getNumOfPassengers();

        // Get pax off the floor list and onto the elevator list
        List<Passenger> floorPassengers = PassengerFloorMap.getBuildingFloorMap().get(r.getCurrentFloor());
        Iterator<Passenger> iterator = floorPassengers.iterator();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (passenger.isGoingUp() == this.isGoingUp()) {
                this.getPassengers().add(passenger);
                iterator.remove();
            }
        }
    }

    public void unloadPassengers(Request request) throws InterruptedException {
        Thread.sleep(5000);
        // subtract pax count
        this.numOfPassengers -= request.getNumOfPassengers();

        // unload pax
        this.getPassengers().removeIf(passenger ->
                passenger.getDestinationFloor() == this.getCurrentFloor()
        );

        // remove from elevator requests
        this.getCurrentRequests().remove(request);
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

    public List<Request> getCurrentRequests() {
        return currentRequests;
    }

}
