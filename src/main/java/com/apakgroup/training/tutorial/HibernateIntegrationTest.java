package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apakgroup.training.tutorial.model.PriceBandImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
@Transactional
public class HibernateIntegrationTest {

    @Resource
    private SessionFactory sessionFactory;
    //
    //    @Resource
    //    private PriceRecord allbands;

    //@Resource
    private PriceBandImpl lowBand = new PriceBandImpl(10, new BigDecimal("20000.0"));

    @Test
    public void testPriceBand() {
        sessionFactory.getCurrentSession();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(lowBand);
        assertNotNull("Band ID is null, problem with session.save", lowBand.getPriceBandID());
    }

    //    @Test
    //    public void testPriceRecord() {
    //        fail("Not yet implemented"); // TODO
    //    }
    //
    //    @Test
    //    public void testVehicle() {
    //        fail("Not yet implemented"); // TODO
    //    }

}
