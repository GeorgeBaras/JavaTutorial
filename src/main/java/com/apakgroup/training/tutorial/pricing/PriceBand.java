package com.apakgroup.training.tutorial.pricing;

import java.math.BigDecimal;

/**
 * PriceBand interface representing a vehicles price at a specific mileage used when calculating
 * used vehicle prices.
 */
public interface PriceBand {

    /**
     * The mileage of the vehicle for this PriceBand.
     * 
     * @return the vehicle mileage
     */
    public int getMileage();

    /**
     * The valuation of the vehicle given the mileage of this band.
     * 
     * @return the valuation
     */
    public BigDecimal getValuation();
}
