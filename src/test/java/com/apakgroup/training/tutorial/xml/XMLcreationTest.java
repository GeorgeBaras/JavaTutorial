package com.apakgroup.training.tutorial.xml;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.dom4j.DocumentException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.StopWatch;

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
                PriceRecordsGenerators.listOfPriceRecordGenerator(100000, 10));
        XMLcreation.marshalToXMLfileJAXB(millionEntries, "MMDom4jEntriesToUnmarhal");
        File file = new File("MMDom4jEntriesToUnmarhal.xml");

        PriceRecordList unmarshalledListOf10 = XMLcreation.unmarshalFromXMLDom4j(file);
        assertTrue(unmarshalledListOf10.compare(millionEntries));
    }

    @Test
    public final void unmarshalFromXMLStAXTest()
            throws JAXBException, IOException, DocumentException, XMLStreamException {
        PriceRecordList listOf10 = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(10, 5));
        XMLcreation.marshalToXMLfileJAXB(listOf10, "StAXEntriesToUnmarhal");
        File file = new File("StAXEntriesToUnmarhal.xml");

        PriceRecordList unmarshalledListOf10 = XMLcreation.unmarshalFromXMLStAX(file);
        assertTrue(unmarshalledListOf10.compare(listOf10));
    }

    // Problems for Dom4j for >10000 entries and problems for StAX for >10^6 entries
    @Ignore
    @Test
    public final void benchmarkUnMarshallingTest()
            throws JAXBException, IOException, DocumentException, XMLStreamException {
        PriceRecordList benchmarkXML = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(100, 10));
        XMLcreation.marshalToXMLfileJAXB(benchmarkXML, "benchmarkXML");
        File file = new File("benchmarkXML.xml");

        StopWatch stopWatch = new StopWatch();

        // JAXB unmarshaller
        stopWatch.start();
        PriceRecordList unmarshalledFromJAXB = XMLcreation.unmarshalFromXMLStAX(file);
        stopWatch.stop();
        System.out.println("JAXB " + stopWatch);

        // StAX unmarshaller
        stopWatch.start();
        PriceRecordList unmarshalledFromStAX = XMLcreation.unmarshalFromXMLStAX(file);
        stopWatch.stop();
        System.out.println("StAX " + stopWatch);

        // Dom4j unmarshaller
        stopWatch.start();
        PriceRecordList unmarshalledFromDom4j = XMLcreation.unmarshalFromXMLDom4j(file);
        stopWatch.stop();
        System.out.println("DOM4J " + stopWatch);

        assert (true);
    }

}
