package com.apakgroup.training.tutorial.xml;

import static org.junit.Assert.assertTrue;

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
        File f = new File("10EntriesXML.xml");
        assertTrue(f.exists());

    }

    @Test
    public final void unmarshalFromXMLfileTest() throws JAXBException, IOException {
        PriceRecordList listOf10 = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(10, 5));
        XMLcreation.marshalToXMLfile(listOf10, "EntriesToUnmarhalXML");
        PriceRecordList unmarshalledListOf10 = (PriceRecordList) XMLcreation
                .unmarshalFromXMLfile("EntriesToUnmarhalXML.xml", PriceRecordList.class);
        // the unmarshalled object has the same contents, yet cannot be compared directly
        assertTrue(unmarshalledListOf10.compare(listOf10));
    }

    @Ignore
    @Test
    public final void marshalToBIGXMLfileTest() throws JAXBException, IOException {
        PriceRecordList millionEntries = new PriceRecordList(
                PriceRecordsGenerators.listOfPriceRecordGenerator(1000000, 10));
        XMLcreation.marshalToXMLfile(millionEntries, "millionEntriesXML");
        File f = new File("millionEntriesXML.xml");
        assertTrue(f.exists());
    }

    @Ignore
    @Test
    public final void unmarshalFromBIGXMLfileTest() throws JAXBException, IOException {
        PriceRecordList millionEntries = new PriceRecordList(
                PriceRecordsGenerators.listOfPriceRecordGenerator(1000000, 10));
        XMLcreation.marshalToXMLfile(millionEntries, "MillionEntriesToUnmarhalXML");
        PriceRecordList unmarshalledMillionEntries = (PriceRecordList) XMLcreation
                .unmarshalFromXMLfile("MillionEntriesToUnmarhalXML.xml", PriceRecordList.class);
        // the unmarshalled object has the same contents, yet cannot be compared directly
        assertTrue(unmarshalledMillionEntries.compare(millionEntries));
    }

}
