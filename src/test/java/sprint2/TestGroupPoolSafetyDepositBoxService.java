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
        SafetyDepositBoxService.resetInstance();
        safetyDepositBoxService = spy(SafetyDepositBoxService.getUniqueInstance());
    }

    @Test
    public void returnsTwoFromGetNumberOfSafetyDepositBoxes_whenProgramStarts(){
        int numOfBoxes = safetyDepositBoxService.getMaxNumberOfSafetyDepositBoxes();
        assertEquals(2, numOfBoxes);
    };

    @Test
    public void returnsListofSizeTwoForSafetyDepositBoxes_whenProgramStarts(){
        int numOfBoxes = safetyDepositBoxService.getCurrTotalBoxesCreated();
        assertEquals(2, numOfBoxes);
    }

    @Test
    public void returnsSafetyDepositBoxQueueofSizeOne_whenSetSizetoOne() {
        safetyDepositBoxService.setMaxNumberOfSafetyDepositBoxes(1);
        int numOfBoxes = safetyDepositBoxService.getMaxNumberOfSafetyDepositBoxes();
        assertEquals(1, numOfBoxes);
    }

    @Test
    public void returnsOne_whenRemovingSafetyDepositBoxFromList() {
        safetyDepositBoxService.setMaxNumberOfSafetyDepositBoxes(1);
        int numOfBoxes = safetyDepositBoxService.getMaxNumberOfSafetyDepositBoxes();
        assertEquals(1, numOfBoxes);
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
        safetyDepositBoxService.allocateSafetyDepositBox();
        safetyDepositBoxService.allocateSafetyDepositBox();
        safetyDepositBoxService.setMaxNumberOfSafetyDepositBoxes(3);
        safetyDepositBoxService.allocateSafetyDepositBox();
        assertEquals(3, safetyDepositBoxService.getCurrTotalBoxesCreated());
    }

    @Test
    public void returnsTwo_whenGetNumberOfAvailableSafetyBoxes(){
        int numOfBoxes = safetyDepositBoxService.getNumberOfAvailableSafetyBoxes();
        assertEquals(2, numOfBoxes);
    }

    @Test
    public void returnsZero_whenGetNumberOfAvailableSafetyBoxesOneAfterAllottedOneAdded(){
        safetyDepositBoxService.setMaxNumberOfSafetyDepositBoxes(3);
        safetyDepositBoxService.allocateSafetyDepositBox();
        safetyDepositBoxService.allocateSafetyDepositBox();
        // increase by one
        // this immediately creates, releases, and allots it - also tests the release function
        safetyDepositBoxService.allocateSafetyDepositBox();
        int numOfBoxes = safetyDepositBoxService.getNumberOfAvailableSafetyBoxes();
        assertEquals(0, numOfBoxes);
    }
}
