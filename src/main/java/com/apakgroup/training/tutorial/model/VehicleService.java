package com.apakgroup.training.tutorial.model;

import java.math.BigDecimal;
import java.util.List;

public class VehicleService {

    private VehicleDAO vehicleDAO;

    public VehicleDAO getVehicleDAO() {
        return vehicleDAO;
    }

    public void setVehicleDAO(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    public VehicleService() {
        this.vehicleDAO = new VehicleDAO();
    }

    // create
    public long addVehicle(Vehicle vehicle) {
        return this.getVehicleDAO().addVehicle(vehicle);
    }

    // read
    public Vehicle getVehicleByID(long id) {
        return this.getVehicleDAO().getVehicleByID(id);
    }

    // update
    public void updateDerivativeByID(long id, String newDerivative) {
        this.getVehicleDAO().updateDerivativeByID(id, newDerivative);
    }

    public List<Vehicle> getAllVehicles() {
        return this.getVehicleDAO().getAllVehicles();
    }

    public void updateMileageByID(long id, int newMileage) {
        this.getVehicleDAO().updateMileageByID(id, newMileage);
    }

    public void updateValueByID(long id, BigDecimal newValue) {
        this.getVehicleDAO().updateValueByID(id, newValue);
    }

    // delete

    public void deleteVehicleByID(long id) {
        this.getVehicleDAO().deleteVehicleByID(id);
    }

    public void deleteAllVehicles() {
        this.getVehicleDAO().deleteAllVehicles();
    }

}
