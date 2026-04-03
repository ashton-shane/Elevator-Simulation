package sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.ConfigLoader;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Configuration;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class TestConfigLoader {
    private ConfigLoader loader;
    private RequestManager requestManager;

    @BeforeEach
    void setUp() {
        requestManager = RequestManager.getInstance();
        requestManager.getRequestsPool().clear();
        requestManager.getReqPendingAssignment().clear();
        loader = new ConfigLoader();
    }

    @Test
    void beforeLoading_requestsPoolIsEmpty() {
        assertTrue(requestManager.getRequestsPool().isEmpty(), "request pool should be empty before loading");
    }

    @Test
    void loadValidResource_populatesConfigAndRequests() {
        loader.loadConfigFile("testConfig.txt");

        assertEquals(5, Configuration.getSimulationPeriod());
        assertEquals(1, Configuration.getSimulationRate());

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
                () -> loader.loadConfigFile("does-not-exist.txt"));

        assertTrue(requestManager.getRequestsPool().isEmpty(), "requests should still be empty after failure");
    }

    @Test
    void repeatedLoads_replacePreviousData() {
        loader.loadConfigFile("testConfig.txt");
        int firstSize = requestManager.getRequestsPool().size();
        loader.loadConfigFile("testConfig.txt");
        assertEquals(firstSize, requestManager.getRequestsPool().size(), "loader should clear previous requests when reloading");
    }
}
