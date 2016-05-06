package com.apakgroup.training.tutorial.webservice;

import com.apakgroup.training.tutorial.model.PriceRecordService;

public class PriceRecordEndpoint {

    private PriceRecordService priceRecordService;

    public PriceRecordEndpoint() {
    }

    // Getters and Setters
    public PriceRecordService getPriceRecordService() {
        return priceRecordService;
    }

    public void setPriceRecordService(PriceRecordService priceRecordService) {
        this.priceRecordService = priceRecordService;
    }

}
