package com.apakgroup.training.webservice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.apakgroup.training.tutorial.webservice.ValueVehicleRequest;
import com.apakgroup.training.tutorial.webservice.ValueVehicleResponse;
import com.apakgroup.training.tutorial.webservice.VehicleEndpoint;

public class VehicleEndpointUnitTest {

    private VehicleEndpoint vehicleEndpoint;

    private ValueVehicleRequest valueVehicleRequest;

    private ValueVehicleResponse valueVehicleResponse;

    @Before
    public void create() {
        vehicleEndpoint = Mockito.mock(VehicleEndpoint.class);
        valueVehicleRequest = Mockito.mock(ValueVehicleRequest.class);
        valueVehicleResponse = Mockito.mock(ValueVehicleResponse.class);
        Mockito.when(vehicleEndpoint.handlevalueVehicleRequest(valueVehicleRequest)).thenReturn(valueVehicleResponse);
    }

    @Test
    public final void testHandlevalueVehicleRequest() {
        ValueVehicleResponse expectedResponse = this.valueVehicleResponse;
        ValueVehicleResponse receivedResponse = this.vehicleEndpoint
                .handlevalueVehicleRequest(this.valueVehicleRequest);
        assertEquals("Result object returned not correct", expectedResponse, receivedResponse);
    }

}
