package com.apakgroup.training.tutorial.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;

public class XSDcreationTest {

    @Test
    public final void testCreatePriceRecordXSD() throws JAXBException, IOException {
        File file = new File("priceRecordSchema");
        XSDcreation.writeXsd(file, PriceRecordImpl.class);
    }

    @Test
    public final void testCreatePriceBandXSD() throws JAXBException, IOException {
        File file = new File("priceBandSchema");
        XSDcreation.writeXsd(file, PriceBandImpl.class);
    }

}
