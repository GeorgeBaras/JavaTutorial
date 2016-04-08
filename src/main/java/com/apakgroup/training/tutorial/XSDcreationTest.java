package com.apakgroup.training.tutorial;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.xml.XSDcreation;

public class XSDcreationTest {

    @Test
    public final void testCreatePriceRecordXSDfromClass() throws JAXBException, IOException {
        XSDcreation.createXSDfromClass(PriceRecordImpl.class);
    }

    @Test
    public final void testCreatePriceBandXSDfromClass() throws JAXBException, IOException {
        XSDcreation.createXSDfromClass(PriceBandImpl.class);
    }

}
