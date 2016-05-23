package com.apakgroup.training.tutorial.webapp.jsf;

import java.math.MathContext;
import java.math.RoundingMode;
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
import com.apakgroup.training.tutorial.xml.VehicleGenerator;

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

    public boolean renderDropDownList() {
        if (this.selectedLookUpCode == null) {
            return true;
        }
        if (this.selectedLookUpCode.length() > 0) {
            return false;
        }
        return true;
    }

    public boolean renderDropDownAsString() {
        if (this.selectedLookUpCode == null || this.selectedLookUpCode.equals("")) {
            return false;
        }
        return true;
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

    /**
     * returns a list of mileage autocomplete recommendations based on the input given.
     * 
     * @param prefix
     *            what the user has typed in the textbox
     * @return list of the autocomplete recommendations
     */
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

    /**
     * returns a list of all vehicles
     * 
     * @return araylist of vehicle objects
     */
    @Transactional
    public ArrayList<Vehicle> getVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.addAll(vehicleService.getAllVehicles());
        return vehicles;
    }

    /**
     * gets and return the vehicle with the lookupcode that the user has selected from the dropdown
     * list
     * 
     * @return Vehicle object
     */
    @Transactional
    public Vehicle getVehicleByLookupCode() {
        if (this.selectedLookUpCode == null || this.selectedLookUpCode.equals("")) {
            return VehicleGenerator.vehicleGenerator(); // random vehicle to avoid error, will not be rendered
        }
        return vehicleService.getVehicleByLookUpCode(selectedLookUpCode);

    }

    /**
     * gets and returns the priceBands of the priceRecord with the lookupcode selected by the user
     * 
     * @return arraylist of priceBands
     */
    @Transactional
    public ArrayList<PriceBand> getPriceBands() {
        ArrayList<PriceBand> priceBands = new ArrayList<PriceBand>();
        // this if statement is only need to avoid an error, the returned value is never rendered
        if (this.selectedLookUpCode.equals(null) || this.selectedLookUpCode.equals("")) {
            priceBands.add(PriceRecordsGenerators.firstpriceBandGenerator());
            return priceBands;
        }

        priceBands.addAll(priceRecordService.getPriceRecordByLookupcode(selectedLookUpCode).getPriceBands());
        return priceBands;
    }

    /**
     * uses the capValuationCalculator to return a price based on the PriceRecord selected and the
     * mileage input
     * 
     * @return string representation of the vehicle's valuation
     */
    @Transactional
    public String calculateValuation() {
        if (!this.mileageInput.equals("")) {
            PriceRecord priceRecord = this.priceRecordService.getPriceRecordByLookupcode(selectedLookUpCode);
            int currentMileage = Integer.valueOf(this.mileageInput);
            return this.capValuationCalculator.calculatePrice(priceRecord, currentMileage)
                    .round(new MathContext(7, RoundingMode.HALF_EVEN)).toString();
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
