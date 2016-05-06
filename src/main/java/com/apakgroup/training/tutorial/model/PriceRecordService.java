package com.apakgroup.training.tutorial.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class PriceRecordService {

    private PriceRecordDAO priceRecordDAO;

    public PriceRecordService() {
        this.priceRecordDAO = new PriceRecordDAO();
    }

    public PriceRecordDAO getPriceRecordDAO() {
        return priceRecordDAO;
    }

    public void setPriceRecordDAO(PriceRecordDAO priceRecordDAO) {
        this.priceRecordDAO = priceRecordDAO;
    }

    public long addPriceRecord(PriceRecord priceRecord) {
        return this.getPriceRecordDAO().addPriceRecord(priceRecord);
    }

    public ArrayList<Long> addPriceRecordList(PriceRecordList priceRecordList) {
        return this.getPriceRecordDAO().addPriceRecordList(priceRecordList);
    }

    public ArrayList<Long> addListOfPriceRecords(List<PriceRecord> listOfPriceRecords) {
        return this.getPriceRecordDAO().addListOfPriceRecords(listOfPriceRecords);
    }

    public ArrayList<Long> addPriceRecordsFromXMLFile(File file) throws JAXBException {
        return this.getPriceRecordDAO().addPriceRecordsFromXMLFile(file);
    }

    public PriceRecord getPriceRecordByLookupcode(String lookupcode) {
        return this.getPriceRecordDAO().getPriceRecordByLookupcode(lookupcode);
    }

    public PriceRecord getPriceRecordByID(long ID) {
        return this.getPriceRecordDAO().getPriceRecordByID(ID);
    }

    public List<PriceRecordImpl> getAllPriceRecords() {
        return this.getPriceRecordDAO().getAllPriceRecords();
    }

    public long getIDbyLookupcode(String lookupcode) {
        return this.getPriceRecordDAO().getIDbyLookupcode(lookupcode);
    }

    public void updateLookupcodeByLookupcode(String oldLookupcode, String newLookupcode) {
        this.getPriceRecordDAO().updateLookupcodeByLookupcode(oldLookupcode, newLookupcode);
    }

    public void updateLookupcodeByID(long id, String newLookupcode) {
        this.getPriceRecordDAO().updateLookupcodeByID(id, newLookupcode);
    }

    public boolean addPriceBandByLookupcode(PriceBand priceBand, String lookupcode) {
        return this.getPriceRecordDAO().addPriceBandByLookupcode(priceBand, lookupcode);
    }

    public void addPriceBandByID(PriceBand priceBand, long id) {
        this.getPriceRecordDAO().addPriceBandByID(priceBand, id);
    }

    public boolean removeLastPriceBandByLookupcode(String lookupcode) {
        return this.getPriceRecordDAO().removeLastPriceBandByLookupcode(lookupcode);
    }

    public boolean removeLastPriceBandByID(long id) {
        return this.getPriceRecordDAO().removeLastPriceBandByID(id);
    }

    public void deletePriceRecordByLookupcode(String lookupcode) {
        this.getPriceRecordDAO().deletePriceRecordByLookupcode(lookupcode);
    }

    public void deletePriceRecordByID(long id) {
        this.getPriceRecordDAO().deletePriceRecordByID(id);
    }

    public void deleteListOfPriceRecords(List<PriceRecord> listOfPriceRecords) {
        this.getPriceRecordDAO().deleteListOfPriceRecords(listOfPriceRecords);
    }

    public void deleteAllPriceRecords() {
        this.getPriceRecordDAO().deleteAllPriceRecords();
    }

}
