package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceBandList;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

public class PriceBandListTest {

    @Test
    public final void addPriceBandTest() {
        PriceBandList priceBandList = new PriceBandList();
        PriceBand priceBand = PriceRecordsGenerators.firstpriceBandGenerator();
        priceBandList.addPriceBand(priceBand);
        // Assert that the Record inserted is actually there
        assertEquals(priceBandList.getPriceBands().get(0), priceBand);
    }

}
