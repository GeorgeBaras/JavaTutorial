package com.apakgroup.training.tutorial.batch;

import org.springframework.batch.item.ItemProcessor;

import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class PriceRecordProcessor implements ItemProcessor<PriceRecord, PriceRecord> {

    @Override
    public PriceRecord process(PriceRecord priceRecord) throws Exception {
        // No Business Logic Here
        return priceRecord;
    }

}
