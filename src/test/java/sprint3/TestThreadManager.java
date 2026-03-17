package sprint3;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Controllers.ElevatorService;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint3.Models.ThreadManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestThreadManager {

    @BeforeEach
    void resetState() {
        ElevatorService.getInstance().getElevators().clear();
    }

    @Test
    void createThreads_addsNThreads_andNCreatesElevators() {
        ThreadManager tm = new ThreadManager();
        tm.createThreads(3);

        assertEquals(3, tm.getThreadList().size());
        assertEquals(3, ElevatorService.getInstance().getElevators().size());
    }
}

