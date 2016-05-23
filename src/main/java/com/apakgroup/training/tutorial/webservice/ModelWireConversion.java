package com.apakgroup.training.tutorial.webservice;

import java.util.ArrayList;
import java.util.List;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class ModelWireConversion {

    // Converters to and from wire classes

    public PriceBandImpl wireToPriceBand(PriceBandWire priceBandWire) {
        PriceBandImpl priceBand = new PriceBandImpl();
        priceBand.setMileage(priceBandWire.getMileage());
        priceBand.setValuation(priceBandWire.getValuation());
        return priceBand;
    }

    public PriceBandWire priceBandToWire(PriceBand priceBand) {
        PriceBandWire priceBandWire = new PriceBandWire();
        priceBandWire.setMileage(priceBand.getMileage());
        priceBandWire.setValuation(priceBand.getValuation());
        return priceBandWire;
    }

    public PriceRecordImpl wireToPriceRecord(PriceRecordWire priceRecordWire) {
        if (priceRecordWire == null) {
            return null;
        }
        PriceRecordImpl priceRecord = new PriceRecordImpl();
        priceRecord.setLookupCode(priceRecordWire.getLookUpCode());
        // get the priceBands from the priceBand List of the priceRecordWire
        List<PriceBand> priceBands = new ArrayList<PriceBand>();
        for (PriceBandWire priceBandWire : priceRecordWire.getPriceBands()) {
            priceBands.add(this.wireToPriceBand(priceBandWire));
        }
        // add the priceBands to the priceRecord
        priceRecord.setPriceBands(priceBands);
        return priceRecord;
    }

    public PriceRecordWire priceRecordToWire(PriceRecord priceRecord) {
        if (priceRecord == null) {
            return null;
        }
        PriceRecordWire priceRecordWire = new PriceRecordWire();
        priceRecordWire.setLookUpCode(priceRecord.getLookupCode());
        // get the priceBands from the priceBand List of the priceRecord
        List<PriceBandWire> priceBands = new ArrayList<PriceBandWire>();
        for (PriceBand priceBand : priceRecord.getPriceBands()) {
            priceBands.add(this.priceBandToWire(priceBand));
        }
        // add the priceBands to the priceRecord
        //priceRecordWire.getPriceBands() = 
        priceRecordWire.getPriceBands().addAll(priceBands);
        return priceRecordWire;
    }

}
