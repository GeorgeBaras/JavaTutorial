package com.apakgroup.training.tutorial.model;

import java.util.List;
import java.util.Map;

import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.pricing.ValuationDAO;

public class ValuationDAOMapImpl implements ValuationDAO {

    Map<String, List> priceRecords;

    public ValuationDAOMapImpl(List<PriceRecord> priceRecords) {
        for (PriceRecord p : priceRecords) {
            // raw type use !?!?
            this.priceRecords.put(p.getLookupCode(), p.getPriceBands());
        }
    }

    @Override
    public PriceRecord getPriceRecord(String lookupCode) {
        PriceRecord p;
        for (Map.Entry<String, List> entry : priceRecords.entrySet()) {
            if (lookupCode.equals(entry.getKey())) {
                return p = new PriceRecordImpl(entry.getKey(), entry.getValue());
            }
        }
        return null;
    }

}
