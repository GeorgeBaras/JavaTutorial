package com.apakgroup.training.webservice;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.server.MockWebServiceClient;

import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.PriceRecordList;
import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.webservice.AddPriceBandRequest;
import com.apakgroup.training.tutorial.webservice.AddPriceBandResponse;
import com.apakgroup.training.tutorial.webservice.AddPriceRecordListRequest;
import com.apakgroup.training.tutorial.webservice.AddPriceRecordListResponse;
import com.apakgroup.training.tutorial.webservice.AddPriceRecordRequest;
import com.apakgroup.training.tutorial.webservice.AddPriceRecordResponse;
import com.apakgroup.training.tutorial.webservice.DeletePriceBandByIDRequest;
import com.apakgroup.training.tutorial.webservice.DeletePriceBandByIDResponse;
import com.apakgroup.training.tutorial.webservice.DeletePriceBandByLookUpCodeRequest;
import com.apakgroup.training.tutorial.webservice.DeletePriceBandByLookUpCodeResponse;
import com.apakgroup.training.tutorial.webservice.DeletePriceRecordByIDRequest;
import com.apakgroup.training.tutorial.webservice.DeletePriceRecordByIDResponse;
import com.apakgroup.training.tutorial.webservice.DeletePriceRecordByLookUpCodeRequest;
import com.apakgroup.training.tutorial.webservice.DeletePriceRecordByLookUpCodeResponse;
import com.apakgroup.training.tutorial.webservice.GetAllPriceRecordsRequest;
import com.apakgroup.training.tutorial.webservice.GetAllPriceRecordsResponse;
import com.apakgroup.training.tutorial.webservice.GetPriceBandsByIDRequest;
import com.apakgroup.training.tutorial.webservice.GetPriceBandsByIDResponse;
import com.apakgroup.training.tutorial.webservice.GetPriceBandsByLookUpCodeRequest;
import com.apakgroup.training.tutorial.webservice.GetPriceBandsByLookUpCodeResponse;
import com.apakgroup.training.tutorial.webservice.GetPriceRecordByIDRequest;
import com.apakgroup.training.tutorial.webservice.GetPriceRecordByIDResponse;
import com.apakgroup.training.tutorial.webservice.GetPriceRecordByLookupCodeRequest;
import com.apakgroup.training.tutorial.webservice.GetPriceRecordByLookupCodeResponse;
import com.apakgroup.training.tutorial.webservice.PriceBandWire;
import com.apakgroup.training.tutorial.webservice.PriceRecordEndpoint;
import com.apakgroup.training.tutorial.webservice.PriceRecordWire;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class PriceRecordEndpointITwithSecurity {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private PriceRecordService priceRecordService;

    private MockWebServiceClient mockWebServiceClient;

    private PriceRecordImpl testPriceRecord;

    private long testPriceRecordID;

    // add WebServiceTemplate for Security2
    @Resource
    private WebServiceTemplate webServiceTemplate;

    @Resource
    private PriceRecordEndpoint priceRecordEndpoint;

    @Before
    public void create() {
        this.mockWebServiceClient = MockWebServiceClient.createClient(this.applicationContext);
        testPriceRecord = new PriceRecordImpl();
        testPriceRecord.setPriceBands(PriceRecordsGenerators.priceRecordGenerator(5).getPriceBands());
        testPriceRecord.setLookupCode("testPriceRecordLookup");
        testPriceRecordID = this.priceRecordService.addPriceRecord(testPriceRecord);

    }

    @Test
    public final void testHandleaddPriceBandRequest() throws JAXBException {
        //Create the request
        AddPriceBandRequest request = new AddPriceBandRequest();
        PriceBandWire priceBand = new PriceBandWire();
        priceBand.setMileage(12345);
        priceBand.setValuation(new BigDecimal("123456789"));
        request.setLookupCode("testPriceRecordLookup"); //already in the database
        // add the priceBand to the request
        request.setPriceBand(priceBand);
        // create the response
        AddPriceBandResponse expectedResponse = new AddPriceBandResponse();
        expectedResponse.setConfirmation(true);

        this.webServiceTemplate.marshalSendAndReceive(request);

    }

    @Ignore // works but do not know the expected id from Oracle SQL
    @Test
    public final void testHandleaddPriceRecordRequest() throws JAXBException {
        try {
            // create a priceRecord
            PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
            priceRecord.setLookupCode("addedRecord");

            AddPriceRecordRequest request = new AddPriceRecordRequest();
            // pass the PR3 to the request as a PriceRecordWire
            request.setPriceRecord(this.priceRecordEndpoint.priceRecordToWire(priceRecord));
            AddPriceRecordResponse expectedResponse = new AddPriceRecordResponse();
            expectedResponse.setId(490848);

            this.webServiceTemplate.marshalSendAndReceive(request);

        } finally {
            this.priceRecordService.deletePriceRecordByLookupcode("addedRecord");
        }
    }

    @Test
    public final void testHandleaddPriceRecordListRequest() throws JAXBException {
        try {
            // create a priceRecordList
            PriceRecordImpl priceRecord1 = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
            priceRecord1.setLookupCode("addedRecord1");
            PriceRecordImpl priceRecord2 = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
            priceRecord2.setLookupCode("addedRecord2");

            PriceRecordList priceRecordList = new PriceRecordList();
            priceRecordList.addPriceRecordToList(priceRecord1);
            priceRecordList.addPriceRecordToList(priceRecord2);

            AddPriceRecordListRequest request = new AddPriceRecordListRequest();
            // pass the PRList to the request as a PriceRecordListWire
            request.setPriceRecords(this.priceRecordEndpoint.priceRecordListToWire(priceRecordList));

            AddPriceRecordListResponse expectedResponse = new AddPriceRecordListResponse();
            expectedResponse.setConfirmation(true);

            this.webServiceTemplate.marshalSendAndReceive(request);

        } finally {
            this.priceRecordService.deletePriceRecordByLookupcode("addedRecord1");
            this.priceRecordService.deletePriceRecordByLookupcode("addedRecord2");
        }
    }

    @Test
    public final void testHandledeletePriceBandByIDRequest() throws JAXBException {
        DeletePriceBandByIDRequest request = new DeletePriceBandByIDRequest();
        request.setId(testPriceRecordID);
        DeletePriceBandByIDResponse expectedResponse = new DeletePriceBandByIDResponse();
        expectedResponse.setConfirmation(true);

        this.webServiceTemplate.marshalSendAndReceive(request);
    }

    @Test
    public final void testHandledeletePriceBandByLookUpCodeRequest() throws JAXBException {
        DeletePriceBandByLookUpCodeRequest request = new DeletePriceBandByLookUpCodeRequest();
        request.setLookupCode(this.testPriceRecord.getLookupCode());
        DeletePriceBandByLookUpCodeResponse expectedResponse = new DeletePriceBandByLookUpCodeResponse();
        expectedResponse.setConfirmation(true);

        this.webServiceTemplate.marshalSendAndReceive(request);
    }

    @Test
    public final void testHandledeletePriceRecordByIDRequest() throws JAXBException {
        // create a PR and put it in the database
        PriceRecordImpl priceRecord = this.testPriceRecord;
        long id = this.priceRecordService.addPriceRecord(priceRecord);
        System.out.println("this is the :" + id);
        // delete the PR
        DeletePriceRecordByIDRequest request = new DeletePriceRecordByIDRequest();
        request.setId(id);
        DeletePriceRecordByIDResponse expectedResponse = new DeletePriceRecordByIDResponse();
        expectedResponse.setConfirmation(true);

        this.webServiceTemplate.marshalSendAndReceive(request);
    }

    @Test
    public final void testHandledeletePriceRecordByLookUpCodeRequest() throws JAXBException {
        // create a PR and put it in the database
        PriceRecordImpl priceRecord = this.testPriceRecord;
        priceRecord.setLookupCode("newLookUp");
        long id = this.priceRecordService.addPriceRecord(priceRecord);
        System.out.println("this is the :" + id);
        // delete the PR
        DeletePriceRecordByLookUpCodeRequest request = new DeletePriceRecordByLookUpCodeRequest();
        request.setLookupCode("newLookUp");
        DeletePriceRecordByLookUpCodeResponse expectedResponse = new DeletePriceRecordByLookUpCodeResponse();
        expectedResponse.setConfirmation(true);

        this.webServiceTemplate.marshalSendAndReceive(request);
    }

    @Ignore //Sometimes can only get some entries and then throws indexOutOfBoundsException
    @Transactional
    @Test
    public final void testHandlegetAllPriceRecordsRequest() throws JAXBException {
        GetAllPriceRecordsRequest request = new GetAllPriceRecordsRequest();
        GetAllPriceRecordsResponse expectedResponse = new GetAllPriceRecordsResponse();

        for (PriceRecordImpl priceRecord : this.priceRecordService.getAllPriceRecordsEAGER()) {
            // priceRecord.getPriceBands();
            expectedResponse.getPriceRecord().add(this.priceRecordEndpoint.priceRecordToWire(priceRecord));
        }
        this.webServiceTemplate.marshalSendAndReceive(request);
    }

    @Transactional
    @Test
    public final void testHandlegetPriceBandsByIDRequest() throws JAXBException {
        GetPriceBandsByIDRequest request = new GetPriceBandsByIDRequest();
        request.setId(testPriceRecordID);

        GetPriceBandsByIDResponse expectedResponse = new GetPriceBandsByIDResponse();
        for (PriceBand priceBand : this.priceRecordService.getPriceRecordByIDEAGER(testPriceRecordID).getPriceBands()) {
            expectedResponse.getPriceBands().add(this.priceRecordEndpoint.priceBandToWire(priceBand));
        }

        this.webServiceTemplate.marshalSendAndReceive(request);
    }

    //@Transactional
    @Test
    public final void testHandlegetPriceBandsByLookUpCodeRequest() throws JAXBException {
        GetPriceBandsByLookUpCodeRequest request = new GetPriceBandsByLookUpCodeRequest();
        request.setLookupCode(this.testPriceRecord.getLookupCode());

        GetPriceBandsByLookUpCodeResponse expectedResponse = new GetPriceBandsByLookUpCodeResponse();
        for (PriceBand priceBand : this.priceRecordService
                .getPriceRecordByLookupcode(this.testPriceRecord.getLookupCode()).getPriceBands()) {
            expectedResponse.getPriceBands().add(this.priceRecordEndpoint.priceBandToWire(priceBand));
        }

        this.webServiceTemplate.marshalSendAndReceive(request);
    }

    @Transactional
    @Test
    public final void testHandlegetPriceRecordByIDRequest() throws JAXBException {
        GetPriceRecordByIDRequest request = new GetPriceRecordByIDRequest();
        request.setId(this.testPriceRecordID);

        GetPriceRecordByIDResponse expectedResponse = new GetPriceRecordByIDResponse();
        expectedResponse.setPriceRecord(this.priceRecordEndpoint.priceRecordToWire(this.testPriceRecord));

        this.webServiceTemplate.marshalSendAndReceive(request);
    }

    @Transactional
    @Test
    public final void testHandlegetPriceRecordByLookupCodeRequest() throws JAXBException {
        GetPriceRecordByLookupCodeRequest request = new GetPriceRecordByLookupCodeRequest();
        request.setLookupCode(this.testPriceRecord.getLookupCode());

        GetPriceRecordByLookupCodeResponse expectedResponse = new GetPriceRecordByLookupCodeResponse();
        expectedResponse.setPriceRecord(this.priceRecordEndpoint.priceRecordToWire(testPriceRecord));

        this.webServiceTemplate.marshalSendAndReceive(request);
    }

    // helper methods
    public boolean priceBandWireCompare(PriceBandWire priceBandWire1, PriceBandWire priceBandWire2) {
        boolean isSame = true;
        if (!(priceBandWire1.getMileage() == priceBandWire2.getMileage())) {
            isSame = false;
        }
        if (!priceBandWire1.getValuation().equals(priceBandWire2.getValuation())) {
            isSame = false;
        }
        return isSame;
    }

    public boolean priceRecordWireCompare(PriceRecordWire priceRecordWire1, PriceRecordWire priceRecordWire2) {
        boolean isSame = true;
        //only check the pricebands if pricebands lists are of the same size
        if (priceRecordWire1.getPriceBands().size() == priceRecordWire2.getPriceBands().size()) {

            // for every priceBand in the priceRecord
            for (int j = 0; j < priceRecordWire1.getPriceBands().size(); j++) {
                // if the mileage or the valuation of the priceBand do not match for the two lists set the siSame to false
                if (priceRecordWire1.getPriceBands().get(j).getMileage() != priceRecordWire2.getPriceBands().get(j)
                        .getMileage()) {
                    isSame = false;
                }
                if (priceRecordWire1.getPriceBands().get(j).getValuation()
                        .compareTo(priceRecordWire2.getPriceBands().get(j).getValuation()) != 0) {
                    isSame = false;
                }
            }
        } else {
            isSame = false;
        }
        if (!priceRecordWire1.getLookUpCode().equals(priceRecordWire2.getLookUpCode())) {
            isSame = false;
        }
        return isSame;
    }

    @After
    public void destroy() {
        this.priceRecordService.deletePriceRecordByID(testPriceRecordID);

    }

}
