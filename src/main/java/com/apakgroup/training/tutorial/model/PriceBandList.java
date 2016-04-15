package com.apakgroup.training.tutorial.model;

import java.util.ArrayList;
import java.util.List;

import com.apakgroup.training.tutorial.pricing.PriceBand;

public class PriceBandList {

    private List<PriceBand> priceBands;

    public PriceBandList() {
        this.priceBands = new ArrayList<>();
    }

    public PriceBandList(List<PriceBand> priceBands) {
        this.priceBands = priceBands;
    }

    public void addPriceBand(PriceBand priceBand) {
        this.priceBands.add(priceBand);
    }

    public List<PriceBand> getPriceBands() {
        return priceBands;
    }

    public void setPriceBands(List<PriceBand> priceBands) {
        this.priceBands = priceBands;
    }

}
