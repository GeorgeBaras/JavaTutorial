package com.apakgroup.training.tutorial.xml;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class PriceRecordsGeneratorsTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public final void testFirstpriceBandGenerator() {
        PriceBand priceBand = null;
        for (int i = 0; i < 5; i++) {
            priceBand = PriceRecordsGenerators.firstpriceBandGenerator();
            //System.out.println(test.getMileage() + " " + test.getValuation());
        }
        assertNotNull(priceBand);
    }

    @Test
    public final void testPriceBandGenerator() {
        PriceBand previousPriceBand = PriceRecordsGenerators.priceBandGenerator(10, new BigDecimal("10000"));
        PriceBand currentPriceBand = null;
        for (int i = 0; i < 10; i++) {
            currentPriceBand = PriceRecordsGenerators.priceBandGenerator(previousPriceBand.getMileage(),
                    previousPriceBand.getValuation());
            previousPriceBand = currentPriceBand;
            //System.out.println(current.getMileage() + " " + current.getValuation());
        }
        assertNotNull(currentPriceBand);
    }

    @Test
    public final void testPriceRecordGenerator() {
        PriceRecord priceRecord = null;
        priceRecord = PriceRecordsGenerators.priceRecordGenerator(10);
        //        System.out.println("LookupCode: " + priceRecord.getLookupCode());
        //        for (PriceBand p : priceRecord.getPriceBands()) {
        //            System.out.println("Band: mileage: " + p.getMileage() + " valuation: " + p.getValuation());
        //        }
        assertNotNull(priceRecord);
    }

    @Test
    public final void testListOfPriceRecordGenerator() {
        List<PriceRecord> listOfPriceRecords = null;
        listOfPriceRecords = PriceRecordsGenerators.listOfPriceRecordGenerator(10, 5);
        //        for (PriceRecord priceRecord : listOfPriceRecords) {
        //            System.out.println("LookupCode: " + priceRecord.getLookupCode());
        //            for (PriceBand p : priceRecord.getPriceBands()) {
        //                System.out.println("Band: mileage: " + p.getMileage() + " valuation: " + p.getValuation());
        //            }
        //        }
        assertNotNull(listOfPriceRecords);
    }

}
