package com.apakgroup.training.tutorial.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apakgroup.training.tutorial.pricing.ValuationCalculator;
import com.apakgroup.training.tutorial.pricing.ValuationDAO;

public class ValuationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValuationService.class);

    private ValuationDAO valuationDAO;

    private ValuationCalculator valuationCalculator;

    public void valueVehicle(Vehicle vehicle) {
        LOGGER.trace("Vehicle {} is being valued", vehicle.getLookupCode());
        vehicle.setValue(this.getCalculator().calculatePrice(this.getDAO().getPriceRecord(vehicle.getLookupCode()),
                vehicle.getMileage()));
    }

    public void setDAO(ValuationDAO valuationDAO) {
        LOGGER.info("Setting the DAO");
        this.valuationDAO = valuationDAO;
    }

    public void setCalculator(ValuationCalculator valuationCalculator) {
        LOGGER.info("Setting the ValuationCalculator");
        this.valuationCalculator = valuationCalculator;
    }

    public ValuationDAO getDAO() {
        return this.valuationDAO;
    }

    public ValuationCalculator getCalculator() {
        return this.valuationCalculator;
    }

}
