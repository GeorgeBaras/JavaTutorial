package com.apakgroup.training.tutorial.rest;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.pricing.cap.CAPValuationCalculator;

@RestController
public class VehicleRestController {

    private PriceRecordService priceRecordService;

    private CAPValuationCalculator capValuationCalculator;

    public VehicleRestController() {

    }

    @RequestMapping(value = "/valueVehicle/{mileage}/{lookupCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> valueVehicle(@PathVariable("mileage") int mileage,
            @PathVariable("lookupCode") String lookupCode) {
        HttpStatus status = HttpStatus.OK;
        String valuationString = null;
        if (mileage < 0) {
            status = HttpStatus.BAD_REQUEST; //400 (Bad Request)
        }
        if (!this.priceRecordService.lookUpCodeIsInDB(lookupCode)) {
            status = HttpStatus.NOT_FOUND; //404 (Not Found)
        }
        if (status == HttpStatus.OK) {
            PriceRecord priceRecord = this.priceRecordService.getPriceRecordByLookupcodeEAGER(lookupCode);
            BigDecimal valuation = this.capValuationCalculator.calculatePrice(priceRecord, mileage);
            valuationString = valuation.toString();
        }
        return new ResponseEntity<>(valuationString, status);
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
