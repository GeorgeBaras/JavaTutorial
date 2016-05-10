package com.apakgroup.training.tutorial.webservice;

import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class PriceRecordEndpoint {

    private PriceRecordService priceRecordService;

    public PriceRecordEndpoint() {
    }

    // Add PriceBand
    public AddPriceBandResponse handleaddPriceBandRequest(AddPriceBandRequest request) {
        AddPriceBandResponse result = new AddPriceBandResponse();
        /* Add the request's priceBand to the PriceRecord with the corresponding lookupCode */
        result.setConfirmation(this.priceRecordService.addPriceBandByLookupcode((PriceBand) request.getPriceBand(),
                request.getLookupCode()));
        return result;
    }

    // Add PriceRecord
    public AddPriceRecordResponse handleaddPriceRecordRequest(AddPriceRecordRequest request) {
        AddPriceRecordResponse result = new AddPriceRecordResponse();
        /* Add the request's PriceRecord to the database */
        result.setId(this.priceRecordService.addPriceRecord((PriceRecord) request.getPriceRecord()));
        return result;
    }

    public DeletePriceBandByIDResponse handledeletePriceBandByIDRequest(DeletePriceBandByIDRequest request) {
        DeletePriceBandByIDResponse result = new DeletePriceBandByIDResponse();
        /* Delete the request's priceBand from the PriceRecord with the corresponding id */
        result.setConfirmation(this.priceRecordService.removeLastPriceBandByID(request.getId()));
        return result;
    }

    public DeletePriceBandByLookUpCodeResponse handledeletePriceBandByLookUpCodeRequest(
            DeletePriceBandByLookUpCodeRequest request) {
        DeletePriceBandByLookUpCodeResponse result = new DeletePriceBandByLookUpCodeResponse();
        /* Delete the request's priceBand from the PriceRecord with the corresponding id */
        result.setConfirmation(this.priceRecordService.removeLastPriceBandByLookupcode(request.getLookupCode()));
        return result;
    }

    public DeletePriceRecordByIDResponse handledeletePriceRecordByIDRequest(DeletePriceRecordByIDRequest request) {
        DeletePriceRecordByIDResponse result = new DeletePriceRecordByIDResponse();
        // Delete the priceRecord with the corresponding id
        this.priceRecordService.deletePriceRecordByID(request.getId());
        // stabbed response !!!
        result.setConfirmation(true);
        return result;
    }

    public DeletePriceRecordByLookUpCodeResponse handledeletePriceRecordByLookUpCodeRequest(
            DeletePriceRecordByLookUpCodeRequest request) {
        DeletePriceRecordByLookUpCodeResponse result = new DeletePriceRecordByLookUpCodeResponse();
        // Delete the priceRecord with the corresponding id
        this.priceRecordService.deletePriceRecordByLookupcode(request.getLookupCode());
        // stabbed response !!!
        result.setConfirmation(true);
        return result;
    }

    public GetAllPriceRecordsResponse handlegetAllPriceRecordsRequest(GetAllPriceRecordsRequest request) {
        GetAllPriceRecordsResponse result = new GetAllPriceRecordsResponse();
        // Get all priceRecords from the DB
        for (PriceRecord priceRecord : this.priceRecordService.getAllPriceRecords()) {
            result.getPriceRecord().add((PriceRecordWire) priceRecord);
        }
        return result;
    }

    public GetPriceBandsByIDResponse handlegetPriceBandsByIDRequest(GetPriceBandsByIDRequest request) {
        GetPriceBandsByIDResponse result = new GetPriceBandsByIDResponse();
        // Get the priceRecord
        PriceRecord priceRecord = this.priceRecordService.getPriceRecordByID(request.getId());
        // populate the result's list with the priceBands of the priceRecord
        for (PriceBand priceBand : priceRecord.getPriceBands()) {
            result.getPriceBands().add((PriceBandWire) priceBand);
        }
        return result;
    }

    public GetPriceBandsByLookUpCodeResponse handlegetPriceBandsByLookUpCodeRequest(
            GetPriceBandsByLookUpCodeRequest request) {
        GetPriceBandsByLookUpCodeResponse result = new GetPriceBandsByLookUpCodeResponse();
        // Get the priceRecord
        PriceRecord priceRecord = this.priceRecordService.getPriceRecordByLookupcode(request.getLookupCode());
        // populate the result's list with the priceBands of the priceRecord
        for (PriceBand priceBand : priceRecord.getPriceBands()) {
            result.getPriceBands().add((PriceBandWire) priceBand);
        }
        return result;
    }

    public GetPriceRecordByIDResponse handlegetPriceRecordByIDRequest(GetPriceRecordByIDRequest request) {
        GetPriceRecordByIDResponse result = new GetPriceRecordByIDResponse();
        // Get the priceRecord
        PriceRecord priceRecord = this.priceRecordService.getPriceRecordByID(request.getId());
        // set the result
        result.setPriceRecord((PriceRecordWire) priceRecord);
        return result;
    }

    public GetPriceRecordByLookupCodeResponse handlegetPriceRecordByLookupCodeRequest(
            GetPriceRecordByLookupCodeRequest request) {
        GetPriceRecordByLookupCodeResponse result = new GetPriceRecordByLookupCodeResponse();
        PriceRecord priceRecord = this.priceRecordService.getPriceRecordByLookupcode(request.getLookupCode());
        // set the result
        result.setPriceRecord((PriceRecordWire) priceRecord);
        return result;
    }

    // Getters and Setters
    public PriceRecordService getPriceRecordService() {
        return priceRecordService;
    }

    public void setPriceRecordService(PriceRecordService priceRecordService) {
        this.priceRecordService = priceRecordService;
    }

}
