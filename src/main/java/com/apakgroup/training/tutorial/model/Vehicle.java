package com.apakgroup.training.tutorial.model;

import java.math.BigDecimal;

public class Vehicle {

    private String make;

    private String model;

    private String derivative;

    private String lookupCode;

    private int mileage;

    private BigDecimal value;

    public Vehicle(String make, String model, String derivative, String lookupCode, int mileage) {
        this.make = make;
        this.model = model;
        this.derivative = derivative;
        this.lookupCode = lookupCode;
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
