package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.pricing.PriceBand;

public class PriceBandTest {

    private final PriceBand lowBand = new PriceBandImpl(10, new BigDecimal(20000.0));

    private final PriceBand midBand = new PriceBandImpl(15, new BigDecimal(15000.0));

    private final PriceBand highBand = new PriceBandImpl(20, new BigDecimal(10000.0));

    @Test
    public void testGetMileage() {
        int receivedMileage = lowBand.getMileage();
        int expectedMileage = 10;
        assertEquals("failure - Mileage not correct", expectedMileage, receivedMileage);
    }

    @Test
    public void testGetValuation() {
        BigDecimal receivedValuation = midBand.getValuation();
        BigDecimal expectedValuation = new BigDecimal(15000.0);
        assertEquals("failure - Valuation not correct", expectedValuation, receivedValuation);
    }

}
