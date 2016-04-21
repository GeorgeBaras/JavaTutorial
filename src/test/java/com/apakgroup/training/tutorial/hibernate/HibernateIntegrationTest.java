package com.apakgroup.training.tutorial.hibernate;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.Vehicle;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })

public class HibernateIntegrationTest {

    @Resource
    private PriceBand lowBand;

    @Resource
    private PriceRecord allBands;

    @Resource
    private SessionFactory sessionFactory;

    @Transactional
    @Rollback
    @Test
    public void testVehicle() {
        //save the entry in the table
        Vehicle vehicle = new Vehicle("TestMake", "TestModel", "TestDerivative", "TestCode", 22);
        long id = (long) sessionFactory.getCurrentSession().save(vehicle);
        // session.flush();

        Vehicle vehicleFromDB = (Vehicle) sessionFactory.getCurrentSession().get(Vehicle.class, id);

        assertNotNull("Vehicle not added to table", vehicleFromDB);
    }

    @Transactional
    @Rollback
    @Test
    public void testBand() {
        long id = (long) sessionFactory.getCurrentSession().save(lowBand);
        PriceBandImpl priceBandFromDB = (PriceBandImpl) sessionFactory.getCurrentSession().get(PriceBandImpl.class, id);
        assertNotNull("PriceBand not added to table", priceBandFromDB);
    }

    @Transactional
    @Rollback
    @Test
    public void testRecord() {
        long id = (long) sessionFactory.getCurrentSession().save(allBands);
        PriceRecordImpl priceRecordFromDB = (PriceRecordImpl) sessionFactory.getCurrentSession()
                .get(PriceRecordImpl.class, id);
        assertNotNull("PriceRecord not added to table", priceRecordFromDB);
    }

}
