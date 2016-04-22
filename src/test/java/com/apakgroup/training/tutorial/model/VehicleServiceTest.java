package com.apakgroup.training.tutorial.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apakgroup.training.tutorial.xml.VehicleGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class VehicleServiceTest {

    @Resource
    private SessionFactory sessionFactory;

    @Resource
    private VehicleService vehicleService;

    @Before
    public void setUp() throws Exception {
    }

    @Transactional
    @Ignore //@Rollback(false) // 
    @Test
    public final void populateDBwithRandomEntries() {
        for (int i = 0; i < 1000; i++) {
            Vehicle vehicle = VehicleGenerator.vehicleGenerator();
            vehicleService.addVehicle(vehicle);
        }
        assert (true);
    }

    @Transactional
    @Rollback //// @Ignore // 
    @Test
    public final void testAddVehicle() {
        Vehicle vehicle = VehicleGenerator.vehicleGenerator();
        vehicleService.addVehicle(vehicle);
        // To get the latest addition to the database, the records will be ordered descendingly and the first one will be pulled
        // sessionFactory.getCurrentSession();
        Session session = sessionFactory.getCurrentSession();
        Criteria criterion = session.createCriteria(Vehicle.class);
        criterion.addOrder(Order.desc("id"));
        criterion.setMaxResults(1);
        Vehicle vehicleFromDB = (Vehicle) criterion.uniqueResult();
        assertTrue(vehicle.compare(vehicleFromDB));

    }

    @Transactional
    @Rollback //// @Ignore // 
    @Test
    public final void testGetVehicleByID() {
        Vehicle vehicle = VehicleGenerator.vehicleGenerator();
        long vehicleID = vehicleService.addVehicle(vehicle);
        Vehicle vehicleFromDB = vehicleService.getVehicleByID(vehicleID);
        assertTrue(vehicleFromDB.compare(vehicle));
    }

    @Transactional
    @Rollback //// @Ignore // 
    @Test
    public final void testGetAllVehicles() {
        Vehicle vehicle = VehicleGenerator.vehicleGenerator();
        Vehicle vehicle1 = VehicleGenerator.vehicleGenerator();
        Vehicle vehicle2 = VehicleGenerator.vehicleGenerator();
        vehicleService.addVehicle(vehicle);
        vehicleService.addVehicle(vehicle1);
        vehicleService.addVehicle(vehicle2);

        // get the count of entries in the table
        Criteria criterion = sessionFactory.getCurrentSession().createCriteria(Vehicle.class);
        criterion.setProjection(Projections.rowCount());
        Long countBefore = (Long) criterion.uniqueResult();
        int countBeforeGetAll = new Integer(countBefore.toString());

        assertEquals(countBeforeGetAll, vehicleService.getAllVehicles().size());
    }

    @Transactional
    @Rollback //// @Ignore // 
    @Test
    public final void testUpdateDerivativeByID() {
        Vehicle vehicle = VehicleGenerator.vehicleGenerator();
        long vehicleID = vehicleService.addVehicle(vehicle);

        vehicleService.updateDerivativeByID(vehicleID, "AlteredDerivative");

        String updatedDerivative = vehicleService.getVehicleByID(vehicleID).getDerivative();
        assertEquals(updatedDerivative, "AlteredDerivative");
    }

    @Transactional
    @Rollback //// @Ignore // 
    @Test
    public final void testUpdateMileageByID() {
        Vehicle vehicle = VehicleGenerator.vehicleGenerator();
        long vehicleID = vehicleService.addVehicle(vehicle);

        vehicleService.updateMileageByID(vehicleID, 9876);

        int updatedMileage = vehicleService.getVehicleByID(vehicleID).getMileage();
        assertEquals(updatedMileage, 9876);
    }

    @Transactional
    @Rollback //// @Ignore // 
    @Test
    public final void testUpdateValueByID() {
        Vehicle vehicle = VehicleGenerator.vehicleGenerator();
        long vehicleID = vehicleService.addVehicle(vehicle);

        vehicleService.updateValueByID(vehicleID, new BigDecimal("9876"));

        BigDecimal updatedValue = vehicleService.getVehicleByID(vehicleID).getValue();
        assertEquals(updatedValue, new BigDecimal("9876"));
    }

    @Transactional
    @Rollback //// @Ignore // 
    @Test
    public final void testDeleteVehicleByID() {
        Vehicle vehicle = VehicleGenerator.vehicleGenerator();
        long vehicleID = vehicleService.addVehicle(vehicle);
        int numberOfRecordsBeforeDeletion = vehicleService.getAllVehicles().size();
        //Remove the record
        vehicleService.deleteVehicleByID(vehicleID);
        int numberOfRecordsAfterDeletion = vehicleService.getAllVehicles().size();
        // Check that one record has been deleted from the DB
        assertEquals(numberOfRecordsBeforeDeletion, numberOfRecordsAfterDeletion + 1);
    }

    @Transactional
    @Rollback //// @Ignore // 
    @Test
    public final void testDeleteAllVehicles() {
        Vehicle vehicle = VehicleGenerator.vehicleGenerator();
        long vehicleID = vehicleService.addVehicle(vehicle);

        //Remove all records
        vehicleService.deleteAllVehicles();

        int numberOfRecordsAfterDeletion = vehicleService.getAllVehicles().size();
        // Check that one record has been deleted from the DB
        assertEquals(numberOfRecordsAfterDeletion, 0);
    }

}
