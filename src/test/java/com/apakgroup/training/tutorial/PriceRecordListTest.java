package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceRecordList;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

public class PriceRecordListTest {

    @Test
    public final void testAddRecordToList() {
        PriceRecordList priceRecordList = new PriceRecordList();
        PriceRecord priceRecord = PriceRecordsGenerators.priceRecordGenerator(3);
        priceRecordList.addPriceRecordToList(priceRecord);
        // Assert that the Record inserted is actually there
        assertEquals(priceRecordList.getListOfPriceRecords().get(0), priceRecord);
    }

    @Test
    public final void testCompare() {
        PriceRecordList firstList = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(5, 4));
        assertTrue(firstList.compare(firstList));
    }

}
