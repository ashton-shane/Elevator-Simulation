package sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.RequestManager;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Elevator;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.PassengerFloorMap;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class TestRequestManager {

    private RequestManager rm;

    @BeforeEach
    void resetState() {
        rm = RequestManager.getInstance();
        rm.getRequestsPool().clear();
        rm.getReqPendingAssignment().clear();
        PassengerFloorMap.getInstance().getPassengerFloorMap().clear();
    }

    @Test
    void moveToPendingRequests_doesNothing_whenPoolEmpty() {
        rm.moveToPendingRequests();
        assertTrue(rm.getReqPendingAssignment().isEmpty());
        assertTrue(rm.getRequestsPool().isEmpty());
    }

    @Test
    void moveToPendingRequests_movesHeadFromPoolToPending_andRemovesFromPool() {
        Request r1 = new Request(0, 3, 1);
        Request r2 = new Request(2, 4, 1);
        Queue<Request> pool = rm.getRequestsPool();
        pool.add(r1);
        pool.add(r2);

        rm.moveToPendingRequests();

        assertEquals(1, rm.getReqPendingAssignment().size());
        assertSame(r1, rm.getReqPendingAssignment().get(0));
        assertEquals(1, pool.size());
        assertSame(r2, pool.peek());
    }

    @Test
    void getNextRequestForElevator_returnsNull_whenNoDestinations() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(1);

        rm.getReqPendingAssignment().add(new Request(1, 2, 1));
        assertNull(rm.getNextRequestForElevator(elevator));
        assertEquals(1, rm.getReqPendingAssignment().size(), "pending list should be unchanged");
    }

    @Test
    void getNextRequestForElevator_claimsAndRemovesMatchingRequest() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(1);
        elevator.loadDestinationFloor(3);

        Request match = new Request(1, 3, 2);
        Request other = new Request(1, 4, 1);
        rm.getReqPendingAssignment().add(other);
        rm.getReqPendingAssignment().add(match);

        Request claimed = rm.getNextRequestForElevator(elevator);
        assertSame(match, claimed);
        assertEquals(1, rm.getReqPendingAssignment().size());
        assertSame(other, rm.getReqPendingAssignment().get(0));
    }

    @Test
    void requestSameFloorAlloc_loadsMatchingRequests_removesFromPending_andLoadsPassengerMap() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(2);

        Request r1 = new Request(2, 5, 2);
        Request r2 = new Request(3, 6, 1);
        rm.getReqPendingAssignment().add(r1);
        rm.getReqPendingAssignment().add(r2);

        rm.requestSameFloorAlloc(elevator);

        assertEquals(1, rm.getReqPendingAssignment().size());
        assertSame(r2, rm.getReqPendingAssignment().get(0));

        assertTrue(elevator.getDestinationFloors().contains(5));
        assertTrue(PassengerFloorMap.getInstance().getPassengerFloorMap().containsKey(2));
        assertEquals(2, PassengerFloorMap.getInstance().getPassengerFloorMap().get(2).size());
    }

    @Test
    void emptyLiftFloorAlloc_returnsImmediately_whenPendingEmpty() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);

        rm.emptyLiftFloorAlloc(elevator);

        assertEquals(5, elevator.getCurrentFloor());
        assertTrue(elevator.getDestinationFloors().isEmpty());
    }

    @Test
    void emptyLiftFloorAlloc_movesElevatorToNearestDestinationFloor_whenCalled() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);

        Request r1 = new Request(6, 8, 5);
        Request r2 = new Request(7, 10, 5);

        rm.getReqPendingAssignment().add(r1);
        rm.getReqPendingAssignment().add(r2);

        rm.emptyLiftFloorAlloc(elevator);

        assertEquals(6, elevator.getCurrentFloor());
    }

    @Test
    void emptyListFloorAlloc_addsDestinationFloorsToElevatorOfRequestsGoingInSameDirectionAndSameCurrentFloor_whenCalled() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);

        Request r1 = new Request(6, 8, 5);
        Request r2 = new Request(6, 9, 5);
        Request r3 = new Request(7, 10, 5);

        rm.getReqPendingAssignment().add(r1);
        rm.getReqPendingAssignment().add(r2);        
        rm.getReqPendingAssignment().add(r3);
        
        rm.emptyLiftFloorAlloc(elevator);

        assertEquals(2, elevator.getDestinationFloors().size());
        assertFalse(elevator.getDestinationFloors().contains(10));
    }
}

