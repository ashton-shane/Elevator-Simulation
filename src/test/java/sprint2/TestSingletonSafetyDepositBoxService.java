package sprint2;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.SafetyDepositBoxService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.InstanceOf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TestSingletonSafetyDepositBoxService {
    @Test
    public void returnsNewUniqueInstance_whenGetInstanceWithoutPriorInstance(){
        assertInstanceOf(SafetyDepositBoxService.class, SafetyDepositBoxService.getUniqueInstance());
    }

    @Test
    public void returnsSameUniqueInstance_whenGetInstanceWithPriorInstance(){
        SafetyDepositBoxService uniqueInstance = SafetyDepositBoxService.getUniqueInstance();
        assertEquals(uniqueInstance, SafetyDepositBoxService.getUniqueInstance());
    }

    @Test
    public void returnsTwoFromAllottedCount_whenProgramStarts(){
        SafetyDepositBoxService uniqueInstance = SafetyDepositBoxService.getUniqueInstance();
        assertEquals(2, uniqueInstance.getAllottedCount());
    }
}
