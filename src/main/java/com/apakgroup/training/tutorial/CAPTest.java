package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.pricing.ValuationCalculator;
import com.apakgroup.training.tutorial.pricing.cap.CAPValuationCalculator;

public class CAPTest {

    PriceBand lowBand = new PriceBandImpl(10, new BigDecimal(20000.0));

    PriceBand midBand = new PriceBandImpl(15, new BigDecimal(15000.0));

    PriceBand highBand = new PriceBandImpl(20, new BigDecimal(10000.0));

    List<PriceBand> priceBands2 = new ArrayList<PriceBand>() {

        {
            add(lowBand);
            add(midBand);
        }
    };

    List<PriceBand> priceBands3 = new ArrayList<PriceBand>() {

        {
            add(lowBand);
            add(midBand);
            add(highBand);

        }
    };

    PriceRecord lowOnly = new PriceRecordImpl("1111", lowBand);

    PriceRecord lowAndMid = new PriceRecordImpl("2222", priceBands2);

    PriceRecord allBands = new PriceRecordImpl("3333", priceBands3);

    ValuationCalculator capValuationCalculator = new CAPValuationCalculator();

    // Mileage exact band mileage
    @Test
    public void testCalculatePriceExactBand1() {
        BigDecimal expectedPrice = new BigDecimal(15000.0);
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowAndMid, 15);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceExactBand2() {
        BigDecimal expectedPrice = new BigDecimal(15000.0);
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(allBands, 15);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceExactBand3() {
        BigDecimal expectedPrice = new BigDecimal(10000.0);
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(allBands, 20);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    // Mileage between two bands

    @Test
    public void testCalculatePriceBetweenTwoBands1() {
        BigDecimal expectedPrice = new BigDecimal(18000.0);
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowAndMid, 12);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceBetweenTwoBands2() {
        BigDecimal expectedPrice = new BigDecimal(12000.0);
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(allBands, 18);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceBetweenTwoBands3() {
        BigDecimal expectedPrice = new BigDecimal(17000.0);
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(allBands, 13);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    // Mileage above maximum mileage band

    @Test
    public void testCalculatePriceAboveMaximumBand1() {
        BigDecimal expectedPrice = new BigDecimal(19408.04);
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowOnly, 20);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceAboveMaximumBand2() {
        BigDecimal expectedPrice = new BigDecimal(14776.35);
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowAndMid, 20);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    // Mileage below minimum mileage band

    @Test
    public void testCalculatePriceBelowMinimumBand1() {
        BigDecimal expectedPrice = new BigDecimal(20060.0);
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowOnly, 9);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceBelowMinimumBand2() {
        BigDecimal expectedPrice = new BigDecimal(20120.18);
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowOnly, 8);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

}
