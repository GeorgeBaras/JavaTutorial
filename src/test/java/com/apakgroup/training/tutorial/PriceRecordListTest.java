package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.apakgroup.training.tutorial.pricing.PriceRecordList;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

public class PriceRecordListTest {

    @Test
    public final void testCompare() {
        PriceRecordList firstList = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(5, 4));
        assertTrue(firstList.compare(firstList));
    }

}
