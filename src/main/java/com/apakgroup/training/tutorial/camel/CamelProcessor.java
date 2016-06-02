package com.apakgroup.training.tutorial.camel;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apakgroup.training.tutorial.model.PriceRecordList;
import com.apakgroup.training.tutorial.webservice.AddPriceRecordListRequest;
import com.apakgroup.training.tutorial.webservice.ModelWireConversion;
import com.apakgroup.training.tutorial.webservice.PriceRecordListWire;

public class CamelProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelProcessor.class);

    //  REQUEST\\listRequest.xml
    public PriceRecordList unmarshalFromFile() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(PriceRecordList.class);
        Unmarshaller u = jc.createUnmarshaller();
        PriceRecordList priceRecordList = (PriceRecordList) u.unmarshal(new File("REQUEST\\listRequest.xml"));
        return priceRecordList;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Got into the CamelProcessor...");
        // create a PriceRecordListWire
        PriceRecordListWire priceRecordListWire = new ModelWireConversion().priceRecordListToWire(unmarshalFromFile());
        AddPriceRecordListRequest request = new AddPriceRecordListRequest();
        request.setPriceRecords(priceRecordListWire);
        System.out.println(request.getPriceRecords().getPriceRecords().size());
        //set body with the request
        exchange.getOut().setBody(request);

    }
}
