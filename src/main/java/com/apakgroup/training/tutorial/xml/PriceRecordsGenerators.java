package com.apakgroup.training.tutorial.xml;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class PriceRecordsGenerators {

    private static int minMileage = 1;

    private static int maxMileage = 100;

    private static int MaxPriceBandsPerRecord = 10;

    private static int MaxPriceRecordsPerList = 100;

    private static BigDecimal maxValuation = new BigDecimal("150000.0");

    private static String lookupCode = "lookUpCode";

    private static int lookUpCode = 0;

    public static PriceBand priceBandGenerator() {
        Random rand = new Random();
        int mileage = rand.nextInt((maxMileage - minMileage) + 1) + minMileage;
        BigDecimal valuation = maxValuation.divide(new BigDecimal(mileage));
        return new PriceBandImpl(mileage, valuation);
    }

    public static PriceRecord priceRecordGenerator() {
        //Generate a lookUpCode
        String lookupCode = PriceRecordsGenerators.lookupCode + String.valueOf(PriceRecordsGenerators.lookUpCode);
        PriceRecordsGenerators.lookUpCode++;
        // Generate a random number of PriceBands
        List<PriceBand> priceBands = new ArrayList<PriceBand>();
        for (int i = 0; i < PriceRecordsGenerators.MaxPriceBandsPerRecord; i++) {
            priceBands.add(PriceRecordsGenerators.priceBandGenerator());
        }
        return new PriceRecordImpl(lookupCode, priceBands);
    }

    public static List<PriceRecord> listOfPriceRecordGenerator() {
        List<PriceRecord> priceRecords = new ArrayList<PriceRecord>();
        for (int i = 0; i < PriceRecordsGenerators.MaxPriceRecordsPerList; i++) {
            priceRecords.add(PriceRecordsGenerators.priceRecordGenerator());
        }
        return priceRecords;
    }

}
