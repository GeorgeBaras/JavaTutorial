package com.apakgroup.training.tutorial.webservice;

import java.math.BigDecimal;

import org.springframework.transaction.annotation.Transactional;

import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.pricing.cap.CAPValuationCalculator;

@Transactional
public class VehicleEndpoint {

    private CAPValuationCalculator capValuationCalculator;

    private PriceRecordService priceRecordService;

    public VehicleEndpoint() {
        super();
    }

    public ValueVehicleResponse handlevalueVehicleRequest(ValueVehicleRequest request) {
        ValueVehicleResponse result = new ValueVehicleResponse();
        // get the priceRecord by the lookupcode sent with the request
        PriceRecord priceRecord = this.priceRecordService.getPriceRecordByLookupcode(request.getLookupCode());
        // calculate the valuation using the priceRecord and the mileage sent with the request
        BigDecimal valuation = this.capValuationCalculator.calculatePrice(priceRecord,
                Integer.parseInt(request.getMileage().toString()));
        // set the result value and return it
        result.setValue(valuation);
        return result;
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
