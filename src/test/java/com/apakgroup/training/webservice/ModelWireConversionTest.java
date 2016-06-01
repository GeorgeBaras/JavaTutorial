package com.apakgroup.training.webservice;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceRecordList;
import com.apakgroup.training.tutorial.webservice.ModelWireConversion;
import com.apakgroup.training.tutorial.webservice.PriceBandWire;
import com.apakgroup.training.tutorial.webservice.PriceRecordListWire;
import com.apakgroup.training.tutorial.webservice.PriceRecordWire;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

public class ModelWireConversionTest {

    private ModelWireConversion converter;

    @Before
    public void setUp() {
        converter = new ModelWireConversion();
    }

    @Test
    public final void testPriceRecordListToWire() {
        PriceRecordList priceRecordList = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(3, 2));
        PriceRecordListWire priceRecordListWire = converter.priceRecordListToWire(priceRecordList);
        boolean isSame = true;
        for (int i = 0; i < priceRecordList.getListOfPriceRecords().size(); i++) {
            PriceRecordWire priceRecordWire1 = this.converter
                    .priceRecordToWire(priceRecordList.getListOfPriceRecords().get(i));
            PriceRecordWire priceRecordWire2 = priceRecordListWire.getPriceRecords().get(i);
            if (!this.priceRecordWireCompare(priceRecordWire1, priceRecordWire2)) {
                isSame = false;
            }
        }
        assertTrue(isSame);
    }

    // helper methods
    public boolean priceBandWireCompare(PriceBandWire priceBandWire1, PriceBandWire priceBandWire2) {
        boolean isSame = true;
        if (!(priceBandWire1.getMileage() == priceBandWire2.getMileage())) {
            isSame = false;
        }
        if (!priceBandWire1.getValuation().equals(priceBandWire2.getValuation())) {
            isSame = false;
        }
        return isSame;
    }

    public boolean priceRecordWireCompare(PriceRecordWire priceRecordWire1, PriceRecordWire priceRecordWire2) {
        boolean isSame = true;
        //only check the pricebands if pricebands lists are of the same size
        if (priceRecordWire1.getPriceBands().size() == priceRecordWire2.getPriceBands().size()) {

            // for every priceBand in the priceRecord
            for (int j = 0; j < priceRecordWire1.getPriceBands().size(); j++) {
                // if the mileage or the valuation of the priceBand do not match for the two lists set the siSame to false
                if (priceRecordWire1.getPriceBands().get(j).getMileage() != priceRecordWire2.getPriceBands().get(j)
                        .getMileage()) {
                    isSame = false;
                }
                if (priceRecordWire1.getPriceBands().get(j).getValuation()
                        .compareTo(priceRecordWire2.getPriceBands().get(j).getValuation()) != 0) {
                    isSame = false;
                }
            }
        } else {
            isSame = false;
        }
        if (!priceRecordWire1.getLookUpCode().equals(priceRecordWire2.getLookUpCode())) {
            isSame = false;
        }
        return isSame;
    }

}
