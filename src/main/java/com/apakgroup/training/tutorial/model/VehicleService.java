package com.apakgroup.training.tutorial.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
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

    public Vehicle getVehicleByLookUpCode(String lookupcode) {
        return this.getVehicleDAO().getVehicleByLookUpCode(lookupcode);
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

    public List<Long> getAllVehicleIDs() {
        List<Long> vehicleIDs = new ArrayList<>();
        for (Vehicle vehicle : this.getAllVehicles()) {
            vehicleIDs.add(vehicle.getID());
        }
        return vehicleIDs;
    }

}
