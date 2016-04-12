package com.apakgroup.training.tutorial;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

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

public class HibernateIntegrationTest {

    @Resource
    private PriceBand lowBand;

    @Resource
    private PriceRecord allBands;

    @Resource
    private SessionFactory sessionFactory;

    @Transactional
    @Test
    public void testVehicle() {
        //save the entry in the table
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Vehicle vehicle = new Vehicle("TestMake", "TestModel", "TestDerivative", "TestCode", 22);
        session.persist(vehicle);
        // session.flush();

        Vehicle vehicleFromDB = (Vehicle) session.get(Vehicle.class, new Long(2));

        //        //Criteria to get the first entry of the table
        //        List<Vehicle> vehicleList;
        //        Criteria queryCriteria = session.createCriteria(Vehicle.class);
        //        queryCriteria.setFirstResult(0);
        //        queryCriteria.setMaxResults(1);
        //        vehicleList = queryCriteria.list();
        //        //get the first entry
        //        Vehicle vehicleFromDB = vehicleList.get(0);

        assertNotNull("Vehicle not added to table", vehicleFromDB);
    }

    @Transactional
    @Test
    public void testBand() {
        //save the entry in the table
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        session.persist(lowBand);
        // session.flush();

        PriceBandImpl priceBandFromDB = (PriceBandImpl) session.get(PriceBandImpl.class, new Long(1));

        //        // Criteria to get the first entry of the table
        //        List<PriceBand> priceBandList;
        //        Criteria queryCriteria = session.createCriteria(PriceBandImpl.class);
        //        queryCriteria.setFirstResult(0);
        //        queryCriteria.setMaxResults(1);
        //        priceBandList = queryCriteria.list();
        //
        //        //get the first entry
        //        PriceBand priceBandFromDB = priceBandList.get(0);

        assertNotNull("PriceBand not added to table", priceBandFromDB);
    }

    @Transactional
    @Test
    public void testRecord() {
        //save the entry in the table
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        session.save(allBands);
        // session.flush();
        assertNotNull("PriceRecord not added to table", session.get(PriceRecordImpl.class, new Long(3)));
    }

}
