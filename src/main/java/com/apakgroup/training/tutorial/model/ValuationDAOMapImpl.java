package com.apakgroup.training.tutorial.model;

import java.util.Map;

import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.pricing.ValuationDAO;

public class ValuationDAOMapImpl implements ValuationDAO {

    private Map<String, PriceRecord> priceRecords;

    // constructor for list<> input
    //    public ValuationDAOMapImpl(List<PriceRecord> priceRecords) {
    //        for (PriceRecord p : priceRecords) {
    //            // raw type use !?!?
    //            this.priceRecords.put(p.getLookupCode(), p.getPriceBands());
    //        }
    //    }

    // constructor for map input
    public ValuationDAOMapImpl(Map<String, PriceRecord> priceRecords) {
        this.priceRecords = priceRecords;
    }

    @Override
    public PriceRecord getPriceRecord(String lookupCode) {
        return priceRecords.get(lookupCode);
    }

}
