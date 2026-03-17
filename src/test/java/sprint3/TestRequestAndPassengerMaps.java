package sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.LiftFloorMap;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Passenger;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.PassengerFloorMap;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestRequestAndPassengerMaps {

    @BeforeEach
    void resetState() {
        RequestManager.getInstance().getRequestsPool().clear();
        RequestManager.getInstance().getReqPendingAssignment().clear();
        PassengerFloorMap.getInstance().getPassengerFloorMap().clear();
        LiftFloorMap.getInstance().getLiftFloorMap().clear();
        ElevatorService.getInstance().getElevators().clear();
    }

    @Test
    void request_setsGoingUpFalse_whenDestinationBelowCurrent() {
        Request r = new Request(5, 2, 1);
        assertFalse(r.isGoingUp());
    }

    @Test
    void request_setsGoingUpTrue_whenDestinationAboveOrEqualCurrent() {
        assertTrue(new Request(2, 5, 1).isGoingUp());
        assertTrue(new Request(2, 2, 1).isGoingUp());
    }

    @Test
    void passengerFloorMap_loadFloorMapWithPassengers_createsCorrectCountAndFields() {
        Request r = new Request(1, 4, 3);
        PassengerFloorMap.getInstance().loadFloorMapWithPassengers(r);

        Map<Integer, List<Passenger>> map = PassengerFloorMap.getInstance().getPassengerFloorMap();
        assertTrue(map.containsKey(1));
        assertEquals(3, map.get(1).size());
        for (Passenger p : map.get(1)) {
            assertEquals(1, p.getCurrentFloor());
            assertEquals(4, p.getDestinationFloor());
            assertTrue(p.isGoingUp());
        }
    }

    @Test
    void liftFloorMap_loadFloorMapWithLifts_groupsElevatorsByCurrentFloor() {
        Elevator e1 = new Elevator();
        e1.setCurrentFloor(0);
        Elevator e2 = new Elevator();
        e2.setCurrentFloor(2);
        Elevator e3 = new Elevator();
        e3.setCurrentFloor(2);

        ElevatorService.getInstance().getElevators().add(e1);
        ElevatorService.getInstance().getElevators().add(e2);
        ElevatorService.getInstance().getElevators().add(e3);

        LiftFloorMap.getInstance().loadFloorMapWithLifts();

        Map<Integer, List<Elevator>> map = LiftFloorMap.getInstance().getLiftFloorMap();
        assertEquals(2, map.size());
        assertTrue(map.get(0).contains(e1));
        assertEquals(2, map.get(2).size());
        assertTrue(map.get(2).contains(e2));
        assertTrue(map.get(2).contains(e3));
    }
}

