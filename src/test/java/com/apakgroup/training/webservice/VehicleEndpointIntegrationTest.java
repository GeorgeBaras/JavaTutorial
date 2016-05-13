package com.apakgroup.training.webservice;

import java.io.StringWriter;
import java.math.BigInteger;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.webservice.ValueVehicleRequest;
import com.apakgroup.training.tutorial.webservice.ValueVehicleResponse;
import com.apakgroup.training.tutorial.webservice.VehicleEndpoint;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class VehicleEndpointIntegrationTest {

    @Resource
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockWebServiceClient;

    @Resource
    private VehicleEndpoint vehicleEndpoint;

    @Resource
    private PriceRecordService priceRecordService;

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

    public StringSource marshal(Object objectToConvert) throws JAXBException {
        StringWriter xml = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(objectToConvert.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(objectToConvert, xml);
        System.out.println(xml.toString());
        return new StringSource(xml.toString());
    }

    @Transactional
    @Test
    public final void testHandlevalueVehicleRequest() throws JAXBException {
        // Create the request
        ValueVehicleRequest request = new ValueVehicleRequest();
        request.setLookupCode(this.priceRecord.getLookupCode()); //already in the database
        Integer mileage = this.priceRecord.getPriceBands().get(0).getMileage();
        request.setMileage(new BigInteger(mileage.toString()));

        // Create the response
        ValueVehicleResponse expectedResponse = new ValueVehicleResponse();
        expectedResponse.setValue(this.priceRecord.getPriceBands().get(0).getValuation());

        this.mockWebServiceClient
                .sendRequest(org.springframework.ws.test.server.RequestCreators.withPayload((marshal(request))))
                .andExpect(org.springframework.ws.test.server.ResponseMatchers.payload(marshal(expectedResponse)));

    }

    @After
    public void destroy() {
        this.priceRecordService.deletePriceRecordByID(PRid);
    }

}
