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

    //Constructor
    public JSFcontroller() {
        this.selectedLookUpCode = null;
        this.mileageInput = null;
    }

    public boolean renderTable() {
        if (this.selectedLookUpCode == null || this.selectedLookUpCode.equals("")) {
            return false;
        }
        return true;
    }

    public boolean renderCalc() {
        if (this.mileageInput == null || this.mileageInput.equals("")) {
            return false;
        }
        return true;
    }

    public ArrayList<String> autocomplete(String prefix) {
        ArrayList<String> miles = new ArrayList<>();

        miles.add("10000");
        miles.add("20000");
        miles.add("50000");
        miles.add("75000");
        miles.add("100000");

        ArrayList<String> result = new ArrayList<String>();
        // recommendations for no input
        if ((prefix == null) || (prefix.length() == 0)) {
            for (int i = 0; i < 5; i++) {
                result.add(miles.get(i));
            }
            return result;
        } else {
            for (String myMiles : miles) {
                if (myMiles.toLowerCase().startsWith(prefix.toLowerCase())) {
                    result.add(myMiles);
                }
            }
            return result;
        }
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
        return "not yet calculated,please reset";
    }

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

}
