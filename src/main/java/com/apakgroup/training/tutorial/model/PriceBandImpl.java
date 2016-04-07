package com.apakgroup.training.tutorial.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.apakgroup.training.tutorial.pricing.PriceBand;

@Entity
@Table(name = "PriceBand")
public class PriceBandImpl implements PriceBand {

    private Long priceBandID;

    private Integer mileage;

    private BigDecimal valuation;

    public PriceBandImpl(int mileage, BigDecimal valuation) {
        this.mileage = mileage;
        this.valuation = valuation;
    }

    @Id
    @GeneratedValue
    public Long getPriceBandID() {
        return priceBandID;
    }

    public void setPriceBandID(Long priceBandID) {
        this.priceBandID = priceBandID;
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
