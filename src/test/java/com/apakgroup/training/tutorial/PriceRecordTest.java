package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class PriceRecordTest {

    private final PriceBand lowBand = new PriceBandImpl(10, new BigDecimal(20000.0));

    private final PriceBand midBand = new PriceBandImpl(15, new BigDecimal(15000.0));

    private final PriceBand highBand = new PriceBandImpl(20, new BigDecimal(10000.0));

    private final List<PriceBand> priceBands2 = new ArrayList<PriceBand>() {

        {
            add(lowBand);
            add(midBand);
        }
    };

    private final List<PriceBand> priceBands3 = new ArrayList<PriceBand>() {

        {
            add(lowBand);
            add(midBand);
            add(highBand);

        }
    };

    private final PriceRecord lowOnly = new PriceRecordImpl("lowOnly", lowBand);

    private final PriceRecord lowAndMid = new PriceRecordImpl("lowAndMid", priceBands2);

    private final PriceRecord allBands = new PriceRecordImpl("allBands", priceBands3);

    @Test
    public void testGetLookupCode() {
        String expectedLookupCode = "lowOnly";
        String receivedLookupCode = lowOnly.getLookupCode();
        assertEquals("failure - LookupCode does not match", expectedLookupCode, receivedLookupCode);
    }

    @Test
    public void testGetLowPriceBand() {
        PriceRecord pr = new PriceRecordImpl("lowOnly", lowBand);
        List<PriceBand> expectedPriceBands = new ArrayList<PriceBand>() {

            {
                add(lowBand);
            }
        };
        List<PriceBand> receivedPriceBands = pr.getPriceBands();
        assertEquals("failure - PriceBands do not match", expectedPriceBands, receivedPriceBands);
    }

    @Test
    public void testGetLowAndMidPriceBands() {
        PriceRecord pr = new PriceRecordImpl("lowOnly", priceBands2);
        List<PriceBand> expectedPriceBands = new ArrayList<PriceBand>() {

            {
                add(lowBand);
                add(midBand);
            }
        };
        List<PriceBand> receivedPriceBands = pr.getPriceBands();
        assertEquals("failure - PriceBands do not match", expectedPriceBands, receivedPriceBands);
    }

    @Test
    public void testGetAllBands() {
        PriceRecord pr = new PriceRecordImpl("lowOnly", priceBands3);
        List<PriceBand> expectedPriceBands = new ArrayList<PriceBand>() {

            {
                add(lowBand);
                add(midBand);
                add(highBand);
            }
        };
        List<PriceBand> receivedPriceBands = pr.getPriceBands();
        assertEquals("failure - PriceBands do not match", expectedPriceBands, receivedPriceBands);
    }

    @Test
    public void testCompare() {
        PriceRecordImpl pr1 = new PriceRecordImpl("lowOnly", priceBands3);
        PriceRecordImpl pr2 = new PriceRecordImpl("lowOnly", priceBands3);
        assertTrue(pr1.compare(pr2));
    }

}
