package com.apakgroup.training.tutorial.webapp.jsf;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.model.Vehicle;
import com.apakgroup.training.tutorial.model.VehicleService;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.pricing.cap.CAPValuationCalculator;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

@Component
@Scope("session")
public class JSFcontroller {

    private VehicleService vehicleService;

    private PriceRecordService priceRecordService;

    private CAPValuationCalculator capValuationCalculator;

    private String selectedLookUpCode;

    private String mileageInput;

    private String showVehicleValue = null;

    //Constructor
    public JSFcontroller() {
        this.selectedLookUpCode = null;
        this.mileageInput = null;
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
        //System.out.println("selectedlookUpCode is: " + selectedLookUpCode);
        // this if statement is only need to avoid an error, the returned value is never rendered
        if (this.selectedLookUpCode.equals(null) || this.selectedLookUpCode.equals("")) {
            priceBands.add(PriceRecordsGenerators.firstpriceBandGenerator());
            return priceBands;
        }

        priceBands.addAll(priceRecordService.getPriceRecordByLookupcode(selectedLookUpCode).getPriceBands());
        return priceBands;
    }

    @Transactional
    public String calculateValuation() {
        if (!this.mileageInput.equals("")) {
            PriceRecord priceRecord = this.priceRecordService.getPriceRecordByLookupcode(selectedLookUpCode);
            int currentMileage = Integer.valueOf(this.mileageInput);
            return this.capValuationCalculator.calculatePrice(priceRecord, currentMileage).toString();
        }
        return "standard return";
    }

    //public PriceRecord

    public void reset() {
        this.selectedLookUpCode = null;
        this.mileageInput = null;

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

    public CAPValuationCalculator getCapValuationCalculator() {
        return capValuationCalculator;
    }

    public void setCapValuationCalculator(CAPValuationCalculator capValuationCalculator) {
        this.capValuationCalculator = capValuationCalculator;
    }

    public String getSelectedLookUpCode() {
        return selectedLookUpCode;
    }

    public void setSelectedLookUpCode(String selectedLookUpCode) {
        this.selectedLookUpCode = selectedLookUpCode;
    }

    public String getMileageInput() {
        return mileageInput;
    }

    public void setMileageInput(String mileageInput) {
        this.mileageInput = mileageInput;
    }

    public String getShowVehicleValue() {
        return showVehicleValue;
    }

    public void setShowVehicleValue(String showVehicleValue) {
        this.showVehicleValue = showVehicleValue;
    }

}
