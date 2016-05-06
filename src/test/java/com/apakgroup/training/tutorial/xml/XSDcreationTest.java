package com.apakgroup.training.tutorial.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.PriceRecordList;
import com.apakgroup.training.tutorial.model.Vehicle;

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

    @Test
    public final void testCreatePriceRecordListXSD() throws JAXBException, IOException {
        File file = new File("priceRecordListSchema");
        XSDcreation.writeXsd(file, PriceRecordList.class);
    }

    @Test
    public final void testCreateVehicleXSD() throws JAXBException, IOException {
        File file = new File("vehicleSchema");
        XSDcreation.writeXsd(file, Vehicle.class);
    }

}
