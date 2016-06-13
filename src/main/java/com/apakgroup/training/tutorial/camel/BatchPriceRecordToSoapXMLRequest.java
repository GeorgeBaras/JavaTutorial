package com.apakgroup.training.tutorial.camel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.xml.transform.StringSource;

import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.PriceRecordList;

public class BatchPriceRecordToSoapXMLRequest implements ItemWriter<PriceRecordImpl> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchPriceRecordToSoapXMLRequest.class);

    public StringSource marshal(Object objectToConvert) throws JAXBException {
        StringWriter xml = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(objectToConvert.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(objectToConvert, xml);
        //System.out.println(xml.toString());
        return new StringSource(xml.toString());
    }

    public void createXML(String directory, String filename, String content) throws IOException {
        // new File("REQUEST").mkdir();
        new File(directory).mkdir();
        File file = new File(directory + "\\" + filename + ".xml");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();

    }

    /**
     * gets a List and creates an XML AddPriceRecordListRequest which is written in a request.xml
     * file
     * 
     * @throws JAXBException
     * @throws IOException
     */
    @Override
    public void write(List<? extends PriceRecordImpl> priceRecordsFromBatch) throws JAXBException, IOException {
        // get the batch priceRecords and put them in a PriceRecordList
        // System.out.println("Got into the ItemWriter..priceRecordsFromBatch.size= " + priceRecordsFromBatch.size());
        PriceRecordList priceRecordList = new PriceRecordList();
        for (PriceRecordImpl priceRecord : priceRecordsFromBatch) {
            priceRecordList.addPriceRecordToList(priceRecord);
        }

        StringSource stringSource;
        stringSource = marshal(priceRecordList);
        this.createXML("REQUEST", "listRequest", stringSource.toString());

    }

}
