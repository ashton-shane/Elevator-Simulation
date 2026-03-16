package sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.ConfigLoader;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Configuration;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class TestConfigLoader {
    private ConfigLoader loader;
    private RequestManager requestManager;
    private ElevatorService elevatorService;

    @BeforeEach
    void setUp() {
        resetSingleton();
        requestManager = RequestManager.getInstance();
        requestManager.getRequestsPool().clear();
        loader = new ConfigLoader();
        elevatorService = new ElevatorService();
    }

    @Test
    void getConfigEmpty_beforeLoading() {
        assertTrue(loader.getConfig().isEmpty(), "no configuration should be present before loading");
        assertTrue(requestManager.getRequestsPool().isEmpty(), "request list should be empty before loading");
    }

    @Test
    void loadValidResource_populatesConfigAndRequests() {
        loader.loadConfigFile("testConfig.txt", elevatorService);

        Optional<Configuration> maybe = loader.getConfig();
        assertTrue(maybe.isPresent());
        Configuration config = maybe.get();
        assertEquals(5, config.simulationPeriod());
        assertEquals(1, config.simulationRate());

        Queue<Request> requests = requestManager.getRequestsPool();
        assertEquals(3, requests.size(), "three requests should be parsed");

        Request r0 = requests.peek();
        assertEquals(0, r0.getCurrentFloor());
        assertEquals(3, r0.getDestinationFloor());
        assertEquals(1, r0.getNumOfPassengers());
        assertTrue(r0.isGoingUp());
        requests.remove(r0);

        Request r1 = requests.peek();
        assertEquals(1, r1.getCurrentFloor());
        assertEquals(0, r1.getDestinationFloor());
        assertEquals(2, r1.getNumOfPassengers());
        assertFalse(r1.isGoingUp());
        requests.remove(r1);

        Request r2 = requests.peek();
        assertEquals(2, r2.getCurrentFloor());
        assertEquals(4, r2.getDestinationFloor());
        assertEquals(5, r2.getNumOfPassengers());
        assertTrue(r2.isGoingUp());
    }

    @Test
    void loadNonexistent_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> loader.loadConfigFile("does-not-exist.txt", elevatorService));

        assertTrue(loader.getConfig().isEmpty(), "configuration should still be empty after failure");
        assertTrue(requestManager.getRequestsPool().isEmpty(), "requests should still be empty after failure");
    }

    @Test
    void repeatedLoads_replacePreviousData() {
        loader.loadConfigFile("testConfig.txt", elevatorService);
        int firstSize = requestManager.getRequestsPool().size();
        loader.loadConfigFile("testConfig.txt", elevatorService);
        assertEquals(firstSize, requestManager.getRequestsPool().size(), "loader should clear previous requests when reloading");
    }

    @Test
    void blankLinesAreIgnored_whenReadingRealFile() {
        loader.loadConfigFile("sprint3.assessment.ElevatorConfig.txt", elevatorService);
        assertTrue(loader.getConfig().isPresent());
        Configuration cfg = loader.getConfig().get();
        assertEquals(1000, cfg.simulationPeriod());
        assertEquals(100, cfg.simulationRate());
        assertFalse(requestManager.getRequestsPool().isEmpty(), "requests should be loaded even if file contains blank lines");
    }

    void resetSingleton(){
        requestManager = null;
    }
}
