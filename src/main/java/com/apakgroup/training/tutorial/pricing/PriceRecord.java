package com.apakgroup.training.tutorial.pricing;

import java.util.List;

/**
 * PriceRecord interface representing a specific vehicles PriceRecord containing {@link PriceBand}'s
 * which are used to calculate the value of a used vehicle based on its current mileage.
 */
public interface PriceRecord {

    /**
     * Unique reference for the current vehicle
     * 
     * @return the reference code for this vehicle
     */
    public String getLookupCode();

    /**
     * List of {@link PriceBand}'s which represent the value of the vehicle at varying different
     * mileages whic can be used to calculate the current value of the vehicle.
     * 
     * @return price bands for this vehicle
     */
    public List<PriceBand> getPriceBands();
}
