package com.apakgroup.training.tutorial.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Vehicle")
public class Vehicle {

    private Long vehicleID;

    private String make;

    private String model;

    private String derivative;

    private String lookupCode;

    private Integer mileage;

    private BigDecimal value;

    public Vehicle(String make, String model, String derivative, String lookupCode, int mileage) {
        this.make = make;
        this.model = model;
        this.derivative = derivative;
        this.lookupCode = lookupCode;
        this.mileage = mileage;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Long vehicleID) {
        this.vehicleID = vehicleID;
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
}
