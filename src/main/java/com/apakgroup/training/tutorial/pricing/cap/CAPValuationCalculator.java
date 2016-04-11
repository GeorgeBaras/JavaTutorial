package com.apakgroup.training.tutorial.pricing.cap;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.pricing.ValuationCalculator;

public class CAPValuationCalculator implements ValuationCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CAPValuationCalculator.class);

    private final BigDecimal ADJUSTMENT_PERCENTAGE = new BigDecimal("0.003");

    //  private MathContext mc = new MathContext(4, RoundingMode.HALF_EVEN);

    @Override
    public BigDecimal calculatePrice(PriceRecord priceRecord, int currentMileage) {
        PriceBand specificPriceBand;
        BigDecimal priceToReturn;

        // check if exact match
        specificPriceBand = findExactPriceBand(priceRecord, currentMileage);
        if (specificPriceBand != null) {
            LOGGER.debug("Calculating price from exact priceBand");
            return specificPriceBand.getValuation();
        }
        //check between bands

        priceToReturn = calculatePriceBetweenTwoBands(findClosestBandBelowMileage(priceRecord, currentMileage),
                findClosestBandAboveMileage(priceRecord, currentMileage), currentMileage);
        if (priceToReturn != null) {
            LOGGER.debug("Calculating price between two priceBands");
            return priceToReturn;
        }
        //check beyond bands
        priceToReturn = calculatePriceFromBand(findBandBeyond(priceRecord, currentMileage), currentMileage);
        if (priceToReturn != null) {
            LOGGER.debug("Calculating price beyond priceBands");
            return priceToReturn;
        }
        return null;
    }

    // EXTRA method to find the band beyond which the mileage lies
    protected PriceBand findBandBeyond(PriceRecord priceRecord, int currentMileage) {

        if (!priceRecord.getPriceBands().isEmpty() && currentMileage > priceRecord.getPriceBands()
                .get(priceRecord.getPriceBands().size() - 1).getMileage()) {
            return priceRecord.getPriceBands().get(priceRecord.getPriceBands().size() - 1);
        }
        if (!priceRecord.getPriceBands().isEmpty()
                && currentMileage < priceRecord.getPriceBands().get(0).getMileage()) {
            return priceRecord.getPriceBands().get(0);
        }

        return null;
    }

    protected BigDecimal calculatePriceFromBand(PriceBand closestBand, int currentMileage) {
        if (currentMileage > closestBand.getMileage()) {
            return adjustPriceDown(closestBand.getValuation(), currentMileage - closestBand.getMileage());
        }
        if (currentMileage < closestBand.getMileage()) {
            return adjustPriceUp(closestBand.getValuation(), closestBand.getMileage() - currentMileage);
        }
        return null;
    }

    protected BigDecimal adjustPriceUp(BigDecimal valuation, int mileageAdjustment) {
        BigDecimal price = valuation;
        for (int i = 0; i < mileageAdjustment; i++) {
            price = price.add(price.multiply(ADJUSTMENT_PERCENTAGE));
            LOGGER.trace("Price in AdjustPriceUp has become: {}", price);
        }
        return price;
    }

    protected BigDecimal adjustPriceDown(BigDecimal valuation, int mileageAdjustment) {
        BigDecimal price = valuation;
        for (int i = 0; i < mileageAdjustment; i++) {
            price = price.subtract(price.multiply(ADJUSTMENT_PERCENTAGE));
            LOGGER.trace("Price in AdjustPriceDown has become: {}", price);
        }
        return price;
    }

    protected BigDecimal calculatePriceBetweenTwoBands(PriceBand bandBelow, PriceBand bandAbove, int currentMileage) {
        // Formula #1
        if (bandBelow != null && bandAbove != null) {
            int differenceInMileage = bandAbove.getMileage() - bandBelow.getMileage();
            BigDecimal differenceInPrice = bandBelow.getValuation().subtract(bandAbove.getValuation());
            BigDecimal priceAdjustment = differenceInPrice.divide(new BigDecimal(differenceInMileage),
                    new MathContext(10, RoundingMode.HALF_EVEN)); // precision of at least 6 is needed
            priceAdjustment = priceAdjustment.multiply(new BigDecimal(currentMileage - bandBelow.getMileage()));
            return bandBelow.getValuation().subtract(priceAdjustment);
        }
        return null;
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
        for (PriceBand band : priceRecord.getPriceBands()) {
            if (band.getMileage() == currentMileage) {
                return band;
            }
        }
        return null;

    }

}
