package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.pricing.PriceBand;

public class PriceBandTest {

    @Test
    public void testGetMileage() {
        PriceBand pb = new PriceBandImpl(15, new BigDecimal(15000.0));
        int receivedMileage = pb.getMileage();
        int expectedMileage = 15;
        assertEquals("failure - Mileage not correct", expectedMileage, receivedMileage);
    }

    @Test
    public void testGetValuation() {
        PriceBand pb = new PriceBandImpl(15, new BigDecimal(15000.0));
        BigDecimal receivedValuation = pb.getValuation();
        BigDecimal expectedValuation = new BigDecimal(15000.0);
        assertEquals("failure - Valuation not correct", expectedValuation, receivedValuation);
    }

}
