package sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.FeeCalculatorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestFeeCalculatorServiceImpl {
    @Test
    public void returnsDoubleFee_whenCalculateFee(){
        FeeCalculatorServiceImpl feeCalculator = new FeeCalculatorServiceImpl();

        assertEquals(20.00, feeCalculator.calculateFee(99.00), 0.001);
        assertEquals(15.00, feeCalculator.calculateFee(455.00), 0.001);
        assertEquals(10.00, feeCalculator.calculateFee(599.00), 0.001);
        assertEquals(5.00, feeCalculator.calculateFee(1789.00), 0.001);
        assertEquals(0.00, feeCalculator.calculateFee(2000.55), 0.001);
    }
}
