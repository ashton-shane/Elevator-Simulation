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
        try (
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            var lines = new ArrayList<String>();
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }

            this.config = new Configuration(
                    Integer.valueOf(lines.get(0).trim()),
                    Integer.valueOf(lines.get(1).trim()));
            for (int i = 2; i < lines.size(); i++) {
                var fields = lines.get(i).split(" ");
                requests.add(
                    new Request(
                        fields[0].trim().equals("G") ? 0 : Integer.valueOf(fields[0].trim()),
                        fields[1].trim().equals("G") ? 0 : Integer.valueOf(fields[1].trim()),
                        Integer.valueOf(fields[2].trim())
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
