package com.apakgroup.training.tutorial.model;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

public class VehicleDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public VehicleDAO() {
    }

    //public Vehicle(long id, String make, String model, String derivative, String lookupCode, int mileage, BDec value)

    public void flushAndClear() {
        Session session = sessionFactory.getCurrentSession();
        session.flush();
        session.clear();
    }

    // create
    @Transactional
    public long addVehicle(Vehicle vehicle) {
        long identifier = (long) sessionFactory.getCurrentSession().save(vehicle);
        return identifier;
    }

    // read

    @Transactional
    public Vehicle getVehicleByID(long id) {
        List vehiclesFromDB = sessionFactory.getCurrentSession().createCriteria(Vehicle.class)
                .add(Restrictions.like("id", id)).list();
        //LOGGER.info("PriceRecord retrieved from the database");
        return (Vehicle) vehiclesFromDB.get(0);
    }

    @Transactional
    public Vehicle getVehicleByLookUpCode(String lookupcode) {
        List vehiclesFromDB = sessionFactory.getCurrentSession().createCriteria(Vehicle.class)
                .add(Restrictions.like("lookupCode", lookupcode)).list();
        //LOGGER.info("PriceRecord retrieved from the database");
        return (Vehicle) vehiclesFromDB.get(0);
    }

    @Transactional
    public List<Vehicle> getAllVehicles() {
        List vehiclesFromDB = sessionFactory.getCurrentSession().createCriteria(Vehicle.class).list();
        return vehiclesFromDB;
    }

    // update
    @Transactional
    public void updateDerivativeByID(long id, String newDerivative) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("update Vehicle set derivative = :Derivative where id = :id");
        query.setParameter("Derivative", newDerivative);
        query.setParameter("id", id);
        query.executeUpdate();
        flushAndClear();
    }

    @Transactional
    public void updateMileageByID(long id, int newMileage) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("update Vehicle set mileage = :Mileage where id = :id");
        query.setParameter("Mileage", newMileage);
        query.setParameter("id", id);
        query.executeUpdate();
        flushAndClear();
    }

    @Transactional
    public void updateValueByID(long id, BigDecimal newValue) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("update Vehicle set value = :Value where id = :id");
        query.setParameter("Value", newValue);
        query.setParameter("id", id);
        query.executeUpdate();
        flushAndClear();
    }

    // delete
    @Transactional
    public void deleteVehicleByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Vehicle vehicleToDelete = (Vehicle) session.load(Vehicle.class, id);
        session.delete(vehicleToDelete);
    }

    @Transactional
    public void deleteAllVehicles() {
        for (Vehicle vehicle : getAllVehicles()) {
            deleteVehicleByID(vehicle.getID());
        }
    }

}
