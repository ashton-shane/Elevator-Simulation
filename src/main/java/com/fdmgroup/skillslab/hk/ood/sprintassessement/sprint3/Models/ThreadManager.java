package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Configuration;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager extends Thread {
    private List<Thread> threadList = new ArrayList<>();
    private ElevatorService elevatorService = ElevatorService.getInstance();

    public void createThreads(int n){
        for(int i = 0; i < n; i++){
            // elevators
            Elevator elevator = new Elevator();
            elevatorService.getElevators().add(elevator);

            // threads
            MyRunnable myRunnable = new MyRunnable();
            Thread thread = new Thread(myRunnable);
            threadList.add(thread);
        }
    }

    public void startThreads(){
        for (Thread t : this.getThreadList()){
            t.start();
        }
    }
    public List<Thread> getThreadList() {
        return threadList;
    }


}
