package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.apakgroup.training.tutorial.model.ValuationService;
import com.apakgroup.training.tutorial.pricing.ValuationCalculator;
import com.apakgroup.training.tutorial.pricing.ValuationDAO;

public class ValuationServiceTest {

    @Mock
    ValuationService valuationService;

    ValuationCalculator valuationCalculator;

    ValuationDAO valuationDAO;

    @Before
    public void create() {
        valuationService = mock(ValuationService.class);
        valuationCalculator = mock(ValuationCalculator.class);
        valuationDAO = mock(ValuationDAO.class);

        when(valuationService.getCalculator()).thenReturn(this.valuationCalculator);
        when(valuationService.getDAO()).thenReturn(this.valuationDAO);
    }

    @Test
    public void testValueVehicle() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testSetDAO() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testSetCalculator() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testGetDAO() {
        ValuationDAO expectedDAO = this.valuationDAO;
        ValuationDAO receivedDAO = valuationService.getDAO();
        assertEquals("DAO object returned not correct", expectedDAO, receivedDAO);
    }

    @Test
    public void testGetCalculator() {
        ValuationCalculator expectedCalculator = this.valuationCalculator;
        ValuationCalculator receivedCalculator = valuationService.getCalculator();
        assertEquals("Calculator object returned not correct", expectedCalculator, receivedCalculator);
    }

}
