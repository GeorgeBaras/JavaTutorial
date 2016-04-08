package com.apakgroup.training.tutorial.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

@Entity
@Table(name = "PriceRecord")
@XmlRootElement(name = "PriceRecord")
public class PriceRecordImpl implements PriceRecord {

    private Long priceRecordID;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    public Long getPriceRecordID() {
        return priceRecordID;
    }

    public void setPriceRecordID(Long priceRecordID) {
        this.priceRecordID = priceRecordID;
    }

    @Override
    @XmlElement(name = "lookUpCode")
    public String getLookupCode() {
        return this.lookupCode;
    }

    @Override
    @OneToMany(targetEntity = PriceBandImpl.class, mappedBy = "priceRecordImpl", cascade = CascadeType.ALL)
    @XmlElementWrapper(name = "priceBands")
    @XmlElement(name = "priceBand")
    public List<PriceBand> getPriceBands() {
        return this.priceBands;
    }

}
