package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.models;

public record Request(
    int currentFloor,
    int destinationFloor,
    int numOfPassengers
) {}
