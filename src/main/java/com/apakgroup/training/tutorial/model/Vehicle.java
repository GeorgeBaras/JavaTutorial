package com.apakgroup.training.tutorial.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    private long id;

    private String make;

    private String model;

    private String derivative;

    private String lookupCode;

    private Integer mileage;

    private BigDecimal value;

    public Vehicle() {

    }

    public Vehicle(String make, String model, String derivative, String lookupCode, int mileage) {
        this.make = make;
        this.model = model;
        this.derivative = derivative;
        this.lookupCode = lookupCode;
        this.mileage = mileage;
    }

    @Id
    @GeneratedValue
    public long getID() {
        return id;
    }

    public void setID(long vehicleID) {
        this.id = vehicleID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDerivative() {
        return derivative;
    }

    public void setDerivative(String derivative) {
        this.derivative = derivative;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public String getLookupCode() {
        return this.lookupCode;
    }

    public int getMileage() {
        return this.mileage;
    }

    public boolean compare(Vehicle vehicle) {
        boolean isSame = true;
        if (!(this.getLookupCode().equals(vehicle.getLookupCode()))) {
            isSame = false;
        }
        if (!(this.getDerivative().equals(vehicle.getDerivative()))) {
            isSame = false;
        }
        if (!(this.getMake().equals(vehicle.getMake()))) {
            isSame = false;
        }
        if (!(this.getModel().equals(vehicle.getModel()))) {
            isSame = false;
        }
        if (!(this.getMileage() == vehicle.getMileage())) {
            isSame = false;
        }
        if (!(this.getValue().equals(vehicle.getValue()))) {
            isSame = false;
        }
        return isSame;
    }
}
