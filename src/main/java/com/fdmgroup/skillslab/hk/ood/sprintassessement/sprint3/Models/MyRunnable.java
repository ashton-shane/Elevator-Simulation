package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Configuration;

public class MyRunnable implements Runnable {

    @Override
    public void run(){
        long endTime = System.currentTimeMillis() + (Configuration.getSimulationPeriod());
        while (System.currentTimeMillis() < endTime) {
            requestFloor
        }
    }
}
