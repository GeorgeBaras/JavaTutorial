package com.apakgroup.training.tutorial.pricing;

import java.math.BigDecimal;

/**
 * common interface for various different vehicle valuation calculators to implement to allow
 * vehicle valuations to be calculated using a common api.
 */
public interface ValuationCalculator {

    /**
     * Method to calculate the price of a vehicle using a {@link PriceRecord} and the current
     * mileage of the vehicle.
     * 
     * @param priceRecord
     *            a {@code PriceRecord} representing a vehicle
     * @param currentMileage
     *            the current mileage of the vehicle
     * @return the price of the vehicle
     */
    public BigDecimal calculatePrice(PriceRecord priceRecord, int currentMileage);
}
