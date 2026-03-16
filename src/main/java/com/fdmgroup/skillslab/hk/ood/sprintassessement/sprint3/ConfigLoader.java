package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ConfigLoader {
    private Configuration config;
    private List<Request> requests = new ArrayList<>();

    public void loadConfigFile(String filePath) {
        // clear any previous configuration so loader can be reused
        this.config = null;
        this.requests.clear();

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

            this.config = new Configuration(
                    Integer.parseInt(lines.get(0)),
                    Integer.parseInt(lines.get(1)));

            for (int i = 2; i < lines.size(); i++) {
                var fields = lines.get(i).split("\\s+");
                if (fields.length < 3) {
                    continue; // skip malformed request line
                }
                requests.add(
                    new Request(
                        "G".equals(fields[0]) ? 0 : Integer.parseInt(fields[0]),
                        "G".equals(fields[1]) ? 0 : Integer.parseInt(fields[1]),
                        Integer.parseInt(fields[2])
                    )
                );
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Optional<Configuration> getConfig() {
        if (this.config == null) {
            return Optional.empty();
        }
        return Optional.of(this.config);
    }

    public List<Request> getRequests() {
        return requests;
    }
}
