package com.apakgroup.training.tutorial.webservice;

import java.util.ArrayList;
import java.util.List;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class PriceRecordEndpoint {

    private PriceRecordService priceRecordService;

    public PriceRecordEndpoint() {
    }

    // Converters to and from wire classes

    public PriceBandImpl wireToPriceBand(PriceBandWire priceBandWire) {
        PriceBandImpl priceBand = new PriceBandImpl();
        priceBand.setMileage(priceBandWire.getMileage());
        priceBand.setValuation(priceBandWire.getValuation());
        return priceBand;
    }

    public PriceBandWire priceBandToWire(PriceBand priceBand) {
        PriceBandWire priceBandWire = new PriceBandWire();
        priceBandWire.setMileage(priceBand.getMileage());
        priceBandWire.setValuation(priceBand.getValuation());
        return priceBandWire;
    }

    public PriceRecordImpl wireToPriceRecord(PriceRecordWire priceRecordWire) {
        if (priceRecordWire == null) {
            return null;
        }
        PriceRecordImpl priceRecord = new PriceRecordImpl();
        priceRecord.setLookupCode(priceRecordWire.getLookUpCode());
        // get the priceBands from the priceBand List of the priceRecordWire
        List<PriceBand> priceBands = new ArrayList<PriceBand>();
        for (PriceBandWire priceBandWire : priceRecordWire.getPriceBands()) {
            priceBands.add(this.wireToPriceBand(priceBandWire));
        }
        // add the priceBands to the priceRecord
        priceRecord.setPriceBands(priceBands);
        return priceRecord;
    }

    public PriceRecordWire priceRecordToWire(PriceRecord priceRecord) {
        if (priceRecord == null) {
            return null;
        }
        PriceRecordWire priceRecordWire = new PriceRecordWire();
        priceRecordWire.setLookUpCode(priceRecord.getLookupCode());
        // get the priceBands from the priceBand List of the priceRecord
        List<PriceBandWire> priceBands = new ArrayList<PriceBandWire>();
        for (PriceBand priceBand : priceRecord.getPriceBands()) {
            priceBands.add(this.priceBandToWire(priceBand));
        }
        // add the priceBands to the priceRecord
        //priceRecordWire.getPriceBands() = 
        priceRecordWire.getPriceBands().addAll(priceBands);
        return priceRecordWire;
    }

    // Add PriceBand
    public AddPriceBandResponse handleaddPriceBandRequest(AddPriceBandRequest request) {
        AddPriceBandResponse result = new AddPriceBandResponse();
        // Convert the request to PriceBand
        PriceBandImpl priceBand = this.wireToPriceBand(request.getPriceBand());
        /* Add the request's priceBand to the PriceRecord with the corresponding lookupCode */
        result.setConfirmation(this.priceRecordService.addPriceBandByLookupcode(priceBand, request.getLookupCode()));
        return result;
    }

    // Add PriceRecord
    public AddPriceRecordResponse handleaddPriceRecordRequest(AddPriceRecordRequest request) {
        AddPriceRecordResponse result = new AddPriceRecordResponse();
        // create a priceRecord out of the request
        PriceRecordImpl priceRecord = this.wireToPriceRecord(request.getPriceRecord());
        /* Add the priceRecord to the database */
        result.setId(this.priceRecordService.addPriceRecord(priceRecord));
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
            result.getPriceRecord().add(this.priceRecordToWire(priceRecord));
        }
        return result;
    }

    public GetPriceBandsByIDResponse handlegetPriceBandsByIDRequest(GetPriceBandsByIDRequest request) {
        GetPriceBandsByIDResponse result = new GetPriceBandsByIDResponse();
        // Get the priceRecord
        PriceRecord priceRecord = this.priceRecordService.getPriceRecordByID(request.getId());
        // populate the result's list with the priceBands of the priceRecord
        for (PriceBand priceBand : priceRecord.getPriceBands()) {
            result.getPriceBands().add(this.priceBandToWire(priceBand));
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
            result.getPriceBands().add(this.priceBandToWire(priceBand));
        }
        return result;
    }

    public GetPriceRecordByIDResponse handlegetPriceRecordByIDRequest(GetPriceRecordByIDRequest request) {
        GetPriceRecordByIDResponse result = new GetPriceRecordByIDResponse();
        // Get the priceRecord
        PriceRecord priceRecord = this.priceRecordService.getPriceRecordByID(request.getId());
        // set the result
        result.setPriceRecord(this.priceRecordToWire(priceRecord));
        return result;
    }

    public GetPriceRecordByLookupCodeResponse handlegetPriceRecordByLookupCodeRequest(
            GetPriceRecordByLookupCodeRequest request) {
        GetPriceRecordByLookupCodeResponse result = new GetPriceRecordByLookupCodeResponse();
        PriceRecord priceRecord = this.priceRecordService.getPriceRecordByLookupcode(request.getLookupCode());
        // set the result
        result.setPriceRecord(this.priceRecordToWire(priceRecord));
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
