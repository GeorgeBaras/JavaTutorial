package com.apakgroup.training.tutorial.webapp.examples;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.model.Vehicle;
import com.apakgroup.training.tutorial.model.VehicleService;
import com.apakgroup.training.tutorial.pricing.PriceBand;

@Component
@Scope("session")
public class UserBean {

    UserBo userBo;

    VehicleService vehicleService;

    PriceRecordService priceRecordService;

    String dropDownListChoice;

    String priceRecordLookupcode;

    public String getPriceRecordLookupcode() {
        return priceRecordLookupcode;
    }

    public void setPriceRecordLookupcode(String priceRecordLookupcode) {
        this.priceRecordLookupcode = priceRecordLookupcode;
    }

    public String getDropDownListChoice() {
        return dropDownListChoice;
    }

    public void setDropDownListChoice(String dropDownListChoice) {
        this.dropDownListChoice = dropDownListChoice;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public UserBo getUserBo() {
        return userBo;
    }

    public void setUserBo(UserBo userBo) {
        this.userBo = userBo;
    }

    public String printMsgFromSpring() {
        return userBo.getMessage();
    }

    public ArrayList<String> getLookupcodes() {
        ArrayList<String> lookupcodes = new ArrayList<String>();

        for (Vehicle vehicle : vehicleService.getAllVehicles()) {
            lookupcodes.add(vehicle.getLookupCode());
        }
        return lookupcodes;
    }

    public ArrayList<Vehicle> getVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.addAll(vehicleService.getAllVehicles());
        // dropDownListChoice = vehicleService.getAllVehicles().get(0).getModel();
        return vehicles;
    }

    public void resetDropDownListChoice() {
        dropDownListChoice = vehicleService.getAllVehicles().get(0).getModel();
    }

    public void resetPriceRecordLookupcode() {
        priceRecordLookupcode = null;
    }

    public PriceRecordService getPriceRecordService() {
        return priceRecordService;
    }

    public void setPriceRecordService(PriceRecordService priceRecordService) {
        this.priceRecordService = priceRecordService;
    }

    public ArrayList<PriceBand> getPriceBands() {
        ArrayList<PriceBand> priceBands = new ArrayList<PriceBand>();
        //priceRecordLookupcode = "lookUpCode0";
        for (PriceBand priceBand : priceRecordService.getPriceRecordByLookupcode("lookUpCode0").getPriceBands()) {
            priceBands.add(priceBand);
        }

        return priceBands;
    }

    public ArrayList<String> gettableColumns() {
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("Mileage");
        columns.add("Valuation");
        return columns;
    }

    public String registerAction() {
        return "result";
    }

}
