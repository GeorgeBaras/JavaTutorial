package com.apakgroup.training.tutorial.model;

import java.util.List;

import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.pricing.ValuationDAO;

public class ValuationDAOListImpl implements ValuationDAO {

    private List<PriceRecord> priceRecords;

    public ValuationDAOListImpl(List<PriceRecord> priceRecords) {
        this.priceRecords = priceRecords;
    }

    @Override
    public PriceRecord getPriceRecord(String lookupCode) {
        for (PriceRecord p : this.priceRecords) {
            if (p.getLookupCode().equals(lookupCode)) {
                return p;
            }
        }
        return null;
    }

}
