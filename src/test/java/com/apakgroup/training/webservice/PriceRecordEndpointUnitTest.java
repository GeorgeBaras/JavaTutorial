package com.apakgroup.training.webservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.webservice.AddPriceBandRequest;
import com.apakgroup.training.tutorial.webservice.AddPriceBandResponse;
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
import com.apakgroup.training.tutorial.webservice.PriceRecordEndpoint;

public class PriceRecordEndpointUnitTest {

    private PriceRecordEndpoint priceRecordEndpoint;

    private PriceRecordService priceRecordService;

    private AddPriceBandRequest addPriceBandRequest;

    private AddPriceBandResponse addPriceBandResponse;

    private AddPriceRecordRequest addPriceRecordRequest;

    private AddPriceRecordResponse addPriceRecordResponse;

    private DeletePriceBandByIDRequest deletePriceBandByIDRequest;

    private DeletePriceBandByIDResponse deletePriceBandByIDResponse;

    private DeletePriceBandByLookUpCodeRequest deletePriceBandByLookUpCodeRequest;

    private DeletePriceBandByLookUpCodeResponse deletePriceBandByLookUpCodeResponse;

    private DeletePriceRecordByIDRequest deletePriceRecordByIDRequest;

    private DeletePriceRecordByIDResponse deletePriceRecordByIDResponse;

    private DeletePriceRecordByLookUpCodeRequest deletePriceRecordByLookUpCodeRequest;

    private DeletePriceRecordByLookUpCodeResponse deletePriceRecordByLookUpCodeResponse;

    private GetAllPriceRecordsRequest getAllPriceRecordsRequest;

    private GetAllPriceRecordsResponse getAllPriceRecordsResponse;

    private GetPriceBandsByIDRequest getPriceBandsByIDRequest;

    private GetPriceBandsByIDResponse getPriceBandsByIDResponse;

    private GetPriceBandsByLookUpCodeRequest getPriceBandsByLookUpCodeRequest;

    private GetPriceBandsByLookUpCodeResponse getPriceBandsByLookUpCodeResponse;

    private GetPriceRecordByIDRequest getPriceRecordByIDRequest;

    private GetPriceRecordByIDResponse getPriceRecordByIDResponse;

    private GetPriceRecordByLookupCodeRequest getPriceRecordByLookupCodeRequest;

    private GetPriceRecordByLookupCodeResponse getPriceRecordByLookupCodeResponse;

    @Before
    public void create() {
        priceRecordEndpoint = mock(PriceRecordEndpoint.class);
        priceRecordService = mock(PriceRecordService.class);

        addPriceBandRequest = mock(AddPriceBandRequest.class);

        addPriceBandResponse = mock(AddPriceBandResponse.class);

        addPriceRecordRequest = mock(AddPriceRecordRequest.class);

        addPriceRecordResponse = mock(AddPriceRecordResponse.class);

        deletePriceBandByIDRequest = mock(DeletePriceBandByIDRequest.class);

        deletePriceBandByIDResponse = mock(DeletePriceBandByIDResponse.class);

        deletePriceBandByLookUpCodeRequest = mock(DeletePriceBandByLookUpCodeRequest.class);

        deletePriceBandByLookUpCodeResponse = mock(DeletePriceBandByLookUpCodeResponse.class);

        deletePriceRecordByIDRequest = mock(DeletePriceRecordByIDRequest.class);

        deletePriceRecordByIDResponse = mock(DeletePriceRecordByIDResponse.class);

        deletePriceRecordByLookUpCodeRequest = mock(DeletePriceRecordByLookUpCodeRequest.class);

        deletePriceRecordByLookUpCodeResponse = mock(DeletePriceRecordByLookUpCodeResponse.class);

        getAllPriceRecordsRequest = mock(GetAllPriceRecordsRequest.class);

        getAllPriceRecordsResponse = mock(GetAllPriceRecordsResponse.class);

        getPriceBandsByIDRequest = mock(GetPriceBandsByIDRequest.class);

        getPriceBandsByIDResponse = mock(GetPriceBandsByIDResponse.class);

        getPriceBandsByLookUpCodeRequest = mock(GetPriceBandsByLookUpCodeRequest.class);

        getPriceBandsByLookUpCodeResponse = mock(GetPriceBandsByLookUpCodeResponse.class);

        getPriceRecordByIDRequest = mock(GetPriceRecordByIDRequest.class);

        getPriceRecordByIDResponse = mock(GetPriceRecordByIDResponse.class);

        getPriceRecordByLookupCodeRequest = mock(GetPriceRecordByLookupCodeRequest.class);

        getPriceRecordByLookupCodeResponse = mock(GetPriceRecordByLookupCodeResponse.class);

        when(priceRecordEndpoint.handleaddPriceBandRequest(this.addPriceBandRequest))
                .thenReturn(this.addPriceBandResponse);
        when(priceRecordEndpoint.handleaddPriceRecordRequest(this.addPriceRecordRequest))
                .thenReturn(this.addPriceRecordResponse);
        when(priceRecordEndpoint.handledeletePriceBandByIDRequest(this.deletePriceBandByIDRequest))
                .thenReturn(this.deletePriceBandByIDResponse);
        when(priceRecordEndpoint.handledeletePriceBandByLookUpCodeRequest(this.deletePriceBandByLookUpCodeRequest))
                .thenReturn(this.deletePriceBandByLookUpCodeResponse);
        when(priceRecordEndpoint.handledeletePriceRecordByIDRequest(this.deletePriceRecordByIDRequest))
                .thenReturn(this.deletePriceRecordByIDResponse);
        when(priceRecordEndpoint.handledeletePriceRecordByLookUpCodeRequest(this.deletePriceRecordByLookUpCodeRequest))
                .thenReturn(this.deletePriceRecordByLookUpCodeResponse);
        when(priceRecordEndpoint.handlegetAllPriceRecordsRequest(this.getAllPriceRecordsRequest))
                .thenReturn(this.getAllPriceRecordsResponse);
        when(priceRecordEndpoint.handlegetPriceBandsByIDRequest(this.getPriceBandsByIDRequest))
                .thenReturn(this.getPriceBandsByIDResponse);
        when(priceRecordEndpoint.handlegetPriceBandsByLookUpCodeRequest(this.getPriceBandsByLookUpCodeRequest))
                .thenReturn(this.getPriceBandsByLookUpCodeResponse);
        when(priceRecordEndpoint.handlegetPriceRecordByIDRequest(this.getPriceRecordByIDRequest))
                .thenReturn(this.getPriceRecordByIDResponse);
        when(priceRecordEndpoint.handlegetPriceRecordByLookupCodeRequest(this.getPriceRecordByLookupCodeRequest))
                .thenReturn(this.getPriceRecordByLookupCodeResponse);
    }

    @Test
    public final void testHandleaddPriceBandRequest() {
        AddPriceBandResponse expectedResponse = this.addPriceBandResponse;
        AddPriceBandResponse receivedResponse = this.priceRecordEndpoint
                .handleaddPriceBandRequest(this.addPriceBandRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

    @Test
    public final void testHandleaddPriceRecordRequest() {
        AddPriceRecordResponse expectedResponse = this.addPriceRecordResponse;
        AddPriceRecordResponse receivedResponse = this.priceRecordEndpoint
                .handleaddPriceRecordRequest(this.addPriceRecordRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

    @Test
    public final void testHandledeletePriceBandByIDRequest() {
        DeletePriceBandByIDResponse expectedResponse = this.deletePriceBandByIDResponse;
        DeletePriceBandByIDResponse receivedResponse = this.priceRecordEndpoint
                .handledeletePriceBandByIDRequest(this.deletePriceBandByIDRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

    @Test
    public final void testHandledeletePriceBandByLookUpCodeRequest() {
        DeletePriceBandByLookUpCodeResponse expectedResponse = this.deletePriceBandByLookUpCodeResponse;
        DeletePriceBandByLookUpCodeResponse receivedResponse = this.priceRecordEndpoint
                .handledeletePriceBandByLookUpCodeRequest(this.deletePriceBandByLookUpCodeRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

    @Test
    public final void testHandledeletePriceRecordByIDRequest() {
        DeletePriceRecordByIDResponse expectedResponse = this.deletePriceRecordByIDResponse;
        DeletePriceRecordByIDResponse receivedResponse = this.priceRecordEndpoint
                .handledeletePriceRecordByIDRequest(this.deletePriceRecordByIDRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

    @Test
    public final void testHandledeletePriceRecordByLookUpCodeRequest() {
        DeletePriceRecordByLookUpCodeResponse expectedResponse = this.deletePriceRecordByLookUpCodeResponse;
        DeletePriceRecordByLookUpCodeResponse receivedResponse = this.priceRecordEndpoint
                .handledeletePriceRecordByLookUpCodeRequest(this.deletePriceRecordByLookUpCodeRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

    @Test
    public final void testHandlegetAllPriceRecordsRequest() {
        GetAllPriceRecordsResponse expectedResponse = this.getAllPriceRecordsResponse;
        GetAllPriceRecordsResponse receivedResponse = this.priceRecordEndpoint
                .handlegetAllPriceRecordsRequest(this.getAllPriceRecordsRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

    @Test
    public final void testHandlegetPriceBandsByIDRequest() {
        GetPriceBandsByIDResponse expectedResponse = this.getPriceBandsByIDResponse;
        GetPriceBandsByIDResponse receivedResponse = this.priceRecordEndpoint
                .handlegetPriceBandsByIDRequest(this.getPriceBandsByIDRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

    @Test
    public final void testHandlegetPriceBandsByLookUpCodeRequest() {
        GetPriceBandsByLookUpCodeResponse expectedResponse = this.getPriceBandsByLookUpCodeResponse;
        GetPriceBandsByLookUpCodeResponse receivedResponse = this.priceRecordEndpoint
                .handlegetPriceBandsByLookUpCodeRequest(this.getPriceBandsByLookUpCodeRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

    @Test
    public final void testHandlegetPriceRecordByIDRequest() {
        GetPriceRecordByIDResponse expectedResponse = this.getPriceRecordByIDResponse;
        GetPriceRecordByIDResponse receivedResponse = this.priceRecordEndpoint
                .handlegetPriceRecordByIDRequest(this.getPriceRecordByIDRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

    @Test
    public final void testHandlegetPriceRecordByLookupCodeRequest() {
        GetPriceRecordByLookupCodeResponse expectedResponse = this.getPriceRecordByLookupCodeResponse;
        GetPriceRecordByLookupCodeResponse receivedResponse = this.priceRecordEndpoint
                .handlegetPriceRecordByLookupCodeRequest(this.getPriceRecordByLookupCodeRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

}
