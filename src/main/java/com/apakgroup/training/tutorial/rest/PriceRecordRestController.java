package com.apakgroup.training.tutorial.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.PriceRecordList;
import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

@RestController
public class PriceRecordRestController {

    private PriceRecordService priceRecordService;

    public PriceRecordRestController() {
    }

    // addTestPriceRecord
    @RequestMapping(value = "/addTestPriceRecord/{lookupCode}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<PriceRecordImpl> addTestPriceRecord(@PathVariable("lookupCode") String lookupCode) {
        HttpStatus status = HttpStatus.OK;
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(2);
        priceRecord.setLookupCode(lookupCode);
        this.priceRecordService.addPriceRecord(priceRecord);
        return new ResponseEntity<>(priceRecord, status);
    }

    // addPriceBandByLookUpCode
    @RequestMapping(value = "/addPriceBand/{lookupCode}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Boolean> addPriceBandByLookUpCode(@RequestBody PriceBand priceBand,
            @PathVariable("lookupCode") String lookupCode) {
        HttpStatus status = HttpStatus.CREATED;
        boolean added = false;
        if (priceBand == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        if (lookupCode == "") {
            status = HttpStatus.BAD_REQUEST; //400 (Bad Request)
        }
        if (!this.priceRecordService.lookUpCodeIsInDB(lookupCode)) {
            status = HttpStatus.NOT_FOUND; //404 (Not Found)
        }
        if (status == HttpStatus.CREATED) {
            this.priceRecordService.addPriceBandByLookupcode(priceBand, lookupCode);
            added = true;

        }
        return new ResponseEntity<>(added, status);
    }

    // addPriceRecordByLookUpCode
    @RequestMapping(value = "/addPriceRecord", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Boolean> addPriceRecord(@RequestBody PriceRecord priceRecord) {
        HttpStatus status = HttpStatus.CREATED;
        boolean added = false;
        if (priceRecord == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        if (!this.priceRecordService.lookUpCodeIsInDB(priceRecord.getLookupCode())) {
            status = HttpStatus.CONFLICT; //409 (Conflict)
        }
        if (status == HttpStatus.CREATED) {
            this.priceRecordService.addPriceRecord(priceRecord);
            added = true;
        }
        return new ResponseEntity<>(added, status);
    }

    // deletePriceBandByLookUpCode
    @RequestMapping(value = "/deletePriceBand/{lookupCode}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Boolean> deletePriceBand(@PathVariable("lookupCode") String lookupCode) {
        HttpStatus status = HttpStatus.CREATED;
        boolean deleted = false;
        if (!this.priceRecordService.lookUpCodeIsInDB(lookupCode)) {
            status = HttpStatus.NOT_FOUND; //409 (Conflict)
        }
        if (status == HttpStatus.CREATED) {
            this.priceRecordService.removeLastPriceBandByLookupcode(lookupCode);
            deleted = true;
        }
        return new ResponseEntity<>(deleted, status);
    }

    // deletePriceRecordByLookUpCode
    @RequestMapping(value = "/deletePriceRecord/{lookupCode}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Boolean> deletePriceRecord(@PathVariable("lookupCode") String lookupCode) {
        HttpStatus status = HttpStatus.CREATED;
        boolean deleted = false;
        if (!this.priceRecordService.lookUpCodeIsInDB(lookupCode)) {
            status = HttpStatus.NOT_FOUND; //404 (Conflict)
        }
        if (status == HttpStatus.CREATED) {
            this.priceRecordService.deletePriceRecordByLookupcode(lookupCode);
            deleted = true;
        }
        return new ResponseEntity<>(deleted, status);
    }

    // getPriceRecords
    @RequestMapping(value = "/getPriceRecords", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PriceRecordList> getPriceRecords() {
        HttpStatus status = HttpStatus.OK;
        PriceRecordList priceRecordList = new PriceRecordList();
        for (PriceRecordImpl priceRecord : this.priceRecordService.getAllPriceRecordsEAGER()) {
            priceRecordList.addPriceRecordToList(
                    this.priceRecordService.getPriceRecordByLookupcodeEAGER(priceRecord.getLookupCode()));
        }
        return new ResponseEntity<>(priceRecordList, status);
    }

    // getPriceRecordByLookUpCode
    @RequestMapping(value = "/getPriceRecord/{lookupCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PriceRecord> getPriceRecord(@PathVariable("lookupCode") String lookupCode) {
        HttpStatus status = HttpStatus.OK;
        PriceRecord priceRecord = null;
        if (!this.priceRecordService.lookUpCodeIsInDB(lookupCode)) {
            status = HttpStatus.NOT_FOUND; //404
        }
        if (status == HttpStatus.OK) {
            priceRecord = this.priceRecordService.getPriceRecordByLookupcodeEAGER(lookupCode);
        }
        return new ResponseEntity<>(priceRecord, status);
    }

    // getPriceBandByLookUpCode
    @RequestMapping(value = "/getPriceBand/{lookupCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PriceBand>> getPriceBand(@PathVariable("lookupCode") String lookupCode) {
        HttpStatus status = HttpStatus.OK;
        List<PriceBand> priceBands = null;
        PriceRecord priceRecord = null;
        if (!this.priceRecordService.lookUpCodeIsInDB(lookupCode)) {
            status = HttpStatus.NOT_FOUND; //404
        }
        if (status == HttpStatus.OK) {
            priceRecord = this.priceRecordService.getPriceRecordByLookupcodeEAGER(lookupCode);
            priceBands = priceRecord.getPriceBands();
        }
        return new ResponseEntity<>(priceBands, status);
    }

    // Getters and Setters
    public PriceRecordService getPriceRecordService() {
        return priceRecordService;
    }

    public void setPriceRecordService(PriceRecordService priceRecordService) {
        this.priceRecordService = priceRecordService;
    }

}
