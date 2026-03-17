package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConfigLoader {
    private static final Logger logger = LogManager.getLogger();
    public void loadConfigFile(String filePath) {
        // clear any previous configuration so loader can be reused
        RequestManager.getInstance().getRequestsPool().clear();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("configuration file not found: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            // read and sanitise lines: trim whitespace and ignore blank lines
            var lines = reader.lines()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();

            if (lines.size() < 2) {
                throw new IllegalArgumentException("configuration file must contain at least two numbers");
            }

            // Set simulation attributes
            Configuration.setSimulationPeriod(Integer.parseInt(lines.get(0)));
            Configuration.setSimulationRate(Integer.parseInt(lines.get(1)));
            logger.debug("Configuration simulation period has been set to: {}", Configuration.getSimulationPeriod());
            logger.debug("Configuration simulation rate has been set to: {}", Configuration.getSimulationRate());

            for (int i = 2; i < lines.size(); i++) {
                var fields = lines.get(i).split("\\s+");
                if (fields.length < 3) {
                    continue; // skip malformed request line
                }
                RequestManager.getInstance().getRequestsPool().add(
                    new Request(
                        "G".equals(fields[0]) ? 0 : Integer.parseInt(fields[0]),
                        "G".equals(fields[1]) ? 0 : Integer.parseInt(fields[1]),
                        Integer.parseInt(fields[2])
                    )
                );
            }
            logger.debug("A total of {} requests has been added to Request Manager", 
                RequestManager.getInstance().getRequestsPool().size());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

//    public Optional<Configuration> getConfig() {
//        if (this.config == null) {
//            return Optional.empty();
//        }
//        return Optional.of(this.config);
//    }
}
