package com.apakgroup.training.tutorial.pricing.cap;

import java.math.BigDecimal;

import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.pricing.ValuationCalculator;

public class CAPValuationCalculator implements ValuationCalculator {

    private BigDecimal ADJUSTMENT_PERCENTAGE; // MISSING GETTER-SETTER, probably 0.3

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
        // Formula #1
        int differenceInMileage = bandAbove.getMileage() - bandBelow.getMileage();
        BigDecimal differenceInPrice = bandBelow.getValuation().subtract(bandAbove.getValuation());
        BigDecimal priceAdjustment = differenceInPrice.divide(new BigDecimal(differenceInMileage)); // money/1000miles
        priceAdjustment = priceAdjustment.multiply(new BigDecimal(currentMileage - bandBelow.getMileage()));

        return bandBelow.getValuation().subtract(priceAdjustment);
    }

    protected PriceBand findClosestBandBelowMileage(PriceRecord priceRecord, int currentMileage) {
        PriceBand closestBelow = null;
        for (int i = priceRecord.getPriceBands().size() - 1; i >= 0; i--) {
            if (currentMileage > priceRecord.getPriceBands().get(i).getMileage()) { // if the currentMileage > than the mileage of the last band
                closestBelow = priceRecord.getPriceBands().get(i); // this is the band that we need so keep it and break
                break;
            }
        }
        return closestBelow;
    }

    protected PriceBand findClosestBandAboveMileage(PriceRecord priceRecord, int currentMileage) {
        PriceBand closestAbove = null;
        for (int i = 0; i <= priceRecord.getPriceBands().size() - 1; i++) {
            if (currentMileage < priceRecord.getPriceBands().get(i).getMileage()) { // if the currentMileage < than the mileage of the first band
                closestAbove = priceRecord.getPriceBands().get(i); // this is the band that we need so keep it and break
                break;
            }
        }
        return closestAbove;
    }

    protected PriceBand findExactPriceBand(PriceRecord priceRecord, int currentMileage) {

        return null;
    }

}
