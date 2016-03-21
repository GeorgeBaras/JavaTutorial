package com.apakgroup.training.tutorial.pricing.cap;

import java.math.BigDecimal;

import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.pricing.ValuationCalculator;

public class CAPValuationCalculator implements ValuationCalculator {

    private BigDecimal ADJUSTMENT_PERCENTAGE; // MISSING GETTER-SETTER

    @Override
    public BigDecimal calculatePrice(PriceRecord priceRecord, int currentMileage) {
        // TODO Auto-generated method stub
        return null;
    }

    protected BigDecimal calculatePriceFromBand(PriceBand closestBand, int currentMileage) {
        return null;
    }

    protected BigDecimal adjustPriceUp(BigDecimal valuation, int mileageAdjustment) {
        return null;
    }

    protected BigDecimal adjustPriceDown(BigDecimal valuation, int mileageAdjustment) {
        return null;
    }

    protected BigDecimal calculatePriceBetweenTwoBands(PriceBand bandBelow, PriceBand bandAbove, int currentMileage) {
        return null;
    }

    protected PriceBand findClosestBandBelowMileage(PriceRecord priceRecord, int currentMileage) {
        return null;
    }

    protected PriceBand findClosestBandAboveMileage(PriceRecord priceRecord, int currentMileage) {
        return null;
    }

    protected PriceBand findExactPriceBand(PriceRecord priceRecord, int currentMileage) {
        return null;
    }

}
