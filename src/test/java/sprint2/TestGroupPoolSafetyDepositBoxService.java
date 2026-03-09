package sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.SafetyDepositBox;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.SafetyDepositBoxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

public class TestGroupPoolSafetyDepositBoxService {
    SafetyDepositBoxService safetyDepositBoxService;

    @BeforeEach
    public void setup_config(){
        safetyDepositBoxService = spy(SafetyDepositBoxService.getUniqueInstance());
    }

    @Test
    public void returnsTwoFromGetNumberOfSafetyDepositBoxes_whenProgramStarts(){
        assertEquals(2, safetyDepositBoxService.getNumberOfSafetyDepositBoxes());
    };

    @Test
    public void returnsListofSizeTwoForSafetyDepositBoxes_whenProgramStarts(){
        assertEquals(2, safetyDepositBoxService.getSafetyDepositBoxes().size());
    }

    @Test
    public void returnsSafetyDepositBoxQueueofSizeOne_whenSetSizetoOne() {
        safetyDepositBoxService.setTotalNumberOfSafetyDepositBoxes(1);
        assertEquals(1, safetyDepositBoxService.getNumberOfSafetyDepositBoxes());
    }

    @Test
    public void returnsNull_whenRemovingSafetyDepositBoxFromList() {
        safetyDepositBoxService.setTotalNumberOfSafetyDepositBoxes(1);
        assertEquals(1, safetyDepositBoxService.getNumberOfSafetyDepositBoxes());
    }

    @Test
    public void verifyGetReleasedSafetyBoxCalledOnce_whenAllocateSafetyDepositBoxWithBoxAvailable(){
        safetyDepositBoxService.allocateSafetyDepositBox();
        verify(safetyDepositBoxService).getReleasedSafetyDepositBox();
    }

    @Test
    public void returnsInstanceOfSafetyDepositBox_whenReleaseSafetyDepositBox(){
        assertInstanceOf(SafetyDepositBox.class, safetyDepositBoxService.getReleasedSafetyDepositBox());
    }

    @Test
    public void returnsListOfSizeThree_whenAllocateSafetyDepositBoxWithCurrentBoxesLessThanTotalAndAllTaken(){
        for (SafetyDepositBox box : safetyDepositBoxService.getSafetyDepositBoxes()) {
            box.setAllotted(true);
        }
        safetyDepositBoxService.setTotalNumberOfSafetyDepositBoxes(5);
        safetyDepositBoxService.allocateSafetyDepositBox();
        assertEquals(3, safetyDepositBoxService.getSafetyDepositBoxes().size());
    }

    @Test
    public void returnsTwo_whenGetNumberOfAvailableSafetyBoxes(){
        assertEquals(2, safetyDepositBoxService.getNumberOfAvailableSafetyBoxes());
    }

    @Test
    public void returnsZero_whenGetNumberOfAvailableSafetyBoxesOneAfterAllottedOneAdded(){
        safetyDepositBoxService.setTotalNumberOfSafetyDepositBoxes(3);
        for (SafetyDepositBox box : safetyDepositBoxService.getSafetyDepositBoxes()) {
            box.setAllotted(true);
        }
        safetyDepositBoxService.allocateSafetyDepositBox(); // note this creates a box and releases it
        assertEquals(0, safetyDepositBoxService.getNumberOfAvailableSafetyBoxes());
    }

    @Test
    public void returnsOne_whenReleaseSafetyDepositBox(){
        safetyDepositBoxService.setTotalNumberOfSafetyDepositBoxes(3);
        for (SafetyDepositBox box : safetyDepositBoxService.getSafetyDepositBoxes()) {
            box.setAllotted(true);
        }

        SafetyDepositBox safetyDepositBox = mock(SafetyDepositBox.class);
        safetyDepositBox.setAllotted(true);
        safetyDepositBoxService.getSafetyDepositBoxes().add(safetyDepositBox);
        safetyDepositBoxService.releaseSafetyDepositBox(safetyDepositBox);
        assertEquals(1, safetyDepositBoxService.getNumberOfAvailableSafetyBoxes());
    }
}
