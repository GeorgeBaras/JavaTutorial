package com.apakgroup.training.tutorial.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public long addPriceRecord(PriceRecord priceRecord) {
        return this.getPriceRecordDAO().addPriceRecord(priceRecord);
    }

    @Transactional
    public ArrayList<Long> addPriceRecordList(PriceRecordList priceRecordList) {
        return this.getPriceRecordDAO().addPriceRecordList(priceRecordList);
    }

    @Transactional
    public ArrayList<Long> addListOfPriceRecords(List<PriceRecord> listOfPriceRecords) {
        return this.getPriceRecordDAO().addListOfPriceRecords(listOfPriceRecords);
    }

    @Transactional
    public ArrayList<Long> addPriceRecordsFromXMLFile(File file) throws JAXBException {
        return this.getPriceRecordDAO().addPriceRecordsFromXMLFile(file);
    }

    @Transactional
    public PriceRecord getPriceRecordByLookupcode(String lookupcode) {
        PriceRecord pr = this.getPriceRecordDAO().getPriceRecordByLookupcode(lookupcode);
        pr.getPriceBands();
        return pr;
    }

    @Transactional
    public PriceRecord getPriceRecordByLookupcodeEAGER(String lookupcode) {
        PriceRecord pr = this.getPriceRecordDAO().getPriceRecordByLookupcodeEAGER(lookupcode);
        pr.getPriceBands();
        return pr;
    }

    @Transactional
    public PriceRecord getPriceRecordByID(long ID) {
        PriceRecord pr = this.getPriceRecordDAO().getPriceRecordByID(ID);
        pr.getPriceBands();
        return pr;
    }

    @Transactional
    public PriceRecord getPriceRecordByIDEAGER(long ID) {
        PriceRecord pr = this.getPriceRecordDAO().getPriceRecordByIDEAGER(ID);
        pr.getPriceBands();
        return pr;
    }

    @Transactional
    public List<PriceRecordImpl> getAllPriceRecords() {
        return this.getPriceRecordDAO().getAllPriceRecords();
    }

    @Transactional
    public List<PriceRecordImpl> getAllPriceRecordsEAGER() {
        List<PriceRecordImpl> prl = this.getPriceRecordDAO().getAllPriceRecordsEAGER();
        return prl;
    }

    @Transactional
    public long getIDbyLookupcode(String lookupcode) {
        return this.getPriceRecordDAO().getIDbyLookupcode(lookupcode);
    }

    @Transactional
    public void updateLookupcodeByLookupcode(String oldLookupcode, String newLookupcode) {
        this.getPriceRecordDAO().updateLookupcodeByLookupcode(oldLookupcode, newLookupcode);
    }

    @Transactional
    public void updateLookupcodeByID(long id, String newLookupcode) {
        this.getPriceRecordDAO().updateLookupcodeByID(id, newLookupcode);
    }

    @Transactional
    public boolean addPriceBandByLookupcode(PriceBand priceBand, String lookupcode) {
        return this.getPriceRecordDAO().addPriceBandByLookupcode(priceBand, lookupcode);
    }

    @Transactional
    public void addPriceBandByID(PriceBand priceBand, long id) {
        this.getPriceRecordDAO().addPriceBandByID(priceBand, id);
    }

    @Transactional
    public boolean removeLastPriceBandByLookupcode(String lookupcode) {
        return this.getPriceRecordDAO().removeLastPriceBandByLookupcode(lookupcode);
    }

    @Transactional
    public boolean removeLastPriceBandByID(long id) {
        return this.getPriceRecordDAO().removeLastPriceBandByID(id);
    }

    @Transactional
    public void deletePriceRecordByLookupcode(String lookupcode) {
        this.getPriceRecordDAO().deletePriceRecordByLookupcode(lookupcode);
    }

    @Transactional
    public void deletePriceRecordByID(long id) {
        this.getPriceRecordDAO().deletePriceRecordByID(id);
    }

    @Transactional
    public void deleteListOfPriceRecords(List<PriceRecord> listOfPriceRecords) {
        this.getPriceRecordDAO().deleteListOfPriceRecords(listOfPriceRecords);
    }

    @Transactional
    public void deleteAllPriceRecords() {
        this.getPriceRecordDAO().deleteAllPriceRecords();
    }

    @Transactional
    public boolean lookUpCodeIsInDB(String lookupcode) {
        return this.getPriceRecordDAO().lookUpCodeIsInDB(lookupcode);
    }

}
