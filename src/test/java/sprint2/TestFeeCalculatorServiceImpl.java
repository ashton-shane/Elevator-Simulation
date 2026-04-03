package sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.FeeCalculatorServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFeeCalculatorServiceImpl {
    @ParameterizedTest(name = "calculateFee({0}) should return {1}")
    @MethodSource("feeTestCases")
    void returnsCorrectFee_whenCalculateFee(double balance, double fee) {
        FeeCalculatorServiceImpl feeCalculator = new FeeCalculatorServiceImpl();
        assertEquals(fee, feeCalculator.calculateFee(balance), 0.001);
    }

    private static Stream<Arguments> feeTestCases() {
        return Stream.of(
                Arguments.of(99.00, 20.00),
                Arguments.of(455.00, 15.00),
                Arguments.of(599.00, 10.00),
                Arguments.of(1789.00, 5.00),
                Arguments.of(2000.55, 0.00)
        );
    }
}



