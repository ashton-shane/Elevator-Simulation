package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;

import java.util.*;

public class Elevator {
    private int currentFloor;
    private Queue<Integer> destinationFloors = new PriorityQueue<>();
    private int numOfPassengers;
    private boolean isGoingUp;
    private List<Passenger> passengers = new ArrayList<>();

    public Elevator() {
        this.numOfPassengers = 0;
        this.isGoingUp = true;
    }

    // ======= FLOOR METHODS ======= //
    public void requestFloor(){
        if (this.numOfPassengers == 0) {
            RequestManager.getInstance().requestSameFloorAlloc(this);
        }
    }

    public void loadDestinationFloor(int destinationFloor) {
        destinationFloors.add(destinationFloor); // PQ will automatically sort new floors
    }

    // ======= PASSENGER LOADING METHODS ======= //
    public void loadPassengers(Request r) throws InterruptedException {
        Thread.sleep(5000);
        // Add pax count
        this.numOfPassengers += r.getNumOfPassengers();

        // Get pax off the floor list and onto the elevator list
        List<Passenger> floorPassengers = PassengerFloorMap.getInstance().getPassengerFloorMap().get(r.getCurrentFloor());
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


    // ======= GETTERS AND SETTERS ======= //
    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public Queue<Integer> getDestinationFloors() {
        return destinationFloors;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Request request) {
        this.currentFloor = request.getDestinationFloor();
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
