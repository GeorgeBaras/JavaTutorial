package com.apakgroup.training.tutorial.model;

import java.math.BigDecimal;

import com.apakgroup.training.tutorial.pricing.PriceBand;

public class PriceBandImpl implements PriceBand {

    private int mileage;

    private BigDecimal valuation;

    public PriceBandImpl(int mileage, BigDecimal valuation) {
        this.mileage = mileage;
        this.valuation = valuation;
    }

    @Override
    public int getMileage() {
        return this.mileage;
    }

    @Override
    public BigDecimal getValuation() {
        return this.valuation;
    }

    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

}
