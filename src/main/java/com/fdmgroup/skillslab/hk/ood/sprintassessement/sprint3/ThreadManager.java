package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager extends Thread {
    public void createThreads(int n){
        List<Thread> threadList = new ArrayList<>();

        for(int i = 0; i < n; i++){
            Thread thread = new Thread();
            threadList.add(thread);
        }
    }

}
