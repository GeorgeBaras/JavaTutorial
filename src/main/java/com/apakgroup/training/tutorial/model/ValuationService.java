package com.apakgroup.training.tutorial.model;

import com.apakgroup.training.tutorial.pricing.ValuationCalculator;
import com.apakgroup.training.tutorial.pricing.ValuationDAO;

public class ValuationService {

    private ValuationDAO valuationDAO;

    private ValuationCalculator valuationCalculator;

    public void valueVehicle(Vehicle vehicle) {
        vehicle.setValue(this.getCalculator().calculatePrice(this.getDAO().getPriceRecord(vehicle.getLookupCode()),
                vehicle.getMileage()));
    }

    public void setDAO(ValuationDAO valuationDAO) {
        this.valuationDAO = valuationDAO;
    }

    public void setCalculator(ValuationCalculator valuationCalculator) {
        this.valuationCalculator = valuationCalculator;
    }

    public ValuationDAO getDAO() {
        return this.valuationDAO;
    }

    public ValuationCalculator getCalculator() {
        return this.valuationCalculator;
    }

}
