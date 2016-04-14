package com.apakgroup.training.tutorial.xml;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;
import org.junit.Ignore;
import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceRecordList;

public class XMLcreationTest {

    private static final Object PriceRecordList = null;

    @Test
    public final void marshalToXMLfileJAXBTest() throws JAXBException, IOException {
        PriceRecordList listOf10 = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(10, 5));
        XMLcreation.marshalToXMLfileJAXB(listOf10, "10EntriesXML");
        File f = new File("10EntriesXML.xml");
        assertTrue(f.exists());

    }

    @Test
    public final void unmarshalFromXMLfileJAXBTest() throws JAXBException, IOException {
        PriceRecordList listOf10 = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(10, 5));
        XMLcreation.marshalToXMLfileJAXB(listOf10, "EntriesToUnmarhal");
        File file = new File("EntriesToUnmarhal.xml");

        PriceRecordList unmarshalledListOf10 = (PriceRecordList) XMLcreation.unmarshalFromXMLfileJAXB(file,
                PriceRecordList.class);

        assertTrue(unmarshalledListOf10.compare(listOf10));
    }

    @Ignore
    @Test
    public final void marshalToBIGXMLfileJAXBTest() throws JAXBException, IOException {
        PriceRecordList millionEntries = new PriceRecordList(
                PriceRecordsGenerators.listOfPriceRecordGenerator(1000000, 10));
        XMLcreation.marshalToXMLfileJAXB(millionEntries, "millionEntriesXML");
        File f = new File("millionEntriesXML.xml");
        assertTrue(f.exists());
    }

    @Ignore
    @Test
    public final void unmarshalFromBIGXMLfileJAXBTest() throws JAXBException, IOException {
        PriceRecordList millionEntries = new PriceRecordList(
                PriceRecordsGenerators.listOfPriceRecordGenerator(1000000, 10));
        XMLcreation.marshalToXMLfileJAXB(millionEntries, "MillionEntriesToUnmarhal");
        File file = new File("MillionEntriesToUnmarhal.xml");

        PriceRecordList unmarshalledMillionEntries = (PriceRecordList) XMLcreation.unmarshalFromXMLfileJAXB(file,
                PriceRecordList.class);

        assertTrue(unmarshalledMillionEntries.compare(millionEntries));
    }

    @Test
    public final void unmarshalFromXMLDom4jTest() throws JAXBException, IOException, DocumentException {
        PriceRecordList listOf10 = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(100, 6));
        XMLcreation.marshalToXMLfileJAXB(listOf10, "Dom4jEntriesToUnmarhal");
        File file = new File("Dom4jEntriesToUnmarhal.xml");

        PriceRecordList unmarshalledListOf10 = XMLcreation.unmarshalFromXMLDom4j(file);
        assertTrue(unmarshalledListOf10.compare(listOf10));
    }

    @Ignore
    @Test
    public final void unmarshalFromBIGXMLDom4jTest() throws JAXBException, IOException, DocumentException {
        PriceRecordList millionEntries = new PriceRecordList(
                PriceRecordsGenerators.listOfPriceRecordGenerator(1000000, 10));
        XMLcreation.marshalToXMLfileJAXB(millionEntries, "Dom4jEntriesToUnmarhal");
        File file = new File("Dom4jEntriesToUnmarhal.xml");

        PriceRecordList unmarshalledListOf10 = XMLcreation.unmarshalFromXMLDom4j(file);
        assertTrue(unmarshalledListOf10.compare(millionEntries));
    }

}
