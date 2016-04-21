package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.apakgroup.training.tutorial.model.Vehicle;
import com.apakgroup.training.tutorial.xml.VehicleGenerator;

public class VehicleTest {

    @Mock
    Vehicle vehicle;

    @Before
    public void create() {
        vehicle = mock(Vehicle.class);
        when(vehicle.getValue()).thenReturn(new BigDecimal("10000.00"));
        when(vehicle.getLookupCode()).thenReturn("LookupCode");
        when(vehicle.getMileage()).thenReturn(10);
    }

    @Test
    public void testSetValue() {
        vehicle.setValue(new BigDecimal("1"));
        verify(vehicle).setValue(new BigDecimal("1"));

    }

    @Test
    public void testGetValue() {
        BigDecimal expectedValue = new BigDecimal("10000.00");
        BigDecimal receivedValue = vehicle.getValue();
        assertEquals("failure - Price not correct", expectedValue, receivedValue);
    }

    @Test
    public void testGetLookupCode() {
        String expectedCode = "LookupCode";
        String receivedCode = vehicle.getLookupCode();
        assertEquals("failure - Price not correct", expectedCode, receivedCode);
    }

    @Test
    public void testGetMileage() {
        int expectedMiles = 10;
        int receivedMiles = vehicle.getMileage();
        assertEquals("failure - Price not correct", expectedMiles, receivedMiles);
    }

    @Test
    public void testCompare() {
        Vehicle vehicle = VehicleGenerator.vehicleGenerator();
        assertTrue(vehicle.compare(vehicle));
    }

}
