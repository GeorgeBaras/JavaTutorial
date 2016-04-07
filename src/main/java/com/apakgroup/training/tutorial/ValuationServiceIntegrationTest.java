package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.ValuationDAOListImpl;
import com.apakgroup.training.tutorial.model.ValuationDAOMapImpl;
import com.apakgroup.training.tutorial.model.ValuationService;
import com.apakgroup.training.tutorial.model.Vehicle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
public class ValuationServiceIntegrationTest {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");

    //    @Resource
    //    ValuationService valuationServiceWithList;

    // Test that all beans have been created

    @Test
    public void testBandBeanNotNull() {
        PriceBandImpl band = (PriceBandImpl) applicationContext.getBean("lowBand");
        Assert.notNull(band);
    }

    @Test
    public void testRecordBeanNotNull() {
        PriceRecordImpl record = (PriceRecordImpl) applicationContext.getBean("allBands");
        Assert.notNull(record);
    }

    @Test
    public void testValuationDAOListBeanNotNull() {
        ValuationDAOListImpl valuationDAOList = (ValuationDAOListImpl) applicationContext
                .getBean("valuationDAOListImpl");
        Assert.notNull(valuationDAOList);
    }

    @Test
    public void testValuationDAOMapBeanNotNull() {
        ValuationDAOMapImpl valuationDAOMap = (ValuationDAOMapImpl) applicationContext.getBean("valuationDAOMapImpl");
        Assert.notNull(valuationDAOMap);
    }

    @Test
    public void testValuationServiceWithListBeanNotNull() {
        ValuationService valuationServiceWithList = (ValuationService) applicationContext
                .getBean("valuationServiceWithList");
        Assert.notNull(valuationServiceWithList);
    }

    @Test
    public void testValuationServiceWithMapBeanNotNull() {
        ValuationService valuationServiceWithMap = (ValuationService) applicationContext
                .getBean("valuationServiceWithMap");
        Assert.notNull(valuationServiceWithMap);
    }

    // Test Valuation with List and Map implementations
    @Test
    public void testValuationDAOwithList() {
        Vehicle vehicle = new Vehicle("testMake", "testModel", "testDerivative", "allBands", 18);
        ValuationService valuationServiceWithList = (ValuationService) applicationContext
                .getBean("valuationServiceWithList");
        valuationServiceWithList.valueVehicle(vehicle);
        assertEquals(vehicle.getValue(), new BigDecimal("12000.0")); // expected value for the test vehicle is 12000.0

    }

    @Test
    public void testValuationDAOwithMap() {
        Vehicle vehicle = new Vehicle("testMake", "testModel", "testDerivative", "allBands", 18);
        ValuationService valuationServiceWithMap = (ValuationService) applicationContext
                .getBean("valuationServiceWithMap");
        valuationServiceWithMap.valueVehicle(vehicle);
        assertEquals(vehicle.getValue(), new BigDecimal("12000.0")); // expected value for the test vehicle is 12000.0

    }

}
