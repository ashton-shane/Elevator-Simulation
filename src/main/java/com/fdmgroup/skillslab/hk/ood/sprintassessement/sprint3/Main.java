package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static void main(String[] args) {

        try {
            // TODO To provide a headstart, below code helps reading out a ElevatorConfig.txt file
            var fileLines =  readElevatorConfigFile("sprint3.assessment.ElevatorConfig.txt");
            for(var line : fileLines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static List<String> readElevatorConfigFile(String elevatorConfigFile) throws IOException {

        var line = "";
        var lines = new ArrayList<String>();
        try(
            var inputStream = Main.class.getClassLoader().getResourceAsStream("sprint3.assessment.ElevatorConfig.txt");
            var bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
        ){
            while((line = bufferedReader.readLine()) != null)
                lines.add(line);
            return lines;
        } catch (IOException e) {
           throw e;
        }
    }
}
