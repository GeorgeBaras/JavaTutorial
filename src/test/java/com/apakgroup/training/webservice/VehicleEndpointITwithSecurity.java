package com.apakgroup.training.webservice;

import java.math.BigInteger;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.server.MockWebServiceClient;

import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.webservice.ValueVehicleRequest;
import com.apakgroup.training.tutorial.webservice.ValueVehicleResponse;
import com.apakgroup.training.tutorial.webservice.VehicleEndpoint;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class VehicleEndpointITwithSecurity {

    @Resource
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockWebServiceClient;

    @Resource
    private VehicleEndpoint vehicleEndpoint;

    @Resource
    private PriceRecordService priceRecordService;

    // add WebServiceTemplate for Security2
    @Resource
    private WebServiceTemplate webServiceTemplate;

    private long PRid;

    private PriceRecordImpl priceRecord;

    @Before
    public void create() {
        this.mockWebServiceClient = MockWebServiceClient.createClient(this.applicationContext);
        // create a priceRecord to use for valuation
        priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
        priceRecord.setLookupCode("PRtest");
        PRid = this.priceRecordService.addPriceRecord(priceRecord);
    }

    @Transactional
    @Test
    public final void testHandleSecuredValueVehicleRequest() throws JAXBException {
        // Create the request
        ValueVehicleRequest request = new ValueVehicleRequest();
        request.setLookupCode(this.priceRecord.getLookupCode()); //already in the database
        Integer mileage = this.priceRecord.getPriceBands().get(0).getMileage();
        request.setMileage(new BigInteger(mileage.toString()));

        // Create the response
        ValueVehicleResponse expectedResponse = new ValueVehicleResponse();
        expectedResponse.setValue(this.priceRecord.getPriceBands().get(0).getValuation());

        Object object = this.webServiceTemplate.marshalSendAndReceive(request);
        // request and responses are printed in the console
    }

    @After
    public void destroy() {
        this.priceRecordService.deletePriceRecordByID(PRid);
    }

}
