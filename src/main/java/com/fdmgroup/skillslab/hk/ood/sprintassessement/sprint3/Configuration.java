package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

public class Configuration {
    private static int simulationPeriod;
    private static int simulationRate;

    public static long getSimulationPeriod() {
        return simulationPeriod;
    }

    public static int getSimulationRate() {
        return simulationRate;
    }

    public static void setSimulationPeriod(int simulationPeriod) {
        Configuration.simulationPeriod = simulationPeriod;
    }

    public static void setSimulationRate(int simulationRate) {
        Configuration.simulationRate = simulationRate;
    }
}