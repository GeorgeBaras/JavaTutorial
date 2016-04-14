package com.apakgroup.training.tutorial.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.PriceRecordList;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class XMLcreation {

    /**
     * Takes in an object and a filename and creates an XML representation of that object,named
     * "filename", in the project directory
     * 
     * @param object
     * @param filename
     * @throws JAXBException
     * @throws IOException
     */
    public static void marshalToXMLfileJAXB(Object object, String filename) throws JAXBException, IOException {
        JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbCtx.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new FileOutputStream(filename + ".xml");
        marshaller.marshal(object, os);
        os.close();
    }

    /**
     * Unmarshals the object represented by the XML file passed
     * 
     * @param filenameWithExtension
     * @param object
     * @return the object unmarshalled from the XML file
     * @throws JAXBException
     */
    public static Object unmarshalFromXMLfileJAXB(File file, Class<?> object) throws JAXBException {
        //File file = new File(filenameWithExtension);
        JAXBContext jaxbContext = JAXBContext.newInstance(object);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(file);
    }

    /**
     * Goes through the
     * 
     * @param file
     * @return
     * @throws DocumentException
     */
    public static PriceRecordList unmarshalFromXMLDom4j(File file) throws DocumentException {
        //Create the object that will hold the parsed data
        PriceRecordList priceRecordlist = new PriceRecordList();
        List<PriceBand> priceBandList = new ArrayList<>();

        // Create dom4j document out of the XML file
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        // Using XPath to navigate the tree
        List<Node> priceRecords = document.selectNodes("/PriceRecordsList/priceRecords");
        //List<Node> mileageValues = document.selectNodes("/PriceRecordsList/priceRecords/priceBands/priceBand/mileage");
        // counter to monitor when to clear priceBandList // every (Bands/Records) entries
        int i = 0;
        for (Node recordNode : priceRecords) {
            // Keep the priceRecord's lookupcode
            String currentLookUpCode = recordNode.selectSingleNode("lookUpCode").getText();
            List<Node> priceBands = document.selectNodes("/PriceRecordsList/priceRecords/priceBands/priceBand");
            // Iterate through the pricebands to get mileage and valuation values

            for (Node bandNode : priceBands) {
                // Create a PriceBandImpl object to keep the values
                PriceBandImpl priceBand = new PriceBandImpl();
                priceBand.setMileage(Integer.parseInt(bandNode.selectSingleNode("mileage").getText()));
                priceBand.setValuation(new BigDecimal(bandNode.selectSingleNode("valuation").getText()));
                priceBandList.add(priceBand);
                if (i % (priceBands.size() / priceRecords.size()) == 0) {
                    PriceRecord priceRecord = new PriceRecordImpl(currentLookUpCode, priceBandList);
                    priceRecordlist.addPriceRecordToList(priceRecord);
                    // clear the priceBandList so that it can be used by the next record
                    priceBandList.clear();
                }
            }

        }

        return priceRecordlist;
    }

}
