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

public class PriceRecordTest {

    PriceBand lowBand = new PriceBandImpl(10, new BigDecimal(20000.0));

    PriceBand midBand = new PriceBandImpl(15, new BigDecimal(15000.0));

    PriceBand highBand = new PriceBandImpl(20, new BigDecimal(10000.0));

    List<PriceBand> priceBands = new ArrayList<PriceBand>() {

        {
            add(lowBand);
            add(midBand);
            add(highBand);

        }
    };

    @Test
    public void testGetLookupCode() {
        PriceRecord pr = new PriceRecordImpl("1111", lowBand);
        String expectedLookupCode = "1111";
        String receivedLookupCode = pr.getLookupCode();
        assertEquals("failure - LookupCode does not match", expectedLookupCode, receivedLookupCode);
    }

    @Test
    public void testGetPriceBands() {
        PriceRecord pr = new PriceRecordImpl("1111", priceBands);
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

}
