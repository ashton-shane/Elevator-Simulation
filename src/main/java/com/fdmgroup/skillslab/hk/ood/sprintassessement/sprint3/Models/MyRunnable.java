package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Configuration;

public class MyRunnable implements Runnable {
    private final Configuration config;

    public MyRunnable(Configuration config) {
        this.config = config;
    }

    @Override
    public void run(){
        long endTime = System.currentTimeMillis() + (1000L * config.simulationPeriod());
        while (System.currentTimeMillis() < endTime) {

        }
    }
}
