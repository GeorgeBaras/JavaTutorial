package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.Vehicle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
@Transactional
public class HibernateIntegrationTest {

    //    @Resource
    //    private SessionFactory sessionFactory;

    //@Resource
    private PriceBandImpl lowBand = new PriceBandImpl(10, new BigDecimal("20000.0"));

    private Vehicle vehicle = new Vehicle("testMake", "testModel", "testDerivative", "testLookupCode", 10);

    @Resource
    private PriceRecordImpl allBands;

    @Test
    public void fakeTest() {
        assertEquals(1, 1);
    }

    //    @Test
    //    public void testPriceBand() {
    //        org.hibernate.Session session = sessionFactory.getCurrentSession();
    //        session.beginTransaction();
    //        session.save(lowBand);
    //        System.out.println(lowBand.getPriceBandID().toString());
    //        assertNotNull("Band ID is null, problem with session.save", lowBand.getPriceBandID());
    //    }
    //
    //    @Test
    //    public void testPriceRecord() {
    //        org.hibernate.Session session = sessionFactory.getCurrentSession();
    //        session.beginTransaction();
    //        session.save(allBands);
    //        assertNotNull("Band ID is null, problem with session.save", allBands.getPriceRecordID());
    //    }
    //
    //    @Test
    //    public void testVehicle() {
    //        org.hibernate.Session session = sessionFactory.getCurrentSession();
    //        session.beginTransaction();
    //        session.save(vehicle);
    //        assertNotNull("Band ID is null, problem with session.save", vehicle.getVehicleID());
    //    }

}
