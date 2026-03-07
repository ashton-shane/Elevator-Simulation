package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

public class FeeCalculatorServiceImpl implements IFeeCalculationService {
    @Override
    public double calculateFee(double accBalance) {
        if (accBalance <= 100.00) {
            return 20.00;
        }
        else if (accBalance > 100.00 && accBalance <= 500.00){
            return 15.00;
        }
        else if (accBalance > 500.00 && accBalance <= 1000.00){
            return 10.00;
        }
        else if (accBalance > 1000.00 && accBalance <= 2000.00){
            return 5.00;
        }
        else if (accBalance > 2000) {
            return 0.00;
        }
        // implement optional next time
        return 0.00;
    }
}
