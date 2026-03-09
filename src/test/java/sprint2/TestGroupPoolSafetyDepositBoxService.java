package sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.SafetyDepositBoxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.verify;

public class TestGroupPoolSafetyDepositBoxService {
    SafetyDepositBoxService safetyDepositBoxService;

    @BeforeEach
    public void setup_config(){
        safetyDepositBoxService = SafetyDepositBoxService.getUniqueInstance();
    }

    @Test
    public void returnsSafetyDepositBoxQueueOfSizeTwo_whenProgramStarts(){
        assertEquals(2, safetyDepositBoxService.getNumberOfSafetyDepositBoxes());
    };

    @Test
    public void returnsSafetyDepositBoxQueueofSizeOne_whenSetSizetoOne() {
        safetyDepositBoxService.setNumberOfSafetyDepositBoxes(1);
        assertEquals(1, safetyDepositBoxService.getNumberOfSafetyDepositBoxes());
    }

    @Test
    public void returnsNull_whenRemovingSafetyDepositBoxFromList() {
        safetyDepositBoxService.setNumberOfSafetyDepositBoxes(1);
        assertEquals(1, safetyDepositBoxService.getNumberOfSafetyDepositBoxes());
    }




}
