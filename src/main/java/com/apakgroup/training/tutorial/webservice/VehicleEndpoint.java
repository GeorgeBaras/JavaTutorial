package com.apakgroup.training.tutorial.webservice;

import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.pricing.cap.CAPValuationCalculator;

public class VehicleEndpoint {

    private CAPValuationCalculator capValuationCalculator;

    private PriceRecordService priceRecordService;

    public VehicleEndpoint() {
        super();
    }

    // Getters and Setters

    public CAPValuationCalculator getCapValuationCalculator() {
        return capValuationCalculator;
    }

    public void setCapValuationCalculator(CAPValuationCalculator capValuationCalculator) {
        this.capValuationCalculator = capValuationCalculator;
    }

    public PriceRecordService getPriceRecordService() {
        return priceRecordService;
    }

    public void setPriceRecordService(PriceRecordService priceRecordService) {
        this.priceRecordService = priceRecordService;
    }

}
