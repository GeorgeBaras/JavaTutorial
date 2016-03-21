package com.apakgroup.training.tutorial.model;

import java.util.ArrayList;
import java.util.List;

import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class PriceRecordImpl implements PriceRecord {

    private String lookupCode;

    private List<PriceBand> priceBands = new ArrayList<PriceBand>();

    public PriceRecordImpl(String lookupCode, List<PriceBand> priceBands) {
        this.lookupCode = lookupCode;
        this.priceBands = priceBands;
    }

    public PriceRecordImpl(String lookupCode, PriceBand priceBands) {
        this.lookupCode = lookupCode;
        this.priceBands.add(priceBands);
    }

    @Override
    public String getLookupCode() {
        return this.lookupCode;
    }

    @Override
    public List<PriceBand> getPriceBands() {
        return this.priceBands;
    }

}
