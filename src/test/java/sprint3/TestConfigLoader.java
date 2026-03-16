package sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.ConfigLoader;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Configuration;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestConfigLoader {

    private ConfigLoader loader;

    @BeforeEach
    void setUp() {
        loader = new ConfigLoader();
    }

    @Test
    void getConfigEmpty_beforeLoading() {
        assertTrue(loader.getConfig().isEmpty(), "no configuration should be present before loading");
        assertTrue(loader.getRequests().isEmpty(), "request list should be empty before loading");
    }

    @Test
    void loadValidResource_populatesConfigAndRequests() {
        loader.loadConfigFile("testConfig.txt");

        Optional<Configuration> maybe = loader.getConfig();
        assertTrue(maybe.isPresent());
        Configuration config = maybe.get();
        assertEquals(5, config.simulationPeriod());
        assertEquals(2, config.simulationRate());

        List<Request> requests = loader.getRequests();
        assertEquals(3, requests.size(), "three requests should be parsed");

        Request r0 = requests.get(0);
        assertEquals(0, r0.getCurrentFloor());
        assertEquals(3, r0.getDestinationFloor());
        assertEquals(1, r0.getNumOfPassengers());
        assertTrue(r0.isGoingUp());

        Request r1 = requests.get(1);
        assertEquals(1, r1.getCurrentFloor());
        assertEquals(0, r1.getDestinationFloor());
        assertEquals(2, r1.getNumOfPassengers());
        assertFalse(r1.isGoingUp());

        Request r2 = requests.get(2);
        assertEquals(2, r2.getCurrentFloor());
        assertEquals(4, r2.getDestinationFloor());
        assertEquals(5, r2.getNumOfPassengers());
        assertTrue(r2.isGoingUp());
    }

    @Test
    void loadNonexistent_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> loader.loadConfigFile("does-not-exist.txt"));

        assertTrue(loader.getConfig().isEmpty(), "configuration should still be empty after failure");
        assertTrue(loader.getRequests().isEmpty(), "requests should still be empty after failure");
    }

    @Test
    void repeatedLoads_replacePreviousData() {
        loader.loadConfigFile("testConfig.txt");
        int firstSize = loader.getRequests().size();
        loader.loadConfigFile("testConfig.txt");
        assertEquals(firstSize, loader.getRequests().size(), "loader should clear previous requests when reloading");
    }

    @Test
    void blankLinesAreIgnored_whenReadingRealFile() {
        loader.loadConfigFile("sprint3.assessment.ElevatorConfig.txt");
        assertTrue(loader.getConfig().isPresent());
        Configuration cfg = loader.getConfig().get();
        assertEquals(1000, cfg.simulationPeriod());
        assertEquals(100, cfg.simulationRate());
        assertFalse(loader.getRequests().isEmpty(), "requests should be loaded even if file contains blank lines");
    }
}
