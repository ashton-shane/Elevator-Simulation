package sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.SafetyDepositBox;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.SafetyDepositBoxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSafetyDepositBox {
    // this test class is to test the SafetyDepositBox class object creation and utilisation
    SafetyDepositBoxService safetyDepositBoxService;

    @BeforeEach
    public void setup_config(){
        safetyDepositBoxService = SafetyDepositBoxService.getUniqueInstance();
        safetyDepositBoxService.setTotalNumberOfSafetyDepositBoxes(3);
    }

    @Test
    public void returnsIdOfThreeFromLastObjectInList_whenSetThreeBoxes(){
        for (SafetyDepositBox box : safetyDepositBoxService.getSafetyDepositBoxes()) {
            box.setAllotted(true);
        }
        // only will it add an extra box when the current boxes are allotted.
        safetyDepositBoxService.allocateSafetyDepositBox();
        SafetyDepositBox lastBox = safetyDepositBoxService.getSafetyDepositBoxes().getLast();
        assertEquals(3, lastBox.getId());
    }

    @Test
    public void returnsNextIdOfThreeFromLastObjectInList_whenProgramStarts(){
        assertEquals(3, SafetyDepositBox.getNextId());
    }

    @Test
    public void returnsTrue_whenSetAllotted(){
        List<SafetyDepositBox> boxes = safetyDepositBoxService.getSafetyDepositBoxes();
        boxes.get(1).setAllotted(true);
        assertTrue(boxes.get(1).isAllotted());
    }
}
