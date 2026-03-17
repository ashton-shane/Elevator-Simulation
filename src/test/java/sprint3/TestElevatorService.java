package sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.LiftFloorMap;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestElevatorService {

    @BeforeEach
    void resetState() {
        LiftFloorMap.getInstance().getLiftFloorMap().clear();
    }

    @Test
    void moveElevator_movesElevatorBetweenFloors_updatesLiftMap_andConsumesDestination() {
        ElevatorService svc = ElevatorService.getInstance();
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(1);
        elevator.loadDestinationFloor(3);

        // Put elevator into lift map at current floor
        Map<Integer, List<Elevator>> map = LiftFloorMap.getInstance().getLiftFloorMap();
        map.put(1, Collections.synchronizedList(new ArrayList<>()));
        map.get(1).add(elevator);

        Request request = new Request(1, 3, 1);

        svc.moveElevator(elevator, request);

        assertEquals(3, elevator.getCurrentFloor());
        assertTrue(map.get(1) == null || !map.get(1).contains(elevator));
        assertNotNull(map.get(3));
        assertTrue(map.get(3).contains(elevator));
        assertTrue(elevator.getDestinationFloors().isEmpty(), "destination queue head should be removed");
    }
}

