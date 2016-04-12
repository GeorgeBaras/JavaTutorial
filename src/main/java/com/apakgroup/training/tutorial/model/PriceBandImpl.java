package com.apakgroup.training.tutorial.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.apakgroup.training.tutorial.pricing.PriceBand;

@Entity
@Table(name = "priceBand")
@XmlRootElement(name = "PriceBand")
public class PriceBandImpl implements PriceBand {

    private long id;

    private Integer mileage;

    private BigDecimal valuation;

    public PriceBandImpl() {
    }

    public PriceBandImpl(int mileage, BigDecimal valuation) {
        this.mileage = mileage;
        this.valuation = valuation;
    }

    @Id
    @GeneratedValue
    @XmlTransient
    public long getID() {
        return id;
    }

    public void setID(long priceBandID) {
        this.id = priceBandID;
    }

    @Override
    @XmlElement(name = "mileage")
    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @Override
    @XmlElement(name = "valuation")
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
