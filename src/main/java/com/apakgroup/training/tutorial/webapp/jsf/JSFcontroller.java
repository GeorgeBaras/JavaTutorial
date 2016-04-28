package com.apakgroup.training.tutorial.webapp.jsf;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.model.Vehicle;
import com.apakgroup.training.tutorial.model.VehicleService;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

@Component
@Scope("session")
public class JSFcontroller {

    private VehicleService vehicleService;

    private PriceRecordService priceRecordService;

    private String selectedLookUpCode = null;

    //private Double selectedMileage;

    //Constructor
    public JSFcontroller() {
        this.selectedLookUpCode = null;
    }

    @Transactional
    public ArrayList<Vehicle> getVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.addAll(vehicleService.getAllVehicles());
        return vehicles;
    }

    @Transactional
    public ArrayList<PriceBand> getPriceBands() {
        ArrayList<PriceBand> priceBands = new ArrayList<PriceBand>();
        System.out.println("selectedlookUpCode is: " + selectedLookUpCode);
        // this if statement is only need to avoid an error, the returned value is never rendered
        if (this.selectedLookUpCode.equals(null) || this.selectedLookUpCode.equals("")) {
            priceBands.add(PriceRecordsGenerators.firstpriceBandGenerator());
            return priceBands;
        }

        priceBands.addAll(priceRecordService.getPriceRecordByLookupcode(selectedLookUpCode).getPriceBands());
        return priceBands;
    }

    public void reset() {
        this.selectedLookUpCode = null;

    }

    // Getters and Setters
    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public PriceRecordService getPriceRecordService() {
        return priceRecordService;
    }

    public void setPriceRecordService(PriceRecordService priceRecordService) {
        this.priceRecordService = priceRecordService;
    }

    public String getSelectedLookUpCode() {
        return selectedLookUpCode;
    }

    public void setSelectedLookUpCode(String selectedLookUpCode) {
        this.selectedLookUpCode = selectedLookUpCode;
    }

    //    public Integer getSelectedMileage() {
    //        return selectedMileage;
    //    }
    //
    //    public void setSelectedMileage(Integer selectedMileage) {
    //        this.selectedMileage = selectedMileage;
    //    }

}
