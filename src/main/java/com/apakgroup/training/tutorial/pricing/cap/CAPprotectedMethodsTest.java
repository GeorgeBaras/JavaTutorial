package com.apakgroup.training.tutorial.pricing.cap;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class CAPprotectedMethodsTest {

    PriceBand lowBand = new PriceBandImpl(10, new BigDecimal("20000.0"));

    PriceBand midBand = new PriceBandImpl(15, new BigDecimal("15000.0"));

    PriceBand highBand = new PriceBandImpl(20, new BigDecimal("10000.0"));

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

    CAPValuationCalculator cap = new CAPValuationCalculator();

    // Test Case: PriceRecords: allBands mileage: 25
    @Test
    public void testFindBandBeyond() {
        PriceBand expectedBand = highBand;
        PriceBand receivedBand = cap.findBandBeyond(allBands, 35);
        assertEquals("failure - Band not correct", expectedBand, receivedBand);
    }

    // Test case: Exp=20120.18  Band=lowOnly Miles=8
    @Test
    public void testCalculatePriceFromBand() {
        BigDecimal expectedPrice = new BigDecimal("20120.18");
        BigDecimal receivedPrice = cap.calculatePriceFromBand(lowBand, 8).round(new MathContext(7));
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    // Test case: Exp.20120.18  Band.lowOnly Miles.8
    @Test
    public void testAdjustPriceUp() {
        BigDecimal expectedPrice = new BigDecimal("20120.18");
        BigDecimal receivedPrice = cap.adjustPriceUp(lowBand.getValuation(), 2).round(new MathContext(7)); //the number is the mile difference
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    // Test case: Exp=19408.04  Band=lowOnly Miles=20  
    @Test
    public void testAdjustPriceDown() {
        BigDecimal expectedPrice = new BigDecimal("19408.04");
        BigDecimal receivedPrice = cap.adjustPriceDown(lowBand.getValuation(), 10).round(new MathContext(7));
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    // Test case: Exp=18000.0  Band=lowAndmid Miles=12 
    @Test
    public void testCalculatePriceBetweenTwoBands() {
        BigDecimal expectedPrice = new BigDecimal("18000.0");
        BigDecimal receivedPrice = cap.calculatePriceBetweenTwoBands(lowBand, midBand, 12);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    // Test case: Exp=lowBand PriceRecords: allBands mileage: 12
    @Test
    public void testFindClosestBandBelowMileage() {
        PriceBand expectedBand = lowBand;
        PriceBand receivedBand = cap.findClosestBandBelowMileage(allBands, 12);
        assertEquals("failure - Band not correct", expectedBand, receivedBand);
    }

    // Test case: Exp=highBand PriceRecords: allBands mileage: 17
    @Test
    public void testFindClosestBandAboveMileage() {
        PriceBand expectedBand = highBand;
        PriceBand receivedBand = cap.findClosestBandAboveMileage(allBands, 17);
        assertEquals("failure - Band not correct", expectedBand, receivedBand);
    }

    // Test case: Exp=lowBand PriceRecords: lowAndMid mileage: 10
    @Test
    public void testFindExactPriceBand() {
        PriceBand expectedBand = lowBand;
        PriceBand receivedBand = cap.findExactPriceBand(lowAndMid, 10);
        assertEquals("failure - Band not correct", expectedBand, receivedBand);
    }

}
