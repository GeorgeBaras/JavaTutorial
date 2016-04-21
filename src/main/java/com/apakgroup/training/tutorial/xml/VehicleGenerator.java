package com.apakgroup.training.tutorial.xml;

import com.apakgroup.training.tutorial.model.Vehicle;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class VehicleGenerator {

    private static String make = "make";

    private static int makeNo = 0;

    private static String model = "model";

    private static int modelNo = 0;

    private static String derivative = "derivative";

    private static int derivativeNo = 0;

    public static Vehicle vehicleGenerator() {
        PriceRecord priceRecord = PriceRecordsGenerators.priceRecordGenerator(1);

        Vehicle vehicle = new Vehicle(make + String.valueOf(makeNo), model + String.valueOf(modelNo),
                derivative + String.valueOf(derivativeNo), priceRecord.getLookupCode(),
                priceRecord.getPriceBands().get(0).getMileage());
        vehicle.setValue(priceRecord.getPriceBands().get(0).getValuation());
        makeNo++;
        modelNo++;
        derivativeNo++;
        return vehicle;
    }

}
