package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@Transactional
public class HibernateIntegrationTest {

    @Resource
    private PriceBand lowBand;

    @Resource
    private PriceRecord allBands;

    @Resource
    private SessionFactory sessionFactory;

    @Test
    public void testBand() {
        //save the entry in the table
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        session.persist(lowBand);

        // session.flush();
        // Criteria to get the first entry of the table
        List<PriceBand> priceBandList;
        Criteria queryCriteria = session.createCriteria(PriceBandImpl.class);
        queryCriteria.setFirstResult(0);
        queryCriteria.setMaxResults(1);
        priceBandList = queryCriteria.list();

        //get the first entry
        PriceBand priceBandFromDB = priceBandList.get(0);

        assertNotNull("Vehicle not added to table", priceBandFromDB);
    }

    @Test
    public void testRecord() {
        //save the entry in the table
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        session.persist(allBands);

        // session.flush();
        // Criteria to get the first entry of the table
        List<PriceRecord> priceRecordList;
        Criteria queryCriteria = session.createCriteria(PriceRecordImpl.class);
        queryCriteria.setFirstResult(0);
        queryCriteria.setMaxResults(1);
        priceRecordList = queryCriteria.list();

        //get the first entry
        PriceRecord priceRecordFromDB = priceRecordList.get(0);

        assertNotNull("Vehicle not added to table", priceRecordFromDB);
    }

    @Test
    public void testVehicle() {
        //save the entry in the table
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Vehicle vehicle = new Vehicle("TestMake", "TestModel", "TestDerivative", "TestCode", 22);
        session.persist(vehicle);

        // session.flush();
        //Criteria to get the first entry of the table
        List<Vehicle> vehicleList;
        Criteria queryCriteria = session.createCriteria(Vehicle.class);
        queryCriteria.setFirstResult(0);
        queryCriteria.setMaxResults(1);
        vehicleList = queryCriteria.list();

        //get the first entry
        Vehicle vehicleFromDB = vehicleList.get(0);

        assertNotNull("Vehicle not added to table", vehicleFromDB);
    }

}
