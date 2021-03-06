package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.pricing.cap.CAPValuationCalculator;

public class CAPTest {

    //    @Before
    //    public void setUp() {
    //        final PriceBand lowBand = new PriceBandImpl(10, new BigDecimal("20000.0"));
    //
    //        final PriceBand midBand = new PriceBandImpl(15, new BigDecimal("15000.0"));
    //
    //        final PriceBand highBand = new PriceBandImpl(20, new BigDecimal("10000.0"));
    //
    //        final List<PriceBand> priceBands2 = new ArrayList<PriceBand>() {
    //
    //            {
    //                add(lowBand);
    //                add(midBand);
    //            }
    //        };
    //
    //        final List<PriceBand> priceBands3 = new ArrayList<PriceBand>() {
    //
    //            {
    //                add(lowBand);
    //                add(midBand);
    //                add(highBand);
    //
    //            }
    //        };
    //
    //        final PriceRecord lowOnly = new PriceRecordImpl("lowOnly", lowBand);
    //
    //        final PriceRecord lowAndMid = new PriceRecordImpl("lowAndMid", priceBands2);
    //
    //        final PriceRecord allBands = new PriceRecordImpl("allBands", priceBands3);
    //
    //        final CAPValuationCalculator capValuationCalculator = new CAPValuationCalculator();
    //
    //        // Edge Case1: new bands and current mileage==2
    //
    //        final PriceBand edgeCaseBand1 = new PriceBandImpl(1, new BigDecimal("50000.00"));
    //
    //        final PriceBand edgeCaseBand2 = new PriceBandImpl(10, new BigDecimal("25000.00"));
    //
    //        final List<PriceBand> edgeCasePriceBands = new ArrayList<PriceBand>() {
    //
    //            {
    //                add(edgeCaseBand1);
    //                add(edgeCaseBand2);
    //            }
    //        };
    //
    //        final PriceRecord edgeCasePriceRecord = new PriceRecordImpl("beyondBands", edgeCasePriceBands);
    //
    //    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CAPTest.class);

    private final PriceBand lowBand = new PriceBandImpl(10, new BigDecimal("20000.0"));

    private final PriceBand midBand = new PriceBandImpl(15, new BigDecimal("15000.0"));

    private final PriceBand highBand = new PriceBandImpl(20, new BigDecimal("10000.0"));

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

    private final CAPValuationCalculator capValuationCalculator = new CAPValuationCalculator();

    // Edge Case1: new bands and current mileage==2

    private final PriceBand edgeCaseBand1 = new PriceBandImpl(1, new BigDecimal("50000.00"));

    private final PriceBand edgeCaseBand2 = new PriceBandImpl(10, new BigDecimal("25000.00"));

    private final List<PriceBand> edgeCasePriceBands = new ArrayList<PriceBand>() {

        {
            add(edgeCaseBand1);
            add(edgeCaseBand2);
        }
    };

    private final PriceRecord edgeCasePriceRecord = new PriceRecordImpl("beyondBands", edgeCasePriceBands);

    // Mileage exact band mileage
    @Test
    public void testCalculatePriceExactBand1() {
        BigDecimal expectedPrice = new BigDecimal("15000.0");
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowAndMid, 15);
        LOGGER.debug("Testing Calculating price from exact priceBand");
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceExactBand2() {
        BigDecimal expectedPrice = new BigDecimal("15000.0");
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(allBands, 15);
        LOGGER.info("Testing Band 2 for expectedPrice: {}", expectedPrice);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceExactBand3() {
        BigDecimal expectedPrice = new BigDecimal("10000.0");
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(allBands, 20);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    // Mileage between two bands

    @Test
    public void testCalculatePriceBetweenTwoBands1() {
        BigDecimal expectedPrice = new BigDecimal("18000.0");
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowAndMid, 12);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceBetweenTwoBands2() {
        BigDecimal expectedPrice = new BigDecimal("12000.0");
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(allBands, 18);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceBetweenTwoBands3() {
        BigDecimal expectedPrice = new BigDecimal("17000.0");
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(allBands, 13);
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    // Mileage above maximum mileage band

    @Test
    public void testCalculatePriceAboveMaximumBand1() {
        BigDecimal expectedPrice = new BigDecimal("19408.04");
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowOnly, 20).round(new MathContext(7));
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceAboveMaximumBand2() {
        BigDecimal expectedPrice = new BigDecimal("14776.35");
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowAndMid, 20).round(new MathContext(7));
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    // Mileage below minimum mileage band

    @Test
    public void testCalculatePriceBelowMinimumBand1() {
        BigDecimal expectedPrice = new BigDecimal("20060.00");
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowOnly, 9).round(new MathContext(7));
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceBelowMinimumBand2() {
        BigDecimal expectedPrice = new BigDecimal("20120.18");
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(lowOnly, 8).round(new MathContext(7));
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    @Test
    public void testCalculatePriceForNewBands() {
        BigDecimal expectedPrice = new BigDecimal("47222.22");
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(edgeCasePriceRecord, 2)
                .round(new MathContext(7)); // problem with precision in division of "calculatePriceBetweenTwoBands"
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

    // Edge Case2: allbands and 1million miles
    @Test
    public void testCalculatePriceForMMmiles() {
        BigDecimal expectedPrice = new BigDecimal("526.326"); // 526.326...
        BigDecimal receivedPrice = capValuationCalculator.calculatePrice(allBands, 1000).round(new MathContext(6));
        assertEquals("failure - Price not correct", expectedPrice, receivedPrice);
    }

}
