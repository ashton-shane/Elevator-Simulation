package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static void main(String[] args) {
        var loader = new ConfigLoader();
        loader.loadConfigFile("ElevatorConfig.txt");
        // loader usage:
        // loader.getConfig(); -> returns Configuration object
        // loader.getRequests(); -> returns List<Request>
    }
}
