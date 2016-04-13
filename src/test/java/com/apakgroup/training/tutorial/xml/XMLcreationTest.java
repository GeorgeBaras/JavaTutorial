package com.apakgroup.training.tutorial.xml;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Ignore;
import org.junit.Test;

import com.apakgroup.training.tutorial.pricing.PriceRecordList;

public class XMLcreationTest {

    private static final Object PriceRecordList = null;

    @Test
    public final void marshalToXMLfileTest() throws JAXBException, IOException {
        PriceRecordList listOf10 = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(10, 5));
        XMLcreation.marshalToXMLfile(listOf10, "10EntriesXML");
        // Uncomment to test for more entries
        //        PriceRecordList listOf10k = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(10000, 10));
        //        XMLcreation.writeToXMLfile(listOf10k, "10kEntriesXML");

        //        PriceRecordList listOf100k = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(100000, 10));
        //        XMLcreation.writeToXMLfile(listOf100k, "100kEntriesXML");
        File f = new File("10EntriesXML");
        assert (f.exists());
    }

    @Ignore
    @Test
    public final void marshalToBigXMLfileTest() throws JAXBException, IOException {
        PriceRecordList listOf100k = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(100000, 10));
        XMLcreation.marshalToXMLfile(listOf100k, "100kEntriesXML");
        File f = new File("100kEntriesXML");
        assert (f.exists());
    }

    @Test
    public final void unmarshalFromXMLfileTest() throws JAXBException, IOException {
        PriceRecordList listOf10 = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(10, 5));
        XMLcreation.marshalToXMLfile(listOf10, "EntriesToUnmarhalXML");
        PriceRecordList unmarshalledListOf10 = (PriceRecordList) XMLcreation
                .unmarshalFromXMLfile("EntriesToUnmarhalXML.xml", PriceRecordList.class);
        // the unmarshalled object has the same contents, yet cannot be compared directly
        assertEquals(unmarshalledListOf10.getListOfPriceRecords().get(3).getLookupCode(),
                listOf10.getListOfPriceRecords().get(3).getLookupCode());
    }

}
