package com.apakgroup.training.tutorial.batch;

import java.math.BigDecimal;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.apakgroup.training.tutorial.model.PriceBandImpl;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.pricing.PriceRecord;

public class PriceRecordFieldSetMapper implements FieldSetMapper<PriceRecord> {

    @Override
    public PriceRecord mapFieldSet(FieldSet fieldSet) throws BindException {
        //System.out.println("Got in the FieldSetMapper, fieldSet: " + fieldSet.toString());
        PriceRecordImpl priceRecord = new PriceRecordImpl();
        if (fieldSet.getFieldCount() == 0) {
            return priceRecord;
        }
        if (rowIsValid(fieldSet)) {
            priceRecord.setLookupCode(convertToLookUpCode(fieldSet.readString("LookupCode")));
            String MileageA = fieldSet.readString("MileageA");
            String PriceA = fieldSet.readString("PriceA");
            String MileageB = fieldSet.readString("MileageB");
            String PriceB = fieldSet.readString("PriceB");
            String MileageC = fieldSet.readString("MileageC");
            String PriceC = fieldSet.readString("PriceC");
            String MileageD = fieldSet.readString("MileageD");
            String PriceD = fieldSet.readString("PriceD");
            String MileageE = fieldSet.readString("MileageE");
            String PriceE = fieldSet.readString("PriceE");
            if (!priceBandIsBlank(MileageA)) {
                // System.out.println("priceBandAisNotBlank..");
                PriceBandImpl priceBand = new PriceBandImpl(convertToMileage(MileageA), convertToValuation(PriceA));
                priceRecord.addPriceBand(priceBand);
            } else {
                priceRecord.addPriceBand(null);
            }
            if (!priceBandIsBlank(MileageB)) {
                //  System.out.println("priceBandBisNotBlank..");
                PriceBandImpl priceBand = new PriceBandImpl(convertToMileage(MileageB), convertToValuation(PriceB));
                priceRecord.addPriceBand(priceBand);
            } else {
                priceRecord.addPriceBand(null);
            }
            if (!priceBandIsBlank(MileageC)) {
                //  System.out.println("priceBandCisNotBlank..");
                PriceBandImpl priceBand = new PriceBandImpl(convertToMileage(MileageC), convertToValuation(PriceC));
                priceRecord.addPriceBand(priceBand);
            } else {
                priceRecord.addPriceBand(null);
            }
            if (!priceBandIsBlank(MileageD)) {
                //  System.out.println("priceBandDisNotBlank..");
                PriceBandImpl priceBand = new PriceBandImpl(convertToMileage(MileageD), convertToValuation(PriceD));
                priceRecord.addPriceBand(priceBand);
            } else {
                priceRecord.addPriceBand(null);
            }
            if (!priceBandIsBlank(MileageE)) {
                //  System.out.println("priceBandEisNotBlank..");
                PriceBandImpl priceBand = new PriceBandImpl(convertToMileage(MileageE), convertToValuation(PriceE));
                priceRecord.addPriceBand(priceBand);
            } else {
                priceRecord.addPriceBand(null);
            }
        }
        return priceRecord;
    }

    public boolean rowIsValid(FieldSet fieldSet) {
        boolean isValid = true;
        String magicNumber1 = fieldSet.readString("MN03468");
        String magicNumber2 = fieldSet.readString("MNLOL");
        String magicNumber3 = fieldSet.readString("MNBees");
        if (!magicNumber1.equals("03468")) {
            isValid = false;
        }
        if (!magicNumber2.equals("LOL")) {
            isValid = false;
        }
        if (!magicNumber3.equals("BEESBEESBEES")) {
            isValid = false;
        }
        return isValid;
    }

    public String convertToLookUpCode(String field) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < field.length() - 1; i++) {
            if (field.charAt(i) != '☃') {
                sb.append(field.charAt(i));
            }
        }
        return sb.toString();
    }

    public int convertToMileage(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("0.");
        sb.append(field);
        double lm = Double.valueOf(sb.toString());
        Double miles = lm * 11176943.8;
        return (int) Math.round(miles);
    }

    public BigDecimal convertToValuation(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append(field).insert(6, '.');
        return new BigDecimal(sb.toString());
    }

    public boolean priceBandIsBlank(String mileage) {
        boolean isBlank = false;
        if (mileage.contains("☂")) {
            isBlank = true;
        }
        return isBlank;
    }

}
