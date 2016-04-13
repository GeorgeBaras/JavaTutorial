package com.apakgroup.training.tutorial.xml;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class PriceRecordsGenerators {

    private static int minMileage = 1;

    private static int firstBandMaxMileage = 50;

    private static int firstBandMaxValuation = 100000;

    private static String lookupCode = "lookUpCode";

    private static int lookUpCode = 0;

    private static MathContext mc = new MathContext(10, RoundingMode.HALF_EVEN);

    /**
     * this method creates a random priceBand withing the mileage and valuation limits
     * 
     * @return
     */
    public static PriceBand firstpriceBandGenerator() {
        Random rand = new Random();
        int mileage = rand.nextInt((firstBandMaxMileage - minMileage) + 1) + minMileage;
        BigDecimal valuation = new BigDecimal(firstBandMaxValuation / mileage);
        // Round Valuation
        return new PriceBandImpl(mileage, valuation);
    }

    /**
     * this method creates a random priceBand withing the mileage and valuation limits but ensures
     * the the mileage is bigger and the valuation is smaller than those of the previous band
     * 
     * @parameters
     */
    public static PriceBand priceBandGenerator(int previousBandMileage, BigDecimal previousBandValuation) {
        Random rand = new Random();
        int mileage = previousBandMileage + (rand.nextInt((firstBandMaxMileage - minMileage) + 1) + minMileage);
        double priceReductionDivisor = (100 / (mileage - previousBandMileage));
        BigDecimal valuation = previousBandValuation
                .subtract(previousBandValuation.divide(new BigDecimal(priceReductionDivisor), mc));
        return new PriceBandImpl(mileage, valuation);
    }

    public static PriceRecord priceRecordGenerator(int numberOfPriceBandsPerRecord) {
        //Generate a lookUpCode
        String lookupCode = PriceRecordsGenerators.lookupCode + String.valueOf(PriceRecordsGenerators.lookUpCode);
        PriceRecordsGenerators.lookUpCode++;
        // Generate a random number of PriceBands
        List<PriceBand> priceBands = new ArrayList<PriceBand>();
        priceBands.add(PriceRecordsGenerators.firstpriceBandGenerator());
        for (int i = 1; i < numberOfPriceBandsPerRecord; i++) {
            priceBands.add(PriceRecordsGenerators.priceBandGenerator(priceBands.get(priceBands.size() - 1).getMileage(),
                    priceBands.get(priceBands.size() - 1).getValuation()));
        }
        return new PriceRecordImpl(lookupCode, priceBands);
    }

    public static List<PriceRecord> listOfPriceRecordGenerator(int numberOfPriceRecordsPerList,
            int numberOfPriceBandsPerRecord) {
        List<PriceRecord> priceRecords = new ArrayList<PriceRecord>();
        for (int i = 0; i < numberOfPriceRecordsPerList; i++) {
            priceRecords.add(PriceRecordsGenerators.priceRecordGenerator(numberOfPriceBandsPerRecord));
        }
        return priceRecords;
    }

}
