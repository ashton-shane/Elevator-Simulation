package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager extends Thread {

    private List<Thread> threadList = new ArrayList<>();

    public void createThreads(int num){
        for (int i = 0; i < num; i++) {
            Thread thread = new Thread();
            this.threadList.add(thread);
        }
    }

    public List<Thread> getThreadList() {
        return this.threadList;
    }
}
