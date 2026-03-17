package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Elevator {
    private int currentFloor;
    private Queue<Integer> destinationFloors = new PriorityQueue<>();
    private int numOfPassengers;
    private boolean isGoingUp;
    private List<Passenger> passengers = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger();

    public Elevator() {
        this.numOfPassengers = 0;
        this.isGoingUp = true;
    }

    // ======= FLOOR METHODS ======= //
    public void requestFloor(){
        if (this.numOfPassengers == 0) {
            RequestManager.getInstance().emptyLiftFloorAlloc(this);
        }
    }

    public void loadDestinationFloor(int destinationFloor) {
        destinationFloors.add(destinationFloor); // PQ will automatically sort new floors
    }

    // ======= PASSENGER LOADING METHODS ======= //
    public void loadPassengers(Request r) throws InterruptedException {
        logger.debug("{} loading {} passengers", Thread.currentThread().getName(), r.getNumOfPassengers());
        Thread.sleep(5000);
        // Add pax count
        this.numOfPassengers += r.getNumOfPassengers();

        // Get pax off the floor list and onto the elevator list
        PassengerFloorMap pfm = PassengerFloorMap.getInstance();
        List<Passenger> floorPassengers = pfm.getPassengerFloorMap().get(r.getCurrentFloor());


        if (floorPassengers == null) { // Handle NPE
            pfm.loadFloorMapWithPassengers(r);
            floorPassengers = pfm.getPassengerFloorMap().get(r.getCurrentFloor());
        }

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
        logger.debug("{} unloading {} passengers", Thread.currentThread().getName(), request.getNumOfPassengers());
        Thread.sleep(5000);
        // subtract pax count
        this.numOfPassengers -= request.getNumOfPassengers();

        // unload pax
        this.getPassengers().removeIf(passenger ->
                passenger.getDestinationFloor() == this.getCurrentFloor()
        );
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

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

    public boolean isGoingUp() {
        return isGoingUp;
    }

    public void setGoingUp(boolean goingUp) {
        this.isGoingUp = goingUp;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}
