package com.apakgroup.training.tutorial.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class PriceRecordProcessor implements ItemProcessor<PriceRecord, PriceRecord> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceRecordProcessor.class);

    @Override
    public PriceRecord process(PriceRecord priceRecord) throws Exception {
        // No Business Logic Here
        LOGGER.info("PriceRecord {} is being processed", priceRecord.getLookupCode());
        return priceRecord;
    }
}
