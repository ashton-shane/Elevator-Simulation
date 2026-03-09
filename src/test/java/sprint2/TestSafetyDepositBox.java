package sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.SafetyDepositBox;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.SafetyDepositBoxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TestSafetyDepositBox {
    SafetyDepositBoxService safetyDepositBoxService;

    @BeforeEach
    public void setup_config(){
        safetyDepositBoxService = SafetyDepositBoxService.getUniqueInstance();
        safetyDepositBoxService.setTotalNumberOfSafetyDepositBoxes(5);
    }

    @Test
    public void returnsIdOfFiveFromLastObjectInList_whenSetFiveBoxes(){
        SafetyDepositBox lastBox = safetyDepositBoxService.getSafetyDepositBoxes().getLast();
        assertEquals(5, lastBox.getId());
    }

    @Test
    public void returnsNextIdOfSixFromLastObjectInList_whenSetFiveBoxes(){
        assertEquals(6, SafetyDepositBox.getNextId());
    }

    @Test
    public void returnsListOfSizeThree_whenSetFivesBoxesThenRemoveTwoBoxes(){
        safetyDepositBoxService.setTotalNumberOfSafetyDepositBoxes(3);
        assertEquals(3, safetyDepositBoxService.getSafetyDepositBoxes().size());
    }
}
